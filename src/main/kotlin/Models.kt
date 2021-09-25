class ListNode(var `val`: Int) {
    var next: ListNode? = null

    // Output as `1 -> 2 -> 3...etc`
    override fun toString(): String =
        next?.let {
            "$`val` -> $it"
        } ?: `val`.toString()
}