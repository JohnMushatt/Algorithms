package jemushatt.hw1;

import algs.hw1.arraysearch.RowOrderedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class RowOrderedArraySolution extends RowOrderedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public RowOrderedArraySolution(int[][] a) {
		super(a);
	}

	/**
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {

		int n = this.length();
		int max = n - 1;
		int min = 0;

		int start = 0;
		int mid, row, col, value;
		int end = n * n - 1;
		while (start <= end) {
			mid = start + (end - start) / 2;
			row = mid / n;
			col = mid % n;
			value = inspect(row, col);

			if (value == target)
				return new int[] { row, col };
			if (value > target)
				end = mid - 1;
			else
				start = mid + 1;
		}
		return null;
		/*
		 * while(target < inspect(max,0)) { if(max!=0) { max-=1; } else { break; } }
		 * for(int r = 0; r < n; r++) { if(target <= inspect(r,n-1)) { int low= 0; int
		 * high = n-1; while(low<=high) { int mid = (low+high) / 2; int rc =
		 * inspect(r,mid) - target; if(rc < 0) { low = mid+1;
		 *
		 * } else if (rc > 0) { high = mid -1;
		 *
		 * } else { return new int[] {r,mid}; } } } }
		 */
		// return null;
	}

	/** Be sure that you call your class constructor. Do not modify this method. */
	public static void main(String args[]) {
		int[][] ar = RowOrderedArraySearch.create(13);
		new RowOrderedArraySolution(ar).trial();
	}
}
