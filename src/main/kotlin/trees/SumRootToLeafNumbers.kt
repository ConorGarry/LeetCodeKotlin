package trees

import TreeNodes.BinaryTree.TreeNode


/**
 * 129. Sum Root to Leaf Numbers
 * Medium
 *
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 *
 * Each root-to-leaf path in the tree represents a number.
 *
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 *
 * Return the total sum of all root-to-leaf numbers.
 * Test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A leaf node is a node with no children.
 */
fun sumNumbers(root: TreeNode?): Int {
    fun dfs(node: TreeNode?, current: Int): Int {
        var cur = current
        if (node == null) return 0
        cur = (cur * 10) + node.`val`
        if (node.left == null && node.right == null) {
            return cur
        }
        return dfs(node.left, cur) +
                dfs(node.right, cur)
    }
    return dfs(root, 0)
}