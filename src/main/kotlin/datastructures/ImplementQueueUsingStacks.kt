package datastructures

/**
 * 232. Implement Queue using Stacks
 * Easy
 *
 * Implement a first in first out (FIFO) queue using only two stacks.The implemented queue
 * should support all the functions of a normal queue (push, peek, pop, and empty).
 */
fun main() {
    // Confirmed in Leetcode.
}
class MyQueue() {

    private val stackIn = ArrayDeque<Int>()
    private val stackOut = ArrayDeque<Int>()

    fun push(x: Int) {
        stackIn.add(x)
    }

    fun pop(): Int {
        if (stackOut.isEmpty())
            transfer()
        return stackOut.removeLast()
    }

    fun peek(): Int {
        if (stackOut.isEmpty())
            transfer()
        return stackOut.last()
    }

    fun empty(): Boolean =
        stackIn.isEmpty() && stackOut.isEmpty()

    private fun transfer() {
        while (stackIn.isNotEmpty()) {
            stackOut.add(stackIn.removeLast())
        }
    }
}
