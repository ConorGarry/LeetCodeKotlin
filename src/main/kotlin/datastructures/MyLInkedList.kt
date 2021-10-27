/*
package datastructures

import MyLinkedList.Node

*/
/**
 * Data Structures and Algorithms in Kotlin.
 *
 * Chapter 3: LinkedList.
 *
 * @sample [MyLinkedList]]
 *//*

class MyLinkedList<T> : MutableCollection<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var _size: Int = 0

    class Node<T>(
        var value: T,
        var next: Node<T>? = null,
        var previous: Node<T>? = null, // Prob not a LinkedList concept?
    ) {
        override fun toString(): String =
            next?.let {
                "$value -> $it"
            } ?: value.toString()
    }

    //region Add values
    */
/**
     * "Head-first insertion"
     *
     * Adds [value] to the start of the LinkedList.
     * Previous head becomes the `next` of this Node.
     * If tail is null, it means our new value is the only element, therefor also the tail.
     * [_size] gets incremented.
     *//*

    fun push(value: T) {
        head = Node(value, next = head)
        if (tail == null) {
            tail = head
        }
        _size++
    }

    */
/**
     * "Tail-end insertion"
     *
     * If nothing in list, delegate to push.
     * If
     *//*

    fun append(value: T) {
        if (isEmpty()) {
            push(value)
            return // So no other later increments affect the size.
        }

        // Seeing as isEmpty is false, there _must_ be a tail.
        with(requireNotNull(tail) { "Tail not set!" }) {
            next = Node(value)
            tail = next
            _size++
        }
    }

    */
/**
     * If [afterNode] happens to be the last Node, just append and return.
     *
     * Create a new node where the next of the [afterNode] is now the next of the new Node.
     * Set the next of the [afterNode] to be the new Node.
     *//*

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        if (tail == afterNode) {
            append(value)
            return requireNotNull(tail)
        }
        val newNode = Node(value, afterNode.next)
        afterNode.next = newNode
        _size++
        return newNode
    }
    //endregion

    */
/**
     * Move head down a Node, effectively removing the first Node, and return its value.
     * Garbage Collector will then remove it from memory after function call has finished.
     *//*

    */
/* fun pop(): T? { // AKA removeFirst.
         if (isEmpty()) return null
         _size--
         if (isEmpty()) tail = null

         return with(requireNotNull(head) { "There should be a head Node!" }) {
             head = next // Move head down a Node.
             value // Return value
         }
     }*//*


    fun pop(): T? { // AKA removeFirst.
        val result = head?.value ?: return null
        head = head!!.next
        _size--
        if (isEmpty()) tail = null
        return result
        // Book version
        */
/*val popped = head ?: return null
        _size--
        if (isEmpty()) tail = null
        head = popped.next // Move head down a Node.
        return popped.value // Return value*//*

    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value ?: return null
        _size--

        // Special care needs to be taken if the removed node is the tail
        // node since the tail reference will need to be updated.
        if (node.next == tail) {
            tail = node
        }
        // Move reference onto following node.
        node.next = node.next!!.next
        return result
    }

    // Not in book, but would be a good exercise.
    fun removeAt(idx: Int): T? {
        TODO()
    }

    */
/**
     * We need a reference to the second last Node to do this operation.
     * Note: The book suggests a traversal right until the end, but we could just use [nodeAt]
     * to get second last item.
     *
     * 1. If head is null, there’s nothing to remove, so you return null.
     * 2. If the list only consists of one node, removeLast is functionally equivalent to
     *      pop. Since pop will handle updating the head and tail references, you can
     *      delegate this work to the pop function.
     * 3. At this point, you know that you’ll be removing a node, so you update the size of
     *      the list accordingly.
     * 4. You keep searching for the next node until current.next is null. This signifies
     *      that current is the last node of the list.
     * 5. Since current is the last node, you disconnect it using the prev.next reference.
     *      You also make sure to update the tail reference.
     *//*

    fun removeLast(): T? {
        // TIL this kind of shadowing!
        // But for readability, I won't use it, will use first instead.
        // https://stackoverflow.com/a/49688168/3429021
        //val head = head ?: return null
        //this.head
        val head = head ?: return null
        if (head.next == null) return pop()
        _size--

        // At this stage we know size is at least 1.
        val secondLast = nodeAt(size - 1) ?: return null
        tail = secondLast
        return requireNotNull(secondLast.next).value

        // Book version.
        */
/*var prev = head
        var current = head

        var next = current.next
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }
        // We have reached the end.
        prev.next = null
        tail = prev
        return current.value*//*

    }

    */
/**
     * Nodes can only be access via the head node, so we must traverse manually from there.
     * (This is what makes LinkedLists inefficient for arbitrary gets).
     *//*

    fun nodeAt(idx: Int): Node<T>? {
        var current = head
        var currentIndex = 0
        while (current != null && currentIndex < idx) {
            current = current.next
            currentIndex++
        }
        return current
    }

    override fun iterator(): MutableIterator<T> =
        object : MutableIterator<T> {

            private var index = 0
            private var last: Node<T>? = null

            override fun hasNext(): Boolean {
                return index < size
            }

            */
/**
             *
             * ```
             * if (index >= size) throw IndexOutOfBoundsException()
             * ```
             * Should never be the case of no elements if clients use the Iterator correctly,
             * always checking with hasNext() before trying to read a value from it with next().
             *
             * ```
             * last = if (index == 0) head else last?.next
             * ```
             * If this is the first iteration, there is no lastNode set, so you take the first node.
             *
             * ```
             * index++
             * ```
             * Since the lastNode property was updated, you need to update the index too.
             * You’ve now gone through one more iteration, so the index increments.
             *//*

            override fun next(): T {
                if (index >= size) throw IndexOutOfBoundsException()
                last =
                    if (index == 0) head else last?.next // We are either starting or resuming, so next has to be valid either way.
                index++
                return last?.value ?: error("Last Node should have a value!")
            }

            */
/**
             * [MutableIterable] implementation.
             * This is the only difference between [MutableIterable] and [Iterable]
             *
             * ```
             * if (index == 1) pop()
             * ```
             * If you’re at the beginning of the list. Using pop() will do.
             *
             * ```
             * val prevNode = nodeAt(index - 2) ?: return
             * ```
             * If the iterator is somewhere inside the list, it needs to find the previous node.
             * That’s the only way to remove items from a linked list.
             *
             * ```
             * removeAfter(prevNode)
             * last = prevNode
             * ```
             * The iterator needs to step back so that next() returns the correct method the
             * next time. This means reassigning the lastNode and decreasing the index.
             *
             *//*

            override fun remove() {
                if (index == 1) {
                    pop()
                } else {
                    // minus 2 because we need the node previous to the one we want to remove.
                    val prevNode = nodeAt(index - 2) ?: return
                    removeAfter(prevNode)
                    last = prevNode
                }
                index--
            }
        }

    // or
    // var size = 0
    //  private set
    override val size: Int
        get() = _size


    override fun contains(element: T): Boolean {
        for (item in this) {
            // Iterator removes the value, not the node, so we can direclty check equality.
            if (item == element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        elements.forEach {
            // Fail fast, detect false first instead of true (opposite of contains).
            if (!contains(it)) return false
        }
        return true
    }

    //region MutableCollection

    // Since the LinkedList doesn’t have a fixed size, add() and addAll()
    // are always successful and need to return true.

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (e in elements) {
            append(e)
        }
        return true
    }

    // GC will take care of the rest after this unlinking is done.
    override fun clear() {
        head = null
        tail = null
        _size = 0
    }

    */
/**
     * ```
     * val iterator = iterator()
     * ```
     * Get an iterator that will help you iterate through the collection.
     *
     * ```
     * while (iterator.hasNext()) {
     *  val item = iterator.next()
     *  ...
     * }
     * ```
     * Create a loop that checks if there are any elements left, and gets the next one.
     *
     * ```
     * if (element == item) {
     *  iterator.remove()
     *  return true
     * }
     * ```
     * Check if the current element is the one you’re looking for, and if it is, remove it.
     * Return a boolean that signals if an element has been removed.
     *//*

    override fun remove(element: T): Boolean {
        val iterator = iterator()

        while (iterator.hasNext()) {
            val item = iterator.next()
            if (element == item) {
                iterator.remove()
                return true
            }
        }
        return false

        // Idiomatic version.
        */
/*with(iterator()) {
                while (hasNext()) {
                    val item = next()
                    if (item == element) {
                        remove()
                        return true
                    }
                }
                return false
            }*//*

    }

    */
/**
     * [removeAll] and [retainAll] are not fail-fast, hence requiring a mutable var boolean.
     * They need to iterate to the end in each case so are 0(n) complexity.
     *//*


    */
/**
     * With removeAll(), you can make use of remove().
     * The return value of removeAll is true if any elements were removed.
     *//*

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (e in elements) {
            result = remove(e) || result
        }
        return result
    }

    */
/**
     * Remove any elements in the list besides the ones passed in as the parameter.
     *//*

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }
    //endregion

    override fun isEmpty(): Boolean = size == 0

    override fun toString(): String = if (isEmpty()) "Empty List" else head.toString()
}

*/
/**
 * Challenges
 * 1. Print reverse LinkedList. recurse and iterative.
 * 2. Middle item
 * 3. Reverse LinkedList. recursive and iterative.
 * 4. Merge two LinkedLists.
 *//*


*/
/**
 * Print out the elements of a linked list in reverse order.
 *
 * ```
 * 1 -> 2 -> 3
 * ```
 * should print out the following:
 * ```
 * 3
 * 2
 * 1
 * ```
 *//*

class Challenges {

    fun <T> printLinkedListReverse(list: MyLinkedList<T>): String {
        return printLinkedListReverse(list.nodeAt(0))
    }

    fun <T> printLinkedListReverse(node: Node<T>?): String {
        var prev: Node<T>? = null
        var current = node
        val reversed = MyLinkedList<T>()
        while (current != null) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
            prev.value?.let { reversed.push(it) }
        }
        return reversed.toString()
    }

    */
/*fun <T> reverseList(list: MyLinkedList<T>): MyLinkedList<T> {
        val reversed = MyLinkedList<T>()

        var prev: Node<T>?
        var current = list.nodeAt(0)
        while (current != null) {
            val next = current.next
            current.next = next
            prev = current
            current = next
            prev.value?.run { reversed.push(this) }
        }
        return reversed
    }*//*


    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }


    //region Leetcode.
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
    //endregion


    fun reverseLinkedList() {
        val listNums = MyLinkedList<Int>().apply {
            add(3)
            add(2)
            add(1)
        }

        */
/**
         * As you'd expect, this function calls itself on the next node. The terminating
         * condition is somewhat hidden in the null-safety operator. If the value of next is
         * null, the function stops because there’s no next node on which to call
         * printInReverse().
         *//*

        */
/*fun <T> MyLinkedList.Node<T>.printReverse() {
            println("printReverse")
            next?.printReverse()
            println(value)
        }*//*


        // Recursive, non ext. function.
        fun <T> printReverseRec(node: Node<T>?) {
            // This wil traverse until we get to the last Node.
            // Then calls will reverse down the stack.
            if (node != null) printReverseRec(node.next)
            println(node?.value)
        }

        // In the end, the current pointer will be null, and the previous pointer
        // will be the last element of the old linked list.
        fun <T> printReverse(node: Node<T>?) {
            var prev: Node<T>? = null
            var current: Node<T>? = node
            while (current != null) {
                val next = current.next
                current.next = prev // Prev will be null on first run.
                prev = current
                current = next
            }

            var nextPrint = prev?.next
            while (nextPrint != null) {
                print("$nextPrint ".trimIndent())
                nextPrint = nextPrint.next
            }
        }

        fun MyLinkedList<Int>.printReverse() {
            */
/*nodeAt(0)?.printReverse()*//*

            */
/*printReverseRec(nodeAt(0))*//*

            printReverse(nodeAt(0))
        }

        println(listNums)
        listNums.printReverse()
    }

    */
/**
     * Runner technique!
     *
     * Two references traverse down the nodes of the list
     * where one is twice as fast as the other.
     * Once the faster reference reaches the end,
     * the slower reference will be in the middle.
     *//*

    fun <T> itemInMiddle(list: MyLinkedList<T>): Node<T>? {
        var slow = list.nodeAt(0)
        var fast = slow?.next

        while (fast != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow

        */
/* Seems slightly faster.
        var slow = head ?: return null
        var fast = slow.next

        while (fast != null) {
            fast = fast.next
            if (fast != null) {
                fast = fast.next
            }
            slow = slow?.next
        }
        return slow*//*


        */
/* Original
        var slow = list.nodeAt(0)
        var fast = list.nodeAt(0)

        while (fast != null) {
            fast = fast.next
            if (fast != null) {
                fast = fast.next
                slow = slow?.next
            }
        }
        return slow*//*

    }
}

// [1] -> [2] -> [3] -> {null}

// O(n)?
fun reverseListRec(head: ListNode?): ListNode? {
    // head == null = no Nodes.
    // head.next == null = single or last Node.
    if (head?.next == null) return head

    val node = reverseListRec(head.next)
    // Start at the second from last, work backwards.
    head.next?.next = head
    head.next = null
    return node
}

*/
/**
 * You start with the first node of each list.
 *
 * The while loop continues until one of the lists reaches its end.
 *
 * You compare the first nodes left and right to append to the result.
 * Since this loop depends on both left and right, it will terminate even if there are
 * nodes left in either list.
 *
 * TODO: See if there's a more Kotlin idiomatic solution.
 *//*

fun <T : Comparable<T>> MyLinkedList<T>.mergeSorted(
    other: MyLinkedList<T>,
): MyLinkedList<T> {
    if (isEmpty()) return other
    if (other.isEmpty()) return this

    val result = MyLinkedList<T>()
    var left = nodeAt(0)
    var right = other.nodeAt(0)
    while (left != null && right != null) {
        if (left.value < right.value) {
            left = with(left) { result.append(value); next }
        } else {
            right = with(right) { result.append(value); next }
        }
    }
    while (left != null) {
        left = with(left) { result.append(value); next }
    }
    while (right != null) {
        right = with(right) { result.append(value); next }
    }
    return result

    */
/* Kotlin Idiomatic.
    if (isEmpty()) return other
    if (other.isEmpty()) return this

    val result = MyLinkedList<T>()
    var left = nodeAt(0)
    var right = other.nodeAt(0)
    while (left != null && right != null) {
        if (left.value < right.value) {
            left = with(left) { result.append(value); next }
        } else {
            right = with(right) { result.append(value); next }
        }
    }
    while (left != null) {
        left = with(left) { result.append(value); next }
    }
    while (right != null) {
        right = with(right) { result.append(value); next }
    }
    return result*//*

}

// Sorted in order.
fun mergeLinkedLists(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) return l2
    if (l2 == null) return l1
    val result = ListNode(-1)
    var temp = result
    var head1 = l1;
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

    if (head1 != null) temp.next = head1
    if (head2 != null) temp.next = head2
    return result.next

    */
/* Works but maybe too clever to explain.
    if (l1 == null) return l2
    if (l2 == null) return l1

    val merged: ListNode
    var temp: ListNode?
    var head1: ListNode?
    var head2: ListNode?

    if (l1.`val` <= l2.`val`) {
        head1 = l1
        head2 = l2
    } else {
        head1 = l2
        head2 = l1
    }

    merged = head1

    while (head1 != null && head2 != null) {
        if (head1.next != null && (head1.next!!.`val` <= head2.`val`)) {
            head1 = head1.next!!
        } else {
            temp = head1.next
            head1.next = head2
            head1 = head1.next
            head2 = temp
        }
    }
    return merged*//*


    */
/* Brute force-ish.
    if (l1 == null) return l2
    if (l2 == null) return l1
    val merged = ListNode(-1)
    var temp: ListNode = merged
    // Kotlin parameters are var, not val!
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

    // While head1 is not null
    while (head1 != null) {
        temp.next = head1
        head1 = head1.next
        temp = temp.next!!
    }

    // While head2 is not null
    while (head2 != null) {
        temp.next = head2
        head2 = head2.next
        temp = temp.next!!
    }
    return merged.next*//*

}

// Recursive. Simpler?
fun sortedMerge(l1: ListNode?, l2: ListNode?): ListNode? {
    val result: ListNode
    if (l1 == null) return l2
    if (l2 == null) return l1

    if (l1.`val` <= l2.`val`) {
        result = l1
        result.next = sortedMerge(l1.next, l2)
    } else {
        result = l2
        result.next = (sortedMerge(l1, l2.next))
    }
    return result
}

fun <T> MyLinkedList<T>.reverseRec(): MyLinkedList<T> {
    val reversed = MyLinkedList<T>()

    fun reverseNodes(node: Node<T>?): Node<T>? {
        // Recursion terminals.
        if (node == null) return null
        if (node.next == null) return node

        val returnedNode: Node<T>? = reverseNodes(node.next)
        node.next!!.next = node
        node.next = null
        reversed.add(node.value!!)
        return returnedNode
    }
    println("reversed: $reversed")
    */
/*reversed.add(reverseNodes(nodeAt(0))!!.value!!)*//*

    return reversed
}

// Leetcode 148: Sorted List
// Given the head of a linked list,
// return the list after sorting it in ascending order.
class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String =
        next?.let {
            "$`val` -> $it"
        } ?: `val`.toString()
}


*/
/*
    [head] -> [next] ->
 *//*


*/
/**
 * This is the official way to get the middle of a LinkedList.
 *//*

fun getMiddle(head: ListNode?): ListNode? {
    var slow = head
    var fast = head?.next
    while (fast != null) {
        slow = slow?.next
        fast = fast.next?.next
    }
    return slow
}

*/
/**
 * This is slightly different to getMiddle, it splits the List in half.
 * NOTE: This is the version required for merge sorts!
 *//*

fun splitLinkedList(head: ListNode?): ListNode? {
    var slow = head;
    var fast = head?.next
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next!!.next
    }
    return slow
}

fun mergeSortList(head: ListNode?): ListNode? {
    // 0 or 1 element.
    if (head?.next == null) return head

    // Divide.
    val middle = splitLinkedList(head) ?: return null
    val nextMiddle = middle.next
    if (middle.next == null) return head
    middle.next = null

    val left = mergeSortList(head)
    val right = mergeSortList(nextMiddle)
    return mergeLinkedLists(left, right)
}

*/
/*
fun leetCodeSortList(head: ListNode?): ListNode? {
    // 0 or 1 element.
    if (head?.next == null) return head

    // Divide.
    var slow = head; var fast = head.next
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next!!.next
    }
    val nextOfMiddle = slow?.next
    slow?.next = null
    if (head?.next == null) return head

    // Divide.
    var slow = head; var fast = head.next
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next!!.next
    }
    val nextOfMiddle = slow?.next
    slow?.next = null

    var left = leetCodeSortList(head)
    var right = leetCodeSortList(nextOfMiddle)

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

    if (left != null) temp.next = left
    if (right != null) temp.next = right
    return result.next
}
*//*



// Iterative. Inefficient
fun sortList(head: ListNode?): ListNode? {
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

fun main() {
    */
/*val challenges = Challenges()
    challenges.reverseLinkedList()*//*


    // sorted list
    // 4,2,1,3
    val sortList = ListNode(4)
        .apply {
            next = ListNode(2)
                .apply {
                    next = ListNode(1)
                        .apply {
                            next = ListNode(3)
                                .apply {
                                    next = ListNode(7)
                                        .apply {
                                            next = ListNode(9)
                                                .apply { next = ListNode(3) }
                                        }
                                }
                        }
                }
        }
    */
/*sortList(sortList)*//*

    */
/*println("sortList: $sortList")*//*

    */
/*println("nthLast 2: ${nthLast(sortList, 2)}")*//*


    */
/*val smallList = ListNode(1).apply { next = ListNode(2) }
    println("smallList: $smallList")
    println("removeNthLast 2: ${removeNthLast(smallList, 2)}")*//*

    */
/*println("removeDuplicates: ${removeDuplicates(sortList)}")*//*


    */
/*println("mergeSortList: ${mergeSortList(sortList)}")*//*

    */
/*println("mergeSortList: ${leetCodeSortList(sortList)}")*//*


    findBoxInMatrix()
}

// tortoise and hare algorithm (AKA runner).
fun nthLast(head: ListNode?, n: Int): ListNode? {
    var fast: ListNode? = head ?: return null
    var slow = head

    // Head start, move fast up to n, then resume traversal with the window created.
    for (i in 0 until n) {
        fast = fast?.next
    }

    while (fast?.next != null) {
        fast = fast.next
        slow = slow?.next
    }
    return slow
}

fun removeNthLast(head: ListNode?, n: Int): ListNode? {
    var fast: ListNode? = head ?: return null
    var slow = fast

    for (i in 0 until n) {
        fast = fast?.next
    }

    // Boundary case, returning first node.
    if (fast == null) {
        return head.also { it.next = it.next?.next }
    }

    while (fast?.next != null) {
        fast = fast.next
        slow = slow?.next
    }

    // Unlink
    slow?.next = slow?.next?.next
    return head
}

// Similar iteration pattern to reverse list.
fun removeDuplicates(head: ListNode?): ListNode? {
    var current: ListNode? = head ?: return null
    var prev: ListNode? = null

    val set = mutableSetOf<Int>()

    while (current != null) {
        val curVal = current.`val`

        if (set.contains(curVal)) {
            prev?.next = current.next
        } else {
            set.add(curVal)
            prev = current
        }
        current = current.next
    }
    return head
}

fun findBoxInMatrix() {

    val image = arrayOf(
        intArrayOf(1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 0, 0, 0, 1),
        intArrayOf(1, 1, 1, 0, 0, 0, 1),
        intArrayOf(1, 1, 1, 0, 0, 0, 1)
    )

    fun topLeft(arr: Array<IntArray>): List<Pair<Int, Int>> {
        val coords = mutableListOf<Pair<Int, Int>>()
        for (i in image.indices) {
            for (j in 0 until image[0].size) {
                if (image[i][j] == 0) {
                    coords.add(j to i)
                }
            }
        }
        return coords
    }

    fun captureCoors(arr: Array<IntArray>, i: Int, j: Int): Pair<Int, Int> {
        var colStart = j
        var j = j
        var i = i
        val start = i to j

        while (j < arr[0].size && arr[i][j] == 0) {
            arr[i][j] = 1
            j++
        }
        val colEnd = --j
        i++
        j = colStart
        while (i < arr.size && arr[i][j] == 0) {
            while (j <= colEnd) {
                arr[i][j] = 1
                j++
            }
            j = colStart
            i++
        }
        return --i to colEnd
    }
    val (x, y) = topLeft(image)

    */
/*println(captureCoors(topLeft(image), ))*//*

}*/
