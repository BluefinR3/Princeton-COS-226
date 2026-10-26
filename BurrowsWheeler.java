import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BurrowsWheeler {

	// Constants.
	private static final int SEQUENCE_LIMIT = 256;

	// apply Burrows-Wheeler transform, reading from standard input and writing to
	// standard output
	public static void transform() {
		String dataInput = StdIn.readString();
		CircularSuffixArray circularSuffix = new CircularSuffixArray(dataInput);
		for (int i = 0; i < circularSuffix.length(); i++) {
			if (circularSuffix.index(i) == 0) {
				StdOut.print(i + " ");
				break;
			}
		}
		for (int i = 0; i < circularSuffix.length(); i++) {
			StdOut.print(dataInput
					.charAt((circularSuffix.index(i) + circularSuffix.length() - 1) % circularSuffix.length()));
		}
	}

	// apply Burrows-Wheeler inverse transform, reading from standard input and
	// writing to standard output
	public static void inverseTransform() {
		int row = StdIn.readInt();
		String transformed = StdIn.readString();
		int[] counting = new int[SEQUENCE_LIMIT + 1];
		int[] after = new int[transformed.length()];
		char[] inverseTransformed = new char[transformed.length()];

		for (int i = 0; i < transformed.length(); i++) {
			int index = transformed.charAt(i) + 1;
			counting[index] = counting[index] + 1;
		}
		for (int i = 0; i < SEQUENCE_LIMIT; i++) {
			counting[i + 1] += counting[i];
		}
		for (int i = 0; i < transformed.length(); i++) {
			int index = counting[transformed.charAt(i)]++;
			inverseTransformed[index] = transformed.charAt(i);
			after[index] = i;
		}
		int index = row;
		for (int i = 0; i < transformed.length(); i++) {
			StdOut.print(inverseTransformed[index]);
			index = after[index];
		}
	}

	// if args[0] is '-', apply Burrows-Wheeler transform
	// if args[0] is '+', apply Burrows-Wheeler inverse transform
	public static void main(String[] args) {
		if (args.length == 1) {
			String operation = args[0];
			if (operation.equals("-")) {
				transform();
			} else if (operation.equals("+")) {
				inverseTransform();
			} else {
				System.out.println("Wrong operation type.");
			}
		} else {
			System.out.println("Need Argument (-/+)");
		}
	}
}
