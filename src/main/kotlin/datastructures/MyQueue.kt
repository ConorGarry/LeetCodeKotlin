import datastructures.DoublyLinkedList
import datastructures.RingBuffer

/**
 * This will be your starting point. From now on, everything you implement will obey
 * the contract of this interface, which describes the core operations for a queue.
 * The core operations for a queue are:
 * • enqueue
 * • dequeue
 * • isEmpty
 * • peek
 *
 * Notice that the queue only cares about removal from the front and insertion at the
 * back. You don’t need to know what the contents are in between. If you did, you’d
 * presumably use an array instead of a Queue.
 *
 * This will be implemented in four ways:
 *
 * • Using an array based list
 *
 * • Using a doubly linked list
 *
 * • Using a ring buffer
 *
 * • Using two stacks
 *
 */
interface IQueue<T> {

    // Inserts an element at the back of the queue and returns true if the operation is successful.
    fun enqueue(element: T): Boolean

    // Removes the element at the front of the queue and returns it.
    fun dequeue(): T?

    val count: Int

    // Checks if the queue is empty using the count property
    val isEmpty: Boolean
        get() = count == 0

    // Returns the element at the front of the queue without removing it.
    fun peek(): T?
}

/**
 * Enqueue is very fast, thanks to an O(1) append operation.
 *
 * There are some shortcomings to the implementation. Removing an item from the
 * front of the queue can be inefficient, as removal causes all elements to shift up by
 * one. This makes a difference for very large queues. Once the list gets full, it has to
 * resize and may have unused space. This could increase your memory footprint over time.
 *
 * enqueue  0(1)    0(1)
 *
 * dequeue  0(n)    0(n)
 *
 * space    0(n)    0(n)
 */
class ArrayListQueue<T> : IQueue<T> {

    private val list = mutableListOf<T>()

    // 0(1): Resizing is 0(n) when resize is required, but happens infrequently to is amortized to 0(1),
    override fun enqueue(element: T): Boolean = list.add(element)

    // 0(n): once first item is removed, other elements need to shift down one block in memory.
    override fun dequeue(): T? = list.removeFirstOrNull()

    override fun peek(): T? = list.firstOrNull()

    override val count: Int get() = list.size

    override fun toString(): String = list.toString()
}

/**
 * A doubly linked list is simply a linked list in which nodes also contain a reference to the previous node.
 *
 * This implementation is similar to ArrayListQueue, but instead of an ArrayList, you create a DoublyLinkedList.
 *
 * enqueue  0(1)    0(1)
 *
 * dequeue  0(1)    0(1)
 *
 * space    0(n)    0(n)
 *
 * With the linked list implementation, you reduced it to a constant
 * operation, O(1). All you needed to do was update the node’s previous and next
 * pointers.
 *
 * The main weakness with LinkedListQueue is not apparent from the table. Despite
 * O(1) performance, it suffers from high overhead. Each element has to have extra
 * storage for the forward and back reference. Moreover, every time you create a new
 * element, it requires a relatively expensive dynamic allocation. By contrast,
 * ArrayListQueue does bulk allocation, which is faster.
 */
class LinkedListQueue<T> : IQueue<T> {

    private val list = DoublyLinkedList<T>()

    // Seems they didn't implement a size for DoublyLinkedList. Maybe by design?
    private var size = 0

    /**
     * Behind the scenes, the doubly linked list will update its tail node’s previous and next
     * references to the new node. You also increment the size. This is an O(1) operation.
     */
    override fun enqueue(element: T): Boolean {
        list.append(element)
        size++
        return true
    }

    /**
     * This code checks to see if the first element of the queue exists. If it doesn’t, it returns
     * null. Otherwise, it removes and returns the element at the front of the queue. In this
     * case it also decrement the size.
     *
     * Removing from the front of the list is also an O(1) operation. Compared to the ArrayList
     * implementation, you didn’t have to shift elements one by one. Instead, you simply update
     * the next and previous pointers between the first two nodes of the linked list.
     */
    override fun dequeue(): T? {
        val first = list.first ?: return null
        size--
        return list.remove(first)
    }

    /**
     * Checking the state of a queue
     */
    override fun peek(): T? = list.first?.value

    override val count: Int
        get() = size

    @OptIn(ExperimentalStdlibApi::class)
    override fun toString(): String = buildList {
        var node = list.first
        while (node != null) {
            add(node.value)
            node = node.next
        }
    }.toString()
}

/**
 * @param size because this structure is fixed-size by design.
 *
 * enqueue  0(1)    0(1)
 *
 * dequeue  0(1)    0(1)
 *
 * space    0(n)    0(n)
 *
 * The ring-buffer-based queue has the same time complexity for enqueue and dequeue
 * as the linked-list implementation. The only difference is the space complexity. The
 * ring buffer has a fixed size, which means that enqueue can fail.
 */
class RingBufferQueue<T>(size: Int) : IQueue<T> {

    private val ringBuffer: RingBuffer<T> = RingBuffer(size)

    /**
     * To append an element to the queue, you call write() on the ringBuffer. This
     * increments the write pointer by one.
     * Since the queue has a fixed size, you must now return true or false to indicate
     * whether the element has been successfully added. enqueue() is still an O(1)
     * operation.
     */
    override fun enqueue(element: T): Boolean = ringBuffer.write(element)

    /**
     * If empty, returns null, else it returns an item from the front of the buffer.
     * Behind the scenes, the ring buffer increments the read pointer by one.
     */
    override fun dequeue(): T? {
        return if (isEmpty) null else ringBuffer.read()
    }

    override fun peek(): T? {
        return ringBuffer.first
    }

    override val count: Int
        get() = ringBuffer.count

    override fun toString(): String = ringBuffer.toString()
}

/**
 * Double-stack implementation.
 * Spatial locality is far superior to the linked list. It also doesn’t need a fixed size like a ring buffer.
 *
 * The idea behind using two stacks is simple. Whenever you enqueue an element, it goes in the right stack.
 * When you need to dequeue an element, you reverse the right stack and place it in the
 * left stack so that you can retrieve the elements using FIFO order.
 *
 * The transfer process is only repeated if/when the left stack is emptied, and the process continues.
 * Very clever and easy to understand once read through.
 *
 * Compared to the list-based implementation, by leveraging two stacks, you were able
 * to transform dequeue() into an amortized O(1) operation.
 *
 * Moreover, your two-stack implementation is fully dynamic and doesn’t have the
 * fixed size restriction that your ring-buffer-based queue implementation has.
 *
 * Finally, it beats the linked list in terms of spatial locality. This is because list
 * elements are next to each other in memory blocks. So a large number of elements
 * will be loaded in a cache on first access.
 *
 * enqueue  0(1)    0(1)
 *
 * dequeue  0(1)    0(1)
 *
 * space    0(n)    0(n)
 */
class StackQueue<T : Any> : IQueue<T> {
    // These would normally be private, scope is relaxed here to make them visible for test.
    val leftSack = Stack<T>()
    val rightStack = Stack<T>()

    override fun enqueue(element: T): Boolean {
        rightStack.push(element)
        return true
    }

    override fun dequeue(): T? {
        if (leftSack.isEmpty) transferElements()
        return leftSack.pop()
    }

    private fun transferElements() {
        while (!rightStack.isEmpty) {
            var next = rightStack.pop()
            while (next != null) {
                leftSack.push(next)
                next = rightStack.pop()
            }
        }
    }

    // While this peek() implementation might seem expensive, it’s amortized to O(1) because each
    // element in the queue only has to be moved from the right stack to the left stack once.
    // If you have a lot of elements in the right stack, calling peek() will be O(n) for just
    // that one call when it has to move all of those elements. Any further calls will be O(1) again.
    //
    // Note: You could also make peak() operations precisely O(1) for all calls if you
    // implemented a method in Stack that let you look at the very bottom of the
    // right stack. That’s where the first item of the queue is if they’re not all in the
    // left stack, which is what peek() should return in that case.
    override fun peek(): T? {
        if (leftSack.isEmpty) transferElements()
        return leftSack.peek()
    }

    override fun toString(): String {
        return "StackQueue(\nleftSack=\n$leftSack\nrightStack=\n$rightStack)"
    }

    override val count: Int
        get() = leftSack.count + rightStack.count

    // This means that there are no elements left to dequeue, and no new elements have been enqueued.
    override val isEmpty: Boolean
        get() = leftSack.isEmpty && rightStack.isEmpty


}