package dynamicprogramming

/**
 * 70. Climbing Stairs
 * Easy
 *
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Neetcode notes:
 * subproblem find (n-1) and (n-2), sum = n;
 *
 * By converting to a decision tree, we can see that work is repeated a lot.
 * If we do DFS, we can keep track of the sub-problems on the way back. Using cache and memoisation.
 *
 * With DP, we can start at the bottom, and work back. Called bottom-up pattern.
 */
fun climbStairs(n: Int): Int {
    // DP
    /*var one = 1
    var two = 1
    for (i in n - 1 downTo 0) {
        val tmp = one
        one+= two
        two = tmp
    }
    return one*/

    // DFS
    fun dfs(n: Int, map: MutableMap<Int, Int>): Int {
        if (map.containsKey(n)) return map[n]!!
        val res = dfs(n - 1, map) + dfs(n -2, map)
        map[n] = res
        return res
    }
    return dfs(n, mutableMapOf(0 to 1, 1 to 1))
}