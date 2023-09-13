package graphs

import graphs.Graph.EdgeType.UNDIRECTED
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.MutableSet
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.count
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.forEach
import kotlin.collections.isNotEmpty
import kotlin.collections.joinToString
import kotlin.collections.listOf
import kotlin.collections.map
import kotlin.collections.mapIndexedNotNull
import kotlin.collections.mutableListOf
import kotlin.collections.mutableSetOf
import kotlin.collections.reversed
import kotlin.collections.set
import kotlin.collections.toList


interface Graph<T> {
    // Create vertex and add to graph.
    fun createVertex(data: T): Vertex<T>

    // Add directed Edge between two vertices.
    fun addDirectedEdge(
        source: Vertex<T>,
        destination: Vertex<T>,
        weight: Double? = null
    )

    // Add undirected (bi-directional) edge between two vertices
    // Same as adding two directed edges.
    fun addUndirectedEdge(
        source: Vertex<T>,
        destination: Vertex<T>,
        weight: Double? = null
    ) {
        addDirectedEdge(source, destination, weight)
        addDirectedEdge(destination, source, weight)
    }

    // Open-ended way of adding EdgeType between vertices.
    fun add(
        edge: EdgeType = UNDIRECTED,
        source: Vertex<T>,
        destination: Vertex<T>,
        weight: Double? = null
    ) {
        when (edge) {
            EdgeType.DIRECTED -> addDirectedEdge(source, destination, weight)
            UNDIRECTED -> addUndirectedEdge(source, destination, weight)
        }
    }

    // Returns a list of Edges for specific vertex.
    fun edges(vertex: Vertex<T>): List<Edge<T>>

    // Returns weight between two vertices.
    fun weight(source: Vertex<T>, destination: Vertex<T>): Double?

    enum class EdgeType { DIRECTED, UNDIRECTED }

    val size: Int

    // Required for tasks such as topological sort and detect cycle.
    val allVertices: List<Vertex<T>>
}

// Holds both a unique index with its graph, and a piece of data.
data class Vertex<T>(val index: Int, val data: T)

// Contains two vertices and an optional weight.
data class Edge<T>(
    val source: Vertex<T>,
    val destination: Vertex<T>,
    val weight: Double? = null
)

class AdjacencyList<T> : Graph<T> {
    private val adjacencies: HashMap<Vertex<T>, MutableList<Edge<T>>> = HashMap()

    // Create and return a new Vertex. Store empty MutableList of edges for new vertex.
    override fun createVertex(data: T): Vertex<T> {
        val vertex = Vertex(adjacencies.count(), data)
        adjacencies[vertex] = mutableListOf()
        return vertex
    }

    // Create edge and add to adjacancies list.
    override fun addDirectedEdge(
        source: Vertex<T>,
        destination: Vertex<T>,
        weight: Double?
    ) {
        adjacencies[source]?.add(
            Edge(source, destination, weight)
        )
    }

    // Return edges, or empty list of source vertex is unknown.
    override fun edges(vertex: Vertex<T>): List<Edge<T>> =
        adjacencies[vertex] ?: emptyList()

    // Return weight between two vertices (if known).
    override fun weight(source: Vertex<T>, destination: Vertex<T>): Double? =
        edges(source).firstOrNull {
            it.destination == destination
        }?.weight

    override fun toString(): String =
        buildString {
            adjacencies.forEach { (v, e) ->
                val edgeString = e.joinToString {
                    it.destination.data.toString()
                }
                append("${v.data} ---> [ $edgeString ]\n")
            }
        }

    override val size: Int
        get() = adjacencies.size

    override val allVertices: List<Vertex<T>>
        get() = adjacencies.keys.toList()
}

class AdjacencyMatrix<T> : Graph<T> {

    private val vertices = mutableListOf<Vertex<T>>()
    private val weights = mutableListOf<MutableList<Double?>>()

    override fun createVertex(data: T): Vertex<T> {
        val vertex = Vertex(vertices.count(), data)
        vertices.add(vertex)

        // Next step is just adding nulls to columns and rows (where list version
        // just adds empty list to signify vertex with no connections yet.
        // New column of nulls.
        weights.forEach { it.add(null) }
        // New row of nulls.
        weights.add(MutableList<Double?>(vertices.count()) { null })
        return vertex
    }

    override fun addDirectedEdge(
        source: Vertex<T>,
        destination: Vertex<T>,
        weight: Double?
    ) {
        weights[source.index][destination.index] = weight
    }

    override fun edges(vertex: Vertex<T>): List<Edge<T>> =
        weights.mapIndexedNotNull { index, _ ->
            weights[vertex.index][index]?.let { weight ->
                Edge(vertex, vertices[index], weight)
            }
        }

    override fun weight(source: Vertex<T>, destination: Vertex<T>): Double? =
        weights[source.index][destination.index]

    // Visualize an adjacency matrix
    override fun toString(): String {
        // 1
        val verticesDescription = vertices
            .joinToString(separator = "\n") { "${it.index}: ${it.data}" }
        // 2
        val grid = weights.map { row ->
            buildString {
                (0 until weights.size).forEach { columnIndex ->
                    val value = row[columnIndex]
                    if (value != null) {
                        append("$value\t")
                    } else {
                        append("ø\t\t")
                    }
                }
            }
        }
        val edgesDescription = grid.joinToString("\n")
        // 3
        return "$verticesDescription\n\n$edgesDescription"
    }

    override val size: Int
        get() = vertices.size

    override val allVertices: List<Vertex<T>>
        get() = vertices
}


fun main() {
    /*val graph = AdjacencyList<String>()*/
    val graph = AdjacencyMatrix<String>()

    val singapore = graph.createVertex("Singapore")
    val tokyo = graph.createVertex("Tokyo")
    val hongKong = graph.createVertex("Hong Kong")
    val detroit = graph.createVertex("Detroit")
    val sanFrancisco = graph.createVertex("San Francisco")
    val washingtonDC = graph.createVertex("Washington DC")
    val austinTexas = graph.createVertex("Austin Texas")
    val seattle = graph.createVertex("Seattle")
    graph.add(UNDIRECTED, singapore, hongKong, 300.0)
    graph.add(UNDIRECTED, singapore, tokyo, 500.0)
    graph.add(UNDIRECTED, hongKong, tokyo, 250.0)
    graph.add(UNDIRECTED, tokyo, detroit, 450.0)
    graph.add(UNDIRECTED, tokyo, washingtonDC, 300.0)
    graph.add(UNDIRECTED, hongKong, sanFrancisco, 600.0)
    graph.add(UNDIRECTED, detroit, austinTexas, 50.0)
    graph.add(UNDIRECTED, austinTexas, washingtonDC, 292.0)
    graph.add(UNDIRECTED, sanFrancisco, washingtonDC, 337.0)
    graph.add(UNDIRECTED, washingtonDC, seattle, 277.0)
    graph.add(UNDIRECTED, sanFrancisco, seattle, 218.0)
    graph.add(UNDIRECTED, austinTexas, sanFrancisco, 297.0)

    /*println(graph)
    graph.edges(austinTexas).forEach {
        println("from: ${it.source.data} to: ${it.destination.data} cost: ${it.weight}")
    }*/

    //distanceBetweenVertices()

    /*graphTraversals()*/

    topologicalSort()
}

// Challenge 1: Find the distance between 2 vertices
// Write a method to count the number of paths between two vertices in a directed
// graph. The example graph below has 5 paths from A to E:

// One solution is to perform a depth-first traversal and keep track of the
// visited vertices.
fun distanceBetweenVertices() {
    val graph = AdjacencyList<Char>()
    val a = graph.createVertex('A')
    val b = graph.createVertex('B')
    val c = graph.createVertex('C')
    val d = graph.createVertex('D')
    val e = graph.createVertex('E')
    with(graph) {
        addDirectedEdge(a, b)
        addDirectedEdge(a, c)
        addDirectedEdge(a, d)
        addDirectedEdge(a, e)
        addDirectedEdge(b, c)
        addDirectedEdge(b, d)
        addDirectedEdge(c, e)
        addDirectedEdge(d, e)
    }

    // Count reference
    data class Ref<T>(var value: T) {}

    fun paths(
        source: Vertex<Char>,
        destination: Vertex<Char>,
        visited: MutableSet<Vertex<Char>>,
        count: Ref<Int>
    ) {
        // Initiate the algorithm by marking the source vertex as visited.
        visited.add(source)

        // If true, we have found a path to our destination, increment by 1.
        if (source == destination) {
            count.value++
        } else {
            // Get all edges adjacent to source vertex.
            val neighbours = graph.edges(source)
            neighbours.forEach {
                // If edge not visited, recursively travel the neighbouring vertices to find path to destination.
                if (it.destination !in visited) {
                    paths(it.destination, destination, visited, count)
                }
            }
        }
        // Remove source from visited list so we can continue to find other paths on that node.
        visited.remove(source)
    }

    fun numOfPaths(
        source: Vertex<Char>,
        destination: Vertex<Char>
    ): Int {
        // Auxiliary count.
        val numberOfPaths = Ref(0)
        // Auxiliary visited history.
        val visited = mutableSetOf<Vertex<Char>>()
        paths(source, destination, visited, numberOfPaths)
        return numberOfPaths.value
    }

    println("num of paths: A - E: ${numOfPaths(a, e)}")
}

fun <T> breadthFirstSearch(graph: Graph<T>, source: Vertex<T>): List<Vertex<T>> {
    // Note we seed queues with initial vertex!
    val queue = ArrayDeque(listOf(source))
    val enqueued = BooleanArray(graph.size)
    val visited = mutableListOf<Vertex<T>>()

    while (queue.isNotEmpty()) {
        val vertex = queue.removeFirst()
        visited.add(vertex)
        enqueued[vertex.index] = true
        graph.edges(vertex).forEach {
            if (!enqueued[it.destination.index]) {
                enqueued[it.destination.index] = true
                queue.add(it.destination)
            }
        }
    }
    return visited
}

fun <T> breadFirstSearchRec(graph: Graph<T>, source: Vertex<T>): List<Vertex<T>> {
    val queue = ArrayDeque(listOf(source))
    val enqueued = BooleanArray(graph.size)
    val visited = mutableListOf<Vertex<T>>()

    fun bfs(
        queue: ArrayDeque<Vertex<T>>,
        enqueued: BooleanArray,
        visited: MutableList<Vertex<T>>
    ) {
        val vertex = queue.removeFirstOrNull() ?: return
        visited.add(vertex)
        enqueued[vertex.index] = true

        graph.edges(vertex).forEach {
            if (!enqueued[it.destination.index]) {
                queue.add(it.destination)
                enqueued[it.destination.index] = true
            }
        }
        bfs(queue, enqueued, visited)
    }

    bfs(queue, enqueued, visited)
    return visited
}

fun <T> depthFirstSearch(graph: Graph<T>, source: Vertex<T>): MutableList<Vertex<T>> {
    val visited = mutableListOf<Vertex<T>>()
    val pushed = BooleanArray(graph.size)
    val stack = ArrayDeque(listOf(source))

    while (stack.isNotEmpty()) {
        val vertex = stack.removeLast()
        if (!pushed[vertex.index]) {
            visited.add(vertex)
            pushed[vertex.index] = true

            val neighbours = graph.edges(vertex).reversed()
            for (v in neighbours.map { it.destination }) {
                if (!pushed[v.index]) {
                    stack.add(v)
                }
            }
        }
    }
    return visited
}

fun <T> depthFirstSearchRec(graph: Graph<T>, source: Vertex<T>): MutableList<Vertex<T>> {
    val pushed = BooleanArray(graph.size)
    val visited = mutableListOf<Vertex<T>>()

    fun <T> dfs(
        graph: Graph<T>,
        source: Vertex<T>,
        visited: MutableList<Vertex<T>>,
        pushed: BooleanArray
    ) {
        visited.add(source)
        pushed[source.index] = true

        graph.edges(source).forEach {
            if (!pushed[it.destination.index]) {
                dfs(graph, it.destination, visited, pushed)
            }
        }
    }

    dfs(graph, source, visited, pushed)
    return visited
}


fun graphTraversals() {
    val graph = AdjacencyList<Char>()
    val a = graph.createVertex('A')
    val b = graph.createVertex('B')
    val c = graph.createVertex('C')
    val d = graph.createVertex('D')
    val e = graph.createVertex('E')
    val f = graph.createVertex('F')
    val g = graph.createVertex('G')
    val h = graph.createVertex('H')
    with(graph) {
        add(UNDIRECTED, a, b)
        add(UNDIRECTED, a, c)
        add(UNDIRECTED, a, d)
        add(UNDIRECTED, b, e)
        add(UNDIRECTED, c, f)
        add(UNDIRECTED, c, g)
        add(UNDIRECTED, e, h)
        add(UNDIRECTED, e, f)
        add(UNDIRECTED, f, g)
    }

    //println("bfs iterative: ${breadthFirstSearch(graph, a).map { it.data }}")
    println("bfs rec: ${breadFirstSearchRec(graph, a).map { it.data }}")

    //println("dfs it: ${depthFirstSearch(graph, a).map { it.data }}")
    println("dfs it: ${depthFirstSearchRec(graph, a).map { it.data }}")
}

fun topologicalSort() {
    val graph = AdjacencyList<Int>()
    val zero = graph.createVertex(0)
    val one = graph.createVertex(1)
    val two = graph.createVertex(2)
    val three = graph.createVertex(3)
    val four = graph.createVertex(4)
    val five = graph.createVertex(5)
    with(graph) {
        addDirectedEdge(five, two)
        addDirectedEdge(five, zero)
        addDirectedEdge(four, zero)
        addDirectedEdge(four, one)
        addDirectedEdge(two, three)
        addDirectedEdge(three, one)
    }

    /*println("top pre graph:\n$graph")
    println(topologicalSort(graph))*/

    val solution = Solution()
    val canFinish = solution.canFinish(2, arrayOf(intArrayOf(1, 0)/*, intArrayOf(1, 0)*/))
    println("canFinish: $canFinish")
    /*solution.canFinish(5, arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 4),
        intArrayOf(3, 4),
    ))*/

    val edgeArrayGraph = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 3),
    )
}

// BFS solution.
fun <T> isDisconnected(graph: Graph<T>): Boolean {
    // If there are no vertices, treat the graph as connected.
    val first = graph.allVertices.firstOrNull() ?: return false

    // Graph is considered disconnected if a vertex is missing in the visited list.
    val visited = breadthFirstSearch(graph, first)
    return graph.allVertices.any {
        !visited.contains(it)
    }
}

fun <T> detectCycle(graph: Graph<T>): Boolean {
    val pushed = BooleanArray(graph.size)

    fun hasCycle(source: Vertex<T>, pushed: BooleanArray): Boolean {
        val neighbours = graph.edges(source).map { it.destination }
        neighbours.forEach {
            if (!pushed[it.index] && hasCycle(it, pushed)) {
                return true
            } else if (pushed[it.index]) {
                return true
            }
        }
        pushed[source.index] = false
        return false
    }
    return hasCycle(graph.allVertices.first(), pushed)
}

// DFS solution (modified).
fun <T> topologicalSort(graph: Graph<T>): List<Vertex<T>> {
    val pushed = BooleanArray(graph.size)
    val stack = ArrayDeque<Vertex<T>>()

    fun topSort(vertex: Vertex<T>, pushed: BooleanArray) {
        pushed[vertex.index] = true
        for (d in graph.edges(vertex).map { it.destination }) {
            if (!pushed[d.index]) {
                topSort(d, pushed)
            }
        }
        stack.add(vertex)
    }

    graph.allVertices.forEach {
        if (!pushed[it.index]) {
            topSort(it, pushed)
        }
    }
    return stack.toList().asReversed()
}

class Solution {

    fun hasCycle(
        graph: Map<Int, List<Int>>,
        index: Int,
        pushed: BooleanArray,
        visited: ArrayDeque<Int>
    ): Boolean {
        if (visited.contains(index)) return false
        // If the adjacent vertex has been visited before, you’ve found a cycle.
        if (pushed[index]) return true

        pushed[index] = true
        for (courseIndex in graph[index]!!) {
            if (hasCycle(graph, courseIndex, pushed, visited)) return true
        }

        // Remove the source vertex so that you can continue to find other paths with a potential cycle.
        pushed[index] = false
        visited.add(index)
        return false // No cycle has been found.
    }

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = mutableMapOf<Int, MutableList<Int>>().apply {
            (0 until numCourses).forEach {
                put(it, mutableListOf())
            }
        }

        val explored = BooleanArray(numCourses)
        val visited = ArrayDeque<Int>()

        for ((crs, pre) in prerequisites) {
            graph[pre]?.add(crs)
        }

        for (index in graph.keys) {
            if (visited.contains(index)) continue
            if (hasCycle(graph, index, explored, visited)) return false
        }
        return true
    }
}