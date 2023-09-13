package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 701. Insert into a Binary Search Tree
 * Medium
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
 * Return the root node of the BST after the insertion.
 * It is guaranteed that the new value does not exist in the original BST.
 * Notice that there may exist multiple valid ways for the insertion,
 * as long as the tree remains a BST after insertion. You can return any of them.
 */
fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
    val nodeVal = root?.`val` ?: return TreeNode(`val`)
    if (`val` < nodeVal) {
        root.left = insertIntoBST(root.left, `val`)
    } else {
        root.right = insertIntoBST(root.right, `val`)
    }
    return root
}