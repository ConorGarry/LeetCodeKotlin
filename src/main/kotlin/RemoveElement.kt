/**
 * 27. Remove Element
 *
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 */
fun main() {
    val input1 = intArrayOf(3, 2, 2, 3)
    val target = 3
    println("removeElement 2: ${removeElement(input1, target)}")
    println("after: ${input1.toList()}")
}

/**
 * Result will be `[2, 2, 2, 3]` which resolves requirement of `[2, 2, _, _]`.
 */
fun removeElement(nums: IntArray, `val`: Int): Int {
    var i = 0
    for (j in nums.indices) {
        if (nums[j] != `val`) {
            nums[i] = nums[j]
            i++
        }
    }
    return i
}
