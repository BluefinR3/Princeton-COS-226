import edu.princeton.cs.algs4.Picture;

import java.io.File;

// SeamCarver..
public class SeamCarver {

	// Attributes..
	private Picture currentPicture;
	private double[][] calculatedEnergy;
	private static final double BORDER_PIXEL = 1000.0;

	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		if (picture == null) {
			throw new IllegalArgumentException("null picture");
		}
		this.currentPicture = new Picture(picture);
		this.calculatedEnergy = new double[picture.width()][picture.height()];
		findEnergy();
	}

	// current picture
	public Picture picture() {
		return new Picture(this.currentPicture);
	}

	// width of current image
	public int width() {
		return this.currentPicture.width();
	}

	// height of current image
	public int height() {
		return this.currentPicture.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x < 0) {
			throw new IllegalArgumentException("negative x");
		} else if (x >= width()) {
			throw new IllegalArgumentException("x more than width");
		} else if (y < 0) {
			throw new IllegalArgumentException("negative y");
		} else if (y >= height()) {
			throw new IllegalArgumentException("y more than height");
		} else {
			return this.calculatedEnergy[x][y];
		}
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new SeamCarver(switchRowColumns(currentPicture)).findVerticalSeam();
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		int w = width(), h = height();
		double[][] distances = new double[w][h];
		int[] seamArray = new int[h];
		int[][] edges = new int[w][h];
		int smallestDistance = 0;
		int[] offsets = {-1, 0, 1};

		for (int x = 0; x < w; x++) {
			distances[x][0] = calculatedEnergy[x][0];
			for (int y = 1; y < h; y++) distances[x][y] = Double.POSITIVE_INFINITY;
		}

		for (int y = 0; y < h - 1; y++) {
			for (int x = 0; x < w; x++) {
				double eachDist = distances[x][y];
				if (eachDist == Double.POSITIVE_INFINITY) {
					continue;
				}
				for (int eachOffset : offsets) {
					int updtX = x + eachOffset;
					int updtY = y + 1;
					if (updtX < 0 || updtX >= w) continue;
					double target = eachDist + calculatedEnergy[updtX][updtY];
					if (target < distances[updtX][updtY]) {
						distances[updtX][updtY] = target;
						edges[updtX][updtY] = x;
					}
				}
			}
		}

		for (int x = 1; x < w; x++) {
			if (distances[x][h - 1] < distances[smallestDistance][h - 1]) {
				smallestDistance = x;
			}
		}
		int height = smallestDistance;
		for (int y = h - 1; y >= 0; y--) {
			seamArray[y] = height;
			if (y > 0) {
				height = edges[height][y];
			}
		}
		return seamArray;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
		if (seam == null) {
			throw new IllegalArgumentException("null seam");
		} else if (height() <= 1) {
			throw new IllegalArgumentException("height <= 1");
		}
		SeamCarver seamCarver = new SeamCarver(switchRowColumns(currentPicture));
		seamCarver.removeVerticalSeam(seam);
		this.currentPicture = switchRowColumns(seamCarver.currentPicture);
		this.calculatedEnergy = new double[width()][height()];
		findEnergy();
	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
		if (seam == null) {
			throw new IllegalArgumentException("null seam");
		} else if (width() <= 1) {
			throw new IllegalArgumentException("width <= 1");
		} else if (seam.length != currentPicture.height()) {
			throw new IllegalArgumentException("wrong height");
		} else {
			Picture modifiedPicture = new Picture(currentPicture.width() - 1, currentPicture.height());
			for (int j = 0; j < height(); j++) {
				int currentColumn = 0;
				for (int i = 0; i < width(); i++) {
					if (i != seam[j]) {
						modifiedPicture.set(currentColumn++, j, currentPicture.get(i, j));
					}
				}
			}
			this.currentPicture = modifiedPicture;
			this.calculatedEnergy = new double[width()][height()];
			findEnergy();
		}
	}

	// It will switch the rows to columns.
	private Picture switchRowColumns(Picture picture) {
		Picture modifiedPicture = new Picture(picture.height(), picture.width());
		for (int i = 0; i < picture.height(); i++) {
			for (int j = 0; j < picture.width(); j++) {
				modifiedPicture.set(i, j, picture.get(j, i));
			}
		}
		return modifiedPicture;
	}

	// It will calculate and set the energy.
	private void findEnergy() {
		int w = width(), h = height();
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				if (i == 0 || i == w - 1 || j == 0 || j == h - 1) {
					calculatedEnergy[i][j] = BORDER_PIXEL;
				} else {
					int gradientLeft = currentPicture.get(i - 1, j).getRGB();
					int gradientRight = currentPicture.get(i + 1, j).getRGB();
					int gradientTop = currentPicture.get(i, j - 1).getRGB();
					int gradientBottom = currentPicture.get(i, j + 1).getRGB();

					int differenceRedX = ((gradientRight >> 16) & 0xFF) - ((gradientLeft >> 16) & 0xFF);
					int differenceRedY = ((gradientBottom >> 16) & 0xFF) - ((gradientTop >> 16) & 0xFF);
					int differenceGreenX = ((gradientRight >> 8) & 0xFF) - ((gradientLeft >> 8) & 0xFF);
					int differenceGreenY = ((gradientBottom >> 8) & 0xFF) - ((gradientTop >> 8) & 0xFF);
					int differenceBlueX = (gradientRight & 0xFF) - (gradientLeft & 0xFF);
					int differenceBlueY = (gradientBottom & 0xFF) - (gradientTop & 0xFF);

					calculatedEnergy[i][j] = Math.sqrt((differenceRedX * differenceRedX
							+ differenceGreenX * differenceGreenX + differenceBlueX * differenceBlueX)
							+ (differenceRedY * differenceRedY + differenceGreenY * differenceGreenY
									+ differenceBlueY * differenceBlueY));
				}
			}
		}
	}

	// unit testing (required)
	public static void main(String[] args) {

		File file = new File("HJoceanSmall.png");
		if (file.exists()) {
			Picture picture = new Picture("HJoceanSmall.png");
			SeamCarver seamCarver = new SeamCarver(picture);
			System.out.println("Width: " + seamCarver.width() + ", Height: " + seamCarver.height());
			System.out.println("Energy at (1, 1): " + seamCarver.energy(1, 1));
			// Removing vertical and horizontal seam.
			for (int i = 0; i < 100; i++) {
				seamCarver.removeVerticalSeam(seamCarver.findVerticalSeam());
			}
			for (int i = 0; i < 50; i++) {
				seamCarver.removeHorizontalSeam(seamCarver.findHorizontalSeam());
			}
			System.out.println("Updated => Width: " + seamCarver.width() + ", Height: " + seamCarver.height());
			Picture resized = seamCarver.picture();
			resized.save("HJoceanSmall_Updated.png");
			resized.show();
		} else {
			System.out.println("File HJoceanSmall.png does not exists!");
		}

	}

}
