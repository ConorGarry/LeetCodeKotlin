package sorting

/**
 * Quicksort is another comparison-based sorting algorithm. Much like merge sort, it
 * uses the same strategy of divide and conquer. One important feature of quicksort is
 * choosing a pivot point. The pivot divides the list into three partitions:
 *      [ elements < pivot | pivot | elements > pivot ]
 *
 * 1. There must be more than one element in the list. If not, the list is considered
 * sorted.
 * 2. Pick the middle element of the list as your pivot.
 * 3. Using the pivot, split the original list into three partitions. Elements less than,
 * equal to or greater than the pivot go into different buckets.
 * 4. Recursively sort the partitions and then combine them.
 */

// Simple recursive solution. Picks the middle element of the list as pivot.
fun <T : Comparable<T>> quickSort(list: List<T>): List<T> {
    if (list.size < 2) return list

    val pivot = list[list.size / 2]
    val less = list.filter { it < pivot }
    val equal = list.filter { it == pivot }
    val more = list.filter { it > pivot }
    return quickSort(less) + equal + quickSort(more)
}

// Less recursion, uses manual partition
fun quickSortIt(arr: IntArray) {

    fun partition(arr: IntArray, l: Int, r: Int): Int {
        var left = l
        var right = r
        val pivot = arr[(l + r) / 2]

        while (left <= right) {
            while (arr[left] < pivot) left++
            while (arr[right] > pivot) right--

            if (left <= right) {
                val tmp = arr[right]
                arr[right] = arr[left]
                arr[left] = tmp
                left++
                right--
            }
        }
        return left
    }

    fun quickSort(arr: IntArray, l: Int, r: Int) {
        val index = partition(arr, l, r)
        if (l < index - 1) {
            quickSort(arr, l, index - 1)
        }
        if (index < r) {
            quickSort(arr, index, r)
        }
    }

    quickSort(arr, 0, arr.size - 1)
}

/**
 * Lomuto’s partitioning algorithm always chooses the last element as the pivot.
 */
fun quickSortLomuto(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {

    fun IntArray.swap(x: Int, y: Int) {
        val tmp = this[x]
        this[x] = this[y]
        this[y] = tmp
    }

    fun partitionLomuto(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low
        for (j in low until high) {
            if (arr[j] <= pivot) {
                arr.swap(i, j)
                i++
            }
        }
        arr.swap(i, high)
        return i
    }

    if (low < high) {
        val pivot = partitionLomuto(arr, low, high)
        quickSortLomuto(arr, low, pivot - 1)
        quickSortLomuto(arr, pivot + 1, high)
    }
}