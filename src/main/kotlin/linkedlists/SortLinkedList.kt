package linkedlists

import ListNode

/**
 * 148. Sort List
 * Medium
 *
 * Given the head of a linked list, return the list after sorting it in ascending order.
 */
fun sortList(head: ListNode?): ListNode? {
    // 0 or 1 items.
    if (head?.next == null) return head

    // Divide
    var slow = head;
    var fast = head.next
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }
    val nextOfMiddle = slow?.next
    slow?.next = null

    var left = sortList(head)
    var right = sortList(nextOfMiddle)

    val result = ListNode(-1)
    var temp = result

    while (left != null && right != null) {
        if (left.`val` < right.`val`) {
            temp.next = left
            left = left.next
        } else {
            temp.next = right
            right = right.next
        }
        temp = temp.next!!
    }

    temp.next = left ?: right
    return result.next
}

// Iterative, 3 pointer, prev, current, next.
// Nested while loop required. A lot trickier than reverse.
fun sortListIt(head: ListNode?): ListNode? {
    var current = head
    var temp: Int
    while (current != null) {
        var next = current.next
        while (next != null) {
            if (current.`val` > next.`val`) {
                temp = current.`val`
                current.`val` = next.`val`
                next.`val` = temp
            }
            next = next.next
        }
        current = current.next
    }
    return head
}
