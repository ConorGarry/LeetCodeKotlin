package algorithms

fun binarySearchRec(
    nums: IntArray,
    target: Int,
    l: Int = 0,
    r: Int = nums.size,
): Int {
    // Base case, search space exhausted.
    if (l > r) return -1
    // Find middle of search space.
    val mid = (l + r) / 2
    return when {
        target < nums[mid] -> binarySearchRec(nums, target, l, mid - 1)
        target > nums[mid] -> binarySearchRec(nums, target, mid + 1, r)
        else -> mid // Base case, mid is the index of our value.
    }
}

// Iterative
fun binarySearchIt(nums: IntArray, target: Int): Int {
    var index = 0
    var end = nums.size - 1

    while (index <= end) {
        val mid: Int = (index + end) / 2
        when {
            target < nums[mid] -> end = mid - 1
            target > nums[mid] -> index = mid + 1
            else -> return mid
        }
    }
    return -1
}

fun main() {
    val array = intArrayOf(-1, 0, 3, 5, 9, 12, 25, 45, 100, 1000) // target = 9 Output: 4
    println("binarySearch 9,should be 4: ${binarySearchIt(array, target = 4)}")
    println("binarySearch 9,should be 4: ${binarySearchRec(array, target = 4)}")
}