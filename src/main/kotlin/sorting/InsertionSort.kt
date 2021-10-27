package sorting

import swap

// Insertion Sort.

// Iterative
fun insertionSort(list: MutableList<Int>) {
    if (list.size < 2) return

    for (i in 1 until list.size) {
        for (j in i downTo 1) {
            if (list[j] < list[j - 1]) {
                swap(list, j, j - 1)
            } else {
                break
            }
        }
        println("insertion pass: $list")
    }
}

// Recursive
fun insertionSortRec(list: MutableList<Int>, n: Int = list.size) {
    // Base case.
    if (n <= 1) return

    // Sort first n-1 elements.
    insertionSortRec(list, n - 1)

    // Insert last element at its correct position in sorted array.
    val last = list[n - 1]
    var j = n - 2

    // Move elements of arr[0..i-1], that are greater than key,
    // to one position ahead of their current position
    while (j >= 0 && list[j] > last) {
        list[j + 1] = list[j]
        j--
    }
    list[j + 1] = last
}