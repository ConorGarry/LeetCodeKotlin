package strings

/**
 * 20. Valid Parentheses
 * Easy
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 * Input: s = "()"
 * Output: true
 *
 * Input: s = "()[]{}"
 * Output: true
 *
 * Input: s = "(]"
 * Output: false
 *
 * Input: s = "([)]"
 * Output: false
 *
 * Input: s = "{[]}"
 * Output: true
 */
fun main() {
    arrayOf(
        "()",
        "()[]{}",
        "(]",
        "([)]",
        "{[]}",
        "]",
    ).forEach {
        println("isValid $it: ${isValid(it)}")
    }
}

fun isValid(s: String): Boolean {
    val stack = ArrayDeque<Char>()
    val map = mapOf('(' to ')', '[' to ']', '{' to '}')
    s.forEach {
        if (map.contains(it))
            stack.add(map[it]!!)
        else {
            if (stack.isEmpty() || stack.removeLast() != it)
                return false
        }
    }
    return stack.isEmpty()
}

fun isValidOther(s: String) : Boolean {
    val map = mapOf(')' to '(', '}' to '{', ']' to '[')
    val stack = ArrayDeque<Char>()
    s.forEach {
        if (stack.lastOrNull() == map[it]) {
            stack.removeLast()
        } else {
            return false
        }
        stack.add(it)
    }
}

