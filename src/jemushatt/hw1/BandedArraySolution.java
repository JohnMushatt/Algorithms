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
		int rowMax =this.length()-1;
		int rowMin =0;
		int n = this.length();
		
		if(target  < inspect(n/2,0)) {
			
			
			for(int r = rowMin; r < rowMax; r++) {
				
				
				if(target <= inspect(r,n-1)) {
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
			}
		}
		return null;
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = BandedArraySearch.create(13);
		new BandedArraySolution(ar).trial();
	}
}
