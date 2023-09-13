package codility

/**
 * Time complexity: O(1).
 *
 * A small frog wants to get to the other side of the road.
 * The frog is currently located at position X and wants to get to a position
 * greater than or equal to Y. The small frog always jumps a fixed distance, D.
 *
 * Count the minimal number of jumps that the small frog must perform to reach its target.
 */
class FrogJmp {
    fun solution(X: Int, Y: Int, D: Int): Int {
        val distance = Y - X
        return if (distance % D == 0) distance / D else distance / D + 1
    }
}