package strings

/**
 * 535. Encode and Decode TinyURL
 * Medium
 *
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 *
 * TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL
 * such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 *
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the
 * tiny URL can be decoded to the original URL.
 *
 * Implement the Solution class:
 *
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl.
 * It is guaranteed that the given shortUrl was encoded by the same object.
 *
 * Input: url = "https://leetcode.com/problems/design-tinyurl"
 * Output: "https://leetcode.com/problems/design-tinyurl"
 *
 * Explanation:
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // returns the encoded tiny url.
 * string ans = obj.decode(tiny); // returns the original url after deconding it.
 */
fun main() {
    // Confirmed in Leetcode.
}

class Codec() {

    private val encode = mutableMapOf<String, String>()
    private val decode = mutableMapOf<String, String>()
    private val base = "http://tinyurl.com/"

    // Encodes a URL to a shortened URL.
    fun encode(longUrl: String): String {
        if (!encode.containsKey(longUrl)) {
            val shortUrl = "$base${encode.size + 1}"
            encode[longUrl] = shortUrl
            decode[shortUrl] = longUrl
        }
        return encode[longUrl]!!
    }

    // Decodes a shortened URL to its original URL.
    fun decode(shortUrl: String): String = decode[shortUrl]!!
}