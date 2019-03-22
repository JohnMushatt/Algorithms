package jemushatt.hw1;

import algs.hw1.arraysearch.SpiralArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class SpiralArraySolution extends SpiralArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public SpiralArraySolution(int[][] a) {
		super(a);
	}

	int min = Integer.MAX_VALUE;
	int max = Integer.MIN_VALUE;

	/**
	 * First compute MIN and MAX and filter out immediately based on these values.
	 * This increases efficiency and only requires storing two additional values
	 * (min and max).
	 *
	 * If you rename this method to 'locate', then this implementation will override
	 * the default implementation of locate found in ArraySearch.
	 */
	public int[] locate(int target) {
		int n = this.length();

		// On the very first time that locate() is called, compute the min
		// and max. Cost is a full inspection of every square in the nxn array.
		if (min == Integer.MAX_VALUE) {
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					int val = inspect(r, c);
					if (val < min) {
						min = val;
					}
					if (val > max) {
						max = val;
					}
				}
			}
		}

		if (target < min) {
			return null;
		}
		if (target > max) {
			return null;
		}

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (inspect(r, c) == target) {
					return new int[] { r, c };
				}
			}
		}

		return null; // not found
	}

	/** Be sure that you call your class constructor. Do not modify this method. */
	public static void main(String args[]) {
		int[][] ar = SpiralArraySearch.create(13);
		new SpiralArraySolution(ar).trial();
	}
}
