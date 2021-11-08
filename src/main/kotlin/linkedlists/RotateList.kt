package linkedlists

import ListNode
import buildLinkedList

/**
 * 61. Rotate List
 * Medium
 *
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 */
fun main() {
    println("rotate: [1, 2, 3, 4, 5] for k=2: " +
            "${rotateRight(buildLinkedList(1, 2, 3, 4, 5), 2)}")
    println("rotate: [0, 1, 2] for k=4: " +
            "${rotateRight(buildLinkedList(0, 1, 2), 4)}")
}

fun rotateRight(head: ListNode?, k: Int): ListNode? {
    if (head?.next == null) return head

    var length = 1
    var tail = head
    while (tail?.next != null) {
        tail = tail.next
        length++
    }

    val k1 = k % length
    if (k1 == 0) return head

    var current = head
    for (i in 0 until length - k1 - 1) {
        current = current!!.next
    }
    val newHead = current!!.next
    current.next = null
    tail!!.next = head
    return newHead
}
