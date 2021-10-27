package linkedlists

import ListNode

/**
 * 206. Reverse Linked List
 * Easy
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var current = head
    while (current != null) {
        val next = current.next
        current.next = prev
        prev = current
        current = next
    }
    return prev
}

// Build up call stack until we get to second last node.
// Change pointers while we pop the stack-frame contents.
fun reverseListRec(head: ListNode?): ListNode? {
    if (head?.next == null) return head

    val returned = reverseListRec(head.next)
    head.next!!.next = head
    head.next = null
    return returned
}
