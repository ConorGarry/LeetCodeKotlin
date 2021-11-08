package trees

import TreeNodes.BinaryTree.TreeNode
import buildBinaryTree

/**
 * Not in Leetcode, asked by Amazon and Microsoft.
 *
 * This problem was asked by Microsoft.
 *
 * Suppose an arithmetic expression is given as a binary tree.
 * Each leaf is an integer and each internal node is one of '+', '−', '∗', or '/'.
 *
 * Given the root to such a tree, write a function to evaluate it.
 *
 * For example, given the following tree:
 *      *
 *    /   \
 *   +     +
 *  / \    / \
 * 3   2   4  5
 *
 * You should return 45, as it is (3 + 2) * (4 + 5).
 */
fun main() {
    //println("Tree from: ${buildBinaryTree(arrayOf(1, 4, 2, 3))}")
    //println("Tree from: ${buildBinaryTree(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9))}")
    println("Tree from: ${buildBinaryTree(arrayOf(null, 2, 3, 5, 7, 8, 9))}")
   /* val tree = TreeNode(1)
        .apply {
            left = TreeNode(2)
                .apply { left = TreeNode(5); right = TreeNode(7)}
            right = TreeNode(3)
        }
    println("Tree: $tree")*/
    //inOrder(buildBinaryTree(arrayOf(1, 2, 3, 5, 7, 8, 9)))
}

/*fun inOrder(root: TreeNode?) {
    if (root == null) {
        println("null")
        return
    }
    inOrder(root.left)
    println(root.`val`)
    inOrder(root.right)
}*/
