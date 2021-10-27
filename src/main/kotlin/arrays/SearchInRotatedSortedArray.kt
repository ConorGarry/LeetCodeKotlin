/**
 * 33. Search in Rotated Sorted Array
 * Medium
 *
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Essentially Binary search but with extra checks to find which half is sorted.
 *
 */
fun main() {
    arrayOf(
        intArrayOf(4, 5, 6, 7, 0, 1, 2) to 0, // 4
        intArrayOf(4, 5, 6, 7, 0, 1, 2) to 3, // -1
        intArrayOf(1) to 0,
        intArrayOf(5, 1, 3) to 0
    ).forEach { (arr, t) ->
        println("${arr.toList()} find: $t: ${search(arr, t)}")
    }
}


private fun search(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.size - 1
    //println("${nums.toList()} : $target")

    // !! NOTE: this pattern in BS is generally just `<`, but for rotated array we need `<=` !!
    while (l < r) {
        val m = l + ((r - l) / 2)
        when {
            target == nums[m] -> return m
            // Left sorted portion.
            nums[l] <= nums[m] ->
                // May not necessary be in first portion, check top half!
                if (target > nums[m] || target < nums[l])
                    l = m + 1
                else
                    r = m - 1
            // Right sorted portion.
            else ->
                // May not necessarily be in right portion, check bottom half!
                if (target < nums[m] || target > nums[r])
                    r = m - 1
                else
                    l = m + 1
        }
    }
    return -1
}

