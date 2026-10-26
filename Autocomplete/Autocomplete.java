import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Implementation of auto-complete.
public class Autocomplete {

    // Attributes..
    private Term[] termsArray;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new IllegalArgumentException("terms is null.");
        }
        else {
            termsArray = terms.clone();
            Arrays.sort(termsArray);
        }
    }

    // Returns all terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("prefix is null.");
        }
        else {
            Term findTerm = new Term(prefix, 0);
            int startIndex = BinarySearchDeluxe.firstIndexOf(termsArray, findTerm,
                                                             Term.byPrefixOrder(prefix.length()));
            if (startIndex == -1) {
                return new Term[0];
            }
            else {
                int endIndex = BinarySearchDeluxe.lastIndexOf(termsArray, findTerm,
                                                              Term.byPrefixOrder(prefix.length()));
                List<Term> matchedList = new ArrayList<>();
                for (int i = startIndex; i <= endIndex; i++) {
                    matchedList.add(termsArray[i]);
                }
                matchedList.sort(Term.byReverseWeightOrder());
                return matchedList.toArray(new Term[0]);
            }
        }
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("prefix is null.");
        }
        else {
            Term findTerm = new Term(prefix, 0);
            int firstIndex = BinarySearchDeluxe.firstIndexOf(this.termsArray, findTerm,
                                                             Term.byPrefixOrder(prefix.length()));
            int matches = 0;
            if (firstIndex != -1) {
                return BinarySearchDeluxe.lastIndexOf(this.termsArray, findTerm,
                                                      Term.byPrefixOrder(prefix.length()))
                        - firstIndex + 1;
            }
            return matches;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong(); // read the next weight
            in.readChar(); // scan past the tab
            String query = in.readLine(); // read the next query
            terms[i] = new Term(query, weight); // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
