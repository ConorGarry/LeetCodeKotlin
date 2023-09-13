package linkedlists

import ListNode

/**
 * 141. Linked List Cycle
 * Easy
 *
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be
 * reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer
 * is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Neetcode notes:
 * dict to remember visited nodes; Time: O(n) Memory: O( n)
 * two pointers at different speeds, if they meet there is loop (Floyd's toroise and hare).
 */

fun hasCycle(head: ListNode?): Boolean {
    // Remember nodes. Memory: O(n)
    val set = mutableSetOf<ListNode>()
    var current = head
    while (current != null) {
        if (current !in set) {
            set.add(current)
        } else {
            return true
        }
        current = current.next
    }
    return false

    // Floyd's tortoise/hare two pointer. Memory: O(1)
    /*var fast = head
    var slow = head
    while (fast?.next != null) { // Iterates by two.
        slow = slow!!.next
        fast = fast.next!!.next
        if (slow == fast) {
            return true
        }
    }
    return false*/
}