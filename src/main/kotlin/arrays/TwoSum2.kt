/**
 * 167. Two Sum II
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= first < second <= numbers.length.
 * Return the indices of the two numbers, index1 and index2, as an integer array [index1, index2] of length 2.
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 */
fun main() {
    listOf(
        intArrayOf(2, 7, 11, 15) to 18,
    ).forEach { (arr, t) ->
        println("twoSum2 for ${arr.toList()}, ${twoSum2(arr, t).toList()}")
    }
}

fun twoSum2(numbers: IntArray, target: Int): IntArray {
    var l = 0
    var r = numbers.size - 1
    numbers.indices.forEach { _ ->
        when {
            numbers[l] + numbers[r] > target -> r--
            numbers[l] + numbers[r] < target -> l++
            else -> return intArrayOf(l, r)
        }
    }
    error("Not possible")
}
