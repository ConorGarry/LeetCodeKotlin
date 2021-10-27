/**
 * 920 Meeeting Rooms (LintCode
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), determine if a person could attend all meetings.
 *
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: false
 * Explanation:
 * (0,30), (5,10) and (0,30),(15,20) will conflict
 *
 *
 * Input: intervals = [(5,8),(9,15)]
 * Output: true
 * Explanation:
 * Two times will not conflict
 */
fun main() {
    println("can attendMeetings: ${
        canAttendMeetings(listOf(
            Interval(0, 30),
            Interval(5, 10),
            Interval(15, 20),
        ))
    }") // expects false

    println("can attendMeetings: ${
        canAttendMeetings(listOf(
            Interval(5, 8),
            Interval(9, 15),
        ))
    }") // expects true
}

/**
 * Sort by start time.
 * Iterate from one, if start of current is < end of previous, there's an overlap.
 */
fun canAttendMeetings(intervals: List<Interval>): Boolean {
    val sorted = intervals.sortedBy { it.start }
    for (i in 1 until sorted.size) {
        if (sorted[i].start < sorted[i - 1].end) {
            return false
        }
    }
    return true
}
