/**
 * ### 7. Reverse Integer
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 */
fun main() {
    println("Reverse Integer: 123: ${reverse(123)}") // 321
    println("Reverse Integer: 1534236469: ${reverse(1534236469)}") // 0 (overflows)
}

// Official solution.
fun reverse(x: Int): Int {
    var res = 0
    var num = x // mutable
    var prev = 0
    while (num != 0) {
        // pop
        val pop = num % 10
        num /= 10
        // push
        res = res * 10 + pop

        // Values of res - pop / 10 should be same as previous,
        // if not, there was an overflow.
        if ((res - pop) / 10 != prev) {
            return 0
        }
        prev = res
    }
    return res
}

// Works but violates the rule:
// `Assume the environment does not allow you to store 64-bit integers (signed or unsigned).`
// So converting to a Long technically shouldn't be allowed.
fun reverseWithLong(x: Int): Int {
    var res: Long = 0
    var num = x // mutable
    while (num != 0) {
        // pop
        val pop = num % 10
        num /= 10
        // push
        res = res * 10 + pop
    }
    return if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) 0 else res.toInt()
}