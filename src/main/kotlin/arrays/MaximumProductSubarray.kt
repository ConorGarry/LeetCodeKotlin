/**
 * 152. Maximum Product SubArray
 * Given an integer array nums, find a contiguous non-empty subarray
 * within the array that has the largest product, and return the product.
 *
 * It is guaranteed that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
fun main() {
    listOf(
        intArrayOf(2, 3, -2, 4) to 6,
        intArrayOf(-2, 0, -1) to 0,
        intArrayOf(-2, 3, 4) to 24,
    ).forEach { (arr, t) ->
        println("maxProductSubArray for ${arr.toList()}, expects: $t: ${maxProduct(arr)}")
    }
}

/*
[2, 3, -2, 4]

 */
fun maxProduct(nums: IntArray): Int {
    var res = nums.maxOrNull()!!
    var curMax = 1
    var curMin = 1
    for (n in nums) {
        val tmpMax = curMax * n
        curMax = maxOf(tmpMax, n * curMin, n)
        curMin = minOf(tmpMax, n * curMin, n)
        res = Math.max(res, curMax)
    }
    return res
}
