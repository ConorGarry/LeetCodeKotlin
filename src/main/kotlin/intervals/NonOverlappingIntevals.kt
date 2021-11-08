package intervals

import java.util.*

/**
 * 435. Non-overlapping Intervals
 *
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest
 * of the intervals non-overlapping.
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
fun main() {
    println("eraseOverlapIntervals: ${
        eraseOverlapIntervals(arrayOf(
            intArrayOf(1,2),
            intArrayOf(2,3),
            intArrayOf(3,4),
            intArrayOf(1,3)
        ))
    }") // expects 1

    println("eraseOverlapIntervals: ${
        eraseOverlapIntervals(arrayOf(
            intArrayOf(1,2),
            intArrayOf(1,2),
            intArrayOf(1,2),
        ))
    }") // expects 2

    println("eraseOverlapIntervals: ${
        eraseOverlapIntervals(arrayOf(
            intArrayOf(1,2),
            intArrayOf(2,3),
        ))
    }") // expects 0
}

/**
 * O(n logn) n for iterations, log n for sort.
 * = means NO OVERLAP.
 * 1. Sort by start values (and end if tied).
 * 2. Iterate, keeping a rolling sum res.
 */
fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
    Arrays.sort(intervals, compareBy({ it[0] }, { it[1] })) // (start, end) sorting orders.
    var res = 0
    var prevEnd = intervals.first()[1] // Always keeping track of an ending value.
    for ((start, end) in intervals.drop(1)) {
         prevEnd = if (start >= prevEnd) { // No overlap. We know new end value will be greater than prev.
            end
        } else {
            res++
            Math.min(end, prevEnd) // Removing the one that ends first covers all edges cases.
        }
    }
    return res
}