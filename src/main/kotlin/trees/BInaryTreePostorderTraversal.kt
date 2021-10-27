package trees

import TreeNodes.BinaryTree.TreeNode
import java.util.*
import kotlin.collections.ArrayDeque

/**
 * 145. Binary Tree Postorder Traversal
 * Easy
 *
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 */
fun postorderTraversal(root: TreeNode?): List<Int> {
    val stack = ArrayDeque(listOf(root ?: return emptyList()))
    val list = LinkedList<Int>()

    while (stack.isNotEmpty()) {
        val node = stack.removeLast()
        list.addFirst(node.`val`)
        node.left?.run { stack.addLast(this) }
        node.right?.run { stack.addLast(this) }
    }
    return list
}

fun postorderTraversalRec(root: TreeNode?): List<Int> {
    return root?.run {
        postorderTraversalRec(left) + postorderTraversalRec(right) + listOf(`val`)
    } ?: listOf()
}