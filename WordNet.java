import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class WordNet {

	// Attributes..
	private ArrayList<String> synsetList;
	private HashMap<String, HashSet<Integer>> nounMap;
	private Digraph digraph;
	// All WordNet nouns
	public Iterable<String> nouns() {
		return this.nounMap.keySet();
	}

	// Constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		if (synsets == null) {
			throw new IllegalArgumentException("synsets is null");
		} else if (hypernyms == null) {
			throw new IllegalArgumentException("hypernyms is null");
		} else {
			this.nounMap = new HashMap<>();
			this.synsetList = new ArrayList<>();
			In inputStream = new In(synsets);
			while (inputStream.hasNextLine()) {
				String[] tokens = inputStream.readLine().split(",");
				String[] nouns = tokens[1].split(" ");
				for (int i = 0; i < nouns.length; i++) {
					String noun = nouns[i];
					if (!this.nounMap.containsKey(noun)) {
						this.nounMap.put(noun, new HashSet<>());
					}
					this.nounMap.get(noun).add(Integer.parseInt(tokens[0]));
				}
				this.synsetList.add(tokens[1]);
			}
			this.digraph = new Digraph(synsetList.size());
			inputStream = new In(hypernyms);
			while (inputStream.hasNextLine()) {
				String[] tokens = inputStream.readLine().split(",");
				for (int i = 1; i < tokens.length; i++) {
					this.digraph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
				}
			}
		}
	}

	// Is the word a WordNet noun?
	public boolean isNoun(String word) {
		if (word == null) {
			throw new IllegalArgumentException("Word is null");
		} else {
			return this.nounMap.containsKey(word);
		}
	}

	// A synset (second field of synsets.txt) that is a shortest common ancestor
	// of noun1 and noun2 (defined below)
	public String sca(String noun1, String noun2) {
		if (!isNoun(noun1)) {
			throw new IllegalArgumentException("Noun1 not present.");
		} else if (!isNoun(noun2)) {
			throw new IllegalArgumentException("Noun2 not present.");
		} else {
			int distance = Integer.MAX_VALUE;
			int ancestor = -1;
			BreadthFirstDirectedPaths firstPath = new BreadthFirstDirectedPaths(digraph, nounMap.get(noun1));
			BreadthFirstDirectedPaths secondPath = new BreadthFirstDirectedPaths(digraph, nounMap.get(noun2));
			for (int i = 0; i < digraph.V(); i++) {
				if (firstPath.hasPathTo(i) && secondPath.hasPathTo(i)) {
					int length = firstPath.distTo(i) + secondPath.distTo(i);
					if (length < distance) {
						distance = length;
						ancestor = i;
					}
				}
			}
			if (ancestor == -1) {
				return null;
			}
			return this.synsetList.get(ancestor);
		}

	}

	// Distance between noun1 and noun2
	public int distance(String noun1, String noun2) {
		if (!isNoun(noun1)) {
			throw new IllegalArgumentException("Noun1 not present.");
		} else if (!isNoun(noun2)) {
			throw new IllegalArgumentException("Noun2 not present.");
		} else {
			int distance = Integer.MAX_VALUE;
			BreadthFirstDirectedPaths firstPath = new BreadthFirstDirectedPaths(digraph, nounMap.get(noun1));
			BreadthFirstDirectedPaths secondPath = new BreadthFirstDirectedPaths(digraph, nounMap.get(noun2));
			for (int i = 0; i < digraph.V(); i++) {
				if (firstPath.hasPathTo(i) && secondPath.hasPathTo(i)) {
					int length = firstPath.distTo(i) + secondPath.distTo(i);
					if (length < distance) {
						distance = length;
					}
				}
			}
			if (distance == Integer.MAX_VALUE) {
				return -1;
			} else {
				return distance;
			}
		}
	}

	// Unit testing
	public static void main(String[] args) {

		WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
		System.out.println("'people' noun: " + wordNet.isNoun("people"));
		System.out.println("'to' noun: " + wordNet.isNoun("to"));
		System.out.println("Distance between 'people' and 'history': " + wordNet.distance("people", "history"));
		System.out.println("Shortest common ancestor of 'people' and 'history': " + wordNet.sca("history", "video"));

	}
}
