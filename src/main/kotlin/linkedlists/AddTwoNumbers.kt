/**
 * ### 2. Add Two Numbers
 *
 * Given two linked lists of numbers, add the reverse of the concatenated
 * digits and return the results as a new LinkedLIst.
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 */
fun main() {
    println("Add two numbers: 243, 564: ${
        addTwoNumbers(
            buildLinkedList(2, 4, 3),
            buildLinkedList(5, 6, 4)
        )
    }")
}

// Elegant Solution
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val result = ListNode(-1)
    var current = result
    var p = l1
    var q = l2
    var sum = 0

    while (p != null || q != null) {
        sum += p?.`val` ?: 0
        p = p?.next
        sum += q?.`val` ?: 0
        q = q?.next

        current.next = ListNode(sum % 10)
        current = current.next!!

        // Carry the 1 (if required).
        sum = if (sum > 9) 1 else 0
    }

    // If there's an overall carry, one extra digit is required.
    if (sum > 0) {
        current.next = ListNode(sum)
    }
    return result.next
}

// My (embarrassing) brute-force attempt.
fun addTwoNumbersMine(l1: ListNode?, l2: ListNode?): ListNode? {

    // Recursively iterate and
    fun gatherNums(l: ListNode?, list: MutableList<Int>) {
        if (l == null) return
        list.add(l.`val`)
        gatherNums(l.next, list)
    }

    fun List<Int>.toReverseInt() =
        joinToString("")
            .reversed()
            .toBigInteger()

    val numString = mutableListOf<Int>()
    gatherNums(l1, numString)
    val list1 = listOf(*numString.toTypedArray()).toReverseInt()

    // Clear our digit storage and gather from second List.
    numString.clear()
    gatherNums(l2, numString)
    val list2 = listOf(*numString.toTypedArray()).toReverseInt()

    // Add and reverse.
    val result = list1 + list2
    val resultDigits: List<Int> = result.toString().reversed().map { it.digitToInt() }

    return buildLinkedList(*resultDigits.toIntArray())
}