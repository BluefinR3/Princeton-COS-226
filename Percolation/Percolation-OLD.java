import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Percolation class.
public class Percolation {

	// Attributes..
	private WeightedQuickUnionUF unionFind, noBottomBackwash;
	private boolean[] sites;
	private int top, bottom, gridSize, openSites;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		if (n > 0) {
			this.gridSize = n;
			this.top = 0;
			this.bottom = n * n + 1;
			int numOfSites = this.bottom + 1;
			this.unionFind = new WeightedQuickUnionUF(this.bottom);
			this.noBottomBackwash = new WeightedQuickUnionUF(numOfSites);
			this.sites = new boolean[numOfSites];
			this.sites[this.top] = this.sites[this.bottom] = true;
		} else {
			throw new IllegalArgumentException("Wrong n");
		}
	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		int siteIndex = validateIndex(row, col);
		if (!sites[siteIndex]) {
			sites[siteIndex] = true;
			openSites++;
			if (row == 1) {
				noBottomBackwash.union(siteIndex, top);
				unionFind.union(siteIndex, top);
			}
			if (row == gridSize) {
				noBottomBackwash.union(siteIndex, bottom);
			}
			int[] xPositions = {-1, 1, 0, 0};
			int[] yPositions = {0, 0, -1, 1};
			for (int i = 0; i < xPositions.length; i++) {
				int x = row + xPositions[i];
				int y = col + yPositions[i];
				if (x > 0 && x <= gridSize && y > 0 && y <= gridSize && sites[validateIndex(x, y)]) {
					noBottomBackwash.union(siteIndex, validateIndex(x, y));
					unionFind.union(siteIndex, validateIndex(x, y));
				}
			}
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		return sites[validateIndex(row, col)];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		return this.unionFind.find(this.top) == this.unionFind.find(validateIndex(row, col));
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return this.openSites;
	}

	// does the system percolate?
	public boolean percolates() {
		return this.noBottomBackwash.find(this.top) == this.noBottomBackwash.find(this.bottom);
	}

	/*
	 * Helper Methods.
	 */
	private int validateIndex(int x, int y) {
		if (x < 1) {
			throw new IndexOutOfBoundsException("x is less than 1");
		} else if (x > this.gridSize) {
			throw new IndexOutOfBoundsException("x is more than size");
		} else if (y < 1) {
			throw new IndexOutOfBoundsException("y is less than 1");
		} else if (y > this.gridSize) {
			throw new IndexOutOfBoundsException("y is more than size");
		} else {
			return (x - 1) * this.gridSize + y;
		}
	}

}