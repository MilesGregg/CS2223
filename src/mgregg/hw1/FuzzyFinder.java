package mgregg.hw1;

import algs.hw1.Coordinate;
import algs.hw1.FuzzySquare;
import algs.hw1.api.IFuzzySquareFinder;

/**
 * Copy this class into your USERID.hw1 package and complete it.
 */
public class FuzzyFinder implements IFuzzySquareFinder {

	public int getCoordinate(FuzzySquare fs,
							 int target,
							 int maxPosition,
							 int row,
							 int column,
							 boolean rowCoordinate) {
		int direction = maxPosition == fs.N ? 1 : -1;
		int output = rowCoordinate ? row : column;
		for (int i = output; maxPosition == fs.N ? i < fs.N : i > 0; i += direction) {
			if (fs.probe3x3(rowCoordinate ? i : row, rowCoordinate ? column : i, target) != FuzzySquare.FOUND) {
				output = maxPosition == fs.N ? i - 2 : i + 2;
				break;
			}
		}

		return output;
	}

	/**
	 * Return the Coordinate(r,c) where target exists. If it is not in
	 * the 2D array, return null.
	 *
	 * You can inspect the contents of the array for fs using the probe3x3() method.
	 */
	public Coordinate find(FuzzySquare fs, int target) {
		int mid = fs.N / 2;

		for (int column = 0; column < fs.N; column += 2) {
			for (int row = 0; row < fs.N; row += 2) {
				int output = fs.probe3x3(row, column, target);
				if (output == FuzzySquare.FOUND) {
					int rowPositionToGoTo = mid > row ? fs.N : 0;
					int columnPositionToGoTo = mid > column ? fs.N : 0;
					int rowOutput = getCoordinate(fs, target, rowPositionToGoTo, row, column, true);
					int columnOutput = getCoordinate(fs, target, columnPositionToGoTo, row, column, false);

					return new Coordinate(rowOutput, columnOutput);
				}
			}
		}

		return null;
	}

	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int i = 5; i < 40; i++) {
			FuzzySquare fs = new FuzzySquare(i, 99);
			fs.solver(new FuzzyFinder());
			System.out.println(i + "\t" + fs.getNumProbes());
		}
	}
}
