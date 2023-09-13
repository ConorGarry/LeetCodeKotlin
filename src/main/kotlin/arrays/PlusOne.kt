/**
 * 66. Plus One
 * Easy
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of
 * the integer. The digits are ordered from most significant to least significant in left-to-right order.
 * The large integer does not contain any leading 0's.
 * Increment the large integer by one and return the resulting array of digits.
 */
fun main() {
    listOf(
        intArrayOf(1, 2, 3) to intArrayOf(1, 2, 4),
        intArrayOf(4, 3, 2, 1) to intArrayOf(4, 3, 2, 2),
        intArrayOf(9) to intArrayOf(1, 0),
        intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0) to intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 1),
    ).forEach { (arr, t) ->
        println("plusOne for ${arr.toList()}, expects: ${t.toList()}: ${plusOne/*Carry*/(arr).toList()}")
    }
}

/**
 * Solution is similar to Add Ti Array Form of Integer.
 * Instead of worrying about the carry, just keep adding to the 1.
 */
fun plusOne(digits: IntArray): IntArray {
    val list = mutableListOf<Int>()
    var current = 1
    var i = digits.size
    while (--i >= 0 || current > 0) {
        if (i >= 0)
            current += digits[i]
        list.add(0, current % 10)
        current /= 10
    }
    return list.toIntArray()
}

/**
 * We can treat this as a microcosm of a bigger version of itself.
 * Start the 1 addition as if it was the carry from a previous sum.
 * With each iterative, decrement, if digit is 9 and we have a carry, set it 0 and continue.
 * Otherwise, add the carry and 'consume' it by setting the variable to 0.
 * If at the end, we still have a carry, then we know we need to add an extra 1 prefix.
 * O(n), we just iterate once.
 */
fun plusOneCarry(digits: IntArray): IntArray {
    var r = 1
    for (i in digits.size - 1 downTo 0) {
        if (digits[i] == 9 && r == 1) {
            digits[i] = 0
        } else {
            digits[i] += r
            r = 0
        }
    }
    return if (r == 1) intArrayOf(1) + digits else digits
}