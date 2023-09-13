package linkedlists

import ListNode

/**
 * 21. Merge Two Sorted Lists
 * Easy
 *
 * Merge two sorted linked lists and return it as a sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 */
fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) return l2
    if (l2 == null) return l1
    val result = ListNode(-1)
    var temp = result
    var head1 = l1
    var head2 = l2

    while (head1 != null && head2 != null) {
        if (head1.`val` < head2.`val`) {
            temp.next = head1
            head1 = head1.next
        } else {
            temp.next = head2
            head2 = head2.next
        }
        temp = temp.next!!
    }

    temp.next = head1 ?: head2
    return result.next
}
