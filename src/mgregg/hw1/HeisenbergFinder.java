package mgregg.hw1;

import algs.hw1.Heisenberg;
import algs.hw1.api.IHeisenbergFinder;

/**
 * Copy this class into your USERID.hw1 package and improve this implementation.
 *
 * NOTE: The runtime results for this implementation appear in Q4 for
 * this homework assignment.
 */
public class HeisenbergFinder implements IHeisenbergFinder {

	/**
	 * Replace this inefficient function with something more efficient.
	 *
	 * You can inspect the contents of the array for h using inspect() method.
	 */
	public int find(Heisenberg h, int target) {
		/*for (int i = 0; i < h.N; i++) {
			if (h.inspect(i) == i+target) { return i; }
		}*/

		int low = 0;
		int high = h.N-1;

		while (low <= high) {
			int mid = (low + high) / 2;
			//System.out.println(h.inspect(mid));
			int rc = h.inspect(mid);
			if (rc < mid+target) {
				low = mid+1;
			} else if (rc > mid+target) {
				high = mid-1;
			} else {
				return mid;
			}
		}

		return -1;
	}

	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int i = 1; i < 20; i++) {
			Heisenberg h = new Heisenberg(i, 99);
			int numProbes = h.solver(new HeisenbergFinder());
			System.out.println(i + "\t" + numProbes);
		}
		System.out.println();

		for (int i = 3; i < 257; i=2*i+1) {
			Heisenberg h = new Heisenberg(i, 99);
			int numProbes = h.solver(new HeisenbergFinder());
			System.out.println(i + "\t" + numProbes);
		}
	}
}
