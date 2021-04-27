package mgregg.hw3;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StopwatchCPU;
import algs.days.day16.ComparableTimSort;
import algs.hw3.CountedItem;
import algs.hw3.PrimitiveTimSort;

/**
 *
 * Use the existing SortTrial class, and write your own for your implementation
 * of TimSort and also the HeapSort
 *
 * https://shakespeare.folger.edu/shakespeares-works/hamlet/download/
 *
 * What is the longest word which is not a modern English word, according to
 * our dictionary?
 */
public class Q1 {

	/** Return time to sort array using merge sort. */
	public static double mergeSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Merge.sort(A);
		return start.elapsedTime();
	}

	/** Return time to sort array using quick sort. */
	public static double quickSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Quick.sort(A);
		return start.elapsedTime();
	}

	/** Return time to sort array using Insertion Sort. */
	public static double insertionSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Insertion.sort(A);
		return start.elapsedTime();
	}

	/** Return time to sort array using Selection Sort. */
	public static double selectionSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Selection.sort(A);
		return start.elapsedTime();
	}

	/** Return time to sort array using Heap Sort. */
	public static double heapSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Heap.sort(A);
		return start.elapsedTime();
	}

	/** Return time to sort array using Primitive Tim Sort. */
	public static double primitiveTimSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		PrimitiveTimSort.sort(A);
		return start.elapsedTime();
	}

	/** Return time to sort array using Optimized Tim Sort. */
	public static double builtinSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		ComparableTimSort.sort(A);
		return start.elapsedTime();
	}

	/** Determine if the array is sorted. */
	public static boolean isSorted(Comparable[] A) {
		for (int i = 0; i < A.length-1; i++) {
			if (A[i].compareTo(A[i+1]) == 1) return false;
		}

		return true;
	}

	/**
	 * Given a sorted array of CountedItem<String> objects, ensure that for
	 * any two index positions, i and j, if A[i] is equal to A[j] and i < j,
	 * then A[i].earlier(A[j]) is true.
	 *
	 * Performance must be O(N).
	 */
	public static boolean isSortedArrayStable(CountedItem[] A) {
		for (int i = 0; i < A.length-1; i++) {
			CountedItem one = A[i];
			CountedItem two = A[i+1];

			if (one.equals(two) && two.earlier(one)) return false;
		}

		return true;
		//throw new RuntimeException("Completed by Student");
	}

	/**
	 * Given an array of integers, return a CountedItem<Integer> array. If you construct
	 * and add the objects from left to right, then for two duplicate values A[i] and A[j],
	 * you know that the counter for A[i] is smaller than the counter for A[j] if i < j.
	 */
	static CountedItem<Integer>[] toCountedArray(Integer vals[]) {
		CountedItem<Integer>[] copy = new CountedItem[vals.length];
		for (int i  = 0; i < copy.length; i++) {
			copy[i] = new CountedItem<>(vals[i]);
		}

		return copy;
	}

	public static void trial1_1() {
		System.out.println("Q1.1");

		// create array of integers with opportunities for duplicates
		Integer vals[] = new Integer[4096];
		for (int i = 0; i < vals.length; i++) { vals[i] = StdRandom.uniform(128); }

		// using this SAME ARRAY, create different CountedItem<> arrays and
		// determine which of the sorting algorithms are stable, and which ones are not.
		CountedItem[] countArrayHeap = toCountedArray(vals);
		CountedItem[] countArrayInsertion = toCountedArray(vals);
		CountedItem[] countArrayMerge = toCountedArray(vals);
		CountedItem[] countArrayQuick = toCountedArray(vals);
		CountedItem[] countArraySelection = toCountedArray(vals);
		CountedItem[] countArrayTimPrimitive = toCountedArray(vals);
		CountedItem[] countArrayTimOptimized = toCountedArray(vals);

		heapSort(countArrayHeap);
		insertionSort(countArrayInsertion);
		mergeSort(countArrayMerge);
		quickSort(countArrayQuick);
		selectionSort(countArraySelection);
		primitiveTimSort(countArrayTimPrimitive);
		builtinSort(countArrayTimOptimized);

		boolean isHeapStable = isSortedArrayStable(countArrayHeap);
		boolean isInsertionStable = isSortedArrayStable(countArrayInsertion);
		boolean isMergeStable = isSortedArrayStable(countArrayMerge);
		boolean isQuickStable = isSortedArrayStable(countArrayQuick);
		boolean isSelectionStable = isSortedArrayStable(countArraySelection);
		boolean isTimPrimitiveStable = isSortedArrayStable(countArrayTimPrimitive);
		boolean isTimOptimizedStable = isSortedArrayStable(countArrayTimOptimized);

		System.out.println("\tAlgorithm" + "\t\t\t\tStable Sort");
		System.out.println(String.format("\t%10s\t%17b", "HeapSort: ", isHeapStable));
		System.out.println(String.format("\t%10s\t%12b", "InsertionSort: ", isInsertionStable));
		System.out.println(String.format("\t%10s\t%16b", "MergeSort: ", isMergeStable));
		System.out.println(String.format("\t%10s\t%17b", "QuickSort: ", isQuickStable));
		System.out.println(String.format("\t%10s\t%13b", "SelectionSort: ", isSelectionStable));
		System.out.println(String.format("\t%10s\t%8b", "TimSort Primitive: ", isTimPrimitiveStable));
		System.out.println(String.format("\t%10s\t%8b", "TimSort Optimized: ", isTimOptimizedStable));
	}

	public static void trial1_2() {
		System.out.println("\n" + "Q1.2");

		System.out.println("\tN" + "\t\t\tTimSort" + "\tMerge" + "\tPrimTS" + "\tQuick" + "\tHeap");

		for (int i = 1048576; i <= 16777216; i *= 2) {
			Integer[] A = new Integer[i];
			for (int j = 0; j < i; j++) {
				A[j] = StdRandom.uniform(i);
			}
			// Tim Sort
			Integer[] builtinSortArray = new Integer[i];
			System.arraycopy(A, 0, builtinSortArray, 0, A.length);
			double builtinSortTime = builtinSort(builtinSortArray);
			// Merge Sort
			Integer[] mergeSortArray = new Integer[i];
			System.arraycopy(A, 0, mergeSortArray, 0, A.length);
			double mergeSortTime = mergeSort(mergeSortArray);
			// Primitive Tim Sort
			Integer[] primitiveTimeSortArray = new Integer[i];
			System.arraycopy(A, 0, primitiveTimeSortArray, 0, A.length);
			double primitiveTimeSortTime = primitiveTimSort(primitiveTimeSortArray);
			// Quick Sort
			Integer[] quickSortArray = new Integer[i];
			System.arraycopy(A, 0, quickSortArray, 0, A.length);
			double quickSortTime = quickSort(quickSortArray);
			// Heap Sort
			Integer[] heapSortArray = new Integer[i];
			System.arraycopy(A, 0, heapSortArray, 0, A.length);
			double heapSortTime = heapSort(heapSortArray);

			System.out.println(String.format("%10d\t\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f",
					i,
					builtinSortTime,
					mergeSortTime,
					primitiveTimeSortTime,
					quickSortTime,
					heapSortTime));
		}

		// completed by student
	}


	public static void trial1_3() {
		System.out.println("\n" + "Q1.3");

		System.out.println("\tN" + "\t\t\tTimSort" + "\tMerge" + "\tPrimTS" + "\tQuick" + "\tHeap");

		for (int i = 1048576; i <= 16777216; i *= 2) {
			Integer[] A = new Integer[i];
			for (int j = 0; j < i; j++) {
				A[j] = StdRandom.uniform(i / 512);
			}
			// Tim Sort
			Integer[] builtinSortArray = new Integer[i];
			System.arraycopy(A, 0, builtinSortArray, 0, A.length);
			double builtinSortTime = builtinSort(builtinSortArray);
			// Merge Sort
			Integer[] mergeSortArray = new Integer[i];
			System.arraycopy(A, 0, mergeSortArray, 0, A.length);
			double mergeSortTime = mergeSort(mergeSortArray);
			// Primitive Tim Sort
			Integer[] primitiveTimeSortArray = new Integer[i];
			System.arraycopy(A, 0, primitiveTimeSortArray, 0, A.length);
			double primitiveTimeSortTime = primitiveTimSort(primitiveTimeSortArray);
			// Quick Sort
			Integer[] quickSortArray = new Integer[i];
			System.arraycopy(A, 0, quickSortArray, 0, A.length);
			double quickSortTime = quickSort(quickSortArray);
			// Heap Sort
			Integer[] heapSortArray = new Integer[i];
			System.arraycopy(A, 0, heapSortArray, 0, A.length);
			double heapSortTime = heapSort(heapSortArray);

			System.out.println(String.format("%10d\t\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f",
					i,
					builtinSortTime,
					mergeSortTime,
					primitiveTimeSortTime,
					quickSortTime,
					heapSortTime));
		}

		// completed by student
	}

	public static void trial1_4() {
		System.out.println("\n" + "Q1.4");

		System.out.println("\tN" + "\t\t\tTimSort" + "\tMerge" + "\tPrimTS" + "\tQuick" + "\tHeap");

		for (int i = 1048576; i <= 16777216; i *= 2) {
			Integer[] A = new Integer[i];
			for (int j = i-1; j >= 0; j--) {
				A[j] = i;
			}
			// Tim Sort
			Integer[] builtinSortArray = new Integer[i];
			System.arraycopy(A, 0, builtinSortArray, 0, A.length);
			double builtinSortTime = builtinSort(builtinSortArray);
			// Merge Sort
			Integer[] mergeSortArray = new Integer[i];
			System.arraycopy(A, 0, mergeSortArray, 0, A.length);
			double mergeSortTime = mergeSort(mergeSortArray);
			// Primitive Tim Sort
			Integer[] primitiveTimeSortArray = new Integer[i];
			System.arraycopy(A, 0, primitiveTimeSortArray, 0, A.length);
			double primitiveTimeSortTime = primitiveTimSort(primitiveTimeSortArray);
			// Quick Sort
			Integer[] quickSortArray = new Integer[i];
			System.arraycopy(A, 0, quickSortArray, 0, A.length);
			double quickSortTime = quickSort(quickSortArray);
			// Heap Sort
			Integer[] heapSortArray = new Integer[i];
			System.arraycopy(A, 0, heapSortArray, 0, A.length);
			double heapSortTime = heapSort(heapSortArray);

			System.out.println(String.format("%10d\t\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f",
					i,
					builtinSortTime,
					mergeSortTime,
					primitiveTimeSortTime,
					quickSortTime,
					heapSortTime));
		}

		// completed by student
	}

	public static void main(String[] args) {
		trial1_1();
		trial1_2();
		trial1_3();
		trial1_4();
	}
}
