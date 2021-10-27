import java.util.*

/**
 * 23. Merge K sorted lists.
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 */
fun main() {
    val lists: Array<ListNode?> = arrayOf(
        buildLinkedList(1, 4, 5),
        buildLinkedList(1, 3, 4),
        buildLinkedList(2, 6),
    )

    // expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6, 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
    println("Input : $lists, expected: ${buildLinkedList(1, 1, 2, 3, 4, 4, 5, 6)}, ${mergeKLists(lists)}")
}

fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    var interval = 1
    while (interval < lists.size) {
        // interval * 2 To increment in pairs.
        for (i in (0..lists.size - interval) step interval * 2) {
            val l1 = lists[i]
            val l2 = lists.takeIf { it.size > i + interval }?.let { it[i + interval] }
            lists[i] = merge(l1, l2)
        }
        interval *= 2 // Jump up to next pair.
    }
    return lists.firstOrNull()
}

// Seems a lot faster ron LeetCode.
fun heapSortMerge(lists: Array<ListNode?>): ListNode? {
    val queue = PriorityQueue<ListNode> { o1, o2 -> o1.`val` - o2.`val` }
    lists.filterNotNull().forEach {
        queue.add(it)
    }
    val result = ListNode(-1)
    var temp: ListNode? = result
    while (queue.isNotEmpty()) {
        temp?.next = queue.poll()
        temp = temp?.next
        temp?.next?.run { queue.add(this) }
    }
    return result.next
}

// Typed from memory as practice.
fun merge(left: ListNode?, right: ListNode?): ListNode? {
    if (left == null) return right
    if (right == null) return left
    val result = ListNode(-1)
    var temp = result
    var l = left
    var r = right
    while (l != null && r != null) {
        if (l.`val` < r.`val`) {
            temp.next = l
            l = l.next
        } else {
            temp.next = r
            r = r.next
        }
        temp = temp.next!!
    }
    temp.next = l ?: r
    return result.next
}