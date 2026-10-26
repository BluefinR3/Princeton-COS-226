import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * It represents the shortest common ancestor.
 */
public class ShortestCommonAncestor {

	// Attributes..
	private Digraph graph;

	// constructor takes a rooted DAG as argument
	public ShortestCommonAncestor(Digraph G) {
		if (G == null) {
			throw new IllegalArgumentException("Graph is null");
		}
		else {
			this.graph = new Digraph(G);
		}
	}

	// Length of shortest ancestral path between v and w
	public int length(int v, int w) {
		checkVertex(v);
		checkVertex(w);
		return getDistance(new BreadthFirstDirectedPaths(graph, v),
						   new BreadthFirstDirectedPaths(graph, w));
	}

	// Shortest common ancestor of vertices v and w
	public int ancestor(int v, int w) {
		checkVertex(v);
		checkVertex(w);
		return getAncestor(new BreadthFirstDirectedPaths(graph, v),
						   new BreadthFirstDirectedPaths(graph, w));
	}

	// Length of shortest ancestral path between subsets subsetA and subsetB
	public int lengthSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
		checkSubset(subsetA);
		checkSubset(subsetB);
		return getDistance(new BreadthFirstDirectedPaths(graph, subsetA),
						   new BreadthFirstDirectedPaths(graph, subsetB));
	}

	// Shortest common ancestor of subsets subsetA and subsetB
	public int ancestorSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
		checkSubset(subsetA);
		checkSubset(subsetB);
		return getAncestor(new BreadthFirstDirectedPaths(graph, subsetA),
						   new BreadthFirstDirectedPaths(graph, subsetB));
	}

	/*
	 * Helper Methods.
	 */
	private void checkVertex(int v) {
		if (v < 0 || v >= graph.V()) {
			throw new IllegalArgumentException("v is wrong");
		}
	}

	private void checkSubset(Iterable<Integer> subset) {
		if (subset == null) {
			throw new IllegalArgumentException("Subset is null");
		}
		else {
			Iterator<Integer> iterator = subset.iterator();
			while (iterator.hasNext()) {
				checkVertex(iterator.next());
			}
		}
	}

	private int getDistance(BreadthFirstDirectedPaths vPath, BreadthFirstDirectedPaths wPath) {
		int distance = Integer.MAX_VALUE;
		for (int i = 0; i < graph.V(); i++) {
			if (vPath.hasPathTo(i) && wPath.hasPathTo(i)) {
				int length = vPath.distTo(i) + wPath.distTo(i);
				if (length < distance) {
					distance = length;
				}
			}
		}
		return distance;
	}

	private int getAncestor(BreadthFirstDirectedPaths vPath, BreadthFirstDirectedPaths wPath) {
		int distance = Integer.MAX_VALUE;
		int ancestor = -1;
		for (int i = 0; i < graph.V(); i++) {
			if (vPath.hasPathTo(i) && wPath.hasPathTo(i)) {
				int length = vPath.distTo(i) + wPath.distTo(i);
				if (length < distance) {
					distance = length;
					ancestor = i;
				}
			}
		}
		return ancestor;
	}

	// unit testing (required)
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sca.length(v, w);
			int ancestor = sca.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}

}
