package jemushatt.hw3;

import edu.princeton.cs.algs4.StdRandom;

/**
 * This is the template code for question 1.
 *
 * Be sure to Explain whether the empirical results support the proposition.
 *
 */
public class Question1 {
	public static void main(String[] args) {

		// for N in 16 .. 512

		//   for each N, do T=100 trials you want to keep track of
		//       what you believe to be the MOST number of exch invocations
		//       and most number of less invocations

		//       compute a random array of N uniform doubles

		//   Make sure you output for each N the maximum values you saw
		//   in a table like...
		//
		//       N   MaxComp    MaxExch
		//       16  22         8
		//     .....


		System.out.println("N\tMaxComp\tMaxEnch");

		for (int n = 16; n < 512; n = n * 2) {
			int maxExch =0;
			int maxComp = 0;
			for (int test = 0; test < 100; test++) {
				Comparable[] list = new Comparable[n];
				for (int elements = 0; elements < n; elements++) {
					list[elements] = (int) Math.floor(StdRandom.uniform() * n);
				}
				Heap.constructHeap(list);
				if(Heap.inspectCount > maxComp) {
					maxComp = Heap.inspectCount;
				}
				if(Heap.exchCount> maxExch) {
					maxExch = Heap.exchCount;
				}
				//System.out.println(n + "\t" + inspectCount + "\t" + exchCount);
				Heap.inspectCount = 0;
				Heap.exchCount = 0;

			}
			System.out.println(n + "\t" + maxComp + "\t" + maxExch);

		}
	}
}
