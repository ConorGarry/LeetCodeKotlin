package graphs

/**
 * 200. Number of Islands
 * Medium
 *
 * Given an m x n 2D binary grid grid which represents a
 * map of '1's (land) and '0's (water),return the number of islands.
 *
 * An island is surrounded by water and is formed by
 * connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 *  Input: grid = [
 *      ["1","1","1","1","0"],
 *      ["1","1","0","1","0"],
 *      ["1","1","0","0","0"],
 *      ["0","0","0","0","0"]
 *  ]
 *  Output: 1
 *
 *  Input: grid = [
 *      ["1","1","0","0","0"],
 *      ["1","1","0","0","0"],
 *      ["0","0","1","0","0"],
 *      ["0","0","0","1","1"]
 *  ]
 *  Output: 3
 *
 *  This could also be categorised under 'Arrays',
 *  t's similar to Word Search which uses dfs, this uses bfs.
 */
fun main() {
    val grid1 = arrayOf(
        charArrayOf('1', '1', '1', '1', '0'),
        charArrayOf('1', '1', '0', '1', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '0', '0', '0')
    )

    val grid2 = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1')
    )

    println("numIslands grid1: ${numIslands(grid1)}")
    println("numIslands grid2: ${numIslands(grid2)}")
}


fun numIslands(grid: Array<CharArray>): Int {
    if (grid.isEmpty()) return 0

    val rows = grid.size
    val cols = grid[0].size
    val visit = mutableSetOf<Pair<Int, Int>>()
    var islands = 0

    fun inBounds(x: Int, y: Int) =
        x in 0 until cols && y in 0 until rows

    // Iterative BFS.
    fun bfs(r: Int, c: Int) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        visit.add(r to c)
        queue.add(r to c)

        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()
            val directions = arrayOf(
                1 to 0,     // Below
                -1 to 0,    // Above
                0 to 1,     // Right
                0 to -1     // Left
            )

            for ((dr, dc) in directions) {
                val (r1, c1) = row + dr to col + dc
                if (inBounds(c1, r1) &&
                    grid[r1][c1] == '1' &&
                    r1 to c1 !in visit
                ) {
                    queue.add(r1 to c1)
                    visit.add(r1 to c1)
                }
            }

        }
    }

    for (r in 0 until rows) {
        for (c in 0 until cols) {
            if (grid[r][c] == '1' && (r to c) !in visit) {
                bfs(r, c)
                islands += 1
            }
        }
    }
    return islands
}