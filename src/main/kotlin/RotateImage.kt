/**
 * 48. Rotate Image
 *
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 */
fun main() {
    val matrix = Array(3) { IntArray(3) { 0 } }
        .apply {
            this[0] = intArrayOf(1, 2, 3)
            this[1] = intArrayOf(4, 5, 6)
            this[2] = intArrayOf(7, 8, 9)
        }

    // Print matrix.
    print2dArray(matrix)
    rotate(matrix)
    println()

    // Print result.
    print2dArray(matrix)
}

fun rotate(matrix: Array<IntArray>) {
    val size = matrix.size // One size fits all, literally! Seeing as it's n x n.
    // Transpose
    for (i in 0 until size) {
        for (j in 0 until i) { // Note that it's j until i.
            val tmp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = tmp
        }
    }

    // Reverse rows
    for (i in 0 until size) {
        // Front/Back Sliding Window technique.
        var l = 0
        var r = size - 1
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
    print2dArray(tmp)
}
