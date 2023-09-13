package dynamicprogramming

/*
    1| 1
    2| 2, 1
    3|
    4|
    5|
 */
class Knapsack {
    // https://www.youtube.com/watch?v=xOlhR_2QCXY&t=2s
    data class Item(
        val name: String,
        val weight: Int,
        val value: Int,
    )

    val items = listOf(
        Item("water", 3, 10),
        Item("book", 1, 3),
        Item("food", 2, 9),
        Item("jacket", 2, 5),
        Item("camera", 1, 6)
    )

    val weights = items.map { it.weight }
    val values = items.map { it.value }
    val max = 6

    fun solve() {
        /*         w     b     f     j     c
            ___________________________________
            | w |  3  |  1  |  2  |  2  |  1  |
            | v |  10 |  3  |  9  |  5  |  6  |
            -----------------------------------
         */

        // There are at most i times w possible variable pairs.
        fun ksRec(i: Int, w: Int): Int {
            // max weight reduced by current value's weight.
            return when {
                i == 0 || w == 0 -> 0
                weights[i] > w -> ksRec(i - 1, w)
                else -> Math.max(
                    ksRec(i - 1, w),
                    // max weight reduced by current value's weight.
                    values[i] + ksRec(i - 1, w - weights[i])
                )
            }
        }
        println("result: ${ksRec(weights.size - 1, 6)}")

        val dpRec = Array(values.size) {
            IntArray(7) { 0 }
        }
        fun ksRecMemo(i: Int, w: Int): Int {
            // max weight reduced by current value's weight.
            return when {
                dpRec[i][w] != 0 -> dpRec[i][w]
                i == 0 || w == 0 -> 0
                weights[i] > w -> ksRec(i - 1, w)
                else -> Math.max( // else block hit at most i * w times.
                    ksRec(i - 1, w),
                    // max weight reduced by current value's weight.
                    values[i] + ksRec(i - 1, w - weights[i])
                ).also {
                    dpRec[i][w] = it
                }
            }
        }
        println("result: ${ksRecMemo(   weights.size - 1, 6)}")

        fun bottomUp() {
            /* // should equate to 220 https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
            val values = intArrayOf(60, 100, 120)
            val weights = intArrayOf(10, 20, 30)
            val max = 50*/
            val arr = Array(values.size + 1) { IntArray(max + 1) }
            //println("arr: ${arr.toList().map { it.toList() }}")
            // Build table bottom up.
            for (i in 0..values.size) {
                for (w in 0..max) {
                    if (i == 0 || w == 0) {
                        arr[i][w] = 0
                    } else if (weights[i - 1] <= w) {
                        arr[i][w] = Math.max(
                            arr[i][w],
                            values[i - 1] + arr[i - 1][w - weights[i - 1]]
                            )
                    } else {
                        arr[i][w] = arr[i - 1][w]
                    }
                }
            }
            //println("arr: ${arr.toList()}")
            println("arr: ${arr[values.size][max]}")
        }
        bottomUp()
    }
}

fun main() {
    with(Knapsack()) {
        solve()
    }
}