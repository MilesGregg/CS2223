package mgregg.hw4;

import algs.hw4.map.GPS;
import algs.hw4.map.HighwayMap;
import algs.hw4.map.Information;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.Graph;

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
	public static Information remove_I90_segments(Information info) {  // TODO: remove public
		Graph copy = new Graph(info.graph.V());

		for (int u = 0; u < info.graph.V(); u++) {
			for (int v : info.graph.adj(u)) {
				if (!info.labels.get(u).startsWith("I-90") &&
						!info.labels.get(v).startsWith("I-90")) {
					copy.addEdge(u, v);
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

	private static int sum(Iterable<Integer> integerIterator) {
		int sum = 0;
		for (int i : integerIterator) {
			sum++;
		}
		return sum-1;
	}

	public static void main(String[] args) {
		Information info = HighwayMap.undirectedGraph();

		int mostNorthernVertex = northernMostVertex(info);
		int mostSouthernVertex = southernMostVertex(info);
		int mostEasternVertex = easternMostVertex(info);
		int mostWesternVertex = westernMostVertex(info);

		System.out.println("Northern Most Vertex: " + mostNorthernVertex);
		System.out.println("Southern Most Vertex: " + mostSouthernVertex);
		System.out.println("Eastern Most Vertex: " + mostEasternVertex);
		System.out.println("Western Most Vertex: " + mostWesternVertex);

		System.out.println("\nWestern most point: " + info.positions.get(mostWesternVertex));
		System.out.println("Eastern most point: " + info.positions.get(mostEasternVertex));

		System.out.println("\nQ2.1 Standard Paths: ");
		BreadthFirstPaths bfsWestToEast = new BreadthFirstPaths(info.graph, mostWesternVertex);
		Iterable<Integer> pathBfsWestToEast = bfsWestToEast.pathTo(mostEasternVertex);
		System.out.println("\tBFS West to East: " + sum(pathBfsWestToEast));

		BreadthFirstPaths bfsSouthToNorth = new BreadthFirstPaths(info.graph, mostSouthernVertex);
		Iterable<Integer> pathBfsSouthToNorth = bfsSouthToNorth.pathTo(mostNorthernVertex);
		System.out.println("\tBFS South to North: " + sum(pathBfsSouthToNorth));

		System.out.println("\nQ2.2 Demonstrate why Depth First Search is inappropriate here: ");
		DepthFirstPaths dfsWestToEast = new DepthFirstPaths(info.graph, mostWesternVertex);
		Iterable<Integer> pathDfsWestToEast = dfsWestToEast.pathTo(mostEasternVertex);
		System.out.println("\tDFS West to East: " + sum(pathDfsWestToEast));

		DepthFirstPaths dfsSouthToNorth = new DepthFirstPaths(info.graph, mostSouthernVertex);
		Iterable<Integer> pathDfsSouthToNorth = dfsSouthToNorth.pathTo(mostNorthernVertex);
		System.out.println("\tDFS South to North: " + sum(pathDfsSouthToNorth));

		System.out.println("\nQ2.3 Eliminate Mass Pike from consideration: ");
		Information newInfo = remove_I90_segments(info);
		BreadthFirstPaths newInfoBfsWestToEast = new BreadthFirstPaths(newInfo.graph, mostWesternVertex);
		Iterable<Integer> newInfoPathBfsWestToEast = newInfoBfsWestToEast.pathTo(mostEasternVertex);
		System.out.println("\tBFS West to East: " + sum(newInfoPathBfsWestToEast));

		BreadthFirstPaths newInfoBfsSouthToNorth = new BreadthFirstPaths(newInfo.graph, mostSouthernVertex);
		Iterable<Integer> newInfoPathBfsSouthToNorth = newInfoBfsSouthToNorth.pathTo(mostNorthernVertex);
		System.out.println("\tBFS South to North: " + sum(newInfoPathBfsSouthToNorth));
	}
}
