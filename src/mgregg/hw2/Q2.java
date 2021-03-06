package mgregg.hw2;

// You will need these when you copy this class file into your USERID.hw2 area.

import algs.hw2.AllCards;
import algs.hw2.Card;
import algs.hw2.Deck;
import algs.hw2.State;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

import java.util.Iterator;

/**
 * For this assignment, you should only have to modify the part where it says
 *
 * "HERE IS WHERE YOUR LOGIC GOES."
 *
 */
public class Q2 {

	/**
	 * Find all deals that bring each card to the top. Since there are multiple ones, any that
	 * you find (which can be validated) are allowed.
	 */
	public static void findDeals() {
		SequentialSearchST<Card, Deck> ordered = new SequentialSearchST<>();
		SequentialSearchST<Card, String> shuffles = new SequentialSearchST<>();

		// Change below to instantiate YOUR DECK NOT MY FRAGMENTARY EXAMPLE
		Deck deck = new MyDeck(13);

		// Start your search from the initial state, where the current shuffle is "" (empty)
		// Record that (for the top card which is the AC) this is the deck
		State state = new State(deck, "");
		shuffles.put(deck.peekTop(), "");
		ordered.put(deck.peekTop(), deck);

		Queue<State> queue = new Queue<>();
		queue.enqueue(state);

		// Until you have an entry for every possible card, continue your search
		while (ordered.size() < deck.size()) {
			// HERE IS WHERE YOUR LOGIC GOES.....

			State currentState = queue.dequeue();
			if (!shuffles.contains(currentState.deck.peekTop())) {
				shuffles.put(currentState.deck.peekTop(), currentState.shuffle);
				ordered.put(currentState.deck.peekTop(), currentState.deck);
			}

			Deck deck1 = currentState.deck.copy();
			Deck deck2 = currentState.deck.copy();

			deck1.in();
			deck2.out();

			String newString1 = currentState.shuffle + "I";
			String newString2 = currentState.shuffle + "O";

			queue.enqueue(new State(deck1, newString1));
			queue.enqueue(new State(deck2, newString2));
		}

		for (Card c : new AllCards()) {
			System.out.println(c + "\t" + shuffles.get(c));
		}
	}
	public static void main(String[] args) {
		findDeals();
	}
}
