package strings


/**
 * 17. Letter Combinations of a Phone Number
 * Medium
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter
 * combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Input: digits = ""
 * Output: []
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * Topics: HashTable, Backtracking, String.
 */
fun main() {
    println("outfor for digits: 23: ${letterCombinations("23")}")
}

// Hardcode a map. It's small no no issue there.
// Time: 4^n.
// O(n*4^n)
fun letterCombinations(digits: String): List<String> {
    val res = mutableListOf<String>()
    val digitToChar = mapOf(
        "2" to "abc",
        "3" to "def",
        "4" to "ghi",
        "5" to "jkl",
        "6" to "mno",
        "7" to "qprs",
        "8" to "tuv",
        "9" to "wxyz"
    )

    fun backtrack(i: Int, str: StringBuilder) {
        if (str.length == digits.length) {
            res.add(str.toString())
            return
        }
        for (c in digitToChar[digits[i].toString()]!!) {
            backtrack(i + 1, str.append(c))
            str.setLength(str.length - 1)
        }
    }
    if (digits.isNotEmpty()) {
        backtrack(0, StringBuilder(""))
    }
    return res
}

/*
fun letterCombinations(digits: String): List<String>? {
    val res: MutableList<String> = ArrayList()
    if (digits.isEmpty()) return res
    val map = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "qprs",
        '8' to "tuv",
        '9' to "wxyz)"
    )
    */
/*val map: MutableMap<Char, String> = HashMap()
    map['2'] = "abc"
    map['3'] = "def"
    map['4'] = "ghi"
    map['5'] = "jkl"
    map['6'] = "mno"
    map['7'] = "pqrs"
    map['8'] = "tuv"
    map['9'] = "wxyz"*//*


    recursion(digits, map, StringBuilder(), res, 0)
    return res
}

fun recursion(
    digits: String, map: Map<Char, String>, sb: StringBuilder,
    res: MutableList<String>, i: Int,
) {
    if (i == digits.length) {
        res.add(sb.toString())
        return
    }
    val str = map[digits[i]]
    for (j in 0 until str!!.length) {
        sb.append(str[j])
        recursion(digits, map, sb, res, i + 1)
        sb.setLength(sb.length - 1)
    }
}*/
