package arrays

/**
 * 704. Binary Search
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 *
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 */
fun main() {
    arrayOf(
        intArrayOf(-1,0,3,5,9,12) to 9, // 4
        intArrayOf(-1,0,3,5,9,12) to 2, // -1
    ).forEach {(arr, t) ->
        println("${arr.toList()} find: $t: ${search(arr, t)}")
    }
}

fun search(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.size - 1

    while (l < r) {
        val m = (l + r)  / 2
        when {
            target < nums[m] -> r = m - 1
            target > nums[m] -> l = m + 1
            else -> return m
        }
    }
    return -1
}