package mgregg.hw5;

import algs.days.day26.FloydWarshallUndirected;
import algs.hw4.map.GPS;
import algs.hw5.FloydWarshallSolutionAnimation;
import algs.hw5.map.HighwayMapWeighted;
import algs.hw5.map.WeightedInformation;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

/**
 * Question 2 of Homework 5.
 *
 * Given the Massachusetts highway map data, find two vertices in the graph such that
 * the shortest distance between them is greater than any other pair of vertices in the graph.
 *
 * In other words, can you find two locations in Massachusetts such that using the available
 * map data, you�ve computed the shortest total distance in terms of accumulated mileage,
 * and no other pair of vertices demands a longer trip.
 */
public class Q2 {
	private static class Node {
		private int test = 0;
	}

	public static void main(String[] args) {
		WeightedInformation info = HighwayMapWeighted.undirectedGraph();
		EdgeWeightedGraph graph = info.ewgraph;

		System.out.println("Computing Floyd-Warshall: This might take up to a minute...");
		FloydWarshallUndirected fw = new FloydWarshallUndirected(graph);
		System.out.println("done");

		double highestSum = Integer.MIN_VALUE;
		int vertex1 = 0;
		int vertex2 = 0;

		// THIS IS WHERE YOU MUST DO SOME WORK TO DETERMINE TWO VERTICES
		// THAT HAVE THE GREATEST OF THE SHORTEST DISTANCES.
		for (int u = 0; u < info.graph.V(); u++) {
			for (int v = u; v < info.graph.V(); v++) {
				/*for (int i : fw.shortestPath(u, v)) {
					GPS gps = info.positions.get(i);
					double mileage = gps.distance(info.positions.get(i+1));

				}*/


				//BreadthFirstPaths bfsWestToEast = new BreadthFirstPaths(information.graph, vertex1);
				//Iterable<Integer> pathBfsWestToEast = bfsWestToEast.pathTo(vertex2);
				double sum = 0;
				int index = 0;
				GPS prev = null;
				for (int i : fw.shortestPath(u, v)) {
					if (index != 0) {
						sum += info.positions.get(i).distance(prev);
					}
					index++;
					prev = info.positions.get(i);
				}
				if (sum > highestSum) {
					highestSum = sum;
					vertex1 = u;
					vertex2 = v;
				}
				//System.out.println("Sum: " + sum);
			}
		}

		System.out.println("Highest: " + highestSum);
		System.out.println("Vertex 1: " + info.positions.get(vertex1));
		System.out.println("Vertex 2: " + info.positions.get(vertex2));

		// To visually animate the solution, properly use integer vertex endpoints. You know this is
		// right when you see it!
		new FloydWarshallSolutionAnimation(fw).launch(vertex1, vertex2);
	}
}
