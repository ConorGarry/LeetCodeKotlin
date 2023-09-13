/**
 * 19. Remove Nth Node From End of List.
 *
 * Given the head of a linked list, remove the nth
 * node from the end of the list and return its head.
 */
fun main() {
    println(removeNthFromEnd(buildLinkedList(1, 2), 1))
    println(removeNthFromEndRec(buildLinkedList(1, 2, 3, 4, 5), 2))
}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    var fast: ListNode? = head ?: return null
    var slow = fast

    for (i in 0 until n) { // Fast pointer head start.
        fast = fast?.next
    }
    // Boundary case, returning first node.
    if (fast == null) {
        return head.next
    }

    while (fast?.next != null) {
        fast = fast.next
        slow = slow?.next
    }

    // Unlink
    slow?.next = slow?.next?.next
    return head
}

// Recursion with auxiliary counter.
fun removeNthFromEndRec(head: ListNode?, n: Int): ListNode? {
    var i = 0
    fun remove(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null
        head.next = remove(head.next, n)
        i++
        return if (i == n) head.next else head
    }
    return remove(head, n)
}

// Neetcode, version, uses dummy Node at start.
// Clean and fast, with no edgecases!
fun removeNthFromEndDummy(head: ListNode?, n: Int): ListNode? {
    // Fastest, no edge case to worry about.
    val dummy = ListNode(-1, head)
    var slow: ListNode? = dummy
    var fast: ListNode? = dummy
    for (i in 0..n) {
        fast = fast!!.next
    }
    while (fast != null) {
        slow = slow?.next
        fast = fast.next
    }
    slow?.next = slow?.next?.next
    return dummy.next
}