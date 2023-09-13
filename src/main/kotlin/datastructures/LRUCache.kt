package datastructures

/**
 * 146. LRU Cache
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the datastructures.LRUCache class:
 *
 * datastructures.LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 * loadFactor: As a general rule, the default load factor (.75) offers a good tradeoff between
 * time and space costs. Higher values decrease the space overhead but increase the lookup cost
 * (reflected in most of the operations of the HashMap class, including get and put)
 *
 * accessOrder: in our case should be the most recently used items are placed in the front-end of the queue.
 *
 * removeEldestEntry: This method typically does not modify the map in any way, instead allowing the map to modify itself as directed by its return value. It is permitted for this method to modify the map directly, but if it does so, it must return false (indicating that the map should not attempt any further modification). The effects of returning true after modifying the map from within this method are unspecified.
 * This implementation merely returns false (so that this map acts like a normal map - the eldest element is never removed).
 *
 * The Javadoc for LinkedHashMap says
 * "This kind of map is well-suited to building LRU caches."
 */
class LRUCache(capacity: Int) {

    /**
     * The internal cache structure is a LinkedHashMap and it has one very important property:
     * Entries in the rear end of the queue are removed when all the available buckets have been occupied.
     */
    private val cache: MutableMap<Int, Int> =
        object : LinkedHashMap<Int, Int>(
            0,
            0.75F,
            true
        ) {
            /**
             * method may be overridden to impose a policy for removing stale
             * mappings automatically when new mappings are added to the map.
             */
            override fun removeEldestEntry(
                eldest: MutableMap.MutableEntry<Int, Int>,
            ): Boolean = size > capacity
        }

    fun get(key: Int): Int = cache[key] ?: -1

    fun put(key: Int, value: Int) {
        cache[key] = value
    }
}