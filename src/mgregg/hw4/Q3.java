package mgregg.hw4;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.StdRandom;

/**
 * How many random directed graphs of V vertices have a cycle? and are connected?
 *
 * Create a random graph by adding an edge between two vertices u and v with a probability
 * of 50%.
 *
 * Run the same trial, this time using graphs whose edges each have a probability of 1/N chance
 * of being present.
 */
public class Q3 {
	public static void main(String[] args) {
		for (int i = 2; i <= 15; i++) {
			int cycleSum = 0;
			for (int j = 0; j < 10000; j++) {
				Digraph digraph = new Digraph(i);
				for (int v = 2; v < i; v++) {
					if (Math.random() < 0.5) {
						digraph.addEdge(v, v*v-1);
					}
				}
				DirectedCycle cycleDetector = new DirectedCycle(digraph);
				if (cycleDetector.hasCycle()) {
					cycleSum++;
				}

				//digraph.addEdge();
			}
			System.out.println(cycleSum);
		}
	}
}
