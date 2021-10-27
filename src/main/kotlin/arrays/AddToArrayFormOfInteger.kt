/**
 * The array-form of an integer num is an array representing its digits in left to right order.
 *
 * For example, for num = 1321, the array form is [1,3,2,1].
 *
 * Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.
 *
 * Input: num = [1,2,0,0], k = 34
 * Output: [1,2,3,4]
 * Explanation: 1200 + 34 = 1234
 *
 * Input: num = [2,7,4], k = 181
 * Output: [4,5,5]
 * Explanation: 274 + 181 = 455
 *
 * Input: num = [2,1,5], k = 806
 * Output: [1,0,2,1]
 * Explanation: 215 + 806 = 1021
 *
 * Input: num = [9,9,9,9,9,9,9,9,9,9], k = 1
 * Output: [1,0,0,0,0,0,0,0,0,0,0]
 * Explanation: 9999999999 + 1 = 10000000000
 *
 */
fun main() {
    val arr = intArrayOf(1, 2, 0, 0)
    var sum = 0
    for (i in arr.indices) {
        sum = sum * 10 + arr[i]
    }
    //println("sum: $sum")

    listOf(
        intArrayOf(1, 2, 0, 0) to 34,
        intArrayOf(2, 7, 4) to 181,
        intArrayOf(2, 1, 5) to 806,
        intArrayOf(9, 9, 9, 9, 9, 9, 9, 9, 9, 9) to 1,
    ).forEach { (arr, t) ->
        println("plusOne for ${arr.toList()}, add: ${addToArrayForm(arr, t).toList()}")
    }
}

fun addToArrayForm(num: IntArray, k: Int): List<Int> {
    val list = mutableListOf<Int>()
    var cur = k
    var i = num.size
    while (--i >= 0 || cur > 0) {
        if (i >= 0) {
            cur += num[i]
        }
        list.add(0, cur % 10)
        cur /= 10
    }
    return list
    /*val add = mutableListOf<Int>()
    var k2 = k
    while (k2 > 0) {
        add.add(0, k2 % 10)
        k2 /= 10
    }

    val result = mutableListOf<Int>()
    var r = 0
    val diff = num.size - add.size
    for (i in num.size - 1 downTo 0) {
        if (i >= diff) {
            val res = num[i] + add[i - diff] + r
            r = if (res > 9) 1 else 0
            result.add(0, res % 10)
        } else {
            if (num[i] == 9 && r == 1) {
                result.add(0, 0)
            } else {
                result.add(0, num[i] + r)
                r = 0 // Consume carry.
            }
        }
    }
    return if (r == 1) listOf(1) + result else result*/
}

// Doesn't scale, even when using a Long instead of Int. But good techniques to know!
// (Converting Int array to number and vice verse).
/*fun addToArrayForm(num: IntArray, k: Int): List<Int> {
    var sum = 0L
    for (i in num.indices) {
        sum = sum * 10 + num[i]
    }
    sum += k
    val list = mutableListOf<Int>()
    while (sum > 0) {
        list.add(0, (sum % 10).toInt())
        sum /= 10
    }
    return list.takeIf { it.isNotEmpty() } ?: listOf(0)
}*/
