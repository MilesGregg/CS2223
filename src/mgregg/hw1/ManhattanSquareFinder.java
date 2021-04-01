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

		if (distance == 0 /*|| distance == (ms.N*2)-2*/) {
			int end = (ms.N*2)-2;
			//System.out.println("at base cases: " + end);
			return new Coordinate(0, 0);
			//return distance == end ? new Coordinate(end, end) : new Coordinate(0, 0);
		}

		Coordinate switchDiagonalStarting = distance > ms.N-1 ?
				new Coordinate(ms.N, (distance-ms.N)+2) :
				new Coordinate(distance+1, distance+1);

		//System.out.println("Searching Pos: " + switchDiagonalStarting.row + ", " + switchDiagonalStarting.column);

		for (int row = 0; row < ms.N; row++) {
			for (int column = 0; column < ms.N; column++) {
				//System.out.println("Column: " + column);
				//System.out.println("Row: " + row);
				if ((column+row) == distance) {
					//System.out.println("--------------------");
					//System.out.println("Column: " + column);
					//System.out.println("Row: " + row);
					//System.out.println("--------------------");
					int onTarget = ms.distance(row, column, target);
					//System.out.println("Distance: " + onTarget);
					if (onTarget == 0) {
						//System.out.println("returning pos");
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
