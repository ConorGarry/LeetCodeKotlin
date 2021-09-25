/**
 * Build a LinkedList of [ListNode] from 1 or more Integers.
 * @param nums vararg of [Integer]
 */
fun buildLinkedList(vararg nums: Int): ListNode {
    val result = ListNode(-1)
    var current = result
    for (i in nums) {
        current.next = ListNode(i)
        current = current.next ?: break
    }
    return result.next!!
}