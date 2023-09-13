package algorithms

/**
 * Simple Mathematical Approach.
 * Join array, sort them, get median.
 */
fun getMedianMath(nums1: IntArray, nums2: IntArray): Double {
    // 1. Merge the two given arrays into one array.
    val arrCopy = nums1 + nums2
    // 2. Then sort the third(merged) array
    arrCopy.sort()

    // 3. If the length of the third array is even then:
    //    divide the length of array by 2
    //    return arr[value] + arr[value - 1] / 2 (mean of middle two digits).
    val n = arrCopy.size
    return if (n % 2 == 0) {
        val z = (n / 2)
        val postMid = arrCopy[z]
        val preMid = arrCopy[z - 1]

        val ans = (postMid + preMid) / 2.0
        ans
    } else {
        // 4. If the length of the third array is odd then:
        //    divide the length of array by 2
        //    round that value
        //    return the arr[value]
        val z = Math.floor(n / 2.0).toInt()
        arrCopy[z].toDouble()
    }
}

// Short leetcode answer.
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val merged = (nums1 + nums2)
    merged.sort()
    val n = merged.size
    return if (n % 2 == 0) {
        val m = n / 2
        (merged[m] + merged[m - 1]) / 2.0
    } else {
        merged[n / 2].toDouble()
    }
}