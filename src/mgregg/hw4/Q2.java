package mgregg.hw4;

import algs.hw4.map.GPS;
import algs.hw4.map.HighwayMap;
import algs.hw4.map.Information;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

/**
 * The goal of this question is to:
 *
 * 1. Find the western-most location in Massachusetts
 * 2. Find the eastern-most location in Massachusetts
 * 3. Determine the shortest distance between these two locations IN TERMS OF THE
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT
 * 4. Next create a copy of the highway graph that removes all line segments from I-90, the
 *    Massachusetts Turnpike toll road.
 * 5. From this copy, determine the shortest distance between these two locations IN TERMS OF THE
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT.
 */
public class Q2 {

	/**
	 * This method must create a copy of the graph, which you can do by recreate a graph with
	 * the same number of vertices as the original one, BUT you only add an edge to the copy
	 * if the edge in the original graph IS NOT EXCLUSIVELY a line segment from the Mass Pike.
	 *
	 * For example, in the data set you will see two nodes:
	 *
	 * 		I-90@49|MA 42.169702 -72.580876
	 * 		I-90@51|MA 42.161558 -72.541995
	 *
	 * These lines correspond to vertex #639 (the first one @49) and vertex #641 (the second one @51).
	 * Because the label for both of these vertices includes "I-90@" this edge must not appear in
	 * the copied graph, since it is a highway segment exclusively on the Mass Turnpike.
	 *
	 * Note that the edge is eliminated only when BOTH are present. For example, the following
	 * line segment will remain:
	 *
	 * 		I-95(23)/MA128	                ==> vertex #705
	 * 		I-90@123B&I-95@24&MA128@24(95)  ==> vertex #1785
	 */
	static Information remove_I90_segments(Information info) {
		Graph copy = new Graph(info.graph.V());
		Queue<Integer> queue = new Queue<>();
		boolean[] visited = new boolean[info.graph.V()];
		visited[0] = true;
		queue.enqueue(0);

		while (!queue.isEmpty()) {
			Integer u = queue.dequeue();
			for (int v : info.graph.adj(u)) {
				if (!visited[v]) {
					if (!info.labels.get(v).startsWith("I-90")) {
						copy.addEdge(u, v);
					}
					visited[v] = true;
					queue.enqueue(v);
				}
			}
		}

		// DO YOUR WORK HERE...

		Information newInfo = new Information(copy, info.positions, info.labels);
		return newInfo;
	}


	/**
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 *
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 *
	 */
	public static int westernMostVertex(Information info) {
		double highestPoint = Integer.MAX_VALUE;
		int index = -1;
		for (int id : info.positions.keys()) {
			GPS currentPose = info.positions.get(id);
			if (currentPose.longitude < highestPoint) {
				highestPoint = currentPose.longitude;
				index = id;
			}
		}
		//System.out.println(info.labels.get(index) + "   ->    " + info.positions.get(index));
		return index;
	}

	/**
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 *
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 *
	 */
	public static int easternMostVertex(Information info) {
		double highestPoint = Integer.MIN_VALUE;
		int index = -1;
		for (int id : info.positions.keys()) {
			GPS currentPose = info.positions.get(id);
			if (currentPose.longitude > highestPoint) {
				highestPoint = currentPose.longitude;
				index = id;
			}
		}
		//System.out.println(info.labels.get(index) + "   ->    " + info.positions.get(index));
		return index;
	}

	/**
	 * This helper method returns the southern-most data point in the Information, given its latitude and
	 * longitude.
	 *
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 *
	 */
	public static int southernMostVertex(Information info) {
		double highestPoint = Integer.MAX_VALUE;
		int index = -1;
		for (int id : info.positions.keys()) {
			GPS currentPose = info.positions.get(id);
			if (currentPose.latitude < highestPoint) {
				highestPoint = currentPose.latitude;
				index = id;
			}
		}
		//System.out.println(info.labels.get(index) + "   ->    " + info.positions.get(index));
		return index;
	}

	/**
	 * This helper method returns the northern-most data point in the Information, given its latitude and
	 * longitude.
	 *
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 *
	 */
	public static int northernMostVertex(Information info) {
		double highestPoint = Integer.MIN_VALUE;
		int index = -1;
		for (int id : info.positions.keys()) {
			GPS currentPose = info.positions.get(id);
			if (currentPose.latitude > highestPoint) {
				highestPoint = currentPose.latitude;
				index = id;
			}
		}
		//System.out.println(info.labels.get(index) + "   ->    " + info.positions.get(index));
		return index;
	}

	public static void main(String[] args) {
		Information info = HighwayMap.undirectedGraph();

		System.out.println(easternMostVertex(info));
		System.out.println(westernMostVertex(info));
		System.out.println(northernMostVertex(info));
		System.out.println(southernMostVertex(info));
		System.out.println();

		BreadthFirstPaths bfs = new BreadthFirstPaths(info.graph, easternMostVertex(info));
		Iterable<Integer> paths = bfs.pathTo(westernMostVertex(info));
		int count = 0;
		for (int i : paths) {
			count++;
		}
		System.out.println("East to West: " + count);

		BreadthFirstPaths bfs2 = new BreadthFirstPaths(info.graph, southernMostVertex(info));
		Iterable<Integer> paths2 = bfs2.pathTo(northernMostVertex(info));
		int count2 = 0;
		for (int i : paths2) {
			count2++;
		}
		System.out.println("South to North: " + count2);

		Information newInfo = remove_I90_segments(info);

		BreadthFirstPaths bfs3 = new BreadthFirstPaths(newInfo.graph, southernMostVertex(newInfo));
		Iterable<Integer> paths3 = bfs3.pathTo(northernMostVertex(newInfo));
		int count3 = 0;
		for (int i : paths3) {
			count3++;
		}
		System.out.println("South to North: " + count3);



		//BFS bfs = new BFS(info.graph, easternMostVertex(info), westernMostVertex(info));
		//BFS bfs2 = new BFS(info.graph, westernMostVertex(info), easternMostVertex(info));






		/*for (int id : info.labels.keys()) {
			System.out.println(info.positions.get(id));
		}*/
	}
}
