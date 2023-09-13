package arrays

/**
 * 11. Container With Most Water
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Input: height = [1,1]
 * Output: 1
 *
 * Input: height = [4,3,2,1,4]
 * Output: 16
 *
 * Input: height = [1,2,1]
 * Output: 2
 */
fun main() {
    arrayOf(
        intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7) to 49,
        intArrayOf(1, 1) to 1,
        intArrayOf(4, 3, 2, 1, 4) to 16,
        intArrayOf(1, 2, 1) to 2
    ).forEach { (arr, t) ->
        println("maxArea: $arr, expects: $t, ${maxArea(arr)}")
    }
}

// O(n) linear time.
// Two-pointer, converge from edges.
fun maxArea(height: IntArray): Int {
    var res = 0
    var l = 0
    var r = height.size - 1
    while (l < r) {
        val area = (r - l) * Math.min(height[l], height[r])
        res = Math.max(res, area)

        // This is the efficient part, we move pointer based on maximising both heights.
        if (height[l] < height[r])
            l++
        else // if right decrement, or if both same, it doesn't matter which, so can be shorted to this.
            r--
    }
    return res
}

// n^2 TLE!
fun maxAreaBruteForce(height: IntArray): Int {
    var res = 0
    for (l in height.indices) {
        for (r in 1 until height.size) {
            val area = (r - l) * Math.min(height[l], height[r]) // Area algorithm.
            res = Math.max(res, area)
        }
    }
    return res
}