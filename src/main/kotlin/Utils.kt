/**
 * Build a LinkedList of [ListNode] from 1 or more Integers.
 * @param nums vararg of [Integer]
 */
fun buildLinkedList(vararg nums: Int): ListNode {
    val result = ListNode(-1)
    var current = result
    for (i in nums) {
        current.next = ListNode(i)
        current = current.next ?: break
    }
    return result.next!!
}

/**
 * Reverse an Integer's digits without requiring Char/String conversion.
 * e.g. 12345 becomes 54321.
 */
fun Int.reverse(): Int {
    var n = this // mutation.
    var result = 0
    while (n > 0) {
        result = result * 10 + (n % 10)
        n /= 10
    }
    return result
}

/**
 * Same as [Int.reverse] but with recursion.
 */
fun Int.reverseRec(): Int {
    var ans = 0
    fun rev(n: Int): Int =
        if (n < 0) 0
        else {
            ans = ans * 10 + (n % 10)
            rev(n / 10)
        }
    rev(this)
    return ans
}
