package datastructures

import java.util.*

interface Heap<T> : MutableCollection<T> {
    fun peek(): T?

    fun remove(): T?

    fun removeAt(i: Int): T?

    fun indexOf(t: T): Int?

    fun heapify(values: List<T>)
}

abstract class AbstractHeap<T> : Heap<T> {

    var elements: MutableList<T> = mutableListOf()

    private fun leftChildIndex(idx: Int) = (2 * idx) + 1

    private fun rightChildIndex(idx: Int) = (2 * idx) + 2

    private fun parentIndex(idx: Int) = (idx - 1) / 2

    // Api
    override fun add(element: T): Boolean {
        elements.add(element)
        siftUp(size - 1)
        return true
    }

    private fun siftUp(idx: Int) {
        var child = idx
        var parent = parentIndex(child)
        while (child > 0 && compare(elements[child], elements[parent]) > 0) {
            Collections.swap(elements, child, parent)
            child = parent
            parent = parentIndex(child)
        }
    }

    // A basic remove operation (no args) removes the root node from the heap.
    override fun remove(): T? {
        if (isEmpty()) return null

        Collections.swap(elements, 0, size - 1)
        val item = elements.removeAt(size - 1)
        siftDown(0)
        return item
    }

    private fun siftDown(idx: Int) {
        var parent = idx
        while (true) {
            val left = leftChildIndex(parent)
            val right = rightChildIndex(parent)
            var candidate = parent

            if (left < size &&
                compare(elements[left], elements[candidate]) > 0
            ) {
                candidate = left
            }
            if (right < size &&
                compare(elements[right], elements[candidate]) > 0
            ) {
                candidate = right
            }

            if (candidate == parent) {
                return
            }

            Collections.swap(elements, parent, candidate)
            parent = candidate
        }
    }

    override fun indexOf(t: T): Int? {
        TODO("searchForElement not yet implemented")
    }


    override fun removeAt(i: Int): T? {
        TODO("remove from arbitrary index not yet implemented")
    }

    override fun remove(element: T): Boolean =
        indexOf(element)?.run {
            removeAt(this) != null
        } ?: false


    override fun heapify(values: List<T>) {
        TODO("heapify not yet implemented")
    }

    //region Collections
    override fun addAll(elements: Collection<T>): Boolean {
        var anyAdded = false
        elements.forEach {
            if (add(it)) anyAdded = true
        }
        return anyAdded
    }

    override fun clear() {
        elements.clear()
    }

    override fun iterator(): MutableIterator<T> {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var anyRemoved = false
        elements.forEach {
            if (remove(it)) anyRemoved = false
        }
        return anyRemoved
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(element: T): Boolean = indexOf(element) != -1

    override fun containsAll(elements: Collection<T>): Boolean =
        elements.map { add(it) }.all { it }

    override fun isEmpty(): Boolean = elements.isEmpty()
    //endregion

    override fun peek(): T? = elements.firstOrNull()

    abstract fun compare(a: T, b: T): Int

    override val size: Int
        get() = elements.size
}

class ComparableHeapImpl<T>(
    private val comparator: Comparator<T>,
) : AbstractHeap<T>() {
    override fun compare(a: T, b: T): Int = comparator.compare(a, b)
}