package jemushatt.hw5;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/** Standard undirected Grah implementation, as starting point for Q2 and Q3 on HW5. */
public class Graph {
    
    final int V;
    int E;
    Bag<Integer>[] adj;
    
    /**
     * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if <tt>V</tt> < 0
     */
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /** Added this method for day20 to load graph from file. */
    public Graph (In in) {
    	this (in.readInt());
    	int E = in.readInt();
    	for (int i = 0; i < E; i++) {
    		int v = in.readInt();
    		int w = in.readInt();
    		addEdge (v,w);
    	}
    }

    public int V() { return V; }
    public int E() { return E; }


    /** Adds the undirected edge v-w to this graph. */
    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


    /** Returns the vertices adjacent to vertex <tt>v</tt>. */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /** Returns the degree of vertex <tt>v</tt>. */
    public int degree(int v) {
        return adj[v].size();
    }

    /** Fill in this method to determine if undirected graph is connected. */
    public boolean connected() {
    	// TODO: REPLACE WITH YOUR CODE
    	return false;
    }

    /** 
     * The diameter of graph is the maximum distance between any pair of vertices. 
     * 
     * If a graph is not connected, then Integer.MAX_VALUE must be returned.
     * @return
     */
    public int diameter() {
    	// TODO: REPLACE WITH YOUR CODE
    	return 0;
    }
    
    /** 
     * The status of a given vertex, v.
     * 
     * If the graph is not connected, then this method is not responsible for return value.
     */
    public int status(int v) {
    	// TODO: REPLACE WITH YOUR CODE
    	return 0;
    }
    
    /**
     * Determine if all status(v) values within the graph represent different values.
     * 
     */
    public boolean statusInjective() {
    	// FIX
    	return false;
    }
    
    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
