package codility

/**
 * An array A consisting of N different integers is given.
 * The array contains integers in the range [1..(N + 1)],
 * which means that exactly one element is missing.
 *
 * Your goal is to find that missing element.
 *
 *
 * You can use the formula (n + 1) * (n + 2) / 2 to calculate the sum of the first N consecutive positive integers
 * without explicitly adding each term. It's a handy formula to know and can be derived as shown above based on
 * the principles of arithmetic progression.
 *
 * For example, if you have 5 consecutive positive integers (1, 2, 3, 4, 5),
 * you can calculate the sum using (5 + 1) * (5 + 2) / 2, which simplifies to (6 * 7) / 2,
 * giving you 21, which is indeed the sum of those integers (1 + 2 + 3 + 4 + 5).
 */
class PermMissingElem {

    // Time complexity: O(n).
    fun solution(A: IntArray): Int {
        val n = A.size
        val expectedSum = (n + 1) * (n + 2) / 2
        val actualSum = A.sum()
        return expectedSum - actualSum
    }
}