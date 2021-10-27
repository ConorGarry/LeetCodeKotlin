class ListNode(
    var `val`: Int,
    var next: ListNode? = null
) {
    // Output as `1 -> 2 -> 3...etc`
    override fun toString(): String =
        next?.let {
            "$`val` -> $it"
        } ?: `val`.toString()
}

class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}

/**
 * Used for Interval questions.
 * Note this is from Lintcode, not sure if Leetcode uses this or some other tuple (array?).
 */
data class Interval(
    val start: Int,
    val end: Int,
)

class TreeNodes {
    class BinaryTree {
        class TreeNode(
            var `val`: Int,
            var left: TreeNode? = null,
            var right: TreeNode? = null,
        ) {

            override fun toString() = "\n" + diagram(this)
            private fun diagram(
                node: TreeNode?,
                top: String = "",
                root: String = "",
                bottom: String = "",
            ): String {
                return node?.let {
                    if (node.left == null && node.right == null) {
                        "$root${node.`val`}\n"
                    } else {
                        diagram(node.right, "$top ", "$top┌──", "$top│ ") +
                                root + "${node.`val`}\n" + diagram(node.left,
                            "$bottom│ ", "$bottom└──", "$bottom ")
                    }
                } ?: "${root}null\n"
            }
        }
    }
}

