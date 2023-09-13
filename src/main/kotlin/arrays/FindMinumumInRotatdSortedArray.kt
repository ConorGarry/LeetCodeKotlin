package arrays

/**
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1
 * time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements,
 * return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 */
fun main() {
    arrayOf(
        intArrayOf(3, 4, 5, 1, 2) to 1, // 4
        intArrayOf(4, 5, 6, 7, 0, 1, 2) to 0, // -1
        intArrayOf(11, 13, 15, 17) to 11
    ).forEach { (arr, t) ->
        println("${arr.toList()} expects: $t: ${findMin(arr)}")
    }
}

// two pointers, l and r
// while l <= r (note the = because it's a rotated array)
// first check if we're already in a sorted array. If so, calc min and break.
// Get mid, check min against current result
// Check which side of array to check next, if nums[m] >= nums[l] to go top half.
fun findMin(nums: IntArray): Int {
    var res = nums[0]
    var l = 0
    var r = nums.size - 1
    while (l <= r) {
        // Check if we're in a sorted sub-array.
        if (nums[l] < nums[r]) {
            res = Math.min(res, nums[l]) // it may or may not be the min.
            break
        }
        val m = (l + r) / 2
        res = Math.min(res, nums[m])
        if (nums[m] > nums[l]) {
            l = m + 1
        } else
            r = m - 1
    }
    return res
}