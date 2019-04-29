package jemushatt.hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import algs.days.day12.SeparateChainingHashST;
// Note that the Day18 implementation of AVL removes <Key,Value> and only uses <Key>
import algs.days.day18.AVL;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Modify this class for problem 1 on HW5.
 */
public class WordLadder {

	/**
	 * Represent the mapping of (uniqueID, 4-letter word)
	 */
	static SeparateChainingHashST<String, Integer> table = new SeparateChainingHashST<String, Integer>();
	static SeparateChainingHashST<Integer, String> reverse = new SeparateChainingHashST<Integer, String>();
	static AVL<String> avl = new AVL<String>();

	/**
	 * Determine if the two same-sized words are off by just a single character.
	 */
	public static boolean offByOne(String w1, String w2) {
		int count = 0;
		for (int i = 0; i < w1.length(); i++) {
			// Check if the i-th char of w1 and w2 are equal, if count is greater than 1 we
			// are not offByOne, same if 0
			if (w2.charAt(i) != w1.charAt(i)) {
				count++;
			}

		}
		return count == 1;
	}

	/**
	 * Build a queue of words that are 1 off of the current src word
	 * 
	 * @param word Word to generate set off of
	 * @return Queue of words that return true from offByOne
	 */
	private static Queue<String> nextSet(String word) {
		Queue<String> set = new Queue<String>();
		for (String str : avl.keys()) {
			if (offByOne(word, str)) {
				set.enqueue(str);
			}
		}
		return set;
	}

	/**
	 * BFS for Word Ladder question
	 * 
	 * @param w1 Source word
	 * @param w2 Target Word
	 * @return true if w1 can reach w2
	 */
	public static ArrayList<String> bfs(String w1, String w2) {
		// breadth first path
		class temp {
			String word;
			int length;

			public temp(String word, int length) {
				this.word = word;
				this.length = length;
			}

			public String toString() {
				return this.word + ":" + this.length;
			}
		}
		ArrayList<String> path = new ArrayList<String>();
		Queue<temp> q = new Queue<temp>();
		temp item = new temp(w1, 1);
		q.enqueue(item);
		boolean f = false;
		while (q.isEmpty() == false && f == false) {
			temp currentItem = q.dequeue();
			Queue<String> set = nextSet(currentItem.word);
			for (String str : avl.keys()) {
				if (offByOne(currentItem.word, str)) {
					item.word = str;
					item.length = currentItem.length + 1;
					q.enqueue(item);
					avl.fastDelete(item.word);
					if (currentItem.word.equals(w2)) {
						f = true;
						break;
					}
				}
			}
		}
		while (q.isEmpty() == false) {
			path.add(q.dequeue().word);
		}
		return path;
	}

	/**
	 * Main method to execute.
	 *
	 * From console, enter the start and end of the word ladder.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// Use this to contain all four-letter words that you find in dictionary
		// AVL<String> avl = new AVL<String>();

		// create a graph where each node represents a four-letter word.
		// Also construct avl tree of all four-letter words.
		// Note: you will have to copy this file into your project to access it, unless
		// you
		// are already writing your code within the SedgewickAlgorithms4ed project.
		Scanner sc = new Scanner(new File("words.english.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.length() == 4) {

				avl.insert(s);
			}
		}
		sc.close();
		// now construct graph, where each node represents a word, and an edge exists
		// between
		// two nodes if their respective words are off by a single letter. Hint: use the
		// keys() method provided by the AVL tree.
		// fill in here...
		Integer count = 0;
		for (String str : avl.keys()) {
			if (str.length() == 4) {
				table.put(str, count);
				reverse.put(count, str);
				count++;
			}
		}
		StdOut.println("Enter word to start from (all in lower case):");
		String start = StdIn.readString().toLowerCase();
		StdOut.println("Enter word to end at (all in lower case):");
		String end = StdIn.readString().toLowerCase();

		// need to validate that these are both actual four-letter words in the
		// dictionary
		if (!avl.contains(start)) {
			StdOut.println(start + " is not a valid word in the dictionary.");
			System.exit(-1);
		}
		if (!avl.contains(end)) {
			StdOut.println(end + " is not a valid word in the dictionary.");
			System.exit(-1);
		}
		String min = avl.min();
		Graph g = new Graph(table.size());
		for(String str : avl.keys()) {
			for(String str2 : avl.keys(min, str)) {
				if(offByOne(str,str2)) {
					g.addEdge(table.get(str), table.get(str2));
				}
			}
		}
		BreadthFirstPaths bfp = new BreadthFirstPaths(g, table.get(start));
		if(bfp.hasPathTo(table.get(end))) {
			Iterable<Integer> it = bfp.pathTo(table.get(end));
			for(Integer i : it) {
				System.out.println(reverse.get(i));
			}
		}
		// Once both words are known to exist in the dictionary, then create a search
		// that finds shortest distance (should it exist) between start and end.
		// be sure to output the words in the word ladder, IN ORDER, from the start to
		// end.

		// fill in here...

		ArrayList<String> p = (bfs(start, end));
		for (String str : p) {
			System.out.println(str);
		}
	}
}
