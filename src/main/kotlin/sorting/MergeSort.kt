package sorting

// Merge Sort
fun mergeSort(list: MutableList<Int>): List<Int> {
    if (list.size < 2) return list

    val mid = list.size / 2
    val left = mergeSort(list.subList(0, mid))
    val right = mergeSort(list.subList(mid, list.size))

    val result = mutableListOf<Int>()
    var l = 0
    var r = 0
    while (l < left.size && r < right.size) {
        val leftElement = left[l]
        val rightElement = right[r]

        if (leftElement < rightElement) {
            result.add(leftElement)
            l++
        } else if (leftElement > rightElement) {
            result.add(rightElement)
            r++
        } else {
            result.add(leftElement)
            l++
            result.add(rightElement)
            r++
        }
    }
    if (l < left.size) {
        result.addAll(left.subList(l, left.size))
    }
    if (r < right.size) {
        result.addAll(right.subList(r, right.size))
    }
    return result
}