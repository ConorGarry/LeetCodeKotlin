package concepts

/**
 * ### A study of the Sliding Window Concept.
 *
 * The problem will involve a data structure that is ordered and iterable like an array or a string.
 * You are looking for some subrange in that array/string, like a longest, shortest or target value.
 * There is an apparent naive or brute force solution that runs in O(N²), O(2^N) or some other large time complexity.
 *
 * The biggest giveaway is that the thing you are looking for is often some kind of optimal,
 * like the longest sequence or shortest sequence of something that satisfies a given condition exactly.
 *
 * Most of the time they can be solved in O(N) time and O(1) space complexity.
 *
 * ### Types
 * - Fast / Slow
 * - Fast / Catchup
 * - Fast / Lagging
 * - Front / Back
 *
 * ### 1 Fast / Slow
 *
 * These have a fast pointer that grows your window under a certain condition.
 * So for Minimum Window Substring, you want to grow your window until you have a window
 * that contains all the characters you’re looking for, e.g. a valid String.
 *
 * It will also have a slow pointer, that shrinks the window. Once you find a
 * valid windo wwith the fast pointer, you want to start sliding the slow
 * pointer up until you no longer have a valid window.
 *
 * Examples: Bit flip, min window substring, consecutive sub-array sum.
 *
 *
 * ### 2 Fast / Catchup
 *
 * This is very similar to the first kind, except, instead of incrementing the slow pointer up,
 * you simply move it up the fast pointer’s location and then keep moving the fast pointer up.
 * It sort of “jumps” to the index of the fast pointer when a certain condition is met.
 * e.g. **Max Consecutive Sum** problem. The slow pointer “jumps” to the fast pointer’s
 * index when the current sum ends up being negative.
 * Again, you’re looking for some kind of optimum (ie the maximum sum).
 *
 * Examples: Max consecutive sum, buy/sell stocks.
 *
 *
 * ### 3 Fast / Lagging
 * This one is a little different, but essentially the slow pointer is simply referencing one
 * or two indices behind the fast pointer and it’s keeping track of some choice you’ve made.
 *
 * This one is a little different, but essentially the slow pointer is simply referencing one
 * or two indices behind the fast pointer and it’s keeping track of some choice you’ve made.
 *
 * Examples: House Robber.
 *
 * ### Front / back
 *
 * These are different because instead of having both pointers traveling from the front,
 * you have one from the front, and the other from the back. An example of this is the
 * Rainwater Problem where you are calculating the maximum amount of rainwater you can
 * capture in a given terrain. Again, you are looking for a maximum value, though the
 * logic is slightly different, you are still optimizing a brute force O(N²) solution.
 *
 * Examples: Rainwater, Sorted two sum.
 */
fun main() {
}
