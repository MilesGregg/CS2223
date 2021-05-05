package mgregg.hw5;

import algs.days.day18.AVL;
import algs.hw5.Dictionary;
import edu.princeton.cs.algs4.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * More complicated ZIPPER: Bonus Questions only
 */
public class BonusWordZipper {

	/**
	 * Represent the mapping of (uniqueID, 3- and 4-letter words) from String <-> Integer where Integer is vertex id
	 */
	static SeparateChainingHashST<String,Integer> map = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();

	/** Store all three-letter and four-letter words (in lowercase). */
	static AVL<String> avl;

	/**
	 * Return a Queue of words that result by adding a single letter to the three letter word.
	 *
	 * There are 4*26 possible words that could result by adding a single letter (a-z) at each of the
	 * four possible spots
	 *
	 *      E A T
	 *
	 *     SEAT
	 *      ERAT
	 *       EAST
	 *        EATS
	 *
	 * It is acceptable for this method to return duplicates in the queue.
	 *
	 * For example, if the word is "BET" then it could include in its response
	 * "BEET" (where the E is inserted between the B and E) and "BEET" (where
	 * the E is inserted between the E and the T).
	 */
	public static Queue<String> addOne(String three) {
		Queue<String> queue = new Queue<>();
		for (int i = 0; i < three.length(); i++) {
			for (int j = 0; j < 26; j++) {
				char newChar = (char) ('a' + j);
				StringBuilder newWord = new StringBuilder(three);
				newWord.insert(i, newChar);

				if (map.contains(newWord.toString())) {
					queue.enqueue(newWord.toString());
				}
			}
		}

		return queue;
	}

	/**
	 * Return valid words by removing one of the four letters.
	 *
	 * It is acceptable for this method to return duplicates in the queue.
	 * For example, if the word is 'BEET' then the words returned could
	 * be {"BEE", "BET", "BET"}
	 */
	public static Queue<String> removeOne(String four) {
		Queue<String> queue = new Queue<>();
		for (int i = 0; i < four.length(); i++) {
			StringBuilder newWord = new StringBuilder(four);
			newWord.deleteCharAt(i);
			//System.out.println(newWord.toString() + " : " + " map contains: " + map.contains(newWord.toString()));

			if (map.contains(newWord.toString())) {
				queue.enqueue(newWord.toString());
			}
		}

		return queue;
	}

	private static boolean hasPath(Graph graph, int start, int target) {
		for (int i : graph.adj(start)) {
			if (i == target) return true;
		}
		return false;
	}

	/**
	 * Main method to execute.
	 *
	 * From console, enter the start and end of the word zipper.
	 *
	 * Can you beat  117.875 seconds for computing RESTAFF to SHERIFF
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String word1 = "restaff";
		String word2 = "sheriff";

		avl = new AVL<String>();

		StopwatchCPU cpu = new StopwatchCPU();

		Scanner sc = Dictionary.words();

		// do all work here
		int i = 0;

		while (sc.hasNext()) {
			String currentWord = sc.next();
			if (currentWord.length() <= 7) {
				avl.insert(currentWord);
				map.put(currentWord, i);
				reverse.put(i, currentWord);
				i++;
			}
		}

		// now construct graph, where each node represents a word, and an edge exists between
		// two nodes if their respective words are off by a single letter. Hint: use the
		// keys() method provided by the AVL tree. Your graph will be an undirected graph.

		// TODO: FILL IN HERE
		Graph words = new Graph(i);
		for (String word : avl.keys()) {
			if (word.length() == 4) {
				Queue<String> queue = removeOne(word);
				while (!queue.isEmpty()) {
					String addedWord = queue.dequeue();
					int start = map.get(word);
					int end = map.get(addedWord);
					if (!hasPath(words, start, end)) {
						words.addEdge(map.get(word), map.get(addedWord));
					}
				}
			} else {
				Queue<String> queue = addOne(word);
				while (!queue.isEmpty()) {
					String addedWord = queue.dequeue();
					int start = map.get(word);
					int end = map.get(addedWord);
					if (!hasPath(words, start, end)) {
						words.addEdge(map.get(word), map.get(addedWord));
					}
				}
			}
		}

		System.out.println("Edge Size: " + words.E());

		sc.close();

		BreadthFirstPaths path = new BreadthFirstPaths(words, map.get(word1));
		for (int j : path.pathTo(map.get(word2))) {
			System.out.println(reverse.get(j));
		}

		System.out.println(cpu.elapsedTime() + " seconds");
	}
}
