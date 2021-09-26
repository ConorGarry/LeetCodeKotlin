/**
 * 26. Remove Duplicates from Sorted Array
 */
fun main() {
    val arr = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
    val uniqueCount = removeDuplicates(arr)
    println("Remove duplicates: $uniqueCount")

    require(arr.toList().subList(0, uniqueCount) == listOf(0, 1, 2, 3, 4)) {
        "This also needs to be true for LeetCode tests to pass!"
    }
}

fun removeDuplicates(nums: IntArray): Int {
    var l = 1 // Unique index pointer.
    for (i in 1 until nums.size) {
        if (nums[i] != nums[i - 1]) {
            nums[l] = nums[i]
            l++
        }
    }
    return l
}
