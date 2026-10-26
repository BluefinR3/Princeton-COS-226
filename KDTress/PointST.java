import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

// Points ST class.
public class PointST<Value> {

	// Attributes.
	private final TreeMap<Point2D, Value> pointsTree;

	// constructs an empty symbol table of points
	public PointST() {
		this.pointsTree = new TreeMap<>();
	}

	// is the symbol table empty?
	public boolean isEmpty() {
		return this.pointsTree.isEmpty();
	}

	// number of points in the symbol table
	public int size() {
		return this.pointsTree.size();
	}

	// associate the value val with point p
	public void put(Point2D p, Value val) {
		if (p == null) {
			throw new IllegalArgumentException("p is null");
		} else if (val == null) {
			throw new IllegalArgumentException("val is null");
		} else {
			this.pointsTree.put(p, val);
		}
	}

	// value associated with point p
	public Value get(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException("p is null");
		} else {
			return this.pointsTree.get(p);
		}
	}

	// does the symbol table contain point p?
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException("p is null");
		} else {
			return this.pointsTree.containsKey(p);
		}
	}

	// all points in the symbol table
	public Iterable<Point2D> points() {
		return this.pointsTree.keySet();
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new IllegalArgumentException("rect is null");
		} else {
			ArrayList<Point2D> pointsList = new ArrayList<>();
			List<Point2D> keys = new ArrayList<>(this.pointsTree.keySet());
			for (int i = 0; i < keys.size(); i++) {
				Point2D eachPoint = keys.get(i);
				if (rect.contains(eachPoint)) {
					pointsList.add(eachPoint);
				}
			}
			return pointsList;
		}
	}

	// a nearest neighbor to point p; null if the symbol table is empty
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException("p is null");
		} else if (!isEmpty()) {
			Point2D nearest = null;
			List<Point2D> keys = new ArrayList<>(this.pointsTree.keySet());
			double distance = Double.POSITIVE_INFINITY;
			for (int i = 0; i < keys.size(); i++) {
				Point2D point = keys.get(i);
				double pointDistance = p.distanceSquaredTo(point);
				if (pointDistance < distance) {
					distance = pointDistance;
					nearest = point;
				}
			}
			return nearest;
		} else {
			return null;
		}
	}

	// Unit testing
	public static void main(String[] args) {

		Point2D firstPoint = new Point2D(0.2, 0.4);
		Point2D secondPoint = new Point2D(0.1, 0.7);
		Point2D thirdPoint = new Point2D(0.3, 0.3);
		Point2D fourthPoint = new Point2D(0.4, 0.2);
		RectHV testRectangle = new RectHV(0, 0, 0.4, 0.4);
		PointST<Integer> points = new PointST<>();

		points.put(firstPoint, 1);
		points.put(secondPoint, 2);
		points.put(thirdPoint, 3);
		System.out.println("Added 3 Points (first, second, third)!");
		System.out.println("Is Empty: " + points.isEmpty());
		System.out.println("Size: " + points.size());
		System.out.println("Contains(second): " + points.contains(secondPoint));
		System.out.println("Contains(fourth): " + points.contains(fourthPoint));
		System.out.println("get(first): " + points.get(firstPoint));
		System.out.println("get(fourth): " + points.get(fourthPoint));

		System.out.println("\nPoints: ");
		for (Point2D point : points.points()) {
			System.out.println(point);
		}

		System.out.println("\nRange (" + testRectangle + ")");
		Iterator<Point2D> rangePoints = points.range(testRectangle).iterator();
		while (rangePoints.hasNext()) {
			System.out.println(rangePoints.next());
		}

		System.out.println("\nNearest to (" + fourthPoint + ")");
		System.out.println("Result: " + points.nearest(fourthPoint));
	}
}
