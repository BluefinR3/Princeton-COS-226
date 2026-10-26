import java.util.Iterator;
import java.util.NoSuchElementException;

// Implementation of Deque
public class Deque<Item> implements Iterable<Item> {

	// attributes.
	private DequeNode head; // head node in deque
	private DequeNode tail; // tail node in deque
	private int manyItems; // total items exists in deque

	// helper class for nodes
	private class DequeNode {

		// data item for each node.
		Item data;
		DequeNode next; // next reference for node
		DequeNode previous; // previous reference for node

		public DequeNode(Item newData) {
			data = newData;
		}

	}

	// construct an empty deque
	public Deque() {
	}

	// is the deque empty?
	public boolean isEmpty() {
		return manyItems == 0;
	}

	// return the number of items on the deque
	public int size() {
		return manyItems;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item != null) {
			DequeNode node = new DequeNode(item);
			if (head == null && tail == null) {
				head = tail = node;
			} else {
				node.next = head;
				head.previous = node;
				head = node;
			}
			manyItems++;
		} else {
			throw new IllegalArgumentException("Item cannot be null.");
		}
	}

	// add the item to the end
	public void addLast(Item item) {
		if (item != null) {
			DequeNode node = new DequeNode(item);
			if (head == null && tail == null) {
				head = tail = node;
			} else {
				tail.next = node;
				node.previous = tail;
				tail = node;
			}
			manyItems++;
		} else {
			throw new IllegalArgumentException("Item cannot be null.");
		}
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (manyItems > 0) {
			Item removed = head.data;
			head = head.next;
			if (head == null) {
				tail = null;
			} else {
				head.previous = null;
			}
			manyItems--;
			return removed;
		} else {
			throw new NoSuchElementException("The queue is empty.");
		}
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (manyItems > 0) {
			Item removed = tail.data;
			tail = tail.previous;
			if (tail == null) {
				head = null;
			} else {
				tail.next = null;
			}
			manyItems--;
			return removed;
		} else {
			throw new NoSuchElementException("The queue is empty.");
		}
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {

			private DequeNode cursor = head;

			@Override
			public boolean hasNext() {
				return cursor != null;
			}

			@Override
			public Item next() {
				if (hasNext()) {
					Item next = cursor.data;
					cursor = cursor.next;
					return next;
				} else {
					throw new NoSuchElementException("No any item to return.");
				}
			}

		};
	}

	// unit testing (required)
	public static void main(String[] args) {

		Deque<Integer> deque = new Deque<>();

		System.out.println("Empty: " + deque.isEmpty());

		deque.addFirst(3);
		deque.addFirst(2);
		deque.addFirst(1);
		deque.addLast(4);
		deque.addLast(5);
		deque.addLast(6);
		deque.addFirst(0);

		System.out.println("Size: " + deque.size());
		System.out.print("Deque :- ");
		Iterator<Integer> iterator = deque.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
		System.out.println("Removed First: " + deque.removeFirst());
		System.out.println("Removed First: " + deque.removeFirst());
		System.out.println("Removed Back: " + deque.removeLast());
		System.out.println("Removed Back: " + deque.removeLast());
		System.out.println("Empty: " + deque.isEmpty());
		System.out.println("Size: " + deque.size());

	}
}
