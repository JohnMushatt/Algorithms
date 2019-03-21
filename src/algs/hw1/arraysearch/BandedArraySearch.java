package algs.hw1.arraysearch;

/**
 * Design a subclass that extends this class to complete this assignment.
 * 
 * Do not copy this class into your USERID.hw1 package. Do not modify this class.
 * 
 * A BandedArray is a two-dimensional, square (N-by-N) array of unique integers.
 * The properties of a BandedArray is as follows:
 * 
 *   1. The smallest value is in the upper left cell A[0][0]
 *   2. The largest value is in the upper right cell A[0][N-1]
 *   3. Each of the upper-left K-by-K cells is smaller than any of the other N*N - K*K cells
 *   4. The values on the perimeter of each upper-left K-by-K square are (a) in ascending order
 *      from left to right; (b) are in ascending order vertically on the left side of the square
 *      and descending order vertically on the right side of the square.
 *      
 *  Here is a sample:
 *  
 *       1  4  9 16 25
 *       2  3  8 15 24
 *       5  6  7 14 23
 *      10 11 12 13 22
 *      17 18 19 20 21
 *
 * I call this a "Banded Array" because you can imagine growing this 5x5 array into a 6x6 array by 
 * adding a "band" of 5+1=6 numbers in a new row at the end of the array, which is closed by a new
 * column of 5 ascending values to form this 6x6 array. 
 * 
 *       1  4  9 16 25 36
 *       2  3  8 15 24 35
 *       5  6  7 14 23 34
 *      10 11 12 13 22 33
 *      17 18 19 20 21 32
 *      26 27 28 29 30 31
 *      
 * This array has some interesting properties:
 * 
 *     1. Each row contains ascending values from left to right
 *     2. The main diagonal (from top-left to bottom-right) has ascending values
 *     3. The values located in columns ABOVE the main diagonal are sorted vertically in descending order
 *     4. The values located in columns BELOW the main diagonal are sorted vertically in ascending order
 * 
 * DO NOT MODIFY THIS CLASS. DO NOT COPY INTO YOUR PROJECT.
 */
public abstract class BandedArraySearch extends ArraySearch {

	public BandedArraySearch(int[][] a) {
		super(a);
	}

	/** Small example to use. Do not modify these values. */
	public static final int[][] sample = new int[][] {
		 { 1,  4,  9, 16, 25, 36},
		 { 2,  3,  8, 15, 24, 35},
		 { 5,  6,  7, 14, 23, 34},
		 {10, 11, 12, 13, 22, 33},
		 {17, 18, 19, 20, 21, 32},
		 {26, 27, 28, 29, 30, 31}
	};
	
	/** larger example to use. Do not modify these values. */
	public static final int[][] larger = new int[][] { 
		 { 1,  4,  9, 16, 25, 36, 49},
		 { 2,  3,  8, 15, 24, 35, 48},
		 { 5,  6,  7, 14, 23, 34, 47},
		 {10, 11, 12, 13, 22, 33, 46},
		 {17, 18, 19, 20, 21, 32, 45},
		 {26, 27, 28, 29, 30, 31, 44},
		 {37, 38, 39, 40, 41, 42, 43},
	};
	
	/**
	 * Create a sample BandedArray.
	 * 
	 * @param n   Size of desired 2d array
	 * @return    A valid BandedArray of size nxn
	 */
	public static final int[][] create(int n) {
		int[][] a = new int[n][n];
		
		int val = 1;
		a[0][0] = val; val += (n-1);
		for (int r = 1; r < n; r++) {
			for (int i = 0; i < r; i++) {
				a[r][i] = val; val += (n-1);
			}
			a[r][r] = val; val += (n-1);
			for (int i = r-1; i >= 0; i--) {
				a[i][r] = val; val += (n-1);
			}
		}
		
		return a;
	}
	
	/** 
	 * Ensures the array conforms to the problem specification:
	 * 
	 * Throws Exception if a is not Banded Array or contains duplicate values, as per homework instructions.
	 * 
	 * @param a    array to be investigated
	 */
	protected void checkProperty(int[][] a) { 
		int n = a.length;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (inspect(r,c) <= 0) {
					throw new IllegalArgumentException ("Values must be positive:" + a[r][c]);
				}
				if (inspect(r,c) > n*n*n) {
					throw new IllegalArgumentException ("Values must be smaller than " + (n*n*n) + ":" + a[r][c]);
				}
			}
		}
		
		int r = 0;
		int c = 0;
		int val = a[r++][c];
		for (int k = 1; k <= n-1; k++) {
			// horizontal
			for (int i = 0; i < k; i++) {
				if (a[r][c] <= val) {
					throw new IllegalStateException ("Array is not BandedArray [" + r + "," + c + "]");
				}
				val = a[r][c++];
			}
			for (int i = 0; i <= k; i++) {
				if (a[r][c] <= val) {
					throw new IllegalStateException ("Array is not BandedArray [" + r + "," + c + "]");
				}
				val = a[r--][c];
			}
			c=0; r = k+1;
		}
	}
}
