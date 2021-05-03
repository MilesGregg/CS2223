package mgregg.hw4;

import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.DirectedDFS;

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
	private static void calculate(boolean first) {
		System.out.println("N" + "\t\t#Cycles" + "\t\t#Connected");
		for (int i = 2; i <= 15; i++) {
			int cycleSum = 0;
			int connectedSum = 0;
			for (int j = 0; j < 10000; j++) {
				Digraph digraph = new Digraph(i);
				for (int u = 0; u < i; u++) {
					for (int v = 0; v < i; v++) {
						if (Math.random() < (first ? 0.5 : 1.0/i) && u != v) {
							digraph.addEdge(u, v);
						}
					}
				}
				DirectedCycle cycleDetector = new DirectedCycle(digraph);
				if (cycleDetector.hasCycle()) {
					cycleSum++;
				}
				DirectedDFS dfs = new DirectedDFS(digraph, 0);
				boolean connected = true;
				for (int k = 1; k < digraph.V(); k++) {
					if (!dfs.marked(k)) {
						connected = false;
					}
				}
				if (connected) connectedSum++;
			}
			System.out.println(String.format("%d\t\t%d\t\t%d", i, cycleSum, connectedSum));
		}
	}

	public static void main(String[] args) {
		System.out.println("Graphs with probability 0.5");
		calculate(true);
		System.out.println("\nGraphs with probability 1/N");
		calculate(false);
	}
}
