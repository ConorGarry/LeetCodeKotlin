package dynamicprogramming

/**
 * 322. Coin Change
 * Medium
 *
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Neetcode notes:
 * top-down: recursive dfs, for amount, branch for each coin,
 * cache to store prev coin_count for each amount;
 *
 * bottom-up: compute coins for amount = 1, up until n,
 * using for each coin (amount - coin), cache prev values
 */
fun coinChange(coins: IntArray, amount: Int): Int {
    val dp = IntArray(amount + 1) {
        // Set first item to 0.
        if (it == 0) 0 else amount + 1
    }

    for (a in 1 until amount + 1) {
        for (c in coins) {
            if (a - c >= 0) {
                dp[a] = Math.min(dp[a], 1 + dp[a - c])
            }
        }
    }
    return if (dp[amount] != amount + 1) dp[amount] else -1
}