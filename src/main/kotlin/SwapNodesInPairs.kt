/**
 * ### 24 Swap Nodes in Pairs
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 */
fun main() {
    // Iterative. Even size.
    println(
        "Swap Nodes: 1, 2, 3, 4:\n${
        swapPairs(
            buildLinkedList(1, 2, 3, 4)
        )}\n"
    )

    // Recursive. Odd size.
    println(
        "Swap Nodes: 1, 2, 3, 4, 5:\n${
        swapPairsRec(
            buildLinkedList(1, 2, 3, 4, 5)
        )}"
    )
}

// [-1]  [1] -> [2] -> [3] -> [4]
//  t     c             n
fun swapPairs(head: ListNode?): ListNode? {
    var current: ListNode? = head ?: return head

    val result = ListNode(-1)
    var temp = result

    while (current?.next != null) {
        val next = current.next!!.next // [3]

        temp.next = current.next // points at [2]
        temp.next?.next = current // [2] points at [1]

        temp = temp.next?.next!! // Move forward two steps [2] to [4]
        current = next // Points at [3]
    }

    // Happens if List size is odd.
    if (current != null) {
        temp.next = current // [5]
        temp = temp.next!! // moves from [3] to [5]
    }
    temp.next = null // Null termination, otherwise we'll get a circular List.
    return result.next
}

// Recursive solution.
fun swapPairsRec(head: ListNode?): ListNode? {
    if (head?.next == null) return head

    val current = head.next
    head.next = swapPairsRec(current!!.next)
    current.next = head
    return current
}
