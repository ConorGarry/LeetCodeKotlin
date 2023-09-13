package sorting

import swap

/**
 * compare one less value than in the previous pass, so you shorten the
 * array by one with each pass.
 *
 * exit the sort as early as you can detect
 * the list is sorted.
 */
fun bubbleSort(list: MutableList<Int>) {
    // If empty or one element, no sort required.
    if (list.size < 2) return
    for (i in 0 until list.size) {
        var swapped = false
        // Every pass compares one less value, so array decrements by 1.
        for (j in 0 until list.size - i - 1) {
            if (list[j] > list[j + 1]) {
                swap(list, j, j + 1)
                swapped = true
            }
        }
        // Allows early exit if list is already sorted.
        if (!swapped) return
    }
}

// Bubble sort rec.
fun bubbleSortRec(list: MutableList<Int>, n: Int = list.size) {
    // If empty or one element, no sort required.
    if (list.size < 2) return
    for (i in 0 until n - 1) {
        if (list[i] > list[i + 1]) {
            swap(list, i, i + 1)
        }
        // Largest element sorted, recur for remaining elements.
        bubbleSortRec(list, n - 1)
    }
}