package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 450. Delete Node in a BST
 * Medium
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 *
 *
 * 1. In the case in which the node is a leaf node, you simply return null, thereby
 *      removing the current node.
 * 2. If the node has no left child, you return node.rightChild to reconnect the right subtree.
 * 3. If the node has no right child, you return node.leftChild to reconnect the left subtree.
 * 4. This is the case in which the node to be removed has both a left and right child.
 *      You replace the node’s value with the smallest value from the right subtree. You
 *      then call remove on the right child to remove this swapped value.
 */
fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
    root ?: return null

    fun TreeNode.min(): TreeNode {
        return left?.min() ?: this
    }

    when {
        key == root.`val` -> {
            // Leaf node.
            if (root.left == null && root.right == null) return null

            // L or R each have no children. Return opposite node to reconnect the subtree.
            if (root.left == null) return root.right
            if (root.right == null) return root.left

            // Replace the node’s value with the smallest value from the right subtree.
            root.right?.min()?.`val`?.let {
                root.`val` = it
            }
            // Then call remove on the right child to remove this swapped value.
            root.right = deleteNode(root.right, root.`val`)
        }
        key < root.`val` ->
            root.left = deleteNode(root.left, key)
        else ->
            root.right = deleteNode(root.right, key)
    }
    return root
}
