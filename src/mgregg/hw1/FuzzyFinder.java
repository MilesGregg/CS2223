package mgregg.hw1;

import algs.hw1.Coordinate;
import algs.hw1.FuzzySquare;
import algs.hw1.api.IFuzzySquareFinder;

/**
 * Copy this class into your USERID.hw1 package and complete it.
 */
public class FuzzyFinder implements IFuzzySquareFinder {

	public int getRowCoordinate(FuzzySquare fs,
							 int target,
							 int maxPosition,
							 int row,
							 int column) {
		int output = row;
		if (maxPosition == fs.N) {
			for (int i = row; i < fs.N; i++) {
				if (fs.probe3x3(i, column, target) != FuzzySquare.FOUND) {
					output = i-2;
					break;
				}
			}
		} else {
			for (int i = row; i > 0; i--) {
				if (fs.probe3x3(i, column, target) != FuzzySquare.FOUND) {
					output = i+2;
					break;
				}
			}
		}

		return output;
	}

	public int getColumnCoordinate(FuzzySquare fs,
								int target,
								int maxPosition,
								int row,
								int column) {
		int output = column;
		if (maxPosition == fs.N) {
			for (int i = column; i < fs.N; i++) {
				if (fs.probe3x3(row, i, target) != FuzzySquare.FOUND) {
					output = i-2;
					break;
				}
			}
		} else {
			for (int i = column; i > 0; i--) {
				if (fs.probe3x3(row, i, target) != FuzzySquare.FOUND) {
					output = i+2;
					break;
				}
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
					int rowOutput = getRowCoordinate(fs, target, rowPositionToGoTo, row, column);
					int columnOutput = getColumnCoordinate(fs, target, columnPositionToGoTo, row, column);

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
