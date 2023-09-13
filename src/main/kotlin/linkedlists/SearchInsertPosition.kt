/**
 * 35. Search Insert Position
 *
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 */
fun main() {
    listOf(
        intArrayOf(1, 3, 5, 6) to 7,
        intArrayOf(1) to 0
    ).forEach { (arr, n) ->
        println("Insert position for 2 in: ${arr.toList()}: ${searchInsert(arr, n)}")
    }
}

fun searchInsert(nums: IntArray, target: Int): Int {
    var i = 0
    var end = nums.size - 1
    var mid = 0
    while (i <= end) {
        mid = (i + end) / 2
        when {
            target < nums[mid] -> end = mid - 1
            target > nums[mid] -> i = mid + 1
            else -> return mid
        }
    }
    return if (nums[mid] < target) mid + 1 else mid
}
