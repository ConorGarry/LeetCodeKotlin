package sorting.questions

/**
 * 179. Largest Number
 *
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 * Example 1:
 *
 * Input: nums = [10,2]
 * Output: "210"
 *
 * Example 2:
 *
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 *
 */
fun main() {
    with(Solution()) {
        listOf(
            intArrayOf(10, 2),
            intArrayOf(3, 30, 34, 5, 9)
        ).forEach {
            println("largestNumer: ${largestNumber(it)}")
        }
    }
}
class Solution {
    fun largestNumber(nums: IntArray): String {
        val newInts = mutableListOf<Int>()
        nums.forEach {
            if (it <= 9) {
                newInts.add(it)
            } else {
                var n = it
                while (n != 0) {
                    newInts.add(n % 10)
                    n /= 10
                }
            }
        }
        newInts.sortWith { o1, o2 -> o2.compareTo(o1) }
        return newInts.sortedWith(object : Comparator<Int>{
            override fun compare(o1: Int, o2: Int): Int = o2.compareTo(o1)
        }).joinToString(separator = "")
    }
}