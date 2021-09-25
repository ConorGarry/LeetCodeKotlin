/**
 * Playground.
 */
fun main() {
    linkedListRecursion()
}

private fun linkedListRecursion() {
    val list = buildLinkedList(1, 2, 3, 4, 5, 6, 7)
    println("list: $list")

    /**
     * Print backwards or forwards.
     */
    fun printListInPlace(node: ListNode?) {
        if (node == null) return

        // Iterative item first will result in backwards
        // printList(node.next)
        // println("out: ${node.`val`}")
        // or
        // Iterative after print will result in order.
        println("out: ${node.`val`}")
        printListInPlace(node.next)
    }

    // Will always print in order.
    fun printReturnList(node: ListNode?): String? {
        if (node == null) return null
        return "${node.`val`} -> ${printReturnList(node.next)}"
    }

    printListInPlace(list)
    printReturnList(list)

    /**
     * Recursive contains check.
     *
     * Iterative: O(n) time, O(1) space.
     * Recursive: O(n) time, O(n) space.
     */
    fun contains(node: ListNode?, target: Int): Boolean {
        if (node == null) return false // Item not in List.
        if (node.`val` == target) return true // Found item.
        return contains(node.next, target) // Recursive call.
    }

    println("contains 10: ${contains(list, 10)}")
    println("contains 2: ${contains(list, 2)}")

    /**
     * Sum up list.
     */
    fun sumList(node: ListNode?): Int {
        if (node == null) return 0 // Return is initial sum.
        return node.`val` + sumList(node.next)
    }
    println("sumUpList (should be 28): ${sumList(list)}")
}
