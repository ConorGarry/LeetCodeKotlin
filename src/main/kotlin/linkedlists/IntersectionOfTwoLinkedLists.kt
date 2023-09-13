package linkedlists

import ListNode

/**
 * 160. Intersection of Two Linked Lists
 * Easy
 *
 * Given the heads of two singly linked-lists headA and headB,
 * return the nodeat which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 */
fun main() {
}

class Solution {
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        var a = headA
        var b = headB

        while (a != b) {
            a = if (a == null) headB else a.next
            b = if (b == null) headA else b.next
        }
        return a
    }
}
