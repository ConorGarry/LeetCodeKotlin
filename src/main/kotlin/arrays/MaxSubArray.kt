import kotlin.math.max

/**
 * 53. Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum. A subarray is a contiguous part of an array.
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Input: nums = [1]
 * Output: 1
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 */
fun main() {
    listOf(
        intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4) to 6,
        intArrayOf(1) to 1,
        intArrayOf(5, 4, -1, 7, 8) to 23,
    ).forEach { (arr, t) ->
        println("maxSubArray for ${arr.toList()}, expects: ${t}: ${maxSubArray(arr)}")
    }
}

/**
 * A sliding window but we don't manually adjust the pointers. We use DP to keep a rolling
 * sum. We slide the window by dis-regarding negative prefixes (simply by converting
 * them to 0). If sum has gone negative, normalise to 0, and continue.
 */
fun maxSubArray(nums: IntArray): Int {
    var max = nums[0] // We know it's not empty.
    var sum = 0
    for (n in nums) {
        if (sum < 0) sum = 0
        sum += n
        max = max(max, sum)
    }
    return max
}


// O(n2) solution.
/*
fun maxSubArray(nums: IntArray): Int {
    var max = 0
    for (i in nums.indices) {
        var curSum = 0
        for (j in i until nums.size) {
            curSum += nums[j]
            max = max(curSum, max)
        }
    }
    return max
}*/
