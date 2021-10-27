package datastructures

import java.util.Comparator

interface Queue<T> {
    fun enqueue(element: T): Boolean
    fun dequeue(): T?
    val count: Int
    val isEmpty: Boolean
        get() = count == 0

    fun peek(): T?
}

abstract class AbstractPriorityQueue<T> : Queue<T> {
    abstract val heap: Heap<T>

    override fun enqueue(element: T): Boolean {
        heap.add(element)
        return true
    }

    override fun dequeue(): T? = heap.remove()

    override val count: Int
        get() = heap.size

    override fun peek(): T? = heap.peek()
}

class ComparatorPriorityQueue<T>(
    private val comparator: Comparator<T>
) : AbstractPriorityQueue<T>() {

    override val heap: Heap<T> = ComparableHeapImpl(comparator)
}

fun main() {
    val queue = ComparatorPriorityQueue(
        Comparator<String> { o1, o2 -> o1.length - o2.length }
    )

    listOf("one", "three", "forty", "seventeen", "nine").forEach {
        queue.enqueue(it)
    }

    while (!queue.isEmpty) {
        println(queue.dequeue())
    }
}