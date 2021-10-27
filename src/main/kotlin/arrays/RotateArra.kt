/**
 * 189. Rorate Array
 *
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 */
fun main() {
    listOf(
        intArrayOf(1, 2, 3, 4, 5, 6, 7) to 3,   // Expected: [5, 6, 7, 1, 2, 3, 4]
        intArrayOf(-1, -100, 3, 99) to 2,       // Expected: [3,99,-1,-100]
    ).forEach { (arr, t) ->
        //println("rotateArray ${arr.toList()}, by: ${t}: ${rotateArray(arr, 3).toList()}")
        println("rotateInPlace ${arr.toList()}, by: ${t}: ${rotateInPlace(arr, t).toList()}")
    }
}

/**
 * Time: O(n)
 * Space: O(n) (extra array for copying).
 */
fun rotateArray(nums: IntArray, k: Int): IntArray {
    //val temp = IntArray(k + 1) { nums[it] }
    val temp = IntArray(nums.size)
    for (i in nums.indices) {
        val offset =
            if (i + k < nums.size) i + k
            else (i + k) % nums.size
        temp[offset] = nums[i]
    }
    for ((i, n) in temp.withIndex()) {
        nums[i] = n
    }

    return temp
}

// Time: O(n)
// Space: O(1)
fun rotateInPlace(nums: IntArray, k: Int): IntArray {
    val kMod = k % nums.size

    fun swap(left: Int, right: Int, arr: IntArray) {
        var l = left
        var r = right
        while (l < r) {
            val tmp = arr[l]
            arr[l] = arr[r]
            arr[r] = tmp
            l++
            r--
        }
    }

    swap(0, nums.size - 1, nums)
    /*while (l < r) {
        val tmp = nums[l]
        nums[l] = nums[r]
        nums[r] = tmp
        l++; r--
    }*/

    swap(0, kMod - 1, nums)
    /*l = 0; r = kMod -1
    while (l < r) {
        val tmp = nums[l]
        nums[l] = nums[r]
        nums[r] = tmp
        l++; r--
    }*/

    swap(kMod, nums.size - 1, nums)
    /*l = kMod; r = nums.size - 1
    while (l < r) {
        val tmp = nums[l]
        nums[l] = nums[r]
        nums[r] = tmp
        l++; r--
    }*/
    return nums
}

/*
fun rotateArray(nums: IntArray, k: Int): IntArray {
    val temp = IntArray(k + 1) { nums[it] }
    println("temp: ${temp.toList()}")
    for (i in k until nums.size - 1) {
        //println("setting index: ${i - k} to ${nums[i + 1]}")
        nums[i - k] = nums[i + 1]
    }
    for (i in 0 until k + 1) {
        println("setting index: ${nums.size + i - 1 - k} to ${temp[i]}")
        nums[nums.size + i - 1 - k] = temp[i]
    }
    return nums
}*/
