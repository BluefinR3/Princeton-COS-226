import java.util.Comparator;

// Each term implementation.
public class Term implements Comparable<Term> {

    // attributes..
    private String queryText;
    private double weightValue;

    // Initializes a term with the given query string and weight.
    public Term(String query, double weight) {
        if (query == null) {
            throw new IllegalArgumentException("query is null");
        }
        else if (weight < 0) {
            throw new IllegalArgumentException("weight is negative");
        }
        else {
            queryText = query;
            weightValue = weight;
        }
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return (o1, o2) -> Double.compare(o2.weightValue, o1.weightValue);
    }

    // Compares the two terms in lexicographic order but using only the first r
    // characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException("Prefix is negative");
        }
        else {
            return (o1, o2) -> {
                String firstPrefix = o1.queryText;
                String secondPrefix = o2.queryText;
                if (o1.queryText.length() >= r) {
                    firstPrefix = firstPrefix.substring(0, r);
                }
                if (o2.queryText.length() >= r) {
                    secondPrefix = secondPrefix.substring(0, r);
                }
                return firstPrefix.compareTo(secondPrefix);
            };
        }
    }

    // Compares the two terms in lexicographic order by query.
    @Override
    public int compareTo(Term other) {
        return queryText.compareTo(other.queryText);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    @Override
    public String toString() {
        return String.format("%14s\t%s", String.format("%.0f", weightValue), queryText);
    }

    // unit testing (required)
    public static void main(String[] args) {

        Term term1 = new Term("king", 7.0);
        Term term2 = new Term("asphalt", 14.0);

        System.out.println("Term 1: " + term1);
        System.out.println("Term 2: " + term2);
        System.out.println("Lexicographical Comparison: " + term1.compareTo(term2));
        System.out.println(
                "Weight Comparison: " + Term.byReverseWeightOrder().compare(term1, term2));
        System.out.println("Prefix comparison (3): " + Term.byPrefixOrder(3).compare(term1, term2));

    }

}
