package intervals

/**
 * 57. Insert Interval
 *
 * You are given an array of non-overlapping intervals intervals where
 * intervals[i] = [starti, endi] represent the start and the end of the
 * ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents
 * the start and end of another interval.
 * Insert newInterval into intervals such that intervals is still sorted in
 * ascending order by starti and intervals still does not have any overlapping
 * intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * Input: intervals = [], newInterval = [5,7]
 * Output: [[5,7]]
 *
 * Input: intervals = [[1,5]], newInterval = [2,3]
 * Output: [[1,5]]
 *
 * Input: intervals = [[1,5]], newInterval = [2,7]
 * Output: [[1,7]]
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,7]]
 *
 */
fun main() {
    println("eraseOverlapIntervals: ${
        insert(arrayOf(
            intArrayOf(1, 3),
            intArrayOf(6, 9),
        ), intArrayOf(2, 5)).map { it.toList() }
    }") // expects [[1,5],[6,9]]

    println("eraseOverlapIntervals: ${
        insert(arrayOf(
            intArrayOf(1,2),
            intArrayOf(3,5),
            intArrayOf(6,7),
            intArrayOf(8,10),
            intArrayOf(12,16),
        ), intArrayOf(4, 8)).map { it.toList() }
    }") // expects [[1,2],[3,10],[12,16]]
}

/**
 * No sort required (according to question).
 */
fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    val result = mutableListOf<IntArray>()
    for (i in intervals.indices) {
        // New end is < current start, can be inserted cleanly at beginning.
        if (newInterval[1] < intervals[i][0]) {
            result += newInterval // result may have values from previous iterations.
            return (result + intervals.drop(i)).toTypedArray()
        } else if (newInterval[0] > intervals[i][1]) {
            result += intervals[i]
        } else {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0])
            newInterval[1] = Math.max(newInterval[1], intervals[i][1])
        }
    }
    result += newInterval
    return result.toTypedArray()
}