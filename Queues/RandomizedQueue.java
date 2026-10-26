import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Implementation of RandomizedQueue.
public class RandomizedQueue<Item> implements Iterable<Item> {

	// attributes.
	private Item[] itemsArray; // total number of items in array
	private int manyItems; // size of array
	private static int INITIAL_LENGTH = 8; // initial length of array

	// construct an empty randomized queue
	public RandomizedQueue() {
		itemsArray = (Item[]) new Object[INITIAL_LENGTH];
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return manyItems == 0;
	}

	// return the number of items on the randomized queue
	public int size() {
		return manyItems;
	}

	// To sample the random item.
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		int randIndex = StdRandom.uniform(manyItems);
		return itemsArray[randIndex];
	}

	// To resize the array.
	private void resize(int capacity) {
		Item[] newArray = (Item[]) new Object[capacity];
		for (int i = 0; i < manyItems; i++) {
			newArray[i] = itemsArray[i];
		}
		itemsArray = newArray;
	}

	// add the item
	public void enqueue(Item item) {
		if (item != null) {
			if (manyItems == itemsArray.length) {
				resize(manyItems * 2);
			}
			itemsArray[manyItems] = item;
			manyItems++;
		} else {
			throw new IllegalArgumentException("Item cannot be null.");
		}
	}

	// remove and return a random item
	public Item dequeue() {
		if (manyItems > 0) {
			int index = StdRandom.uniform(manyItems);
			Item removed = itemsArray[index];
			itemsArray[index] = itemsArray[manyItems - 1];
			itemsArray[manyItems - 1] = null;
			manyItems--;
			if (manyItems > 0 && manyItems == itemsArray.length / 4) {
				resize(itemsArray.length / 2);
			}
			return removed;
		} else {
			throw new NoSuchElementException("No any item to remove.");
		}
	}

	private Item[] getRandomCopy() {
		Item[] copiedArray = (Item[]) new Object[manyItems];
		for (int i = 0; i < manyItems; i++) {
			copiedArray[i] = itemsArray[i];
		}
		StdRandom.shuffle(copiedArray);
		return copiedArray;
	}

	// return an independent iterator over items in random order
	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {

			// attributes..
			private Item[] randomArray = getRandomCopy();
			private int cursorIndex;

			@Override
			public boolean hasNext() {
				return cursorIndex < randomArray.length;
			}

			@Override
			public Item next() {
				if (hasNext()) {
					return this.randomArray[cursorIndex++];
				} else {
					throw new NoSuchElementException("No any item to return.");
				}
			}

		};
	}

	// unit testing (required)
	public static void main(String[] args) {
		
		RandomizedQueue<Integer> queue = new RandomizedQueue<>();

		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);

		System.out.println("Empty: " + queue.isEmpty());
		System.out.println("Size: " + queue.size());
		
		System.out.print("Randomized Items:- ");
		Iterator<Integer> iterator = queue.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
		
		System.out.println("Dequeue: " + queue.dequeue());
		System.out.println("Dequeue: " + queue.dequeue());
		System.out.println("Dequeue: " + queue.dequeue());
		System.out.println("Size: " + queue.size());
		
	}
}
