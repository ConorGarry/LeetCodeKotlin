package arrays

/**
 * 238. Product of Array Except Self
 *
 * Given an integer array nums, return an array answer such that answer[i]
 * is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
fun main() {
    arrayOf(
        intArrayOf(1, 2, 3, 4),     // 24,12,8,6
        intArrayOf(-1, 1, 0, -3, 3) // 0,0,9,0,0
    ).forEach {
        println("product except self: ${productExceptSelf(it).toList()}")
        //println("product except self: ${productExceptSelfBruteForce(it).toList()}")
    }
}
// Iterate 0 to end, adding prefix for each step.
// Then Iterate end to 0 performing same opration, accumulating postfix with *= nums[i0.
fun productExceptSelf(nums: IntArray): IntArray {
    val res = IntArray(nums.size) { 1 }
    var prefix = 1
    for (i in nums.indices) {
        res[i] = prefix
        prefix *= nums[i]
    }
    var postfix = 1
    for (i in nums.size - 1 downTo 0) {
        res[i] *= postfix
        postfix *= nums[i]
    }
    return res
}

// n²
fun productExceptSelfBruteForce(nums: IntArray): IntArray {
    val res = IntArray(nums.size)
    for (i in nums.indices) {
        var mul = 1
        for (j in nums.indices) {
            if (i == j) continue
            mul *= nums[j]
        }
        res[i] = mul
    }
    return res
}