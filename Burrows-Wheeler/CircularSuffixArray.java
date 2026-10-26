import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {

	// Attributes..
	private String content;
	private Integer[] suffixArray;

	// circular suffix array of s
	public CircularSuffixArray(String s) {
		if (s == null) {
			throw new IllegalArgumentException("s is null");
		} else {
			this.content = s;
			this.suffixArray = new Integer[content.length()];
			for (int i = 0; i < content.length(); i++) {
				suffixArray[i] = i;
			}
			Comparator<Integer> suffixComparator = new Comparator<Integer>() {
				public int compare(Integer first, Integer second) {
					for (int i = 0; i < content.length(); i++) {
						char firstChar = content.charAt((first + i) % content.length());
						char secondChar = content.charAt((second + i) % content.length());
						if (firstChar < secondChar) {
							return -1;
						} else if (firstChar > secondChar) {
							return 1;
						}
					}
					return 0;
				}
			};
			Arrays.sort(suffixArray, suffixComparator);
		}
	}

	// length of s
	public int length() {
		return content.length();
	}

	// returns index of ith sorted suffix
	public int index(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("i is less than 0");
		} else if (i >= content.length()) {
			throw new IllegalArgumentException("i is out of range");
		} else {
			return suffixArray[i];
		}
	}

	// unit testing (required)
	public static void main(String[] args) {
		CircularSuffixArray circularSuffix = new CircularSuffixArray("ABRACADABRA!");
		System.out.println("Length: " + circularSuffix.length());
		for (int i = 0; i < circularSuffix.length(); i++) {
			System.out.println("Index: " + i + ", Value: " + circularSuffix.index(i));
		}
	}
}
