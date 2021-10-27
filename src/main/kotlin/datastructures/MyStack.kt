/**
 * Stacks are used prominently in all disciplines of programming, such as:
 * • Android uses the fragment stack to push and pop fragments into and out of an
 * Activity.
 * • Memory allocation uses stacks at the architectural level. Memory for local
 * variables is also managed using a stack.
 * • Search and conquer algorithms, such as finding a path out of a maze, use stacks to
 * facilitate backtracking.
 *
 * Note: The previous Stack interface is different from the Stack class provided
 * by Kotlin (or Java) which extends the Vector class and provides methods we
 * don't need here.
 */
interface IStack<Element> {
    val storage: List<Element>
    fun push(element: Element)
    fun pop(): Element?

    // Not-essentials.
    fun peek(): Element?
    val count: Int
    val isEmpty: Boolean
}

/**
 * You can implement your Stack interface in different ways and choosing the right
 * storage type is important.
 *
 * ArrayList is an obvious choice since it offers
 * constant time insertions and deletions at one end via add and removeAt with the last
 * index as a parameter. Usage of these two operations will facilitate the LIFO nature of
 * stacks.
 *
 * Less is More!
 * A stack’s purpose is to limit the number of ways to access your data, and
 * adopting interfaces such as Iterable would go against this goal by exposing all of
 * the elements via iterators. In this case, less is more!
 */
class Stack<T : Any> : IStack<T> {

    // Add / remove from then end of a list is constant time complexity 0(1).
    override val storage = mutableListOf<T>()

    override fun push(element: T) {
        storage.add(element)
    }

    override fun pop(): T? = storage.removeLastOrNull()

    override fun toString(): String {
        return buildString {
            appendLine("---top---")
            storage.asReversed().forEach {
                appendLine("$it")
            }
            appendLine("---------")
        }
    }

    override fun peek(): T? = storage.lastOrNull()

    override val count: Int
        get() = storage.size

    override val isEmpty: Boolean
        get() = storage.isEmpty()
}

fun <T : Any> List<T>.toStack(): Stack<T> =
    Stack<T>().apply {
        this@toStack.forEach {
            push(it)
        }
    }

fun <T : Any> stackOf(vararg elements: T): Stack<T> =
    listOf(*elements).toStack()

/*fun <T : Any> reverseListWithStack(list: MyLinkedList<T>): MyLinkedList<T> {
    val stack = Stack<T>()
    for (node in list) {
        stack.push(node)
    }
    list.clear()
    while (!stack.isEmpty) {
        list.append(stack.pop()!!)
    }
    return list
}*/

/**
 * Google question:
 * Given a string of round, curly, and square open and closing brackets, return whether the brackets are balanced (well-formed).
 * For example, given the string "([])[]({})", you should return true.
 * Given the string "([)]" or "((()", you should return false.
 */
fun String.parenthesisBalanced(): Boolean {
    val stack = Stack<Char>()
    val mapS = mutableMapOf<Char, Int>()
    mapS.values.maxByOrNull { it }

    forEach {
        mapS[it] = mapS.getOrDefault(it, 0) + 1
    }
    forEach {
        when (it) {
            '(', '[', '{' -> stack.push(it)
            // If you’re out of items on the stack, your string is already imbalanced, so you can immediately return
            // from the function.
            ')', '}', ']' -> if (stack.isEmpty) return false else stack.pop()
        }
    }
    return stack.isEmpty
}
