package matrix

/**
 * 48. Rotate Image
 *
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input
 * 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * Input: matrix = [[1]]
 * Output: [[1]]
 *
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[3,1],[4,2]]
 */
fun main() {
    val input = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9),
    )

    println("matrix: ${input.map { it.toList() }}")
    rotate(input)
    println("rotate: ${input.map { it.toList() }}")
}

fun rotate(matrix: Array<IntArray>) {
    var l = 0
    var r = matrix.size - 1
    while (l < r) {
        for (i in 0 until r - l) {
            val top = l
            val bottom = r
            // Save top left into a temp.
            val topLeft = matrix[top][l + i]

            // bottom left to top left.
            matrix[top][l + i] = matrix[bottom - i][l]

            // bottom right to bottom left
            matrix[bottom - i][l] = matrix[bottom][r - i]

            // top right to bottom right
            matrix[bottom][r - i] = matrix[top + i][r]

            // top left to top right
            matrix[top + i][r] = topLeft
        }
        l++
        r--
    }
}

/**
 * Extra step but faster on Leetcode every time (< 200ms).
 */
fun rotateTwoStep(matrix: Array<IntArray>) {
    // Transpose
    for (i in matrix.indices) {
        for (j in 0 until i) {
            val tmp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = tmp
        }
    }

    // Reverse rows.
    for (i in matrix.indices) {
        var l = 0
        var r = matrix.size - 1
        while (l < r) {
            val tmp = matrix[i][l]
            matrix[i][l] = matrix[i][r]
            matrix[i][r] = tmp
            l++
            r--
        }
    }
}

// One solution, but doesn't count as it creates another matrix.
fun rotateWithTemp(matrix: Array<IntArray>) {
    val size = matrix.size
    val tmp = Array(size) { IntArray(size) { 0 } }
    for (i in 0 until size) {
        for (j in 0 until size) {
            tmp[i][j] = matrix[size - j - 1][i]
        }
    }

    //print2dArray(tmp)
}