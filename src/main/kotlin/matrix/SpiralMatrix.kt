package matrix

/**
 * 54. Spiral Matrix
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
fun main() {
    println("matrix in: ${
        spiralOrder(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            )
        )
    }")
}

fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    var l = 0
    var top = l
    var r = matrix[0].size
    var bottom = matrix.size
    val res = mutableListOf<Int>()

    while (l < r && top < bottom) {
        // Top row
        for (i in l until r) {
            res += matrix[top][i]
        }
        top++
        // Right column
        for (i in top until bottom) {
            res += matrix[i][r - 1]
        }
        r--

        // For recangular matrices.
        if (l >= r || top >= bottom) break

        // Bottom row
        for (i in r - 1 downTo l) {
            res += matrix[bottom - 1][i]
        }
        bottom--
        // Left column
        for (i in bottom - 1 downTo top) {
            res += matrix[i][l]
        }
        l++
    }
    return res
}