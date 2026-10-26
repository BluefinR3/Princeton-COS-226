import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.Iterator;

public class KdTreeST<Value> {

	// Attributes.
	private Node root;
	private int manyItems;

	private class Node {

		private Point2D dataPoint;
		private Value dataValue;
		private RectHV rectangle;
		private Node left;
		private Node right;

		public Node(Point2D dataPoint, Value dataValue, RectHV rectangle) {
			this.dataPoint = dataPoint;
			this.dataValue = dataValue;
			this.rectangle = rectangle;
			this.left = null;
			this.right = null;
		}

	}

	// constructor
	public KdTreeST() {
		this.root = null;
		this.manyItems = 0;
	}

	// static.
	private static RectHV defaultRectangle() {
		return new RectHV(0, 0, 1, 1);
	}

	// is the symbol table empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// number of points in the symbol table
	public int size() {
		return this.manyItems;
	}

	// associate the value val with point p
	public void put(Point2D p, Value val) {
		if (p == null) {
			throw new IllegalArgumentException("p is null");
		} else if (val == null) {
			throw new IllegalArgumentException("val is null");
		} else {
			root = putRecursive(root, p, val, 0, defaultRectangle());
		}
	}

	// Helper method for put.
	private Node putRecursive(Node parent, Point2D location, Value value, int depth, RectHV range) {
		Node updatedParent = parent;
		if (parent == null) {
			manyItems += 1;
			updatedParent = new Node(location, value, range);
		} else if (parent.dataPoint.equals(location)) {
			parent.dataValue = value;
			updatedParent = parent;
		} else {
			if (depth % 2 == 0) {
				if (location.x() < parent.dataPoint.x()) {
					parent.left = putRecursive(parent.left, location, value, depth + 1,
							new RectHV(range.xmin(), range.ymin(), parent.dataPoint.x(), range.ymax()));
				} else {
					parent.right = putRecursive(parent.right, location, value, depth + 1,
							new RectHV(parent.dataPoint.x(), range.ymin(), range.xmax(), range.ymax()));
				}
			} else {
				if (location.y() < parent.dataPoint.y()) {
					parent.left = putRecursive(parent.left, location, value, depth + 1,
							new RectHV(range.xmin(), range.ymin(), range.xmax(), parent.dataPoint.y()));
				} else {
					parent.right = putRecursive(parent.right, location, value, depth + 1,
							new RectHV(range.xmin(), parent.dataPoint.y(), range.xmax(), range.ymax()));
				}
			}

		}
		return updatedParent;
	}

	// value associated with point p
	public Value get(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException("p is null");
		} else {
			return getRecursive(root, p, 0);
		}
	}

	// Helper method for get.
	private Value getRecursive(Node x, Point2D p, int depth) {
		Value result = null;
		if (x != null) {
			if (x.dataPoint.equals(p)) {
				result = x.dataValue;
			} else {
				if (depth % 2 == 0) {
					if (p.x() < x.dataPoint.x()) {
						result = getRecursive(x.left, p, depth + 1);
					} else {
						result = getRecursive(x.right, p, depth + 1);
					}
				} else {
					if (p.y() < x.dataPoint.y()) {
						result = getRecursive(x.left, p, depth + 1);
					} else {
						result = getRecursive(x.right, p, depth + 1);
					}
				}

			}
		}
		return result;
	}

	// does the symbol table contain point p?
	public boolean contains(Point2D p) {
		Value result = get(p);
		return result != null;
	}

	// all points in the symbol table
	public Iterable<Point2D> points() {
		ArrayList<Point2D> result = new ArrayList<>();
		pointsRecursive(root, result);
		return result;
	}

	// Helper method for points
	private void pointsRecursive(Node node, ArrayList<Point2D> result) {
		if (node != null) {
			result.add(node.dataPoint);
			pointsRecursive(node.left, result);
			pointsRecursive(node.right, result);
		}
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new IllegalArgumentException("rect is null");
		} else {
			ArrayList<Point2D> result = new ArrayList<>();
			range(root, rect, result);
			return result;
		}
	}

	// Helper method for range.
	private void range(Node node, RectHV range, ArrayList<Point2D> result) {
		if (node != null) {
			if (range.intersects(node.rectangle)) {
				if (range.contains(node.dataPoint)) {
					result.add(node.dataPoint);
				}
				range(node.left, range, result);
				range(node.right, range, result);
			}
		}
	}

	// a nearest neighbor to point p; null if the symbol table is empty
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException("Argument cannot be null");
		} else if (root != null) {
			return nearest(root, p, root.dataPoint, 0);
		}
		return null;
	}

	// Helper method for nearest.
	private Point2D nearest(Node node, Point2D point, Point2D nearest, int depth) {
		if (node != null) {
			if (point.distanceSquaredTo(node.dataPoint) < point.distanceSquaredTo(nearest)) {
				nearest = node.dataPoint;
			}
			Node parent;
			Node finder;
			if (depth % 2 == 0) {
				if (point.x() < node.dataPoint.x()) {
					parent = node.left;
					finder = node.right;
				} else {
					parent = node.right;
					finder = node.left;
				}
			} else {
				if (point.y() < node.dataPoint.y()) {
					parent = node.left;
					finder = node.right;
				} else {
					parent = node.right;
					finder = node.left;
				}
			}
			nearest = nearest(parent, point, nearest, depth + 1);
			if (finder != null && finder.rectangle.distanceSquaredTo(point) < point.distanceSquaredTo(nearest)) {
				nearest = nearest(finder, point, nearest, depth + 1);
			}
		}
		return nearest;
	}

	// Extra Challenge Method.
	public Iterable<Point2D> nearest(Point2D p, int k) {
		return new ArrayList<>();
	}

	// Unit testing
	public static void main(String[] args) {

		Point2D firstPoint = new Point2D(0.2, 0.4);
		Point2D secondPoint = new Point2D(0.1, 0.7);
		Point2D thirdPoint = new Point2D(0.3, 0.3);
		Point2D fourthPoint = new Point2D(0.4, 0.2);
		RectHV testRectangle = new RectHV(0, 0, 0.4, 0.4);

		KdTreeST<Integer> kdTree = new KdTreeST<>();
		kdTree.put(firstPoint, 1);
		kdTree.put(secondPoint, 2);
		kdTree.put(thirdPoint, 3);
		System.out.println("Added 3 Points (first, second, third)!");
		System.out.println("Is Empty: " + kdTree.isEmpty());
		System.out.println("Size: " + kdTree.size());
		System.out.println("Contains(second): " + kdTree.contains(secondPoint));
		System.out.println("Contains(fourth): " + kdTree.contains(fourthPoint));
		System.out.println("get(first): " + kdTree.get(firstPoint));
		System.out.println("get(fourth): " + kdTree.get(fourthPoint));

		kdTree.put(new Point2D(0.7, 0.2), 1);
		kdTree.put(new Point2D(0.5, 0.4), 2);
		kdTree.put(new Point2D(0.2, 0.3), 3);
		kdTree.put(new Point2D(0.4, 0.7), 4);
		kdTree.put(new Point2D(0.9, 0.6), 5);

		System.out.println("\nPoints: ");
		for (Point2D point : kdTree.points()) {
			System.out.println(point);
		}

		System.out.println("\nRange (" + testRectangle + ")");
		Iterator<Point2D> rangePoints = kdTree.range(testRectangle).iterator();
		while (rangePoints.hasNext()) {
			System.out.println(rangePoints.next());
		}

		System.out.println("\nNearest to (" + fourthPoint + ")");
		System.out.println("Result: " + kdTree.nearest(fourthPoint));

	}
}
