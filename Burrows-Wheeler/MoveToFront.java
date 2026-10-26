import java.util.LinkedList;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Implemented the MoveToFront.
public class MoveToFront {

	// Attributes & Constants.
	private static LinkedList<Character> sequenceList = new LinkedList<>();
	private static final int SEQUENCE_LIMIT = 256;

	// Setting up.
	static {
		for (int i = 0; i < SEQUENCE_LIMIT; i++) {
			sequenceList.add((char) i);
		}
	}

	// Apply move-to-front encoding, reading from standard input and writing to
	// standard output
	public static void encode() {
		while (!StdIn.isEmpty()) {
			char character = StdIn.readChar();
			int charIndex = sequenceList.indexOf(character);
			sequenceList.remove(charIndex);
			sequenceList.addFirst(character);
			StdOut.println(charIndex);
		}
	}

	// Apply move-to-front decoding, reading from standard input and writing to
	// standard output
	public static void decode() {
		while (!StdIn.isEmpty()) {
			int charIndex = StdIn.readInt();
			char character = sequenceList.remove(charIndex);
			sequenceList.addFirst(character);
			StdOut.println(character);
		}
	}

	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) {
		if (args.length == 1) {
			String operation = args[0];
			if (operation.equals("-")) {
				encode();
			} else if (operation.equals("+")) {
				decode();
			} else {
				System.out.println("Wrong operation type.");
			}
		} else {
			System.out.println("Need Argument (-/+)");
		}
	}
}
