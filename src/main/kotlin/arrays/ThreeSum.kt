/**
 * 15. 3Sum
 *
 * Given an integer array nums, return all the triplets
 * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 */
fun main() {
    listOf(
        intArrayOf(1, 0, 1, 2, -1, -4) to arrayOf(intArrayOf(-1, -1, 2), intArrayOf(-1, 0, 1)),
    ).forEach { (arr, t) ->
        println("plusOne for ${arr.toList()}, expects: ${t.map { it.toList() }}: ${threeSum(arr).map { it.toList() }}")
    }
}

fun threeSum(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    nums.sort()

    for (i in nums.indices) {
        if (i > 0 && nums[i] == nums[i - 1]) continue
        var l = i + 1
        var r = nums.size - 1
        while (l < r) {
            val sum = nums[i] + nums[l] + nums[r]
            when {
                sum > 0 -> r--
                sum < 0 -> l++
                else -> {
                    result.add(listOf(nums[i], nums[l], nums[r]))
                    l++
                    while (nums[l] == nums[l - 1] && l < r)
                        l++
                }
            }
        }
    }
    return result
}
