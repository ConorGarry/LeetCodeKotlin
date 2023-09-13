import java.util.*

/**
 * University Career Fair
 *
 * Not on Leetcode but on Hackerrank. Asked by both Patreon and Twitter
 *
 * Arrange university's career fair, has a list of arrive times and durations, only one stage is available.
 *
 * Reddit description:
 * The question is pretty much given 2 arrays, one for arrival times and one for duration of stay
 * (the indexes match up to each other), how many companies can perform at a career fair.
 */
fun main() {
    println("maxEvents:")
    println("For [1, 3, 3, 5, 7], [2, 2, 1, 2, 1], expects: 4, ans: " +
            "${maxEvents(intArrayOf(1, 3, 3, 5, 7), intArrayOf(2, 2, 1, 2, 1))}")
    println("For [1, 2], [7, 3], expects: 1, ans: " +
            "${maxEvents(intArrayOf(1, 2), intArrayOf(7, 3))}")
    println("For [1, 3, 4, 6], [4, 3, 3, 2], expects: 2, ans: " +
            "${maxEvents(intArrayOf(1, 3, 4, 6), intArrayOf(4, 3, 3, 2))}")
}

/*
    Time complexity: O(n log n) since we have a sort
    Space complexity: O(n) since I create a new list to store the sorted information,
    you can also sort the input list in-place so it uses O(1) space

    Ex. 1
                  *-*
              *---*
          *-*
          *---*
      *---*
    0 1 2 3 4 5 6 7 8 9 10
 */
fun maxEvents(
    arrivals: IntArray,
    durations: IntArray,
): Int {
    // First zip and sort the event according to (end_time, duration) in ascending order.
    val sorted = arrivals.zip(durations).sortedWith(compareBy(
        { (arr, dur) -> arr + dur }, { (_, dur) -> dur }
    ))
    // Then sweep the events with initial end = -Inf and ans = 0
    var (ans, end) = 0 to Int.MIN_VALUE
    sorted.forEach { (arr, dur) ->
        // if arrival >= to end, increment ans, update end as the end time for current event.
        // Otherwise, ignore the current event.
        if (arr >= end) {
            ans++
            end = arr + dur
        }
    }
    return ans
}

fun findLongestChain(arrivals: IntArray, durations: IntArray): Int {
    val pq = PriorityQueue<IntArray> { a, b ->
        if (a[1] != b[1]) a[1] - b[1] else a[0] - b[0]
    }
    arrivals.zip(durations).forEach { (a, b) ->
        pq.add(intArrayOf(a, b))
    }
    var count = 1
    var prev = pq.poll()
    while (pq.isNotEmpty()) {
        val cur = pq.poll()
        if (prev[1] < cur[0]) {
            count++
            prev = cur
        }
    }
    return count
}

/*
# https://leetcode.com/discuss/interview-question/algorithms/374846/twitter-oa-2019-university-career-fair
def universityCareerFair(arrival, duration):
    aux = sorted(
        list(zip(arrival, duration)),
        key=lambda p: (sum(p), p[1])
    )
    ans, end = 0, -float('inf')
    for arr, dur in aux:
        if arr >= end:
            ans, end = ans + 1, arr + dur
    return ans


print(universityCareerFair([1, 3, 3, 5, 7], [2, 2, 1, 2, 1])) # 4
print(universityCareerFair([1, 2], [7, 3])) # 1
print(universityCareerFair([1, 3, 4, 6], [4, 3, 3, 2])) # 2
 */