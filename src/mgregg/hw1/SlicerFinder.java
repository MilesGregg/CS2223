package mgregg.hw1;

import algs.hw1.Coordinate;
import algs.hw1.Slicer;
import algs.hw1.api.ISlicerFinder;

/**
 * Copy this class into your project and complete its implementation
 */
public class SlicerFinder implements ISlicerFinder {

	/*for (int i = 0; i < s.N; i++) {
			if (s.inLeft(i, target)) {
				column = i;
				break;
			}
		}
		for (int i = 0; i < s.N; i++) {
			if (s.inTop(i, target)) {
				row = i;
				break;
			}
		}*/


	private int low;
	private int high;

	/*public int findRow(Slicer s, int low, int high, int target) {
		if (low >= high) {
			return high;
		}

		if ((low+high) % 2 == 0) {
			mid = (low+high)/2;
		} else {
			mid = (low+high+1)/2;
		}
		boolean condition = s.inTop(mid, target);
		if (condition) {
			return findRow(s, low, mid, target);
		} else {
			return findRow(s, mid, high, target);
		}
	}

	public int findColumn(Slicer s, int low, int high, int target) {
		if (low >= high) {
			return high;
		}

		if ((low+high) % 2 == 0) {
			mid = (low+high)/2;
		} else {
			mid = (low+high+1)/2;
		}

		int mid = (low+high)/2;

		if (s.inLeft(mid, target)) {
			return findColumn(s, low, mid, target);
		} else {
			return findColumn(s, mid, high, target);
		}
	}*/

	/**
	 * Complete this implementation.
	 *
	 * You can inspect the contents of the array for s using the inLeft() and inTop() methods.
	 */
	public Coordinate find(Slicer s, int target) {
		/*if (s.N == 0) return null;
		if (s.N == 1){
			if(s.inTop(0,target) && s.inLeft(0,target)){
				return new Coordinate(0, 0);
			} else{
				return null;
			}
		}

		if (s.N == 2){
			int column= 0 , row= 0;
			for (int i = 0; i < s.N; i++) {
			if (s.inLeft(i, target)) {
				column = i;
				break;
			}
		}
		for (int i = 0; i < s.N; i++) {
			if (s.inTop(i, target)) {
				row = i;
				break;
			}
		}
			return new Coordinate(row, column);
		}*/


		/*int low = 0;
		int high = s.N-1;

		int row = 0;
		int column = 0;

		// for row
		while (low <= high) {
			int mid = (low + high) / 2;

			if ((low + high) % 2 == 0) {
				if (s.inLeft(mid, target)) {
					row = high;
					break;
				}
				high = mid-1;
			} else {
				if (s.inLeft(high, target)) {
					row = high;
					break;
				}
				low = mid+1;
			}
		}

		System.out.println("row: " + row);

		low = 0;
		high = row-1;

		while (low <= high) {
			int mid = (low + high) / 2;

			if ((low + high) % 2 == 0) {
				if (s.inTop(mid, target)) {
					column = high;
					break;
				}
				high = mid-1;
			} else {
				if (s.inTop(high, target)) {
					column = high;
					break;
				}
				low = mid+1;
			}
		}

		System.out.println("column: " + column);*/














		// find row
		//int row = findRow(s, low, high, target);
		//int column = findColumn(s, low, high, target);

		int column = 0;
		int row = 0;

		for (int i = 0; i < s.N; i++) {
			if (s.inLeft(i, target)) {
				column = i;
				break;
			}
		}

		for (int i = 0; i < s.N; i++) {
			if (s.inTop(i, target)) {
				row = i;
				break;
			}
		}

		return new Coordinate(row, column);
		//throw new RuntimeException("to be completed by student.");
	}

	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int i = 1; i < 20; i++) {
			Slicer s = new Slicer(i, 99);
			s.solver(new SlicerFinder());

			System.out.println(i + "\t" + s.getNumProbes());
		}
		System.out.println();

		for (int n = 1; n < 65; n*=2) {
			Slicer s = new Slicer(n, 99);
			int numProbes = s.solver(new SlicerFinder());
			System.out.println(n + "\t" + numProbes);
		}
	}
}
