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
//			System.out.println("column checking: " + column);
			for (int i = column; i < fs.N; i++) {
				//int probe = fs.probe3x3(row, i, target);
//				System.out.println("column: " + i + " probe: " + probe);
				if (fs.probe3x3(row, i, target) != FuzzySquare.FOUND) {
//					System.out.println("outputting");
					output = i-2;
					break;
				}
			}
		} else {
//			System.out.println("in descending: " + column);
			for (int i = column; i > 0; i--) {
//				System.out.println("column: " + i);
				if (fs.probe3x3(row, i, target) != FuzzySquare.FOUND) {
//					System.out.println("outputting");
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

//		for (int row = 0; row < fs.N; row += 3) {
//			for (int column = 0; column < fs.N; column += 3) {

		int mid = fs.N / 2;

		for (int column = 0; column < fs.N; column += 2) {
			for (int row = 0; row < fs.N; row += 2) {
				int output = fs.probe3x3(row, column, target);
				//System.out.println("probe output: " + output);
				if (output == FuzzySquare.FOUND) {
//					System.out.println("--------------------------------------------------------------------------");
//					System.out.println("target: " + target);
//					System.out.println("mid: " + mid);
//					System.out.println("current row: " + row);
//					System.out.println("current column: " + column);
					int rowPositionToGoTo = mid > row ? fs.N : 0;
					int columnPositionToGoTo = mid > column ? fs.N : 0;
					//System.out.println("mid: " + mid);
//					System.out.println("row pos to go to: " + rowPositionToGoTo);
//					System.out.println("column pos to go to: " + columnPositionToGoTo);

					int rowOutput = getRowCoordinate(fs, target, rowPositionToGoTo, row, column);
					int columnOutput = getColumnCoordinate(fs, target, columnPositionToGoTo, row, column);

//					System.out.println("row output: " + rowOutput);
//					System.out.println("column output: " + columnOutput);
//					System.out.println("--------------------------------------------------------------------------");
					return new Coordinate(rowOutput, columnOutput);
				}
			}
		}

		return null;

		//throw new RuntimeException("to be completed by student.");
	}

	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		/*FuzzySquare fs = new FuzzySquare(6, 99);
		fs.solver(new FuzzyFinder());
		System.out.println();
		System.out.println(fs.getNumProbes());*/

		for (int i = 5; i < 40; i++) {
			FuzzySquare fs = new FuzzySquare(i, 99);
			fs.solver(new FuzzyFinder());
			System.out.println(i + "\t" + fs.getNumProbes());
		}
	}
}
