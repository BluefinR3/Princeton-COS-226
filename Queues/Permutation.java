import java.util.Scanner;

// Permutation 
public class Permutation {

	// Main method.
	public static void main(String[] args) {

		if (args.length == 1) {
			RandomizedQueue<String> queue = new RandomizedQueue<>();
			int numberOfRandoms = Integer.parseInt(args[0]);
			Scanner scan = new Scanner(System.in);
			String[] tokens = scan.nextLine().split(" ");
			for (int i = 0; i < tokens.length; i++) {
				queue.enqueue(tokens[i]);
			}
			for (int i = 0; i < numberOfRandoms; i++) {
				System.out.println(queue.dequeue());
			}
			scan.close();
		} else {
			throw new IllegalArgumentException("Number of arguments should be only 1");
		}

	}
	
}
