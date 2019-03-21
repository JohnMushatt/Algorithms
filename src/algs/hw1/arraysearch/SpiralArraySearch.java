package algs.hw1.arraysearch;


/**
 * This is an optional assignment.
 * 
 * Design a subclass that extends this class.
 * 
 * You do not need to copy this class into your USERID.hw1 package. You should make no modifications
 * to this class.
 * 
 * A SpiralArray is a two-dimensional, square (N-by-N) array of unique integers where N is an odd number.
 * The properties of a SpiralArray is as follows:
 * 
 *   1. The smallest value is in the innermost cell A[N/2][N/2]
 *   2. The largest value is in the lower right cell A[N-1][N-1]
 *   3. All values appear in ascending order in a counterclockwise spiral from the innermost cell
 *      to the lower right cell.
 *      
 *  Here is a sample:
 *  
 *      17 16 15 14 13
 *      18  5  4  3 12
 *      19  6  1  2 11
 *      20  7  8  9 10
 *      21 22 23 24 25
 *      
 * Note that the innermost cell contains the value 1, while the lower right cell contains 25. There are
 * N^2=25 values in this array. 
 * 
 * Note this structure forms the basis of the Ulam Spiral (https://en.wikipedia.org/wiki/Ulam_spiral)
 * 
 * DO NOT MODIFY THIS CLASS. DO NOT COPY INTO YOUR PROJECT.
 */
public abstract class SpiralArraySearch extends ArraySearch {

	public SpiralArraySearch(int[][] a) {
		super(a);
	}

	/** 
	 * Small example to use. This represents the first 25 values in the Ulam Spiral.
	 * Do not modify these values.
	 *  
	 * https://en.wikipedia.org/wiki/Ulam_spiral  
	 */
	public static final int[][] sample = new int[][] {
		{ 17, 16, 15, 14, 13 },
		{ 18,  5,  4,  3, 12 },
		{ 19,  6,  1,  2, 11 },
		{ 20,  7,  8,  9, 10 },
		{ 21, 22, 23, 24, 25 }
	};
	
	/** final example to use. Do not modify these values. */
	public static final int[][] larger = new int[][] { 
		{ 61, 58, 57, 55, 50, 47, 46 },
		{ 64, 21, 20, 18, 17, 16, 45 },
		{ 65, 23,  5,  4,  3, 15, 43 },
		{ 68, 24,  6,  1,  2, 13, 42 },
		{ 70, 27,  7,  8, 10, 11, 40 },
		{ 72, 28, 30, 31, 35, 36, 38 },
		{ 76, 77, 79, 83, 87, 89, 97 }
	};
	
	/**
	 * Create a sample SpiralArray.
	 * 
	 * @param n   Size of desired 2d array
	 * @return    A valid SpiralArray of size nxn
	 */
	public static final int[][] create(int n) {
		if (n %2 != 1) { 
			throw new IllegalArgumentException ("Must be an odd-numbered array size.");
		}
		int[][] a = new int[n][n]; 
		
		int val = 1;
		int r = n/2;
		int c = n/2;
		a[r][c++] = val++;
		for (int k = 2; k <= n-1; k+= 2) {
			for (int i = 0; i < k; i++) {
				a[r--][c] = val++;
			}
			c--; r++;
			for (int i = 0; i < k; i++) {
				a[r][c--] = val++;
			}
			r++; c++;
			for (int i = 0; i < k; i++) {
				a[r++][c] = val++;
			}
			c++; r--;
			for (int i = 0; i < k; i++) {
				a[r][c++] = val++;
			}
		}
		
		return a;
	}
	
	/** 
	 * Ensures the array conforms to the problem specification:
	 * 
	 *   1. Array is a square 2D array
	 *   2. Values appear in ascending sorted order in each row
	 *   3. Values appear in ascending sorted order in each column
	 *   4. No value is duplicated in the array 
	 * 
	 * Throws Exception if a is not twice sorted or contains duplicate values, as per homework instructions.
	 * 
	 * @param a    array to be investigated
     * @throws IllegalStateException if the array doesn't have proper structure.
	 */
	protected void checkProperty(int[][] a) {
		int n = a.length;
		int r = n/2;
		int c = n/2;
		int val = a[r][c++];
		for (int k = 2; k <= n-1; k+= 2) {
			for (int i = 0; i < k; i++) {
				if (a[r][c] <= val) {
					throw new IllegalStateException ("Array is not SpiralArray [" + r + "," + c + "]");
				}
				val = a[r--][c];
			}
			c--; r++;
			for (int i = 0; i < k; i++) {
				if (a[r][c] <= val) {
					throw new IllegalStateException ("Array is not SpiralArray [" + r + "," + c + "]");
				}
				val = a[r][c--];
			}
			r++; c++;
			for (int i = 0; i < k; i++) {
				if (a[r][c] <= val) {
					throw new IllegalStateException ("Array is not SpiralArray [" + r + "," + c + "]");
				}
				val = a[r++][c];
			}
			c++; r--;
			for (int i = 0; i < k; i++) {
				if (a[r][c] <= val) {
					throw new IllegalStateException ("Array is not SpiralArray [" + r + "," + c + "]");
				}
				val = a[r][c++];
			}
		}
	}
}
