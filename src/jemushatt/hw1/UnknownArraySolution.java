package jemushatt.hw1;

import algs.hw1.arraysearch.UnknownArraySearch;

/**
 * This is provided as an example to show how you will modify the necessary classes
 * for this assignment.
 *
 * You do not need to copy or modify this method for this assignment.
 */
public class UnknownArraySolution extends UnknownArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public UnknownArraySolution(int[][] a) {
		super(a);
	}

	/** Be sure that you call your class constructor. Do not modify this method. */
	public static void main (String args[]) {
		for(int i = 1; i < 25;i++) {
			int[][] ar = UnknownArraySearch.create(i);
			new UnknownArraySolution(ar).trial();
		}
	}
}
