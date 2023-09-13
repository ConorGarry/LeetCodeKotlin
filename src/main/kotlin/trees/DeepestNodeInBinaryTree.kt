package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * Given a binary tree, find the deepest node in it.
 * Medium. GeeksForGeeks.
 */
fun main() {
    val tree = TreeNode(
        4,
        TreeNode(2, TreeNode(1), TreeNode(3)),
        TreeNode(5, TreeNode(6, TreeNode(0)), TreeNode(7))
    )

    println("tree: $tree")

    println("deepestNode: ${deepestNode(tree)}")

    // TODO: This doesn't work as I expect it to. 0 Should really be the deepest.
}

// Auxiliary wrapper.
private class Result(
    var data: Int = -1,
    var level: Int = 0,
    var maxLevel: Int = -1,
) {
    override fun toString(): String {
        return "Result(data=$data, level=$level, maxLevel=$maxLevel)"
    }
}

private fun find(node: TreeNode?, res: Result) {
    if (node == null) return
    println("res: $res")
    res.level++
    find(node.left, res)
    if (res.level > res.maxLevel) {
        res.data = node.`val`
        res.maxLevel = res.level
    }
    find(node.right, res)
    println("== res: $res")
}

fun deepestNode(root: TreeNode?): Int {
    val res = Result()
    find(root, res)
    return res.data
}
