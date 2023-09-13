package trees

import TreeNodes.BinaryTree.TreeNode

fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) return true

    fun isMirror(l: TreeNode?, r: TreeNode?): Boolean {
        if (l == null && r == null) return true
        return (l != null && r != null &&
                l.`val` == r.`val` &&
                isMirror(l.left, r.right) && isMirror(l.right, r.left))
    }
    return isMirror(root.left, root.right)
}