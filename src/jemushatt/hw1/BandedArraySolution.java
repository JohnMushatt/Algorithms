package jemushatt.hw1;

import algs.hw1.arraysearch.BandedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class BandedArraySolution extends BandedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public BandedArraySolution(int[][] a) {
		super(a);
	}

	/**
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
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
		/*
		 * int rowMax = this.length() - 1; int rowMin = 0; int mid = this.length() / 2;
		 * boolean bandFound = false; boolean targetFound = false; int n =
		 * this.length();
		 * 
		 * while (!bandFound) { int upper = inspect(0, mid); int lower = inspect(mid,
		 * 0); int middle = inspect(mid,mid); // If we are in the correct band if
		 * (target <= upper & target >= lower) {
		 * 
		 * if(target>middle) { int start = 0; int midPoint, row, col, value; int end = n
		 * * n - 1; while (start <= end) { midPoint = start + (end - start) / 2; row =
		 * midPoint / n; col = midPoint % n; value = inspect(row, mid);
		 * 
		 * if (value == target) return new int[] { row, mid }; if (value > target) end =
		 * midPoint - 1; else start = midPoint + 1; } } else if(target < middle) { int
		 * start = 0; int midPoint, row, col, value; int end = n * n - 1; while (start
		 * <= end) { midPoint = start + (end - start) / 2; row = midPoint / n; col =
		 * midPoint % n; value = inspect(row, mid);
		 * 
		 * if (value == target) return new int[] { row, mid }; if (value > target) end =
		 * midPoint - 1; else start = midPoint + 1; } } } // If target is still in an
		 * outer band else if (target > upper) { mid = (mid + n) / 2; } // If target is
		 * still in a lower band else if (target < lower) { mid = mid / 2; }
		 * 
		 * else { return null; } } return null;
		 */
	}

	/** Be sure that you call your class constructor. Do not modify this method. */
	public static void main(String args[]) {
		int[][] ar = BandedArraySearch.create(13);
		new BandedArraySolution(ar).trial();
	}
}
