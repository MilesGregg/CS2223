package mgregg.hw1;

import algs.hw1.Coordinate;
import algs.hw1.ManhattanSquare;
import algs.hw1.api.IManhattanSquareFinder;

public class ManhattanSquareFinder implements IManhattanSquareFinder {

	/**
	 * Return the Coordinate of location in ManhattanSquare containing target.
	 *
	 * You can inspect the contents of the array for ms using the distance() method.
	 */
	public Coordinate find(ManhattanSquare ms, int target) {
		int distance = ms.distance(0, 0, target);

		if (distance == 0) {
			return new Coordinate(0, 0);
		}

		for (int row = 0; row < ms.N; row++) {
			for (int column = 0; column < ms.N; column++) {
				if ((column+row) == distance) {
					int onTarget = ms.distance(row, column, target);
					if (onTarget == 0) {
						return new Coordinate(row, column);
					}
				}
			}
		}

		return null;
	}

	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int n = 1; n < 15; n++) {
			ManhattanSquare ms = new ManhattanSquare(n, 99);
			int numProbes = ms.solver(new ManhattanSquareFinder());
			System.out.println(n + "\t" + numProbes);
		}
	}
}
