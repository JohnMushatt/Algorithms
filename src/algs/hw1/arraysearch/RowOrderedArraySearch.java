package algs.hw1.arraysearch;

/**
 * Design a subclass that extends this class to complete this assignment.
 * 
 * You should not copy this class into your USERID.hw1 package. You should make no modifications
 * to this class.
 * 
 * A RowOrderedArray is a two-dimensional, square (N-by-N) array of unique integers.
 * The properties of a RowOrderedArray is as follows:
 * 
 *   1. Each row contains ascending values from left to right.
 *   2. Each of the values in row 0 <= k < (n-1) are smaller than the values in row (k+1)
 *      
 *  Here is a sample:
 *  
 *       1  3  9 11 12
 *      13 23 24 25 29
 *      35 36 37 44 47
 *      48 52 54 55 60
 *      63 72 77 78 79
 *      
 * DO NOT MODIFY THIS CLASS. DO NOT COPY INTO YOUR PROJECT.
 */
public abstract class RowOrderedArraySearch extends ArraySearch {

	public RowOrderedArraySearch(int[][] a) {
		super(a);
	}

	/** Small example to use. Do not modify these values. */
	public static final int[][] sample = new int[][] {
		{ 1,  3,  9,  11, 12},
		{ 13, 23, 24, 25, 29},
		{ 35, 36, 37, 44, 47},
		{ 48, 52, 54, 55, 60},
		{ 63, 72, 77, 78, 79}
	};
	
	/** larger example to use. Do not modify these values. */
	static final int[][] big = new int[][] { 
		 { 1,  2,  3,  4,  5,  6,  7},
		 { 8,  9, 10, 11, 12, 13, 14},
		 {15, 16, 17, 18, 19, 20, 21},
		 {22, 23, 24, 25, 26, 27, 28},
		 {29, 30, 31, 32, 33, 34, 35},
		 {36, 37, 38, 39, 40, 41, 42},
		 {43, 44, 45, 46, 47, 48, 49},
	};
	
	/** final example to use. Do not modify these values. */
	static final int[][] run = new int[][] { 
		 { 4, 10, 20, 24, 26, 27, 29},
		 {31, 33, 35, 36, 37, 38, 40},
		 {42, 44, 45, 46, 48, 49, 50},
		 {52, 53, 55, 56, 58, 59, 60},
		 {61, 63, 64, 65, 67, 68, 70},
		 {71, 73, 74, 75, 77, 78, 79},
		 {81, 82, 83, 84, 87, 88, 89},
	};
	
	/**
	 * Create a sample RowOrdered Array.
	 * 
	 * @param n   Size of desired 2d array
	 * @return    A valid RowOrderedArray of size nxn
	 */
	public static final int[][] create(int n) {
		int[][] a = new int[n][n];
		
		int val = 1;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				a[r][c] = val;  val += (n-1); 
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
	 * @throws IllegalStateException if the array doesn't have proper structure.
	 */
	protected void checkProperty(int[][] a) {
		int n = a.length;
		int r = 0;
		int val = a[r][0]-1;
		for (int k = 1; k <n; k++) {
			for (int c = 0; c < n; c++) {
				if (a[r][c] <= val) {
					throw new IllegalStateException ("Array is not RowOrdered [" + r + "," + c + "]");
				}
				val = a[r][c];
			}
			r++;
		}
	}
}
