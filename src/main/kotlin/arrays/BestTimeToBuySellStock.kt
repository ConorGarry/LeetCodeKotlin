/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * 75Q solution:
 * find local min and search for local max, sliding window;
 *
 */
fun main() {
    listOf(
        intArrayOf(7, 1, 5, 3, 6, 4) to 5,
        intArrayOf(7, 6, 4, 3, 1) to 0,
    ).forEach { (arr, t) ->
        println("bestTimeToBuyStock for ${arr.toList()}, expects: $t: ${maxProfit(arr)}")
    }
}

// Sliding Window, fast / catch-up?
fun maxProfit(prices: IntArray): Int {
    var buy = 0
    var profit = 0
    for (sell in prices.indices) {
        if (prices[buy] < prices[sell]) {
            profit = Math.max(profit, prices[sell] - prices[buy])
        } else {
            buy = sell
        }
    }
    return profit
}

// brute force O(n2). Time limit exceeded.
/*
fun maxProfit(prices: IntArray): Int {
    var profit = 0
    for (buy in prices.indices) {
        for (sell in buy + 1 until prices.size) {
            val diff = prices[sell] - prices[buy]
            if (diff > 0) {
                profit = Math.max(profit, prices[sell] - prices[buy])
            }
        }
    }
    return profit
}*/
