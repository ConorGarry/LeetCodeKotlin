/**
 * 919 Meeting Rooms II (Lintcode).
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), find the minimum number of conference rooms required.
 *
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: 2
 * Explanation:
 * We need two meeting rooms
 * room1: (0,30)
 * room2: (5,10),(15,20)
 *
 * Input: intervals = [(2,7)]
 * Output: 1
 * Explanation:
 * Only need one meeting room
 */
fun main() {
    println("can attendMeetings: ${
        minMeetingRooms(listOf(
            Interval(0, 30),
            Interval(5, 10),
            Interval(15, 20),
        ))
    }") // expects 2

    println("can attendMeetings: ${
        minMeetingRooms(listOf(
            Interval(2, 7),
        ))
    }") // expects 1
}

/**
 * Find min number = just find out how many rooms we need. i.e. how many overlaps are there.
 * 1. Sort by start times (individually! Not atomic!)
 * 2. Iterate intervals (s)tart, (e)nd with a while loop s < size.
 * 3. if s of starts < e of ends, we have overlap, increment total. Increment s!
 * 4. else increment e, decrement total.
 * 5. Assign if bigger than current sum. Math.max(result, count).
 * 6. return max.
 */
fun minMeetingRooms(intervals: List<Interval>): Int {
    val starts = intervals.map { it.start }.sorted()
    val ends = intervals.map { it.end }.sorted()
    var (res, count) = 0 to 0
    var (s, e) = 0 to 0
    while (s < intervals.size) {
        if (starts[s] < ends[e]) {
            s++
            count++
        } else {
            e++
            count--
        }
        res = Math.max(res, count)
    }
    return res
}
