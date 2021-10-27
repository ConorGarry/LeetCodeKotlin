package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 94. Binary Tree Inorder Traversal
 * Easy
 *
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 */
fun inorderTraversal(root: TreeNode?): List<Int> {
    var node: TreeNode? = root ?: return listOf()

    val stack = ArrayDeque<TreeNode>()
    val list = mutableListOf<Int>()

    while (node != null || stack.isNotEmpty()) {
        while (node != null) {
            stack.addLast(node)
            node = node.left
        }
        node = stack.removeLast()
        list.add(node.`val`)
        node = node.right
    }
    return list
}

// Recursive (slightly faster in Leetcode submission).
fun inorderTraversalRec(root: TreeNode?): List<Int> {
    return root?.run {
        inorderTraversalRec(root.left) + listOf(`val`) + inorderTraversalRec(root.right)
    } ?: emptyList()
}