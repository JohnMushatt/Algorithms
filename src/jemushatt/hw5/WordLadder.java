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
	static SeparateChainingHashST<String,Integer> table = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();
	static 	AVL<String> avl = new AVL<String>();

	/**
	 * Determine if the two same-sized words are off by just a single character.
	 */
	public static boolean offByOne(String w1, String w2) {
		int count =0;
		for(int i = 0; i < w1.length();i++) {
			//Check if the i-th char of w1 and w2 are equal, if count is greater than 1 we are not offByOne, same if 0
			if(w2.charAt(i)!=w1.charAt(i)) {
				count++;
			}

		}
		return count==1;
	}
	/**
	 * Build a queue of words that are 1 off of the current src word
	 * @param word Word to generate set off of
	 * @return Queue of words that return true from offByOne
	 */
	private static Queue<String> nextSet (String word) {
		Queue<String> set = new Queue<String>();
		for(String str : avl.keys()) {
			if(offByOne(word,str)) {
				set.enqueue(str);
			}
		}
		return set;
	}
	/**
	 * BFS for Word Ladder question
	 * @param w1 Source word
	 * @param w2 Target Word
	 * @return Shortest path between source and target if it exits
	 */
	public static ArrayList<String> bfs(String w1, String w2) {
		ArrayList<String> path = new ArrayList<String>();
		boolean found = false;
		Queue<String> toVisit = new Queue<String>();
		toVisit.enqueue(w1);
		ArrayList<String> visited = new ArrayList<String>();
		int shortestDistance = Integer.MAX_VALUE;
		int currentPathDistance = 0;
		//If we have not found the path
		while(!toVisit.isEmpty() && !found) {
			//Dequeue next element from toVisit
			String word = toVisit.dequeue();
			//Add it to the already visited list
			visited.add(word);

			//Get the words that are offByOne of the current word
			Queue<String> closeWords = nextSet(word);
			while(!closeWords.isEmpty())  {
				toVisit.enqueue(closeWords.dequeue());
			}
			//Check if we have arrived
			if(word.equals(w2)) {
				found = true;
				if(currentPathDistance < shortestDistance) {
					shortestDistance = currentPathDistance;
					return path;
				}
			}

			currentPathDistance++;
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
		//AVL<String> avl = new AVL<String>();

		// create a graph where each node represents a four-letter word.
		// Also construct avl tree of all four-letter words.
		// Note: you will have to copy this file into your project to access it, unless you
		// are already writing your code within the SedgewickAlgorithms4ed project.
		Scanner sc = new Scanner(new File ("words.english.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.length() == 4) {

				avl.insert(s);
			}
		}
		sc.close();
		// now construct graph, where each node represents a word, and an edge exists between
		// two nodes if their respective words are off by a single letter. Hint: use the
		// keys() method provided by the AVL tree.
		// fill in here...
		Integer count = 1;
		for(String str : avl.keys()) {

			table.put(str,count);
			reverse.put(count,str);
			count++;
		}

		StdOut.println("Enter word to start from (all in lower case):");
		String start = StdIn.readString().toLowerCase();
		StdOut.println("Enter word to end at (all in lower case):");
		String end = StdIn.readString().toLowerCase();

		// need to validate that these are both actual four-letter words in the dictionary
		if (!avl.contains(start)) {
			StdOut.println (start + " is not a valid word in the dictionary.");
			System.exit(-1);
		}
		if (!avl.contains(end)) {
			StdOut.println (end + " is not a valid word in the dictionary.");
			System.exit(-1);
		}

		// Once both words are known to exist in the dictionary, then create a search
		// that finds shortest distance (should it exist) between start and end.
		// be sure to output the words in the word ladder, IN ORDER, from the start to end.

		// fill in here...

		ArrayList<String> path = bfs(start,end);
	}
}
