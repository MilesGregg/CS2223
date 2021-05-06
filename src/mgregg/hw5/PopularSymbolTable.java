package mgregg.hw5;

// use any classes you want from Sedgewick

import algs.days.day18.TreeMap;
import algs.hw4.map.GPS;
import edu.princeton.cs.algs4.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Your goal is to maintain a set of (key, value) pairs like a symbol table, with some
 * enhanced behavior.
 *
 * For simplicity, each key is just an 'Integer' and each value is also an 'Integer'.
 * Aside from this limitation, the key (or value) can be any 32-bit integer, including
 * negative numbers.
 *
 * You can 'put(key, value)' or 'get(key)' or 'remove(key)' as expected for a symbol table.
 * These should all execute with performance no worse than O(log N). You need to know the
 * size of the table and be ready to return size on demand in O(1) time.
 *
 * In addition:
 *
 * 1. 'get(key)' returns the value associated with key. This must (in worst case) perform in O(log N).
 *     If no key is in the symbol table, then null is returned.
 *
 * 2. 'put(key, value)' associated the given value with the key. This must (in worst case) perform in O(log N).
 *     This method returns true when adding the key for the first time, otherwise it returns false when
 *     simply replacing the value associated with the key.
 *
 * 3. 'reverseMatch(value)' -- returns an Iterable containing in ascending order all keys that map to a given value.
 *     Note that this is the reverse of symbol table.
 *
 *     To receive full credit, the performance of this operation must be O(K + log N) where K is
 *     the number of keys that map to the given value. If the number of keys that map is independent
 *     of N, then in the best case this takes O(log N) time.
 *
 *     If none of the keys map to this value, then an empty Iterable is returned (never null).
 *
 *     Note K <= N in all cases. If, in fact, all keys in the symbol table map to the same value,
 *     then performance is O(N) since K=N.
 *
 * 4. 'remove(key)' -- removes the (key, value) pair if it exists. Note that if the key does not exist,
 *     then false is returned, otherwise true is returned.
 *
 */
public class PopularSymbolTable {
	private int N;
	private TreeMap<Integer, Integer> st;
	private TreeMap<Integer, LinkedList<Integer>> reverse;

	public PopularSymbolTable () {
		// fill in by student....
		st = new TreeMap<>();
		reverse = new TreeMap<>();
	}

	/** Return number of (key, value) pairs in the table. Performance must be O(1). */
	public int size() {
		return st.size();
		//throw new RuntimeException ("Student Must complete.");
	}

	/** Might return an empty Queue object. */
	public Queue<Integer> reverseMatch(Integer value) {
		//System.out.println("Find this value: " + value);
		//System.out.println("Last entry: " + st.firstEntry().getKey());
		/*Queue<Integer> queue = new Queue<>();
		for (int i = st.firstEntry().getKey(); i <= st.lastEntry().getKey(); i++) {
			Integer getValue = st.get(i);
			//System.out.println(getValue);
			if (getValue == null) continue;
			if (getValue.equals(value)) {
				//System.out.println("Enqueue: " + i);
				queue.enqueue(i);
			}
		}
		return queue;*/

		Queue<Integer> queue = new Queue<>();
		LinkedList<Integer> current = reverse.get(value);
		if (current == null) return new Queue<>();
		//LinkedList<Integer> newLinked = current;
		//newLinked.sort((Comparator<? super Integer>) current);
		current.sort(Integer::compareTo);
		Iterator<Integer> iterator = current.iterator();
		for (Iterator<Integer> it = iterator; it.hasNext(); ) {
			Integer integer = it.next();
			//System.out.println("Val: " + integer);
			queue.enqueue(integer);
		}

		return queue;

		//throw new RuntimeException ("Student Must complete.");
	}

	/** Return value associated with key. */
	public Integer get(Integer key) {
		if (key == null) throw new IllegalArgumentException("key is null");
		return st.get(key);
	}

	/**
	 * Return true if the key was newly added to the collection.
	 */
	public boolean put (Integer key, Integer value) {
		//throw new RuntimeException ("Student Must complete.");
		if (key == null) {
			throw new IllegalArgumentException("calls put() with null key");
		}

		LinkedList<Integer> current = reverse.get(value) == null ? new LinkedList<>() : reverse.get(value);
		current.add(key);
		reverse.put(value, current);

		Integer val = st.put(key, value);
		return val == null;
	}

	/**
	 * Return true if the key was removed.
	 */
	public boolean remove (Integer key) {
		if (key == null) throw new IllegalArgumentException("calls remove() with null key");

		Integer currentValue = st.get(key);
		System.out.println("Current Value: " + currentValue);
		if (currentValue != null) {
			LinkedList<Integer> current = reverse.get(currentValue) == null ? new LinkedList<>() : reverse.get(currentValue);
			current.remove(key);
			reverse.put(currentValue, current);
		}


		Integer oldValue = st.remove(key);
		return oldValue != null;
	}
}
