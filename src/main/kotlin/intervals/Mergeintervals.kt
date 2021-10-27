package intervals

import java.util.*

/**
 * Given an array of intervals where intervals[i] = [starti, endi]
 * merge all overlapping intervals, and return an array of the
 * non-overlapping intervals that cover all the intervals in the input.
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
fun main() {
    println("mergedInterval: ${
        merge(arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 6),
            intArrayOf(8, 10),
            intArrayOf(15, 18)
        )).map { it.toList() }
    }")
}

/*
    [[1,3],[2,6],[8,10],[15,18]]

        *------*
      *---*         *----*              *--------*
    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18

*/
/**
 * 1. sort in place by starting time.
 * 2. Init output array/list with first element (we will at least hive this).
 * 3. Iterate from 1 (second element).
 * 4. if start less than or equal to end of previous, there is overlap.
 *      Set previous element to mx of lastEnd and current end.
 * 5. Else just add interval to output result.
 */
private fun merge(intervals: Array<IntArray>): Array<IntArray> {
    // Sort by starting time.
    Arrays.sort(intervals, compareBy { it.first() })
    val output = mutableListOf(intervals.first())

    for (i in 1 until intervals.size) {
        val (start, end) = intervals[i]
        val lastEnd = output.last()[1]

        // If start of this interval is before end of last, we have overlap.
        if (start <= lastEnd) {
            output.last()[1] = Math.max(lastEnd, end)
        } else {
            output.add(intArrayOf(start, end))
        }
    }
    return output.toTypedArray()
}