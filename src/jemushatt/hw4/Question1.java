package jemushatt.hw4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * All you have to do for Question 1 is rearrange the values in line 17.
 */
public class Question1 {

	public static void main(String[] args) {

		AVL<Integer, Boolean> tree = new AVL<Integer, Boolean>();

		// find a different order to insert the numbers from 1 to 12 such that
		// you have no rotations. You ONLY have to modify the order in which these
		// twelve numbers appears below.
		int[] values = { 6, 3, 9, 1, 4, 7, 11, 2, 5, 8, 10, 12 };

		for (int i : values) {
			tree.put(i, true); // for this question, the value is ignored....
		}
		StdOut.println("Number of rotations:" + tree.rotations);
		StdOut.println("Height of tree:" + tree.height());

		// validate all values are there
		for (int i = 1; i <= 12; i++) {
			if (!tree.contains(i)) {
				StdOut.println("Error:  doesn't contain " + i);
			}
		}

		// Q1.2
		System.out.println("N\tMaxHt\tMaxRot");
		for (int i =1; Math.pow(2, i)-1 <= 4095; i++) {
			int maxH = 0;
			int maxR = 0;

			for(int test = 0; test < 1000; test++) {
				AVL<Integer, Boolean> testTree = new AVL<Integer, Boolean>();

				StdRandom.shuffle(values);
				for(int element: values) {
					testTree.put(element, true);
				}
				if(testTree.rotations>maxR) {
					maxR= testTree.rotations;
				}
				if(tree.height()>maxH) {
					maxH=testTree.height();
				}
			}
			System.out.println((int)Math.pow(2, i)-1+"\t"+maxH+"\t"+maxR);
		}
	}
}
