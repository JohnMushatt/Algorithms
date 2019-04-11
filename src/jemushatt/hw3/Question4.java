package jemushatt.hw3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdIn;

/**
 * If you place the "tale.txt" file as a top-level file in your MyCS2223
 * project, it will be found
 */
public class Question4 {

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream(new File("tale.txt")));
		String[] strings = StdIn.readAllStrings();

		// First Construct the Binary Search Tree from these Strings where
		// the associated value is the total number of times the key appeared
		// in "The Tale Of Two Cities".
		BST<String, Integer> bt = new BST<String, Integer>();
		HashMap<String, Integer> seen = new HashMap<String, Integer>();
		for (int i = 0; i < strings.length; i++) {
			String word = strings[i];
			Integer count = new Integer(1);
			if (seen.get(word) == null) {

				for (int j = 0; j < strings.length; j++) {
					if (j != i) {
						if (strings[j].equals(word)) {
							count++;
						}
					}
					bt.put(word, count);
					seen.put(word, count);
				}
			}
			System.out.println(word);
		}

		// Now output the number of leaves in the tree.
		System.out.println(bt.numLeaves() + " leaves in the tree.");
		//HashMap<String,Integer> top10 = new HashMap<String,Integer>();
		for(int i =0; i < 10; i++) {
			String word = bt.maxValue();
			System.out.println("Word: "+word + "\tFrequency: " + bt.get(word));
			bt.delete(word);
		}
		// Now output the top ten most common words. You can do this by repeatedly
		// deleting
		// the key value that has the maxValue in the Tree.
	}

}
