import java.util.Comparator;

// Implementation of binary search deluxe.
public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that equals the search key, or -1
    // if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        return binarySearchDeluxe(a, key, comparator, true);
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if
    // no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        return binarySearchDeluxe(a, key, comparator, false);
    }

    // Helper method to iterate from front and back.
    private static <Key> int binarySearchDeluxe(Key[] a, Key key, Comparator<Key> comparator, boolean first) {
        if (a == null) {
            throw new IllegalArgumentException("key array is null");
        }
        else if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        else if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }
        else {
            int left = 0, right = a.length - 1, index = -1;
            while (left <= right) {
                int middleIndex = left + (right - left) / 2;
                int result = comparator.compare(a[middleIndex], key);
                if (result == 0) {
                    index = middleIndex;
                    if (first) {
                        right = middleIndex - 1;
                    } else {
                        left = middleIndex + 1;
                    }
                }
                else if (result < 0) {
                    left = middleIndex + 1;
                }
                else {
                    right = middleIndex - 1;
                }
            }
            return index;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Term[] terms = {
                new Term("Tokyo", 5), new Term("Tokyo", 5), new Term("Zhumadian", 2),
                new Term("Taipei", 7),
                new Term("London", 7), new Term("Dhaka", 3)
        };
        System.out.println("First Index (Tokyo): " + firstIndexOf(terms, new Term("Tokyo", 0),
                                                                  Term.byPrefixOrder(5)));
        System.out.println("Last Index (Tokyo): " + lastIndexOf(terms, new Term("Tokyo", 0),
                                                                Term.byPrefixOrder(5)));

    }

}
