package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Medium
 * Given two integer arrays preorder and inorder where preorder is the preorder
 * traversal of a binary tree and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 */
fun main() {
    println("buildTree: ${
        buildTree(
            intArrayOf(3, 9, 20, 15, 7),
            intArrayOf(9, 3, 15, 20, 7)
        )
    }")
}

// Every value will be unique.
// Pre-order, first will always be root. Same applies with subsequent steps.
// Everything to the right of root of inorder will be in left subtree.
fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty() || inorder.isEmpty()) return null
    //println("preOrder: ${preorder.toList()}, inOrder: ${inorder.toList()}")

    //println("preorder[0]: ${preorder[0]}")
    val root = TreeNode(preorder[0])
    val mid = inorder.indexOf(preorder[0])

    root.left = buildTree(
        preorder.copyOfRange(1, mid + 1),
        inorder.copyOfRange(0, mid)
    )
    root.right = buildTree(
        preorder.copyOfRange(mid + 1, preorder.size),
        inorder.copyOfRange(mid + 1, inorder.size)
    )
    return root
}

