package mgregg.hw1;

import algs.hw1.Coordinate;
import algs.hw1.Slicer;
import algs.hw1.api.ISlicerFinder;

/**
 * Copy this class into your project and complete its implementation
 */
public class SlicerFinder implements ISlicerFinder {


	/**
	 * Complete this implementation.
	 *
	 * You can inspect the contents of the array for s using the inLeft() and inTop() methods.
	 */
	public Coordinate find(Slicer s, int target) {
		int lowRow = 0;
		int highRow = s.N-1;
		while (lowRow <= highRow) {
			int mid = (lowRow+highRow) / 2;
			if (lowRow == highRow) {
				break;
			}
			if (s.inTop(mid, target)) {
				highRow = mid;
			} else {
				lowRow = mid + 1;
			}
		}

		int lowColumn= 0;
		int highColumn = s.N-1;
		while (lowColumn <= highColumn) {
			int mid = (lowColumn + highColumn) / 2;
			if (lowColumn == highColumn) {
				return new Coordinate(highRow, highColumn);
			}
			if (s.inLeft(mid, target)) {
				highColumn = mid;
			} else {
				lowColumn = mid + 1;
			}
		}

		return null;
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
