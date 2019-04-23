package jemushatt.hw5;

import algs.days.day19.Graph;
import edu.princeton.cs.algs4.*;

/**
 * Compute the BreadthFirst distance and DepthFirst distance for each vertex from the initial vertex 0.
 * Call these bfsDistTo[v] and dsfDistTo[v]. 
 * 
 * Observe that bfsDistTo[v] is always smaller than or equal to dfsDistTo[v].
 * Now define excess[v] = dfsDistTo[v] - bfsDistTo[v]. This assignment asks you to compute the
 * sum total of excess[v] for all vertices in the graph G.
 * 
 * Note that it is possible that some vertices are unreachable from s, and thus the dfsDistTo[v] and
 * bfsDistTo[v] would both be INFINITY. 
 */
public class SearchCompare {
	
	public static int excess(Graph G, int s) {
		// fill in here
		return -1;
	}
	

	public static void main(String[] args) {
		String input;
		if (args.length != 0) {
			input = args[0];
		} else {
			input = "tinyG.txt";
		}
		In in = new In(input);
		Graph g = new Graph(in);

		// Compute and report Excess on tinyG.txt by default
		System.out.println("Excess:" + SearchCompare.excess(g, 0));
		
		for (int N = 4; N <= 1024; N *= 2) {
			System.out.print(N + "\t" );
			for (double p = 0.5; p <= 1.0; p += 0.5) {
				Graph gr = new Graph(N);
				
				// every possible edge exists with probability p
				for (int i = 0; i < N-1; i++) {
					for (int j = i+1; j < N; j++) {
						if (Math.random() < p) {
							gr.addEdge(i, j);
						}
					}
				}
				
				System.out.print(SearchCompare.excess(gr, 0) + "\t");
			}
			System.out.println();
			
		}
	}

}