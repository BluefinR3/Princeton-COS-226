import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

public class Outcast {

	// Attributes..
	private WordNet wordNet;

	// Constructor takes a WordNet object
	public Outcast(WordNet wordNet) {
		if (wordNet == null) {
			throw new IllegalArgumentException("WordNet is null");
		} else {
			this.wordNet = wordNet;
		}
	}

	// Given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
		if (nouns == null) {
			throw new IllegalArgumentException("nouns is null");
		} else if (nouns.length < 2) {
			throw new IllegalArgumentException("length is not 2+");
		} else {
			int distance = -1;
			String outcast = null;
			for (int i = 0; i < nouns.length; i++) {
				if (wordNet.isNoun(nouns[i])) {
					int total = 0;
					for (int j = 0; j < nouns.length; j++) {
						if (!Objects.equals(nouns[i], nouns[j])) {
							total += wordNet.distance(nouns[i], nouns[j]);
						}
					}
					if (total > distance) {
						distance = total;
						outcast = nouns[i];
					}
				} else {
					throw new IllegalArgumentException(nouns[i] + " is not noun");
				}
			}

			return outcast;
		}
	}

	// Test client
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}
}
