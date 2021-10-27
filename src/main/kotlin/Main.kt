import kotlin.math.ceil

/**
 * Playground.
 */
fun main() {
    val arr = intArrayOf(1, 4, 3, 2, 7, 10)
    //println(reverseFrontBack(arr).toList())
    //println(reverseMiddleOut(arr).toList())
    println("factorial: 5: ${factorial(5)}")
}

fun factorial(i: Int): Int {
    if (i == 0) return 1
    return i * factorial(i - 1)
}

private fun reverseMiddleOut(arr: IntArray): IntArray {
    // Ceil will either get half, or for odd nums, get half + 1.
    // If even, decrement 1 to get opposite, or if odd, decrement 2.
    var r = ceil((arr.size / 2.0)).toInt()
    var l = r - if (arr.size %2 != 1) 1 else 2
    println("l: $l, r: $r")
    while (r < arr.size) {
        val temp = arr[l]
        arr[l] = arr[r]
        arr[r] = temp
        l--
        r++
    }
    return arr
}

/**
 * Two pointers, one at start, one at last.
 * Converge while l < r, swapping elements with each iteration.
 */
private fun reverseFrontBack(arr: IntArray): IntArray {
    var l = 0
    var r = arr.size - 1
    while (l < r) {
        val temp = arr[l]
        arr[l] = arr[r]
        arr[r] = temp
        l++
        r--
    }
    return arr
}