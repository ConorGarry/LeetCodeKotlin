package matrix

/**
 * 73. Set Matrix Zeroes
 *
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
 * You must do it in place.
 */
fun main() {

}

fun setZeroes(matrix: Array<IntArray>) {
    val rows = matrix.size
    val cols = matrix[0].size
    var rowZero = false

    // Determine which rows and columns need to be set to 0.
    for (r in 0 until rows) {
        for (c in 0 until cols) {
            if (matrix[r][c] == 0) {
                matrix[0][c] = 0
                if (r > 0) { // Can't set for top left.
                    matrix[r][0] = 0
                } else {
                    rowZero = true
                }
            }
        }
    }

    // Zero out `most` of hte rows/cols.
    for (r in 1 until rows) {
        for (c in 1 until cols) {
            if (matrix[0][c] == 0 || matrix[r][0] == 0) {
                matrix[r][c] = 0
            }
        }
    }

    // Zero out first column (if required)
    if (matrix[0][0] == 0) {
        for (r in 0 until rows) {
            matrix[r][0] = 0
        }
    }

    // Zero out first row (if required).
    if (rowZero) {
        for (c in 0 until cols) {
            matrix[0][c] = 0
        }
    }
}