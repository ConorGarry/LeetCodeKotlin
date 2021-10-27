import java.util.*

/**
 * 209. Minimum Size Subarray Sum
 * Medium
 *
 * Given an array of positive integers nums and a positive integer target, return the
 * minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which
 * the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
fun main() {
    listOf(
        intArrayOf(2, 3, 1, 2, 4, 3) to 7,
        intArrayOf(1, 4, 4) to 4,
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1) to 1
    ).forEach { (arr, t) ->
        println("minSubArrayLen for ${arr.toList()}, target: $t: ${minSubArrayLen(t, arr)}")
    }
}

fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var res = Int.MAX_VALUE
    var start = 0
    var sum = 0
    for (i in nums.indices) {
        sum += nums[i]
        while (sum >= target) {
            res = Math.min(res, i + 1 - start)
            sum -= nums[start++]
        }
    }
    return if (res != Int.MAX_VALUE) res else 0
}


/*
fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var result = -1
    var l = 0
    for (r in 1 until nums.size) {
        val num = nums.copyOfRange(l, r).sum()
        if (result > -1 && num < result && r - l >= target) {
            result = nums.copyOfRange(l, r).sum()
            l++
        } else {
            // TODO: 30/09/2021 Prob nonsense!
            result = nums.copyOfRange(l, r).size
        }
    }
    return result.takeIf { it > -1 } ?: 0
}
*/
