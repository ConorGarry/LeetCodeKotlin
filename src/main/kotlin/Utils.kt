import TreeNodes.BinaryTree.TreeNode

/**
 * Build a LinkedList of [ListNode] from 1 or more Integers.
 * @param nums vararg of [Integer]
 */
fun buildLinkedList(vararg nums: Int): ListNode {
    val result = ListNode(-1)
    var current = result
    for (i in nums) {
        current.next = ListNode(i)
        current = current.next ?: break
    }
    return result.next!!
}

fun TreeNode.l(n: Int) {
    left = TreeNode(n)
}

fun TreeNode.r(n: Int) {
    right = TreeNode(n)
}

fun TreeNode.lr(n: Pair<Int, Int>) {
    val (l, r) = n
    left = TreeNode(l)
    right = TreeNode(r)
}

data class TreeWrap(
    var node: TreeNode,
)

/**
 * Build a Binary Tree from Array representation.
 * This can accommodate icomplete binary trees, hence the nullable Int? type.
 */
fun buildBinaryTree(nums: Array<Int?>): TreeNode? {

    fun createTree(node: TreeNode?, i: Int, arr: Array<Int?>) {
        if (node == null) return
        val iL = 2 * i + 1
        if (iL < arr.size) {
            if (arr[iL] != null)
                node.left = TreeNode(arr[iL]!!)
            createTree(node.left, iL, arr)
        }
        val iR = 2 * i + 2
        if (iR < arr.size) {
            if (arr[iR] != null)
                node.right = TreeNode(arr[iR]!!)
            createTree(node.right, iR, arr)
        }
    }
    if (nums.isEmpty()) return null
    val head = TreeNode(nums[0]!!)
    createTree(head, 0, nums)
    return head

    // Only works for complete.
    /*fun createTree(arr: Array<Int?>, root: TreeNode?, i: Int): TreeNode? {
        if (i >= arr.size) return root
        arr[i]?.run {
            val head = TreeNode(this)
            head.left = createTree(arr, head, 2 * i + 1)
            head.right = createTree(arr, head, 2 * i + 2)
            return head
        }
        return root
    }
    return createTree(nums, TreeNode(-1), 0)*/
}

fun insertLevelOrder(
    arr: IntArray, root: TreeNode,
    i: Int,
): TreeNode {
    // Base case for recursion
    var root = root
    if (i < arr.size) {
        val temp = TreeNode(arr[i])
        root = temp

        // insert left child
        root.left = insertLevelOrder(arr, root.left!!, 2 * i + 1)

        // insert right child
        root.right = insertLevelOrder(arr, root.right!!, 2 * i + 2)
    }
    return root
}

fun <T> swap(list: MutableList<T>, x: Int, y: Int) {
    val tmp = list[x]
    list[x] = list[y]
    list[y] = tmp
}

/**
 * Print 2D array to console.
 */
fun print2dArray(arr: Array<IntArray>) {
    for (i in arr.indices) {
        for (j in 0 until arr[0].size) {
            print(arr[i][j])
        }
        println()
    }
}

/**
 * Reverse an Integer's digits without requiring Char/String conversion.
 * e.g. 12345 becomes 54321.
 */
fun Int.reverse(): Int {
    var n = this // mutation.
    var result = 0
    while (n > 0) {
        result = result * 10 + (n % 10)
        n /= 10
    }
    return result
}

/**
 * Same as [Int.reverse] but with recursion.
 */
fun Int.reverseRec(): Int {
    var ans = 0
    fun rev(n: Int): Int =
        if (n < 0) 0
        else {
            ans = ans * 10 + (n % 10)
            rev(n / 10)
        }
    rev(this)
    return ans
}
