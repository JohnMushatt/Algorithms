package algs.hw1.arraysearch;

/**
 * Generic engine for searching for target integer values within a 2d array containing unique integer
 * values.
 * 
 * There are a number of alternative strategies for storing these integers in the array, as handled
 * by the subclasses.
 * 
 * DO NOT MODIFY THIS CLASS. DO NOT COPY INTO YOUR PROJECT.
 */
public abstract class ArraySearch {
	
	/** Array containing the values. */
	final private int[][] arr;
	
	/** Number of inspections. */
	int numChecked = 0; 
	
	/** 
	 * Validates that array maintains Banded Array property.
	 * 
	 * Note: You do not need to modify this code.
	 */
	public ArraySearch(int[][] a) {
		this.arr = a;
		checkProperty(a);
	}
	
	/** 
	 * Return the contents of array [r][c] and increment count of such inspections. 
	 *
	 * FOR THIS HOMEWORK ASSIGNMENT, this is the only allowed way to retrieve the element at a[r][c].
	 * Note how we keep track of all array inspections.
	 */
	protected final int inspect(int r, int c) {
		numChecked++;
		return arr[r][c];
	}

	/** 
	 * Return the number of rows (or columns, since square array).
	 */
	public final int length() { return arr.length; }
	
	/** 
	 * Subclass must override this default implementation to find an improvement
	 * based upon the structure of the array.
	 *  
	 * You can assume that the nxn square array contains unique integer values between 
	 * (and including) 1 and n*n*n.
	 * 
	 * Note: To return an array of two values, use code that looks like this:
	 * 
	 *   return new int[] { r, c };
	 *   
	 * where 'r' and 'c' are the row and column of the desired value that you have found.
	 * 
	 * @param target value to be searched
	 * @return pair of integers r, c in array of integers for found location, or null if not found.
	 */
	public int[] locate(int target) {
		int n = this.length();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (inspect(r,c) == target) {
					return new int[] { r, c };
				}
			}
		}
		
		return null;  // not found
	}
	
	public int[] locateAltOne(int target) {
		int n = this.length();
		
		
		for(int r = 0; r < n; r++) {
			int low= 0;
			int high = n-1;
			while(low<=high) {
				int mid = (low+high) / 2;
				int rc = inspect(r,mid) - target;
				if(rc < 0) {
					low = mid+1;
					
				}
				else if (rc > 0) {
					high = mid -1;
					
				}
				else {
					return new int[] {r,mid};
				}
			}
		}
		
		return null;
	}
	/**
	 * This runs a trial looking for all integers from 1 up to and including 2^(2n)
	 * where n is the size of the square array (determined by length()).
	 * 
	 * It outputs the total number of inspections
	 * 
	 * @param max  The total number of integers to search (from 0 up to but not including max).
	 * @return The number of array inspections
	 * @throws IllegalStateException if the implementation is wrong.
	 */
	public final void trial() {
		numChecked = 0;
		int lastChecked = -1;
		int worstCase = -1;
		int numRight = 0;
		int n = length();
		int max = n*n*n;
		for (int i = 1; i <= max; i++) {
			lastChecked = numChecked;
			int[] spot = locate(i);
			int numToFind = numChecked - lastChecked;
			if (numToFind > worstCase) {
				worstCase = numToFind;
			}
			if (spot != null) {
				if (arr[spot[0]][spot[1]] != i) {
					throw new IllegalStateException("Trial returned wrong location for:" + i + " (" + spot[0] + "," + spot[1] + ")");
				} else {
					numRight++;
				}
			}
		}
		
		if (numRight != length() * length()) {
			throw new IllegalStateException("Only found " + numRight + " values.");
		}
		
		System.out.println("For " + max + " targets, the number of inspections was:" + numChecked + ", Worst Case:" + worstCase);
	}
	
	/**
	 * Delegate to subclass the responsibility of validating that the structure of the two-dimensional 
	 * array is valid. 
	 * 
	 * If not valid, then a runtime exception is thrown.
	 * @throws IllegalStateException if the array doesn't have proper structure.
	 */
	protected abstract void checkProperty(int[][] a);

	/**
	 * Retrieve the total number of inspections to date.
	 * 
	 * This method is really here only for HW2.
	 */
	public int numInspections() { return numChecked; }
	
}
