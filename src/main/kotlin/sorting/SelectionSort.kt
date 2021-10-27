package sorting

import swap

// Selection Sort

/**
 * Requires less swap operations than Bubble Sort.
 *
 * Omit last as it will be in order once the others are sorted.
 * ```
 * for (i in 0 until list.size - 1) {
 * ```
 * has a worst and average time complexity of O(n²),
 * which is fairly dismal. Unlike the bubble sort, it also has the best time complexity of
 * O(n²). Despite this, it performs better than bubble sort because it performs only O(n)
 * swaps — and the best thing about it is that it’s a simple one to understand.
 */

// Iterative
fun selectionSort(list: MutableList<Int>) {
    if (list.size < 2) return

    for (i in 0 until list.size - 1) {
        var lowest = i
        // Go through remainder until we find lowest value.
        for (j in i + 1 until list.size) {
            if (list[lowest] > list[j]) {
                lowest = j
            }
        }
        if (lowest != i) {
            swap(list, lowest, i)
        }
    }
}

/**
 *
 * Maintains two parts
 * First part that is already sorted
 * Second part that is yet to be sorted.
 *
 * The algorithm works by repeatedly finding the minimum element
 * (considering ascending order) from unsorted part and putting it at the end of sorted part.
 */
// Recursive
fun selectionSortRec(list: MutableList<Int>, i: Int = 0, n: Int = list.size) {
    // Base case, check there are still elements to sort.
    if (i == n) return

    // Min element of unsorted array.
    var min = i
    for (j in i + 1 until n) {
        if (list[j] < list[min]) {
            min = j
        }
    }

    // Swap min element in subarray `list[i..n-1]` with `list[i]`
    swap(list, min, i)
    // Recursive call
    selectionSortRec(list, i + 1, n)
}