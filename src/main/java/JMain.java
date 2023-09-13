import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Java playground.
 * Lintcode doesn't offer Kotlin.
 */

class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;
}

public class JMain {
    private static List<Interval> list = new ArrayList<Interval>() {{
        add(new Interval(3, 4));
        add(new Interval(0, 1));
        add(new Interval(7, 8));
    }};

    public static void main(String[] args) {
        System.out.println("lists equal: ");
        compareLists(null, null);
    }

    static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode left = head1;
        SinglyLinkedListNode right = head2;

        while (left != null) {
            left = left.next;
            right = right.next;
            if (Objects.equals(left, right)) {
                return false;
            }
        }
        return true;
    }

}

