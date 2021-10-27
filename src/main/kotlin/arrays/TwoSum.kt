/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * 75Q solution:
 * use hash map to instantly check for difference value,
 * map will add index of last occurrence of a num,
 * don’t use same element twice;
 */
fun main() {
    listOf(
        intArrayOf(2, 7, 11, 15) to 9,
        intArrayOf(3, 2, 4) to 6,
        intArrayOf(3, 3) to 6
    ).forEach { (list, target) ->
        println("twoSum $list - $target: ${twoSum(list, target)}")
    }
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    nums.forEachIndexed { i, num ->
        // Check for difference value.
        if (map.containsKey(target - num)) {
            return intArrayOf(i, map[target - num]!!)
        }
        // Map will add index of last occurrence of a num.
        map[num] = i
    }
    error("Each input would have exactly one solution.")
}