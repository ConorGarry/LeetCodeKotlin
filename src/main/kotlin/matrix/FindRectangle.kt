package matrix

/**
 * Not on LeetCode. Asked in Reddit Hackerrank.
 *
 * Given a rectangle represented by a 0-1 2-d array and assume it contains
 * one rectangle of all 0, return the upper left corner and lower right corner.
 *
 * [[1,1,1,1,1],
 * [1,0,0,1,1],
 * [1,0,0,1,1],
 * [1,1,1,1,1]], you should return [[[1,1],[2,2]]]
 *
 * Followup, what if there are multiple rectangles that are made of 0, return all.
 *
 * [[1,1,1,1,1],
 * [1,0,0,1,1],
 * [1,0,0,1,1],
 * [1,1,1,1,0]], you should return [[[1,1],[2,2]],[[3,4],[3,4]]]
 */
fun main() {
    val inSingle = arrayOf(
        intArrayOf(1, 1, 1, 1, 1),
        intArrayOf(1, 0, 0, 1, 1),
        intArrayOf(1, 0, 0, 1, 1),
        intArrayOf(1, 1, 1, 1, 0)
    )
    val inMultiple = arrayOf(
        intArrayOf(1, 1, 1, 1, 1),
        intArrayOf(1, 0, 0, 1, 1),
        intArrayOf(1, 0, 0, 1, 1),
        intArrayOf(1, 0, 1, 1, 0)
    )

    println("coords single, expect: [1, 1], [2, 2]: ${findCoords(inSingle).map { it.toList() }}")
}

fun findCoords(matrix: Array<IntArray>): Array<IntArray> {
    val rows = matrix.size
    val cols = matrix[0].size

    val res = mutableListOf<IntArray>()

    o@ for (r in 0 until rows) {
        for (c in 0 until cols) {
            if (matrix[r][c] == 0) {
                //res += intArrayOf(r, c)
                println("findSquare: ${findSquare(matrix, c, r).map { it.toList() }}")
                res += findSquare(matrix, c, r)
                break@o
            }
        }
    }
    /*println("res: ${res.map { it.toList() }}")
    return arrayOf(
        res.minByOrNull { it[0] + it[1] }!!,
        res.maxByOrNull { it[0] + it[1] }!!
    )*/
    return res.toTypedArray()
}

fun findSquare(m: Array<IntArray>, left: Int, top: Int): List<IntArray> {
    var l = left
    var t = top
    val res = mutableListOf<IntArray>()
    while (l < m[0].size) {
        if (m[top][l] != 0) {
            res += intArrayOf(top, l - 1)
            break
        }
        l++
    }
    while (t < m.size) {
        if (m[t][left] != 0) {
            res += intArrayOf(t - 1, l - 1)
            break
        }
        t++
    }

    return res
}