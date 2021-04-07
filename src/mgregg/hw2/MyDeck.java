package mgregg.hw2;

import algs.hw2.Card;
import algs.hw2.Deck;
import algs.hw2.Node;
import algs.hw2.Suit;

/**
 * COPY THIS CLASS into your development area and complete it.
 * @author Home
 *
 */
public class MyDeck extends Deck {
	private enum HonorCards {
		ACE(1)    { public String getCharacter() { return "A"; } },
		JACK(11)  { public String getCharacter() { return "J"; } },
		QUEEN(12) { public String getCharacter() { return "Q"; } },
		KING(13)  { public String getCharacter() { return "K"; } };

		private final int position;

		HonorCards(int position) {
			this.position = position;
		}

		public abstract String getCharacter();
	}

	private int N = 1;

	/**
	 * Ensure that no one OUTSIDE of this class invokes the no-argument constructor. You will find
	 * it useful to have this constructor within the copy() method since it must return an accurate
	 * copy of the current Deck, and it will first need to construct an "empty" MyDeck object
	 * without using the MyDeck(int max_rank) constructor.
	 *
	 */
	protected MyDeck() {
		// You do not need to modify this method. This constructor exists to ensure that
		// within this class, you can construct an empty MyDeck whose first and last are null.
	}

	/**
	 * Construct a playing deck with {max_rank} cards in specific order.
	 *
	 * Once done, the linked list of card Nodes must represent a deck that looks like the following (if
	 * {max_rank} were 3). The suites are ordered Club < Diamond < Hearts < Spades.
	 *
	 * AC -> 2C -> 3C -> AD -> 2D -> 3D -> AH -> 2D -> 3H -> AS -> 2S -> 3S
	 *
	 * Note your deck will have 4*{max_rank} cards.
	 *
	 * Performance must be O(N) where N is max_rank.
	 */
	public MyDeck(int max_rank) {
		if (max_rank < Card.ACE || max_rank > Card.KING) { throw new IllegalArgumentException("max_rank must be between " + Card.ACE + " and " + Card.KING + " respectively"); }

		for (Suit suit : Suit.values()) {
			for (int card = Card.ACE; card <= max_rank; card++) {
				String beginning = String.valueOf(card);
				for (HonorCards unique : HonorCards.values()) {
					if (card == unique.position) beginning = unique.getCharacter();
				}
				//System.out.println("Representation: " + (beginning + suit.abbreviation()));
				Node newNode = new Node(new Card(beginning + suit.abbreviation()));
				if (first == null) {
					first = newNode;
				} else {
					last.next = newNode;
				}
				last = newNode;
				N++;
			}
		}

		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public Card peekTop() {
		return last.card;
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public Card peekBottom() {
		return first.card;
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public boolean match(Card c, int n) {
		int currentIndex = 1;
		int targetIndex = N-n;
		boolean output = false;
		for (Node node = first; node != null; node = node.next) {
			if (currentIndex == targetIndex) {
				output = node.card.equals(c);
				break;
			}
			currentIndex++;
		}
		//throw new RuntimeException("To Be Completed By Student");
		return output;
	}

	@Override
	public Deck copy() {
		MyDeck newDeck = new MyDeck();
		for (Node node = first; node != null; node = node.next) {
			if (newDeck.first == null) {
				newDeck.first = node;
			} else {
				last.next = node.next;
			}
			last = node.next;
		}

		return newDeck;
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public int size() {
		return N;
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	protected Node cutInHalf() {
		//System.out.println(size()-1);
		//if (N % 2 != 0) throw new RuntimeException("Deck is a odd length!");

		Node fast = first;
		Node current = first;
		while (fast.next.next != null) {
			fast = fast.next.next;
			current = current.next;
		}
		System.out.println(current.card.toString());
		last = current;
		last.next = null;

		return current;
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public void out() {
		cutInHalf();
		/*int mid = N/2;
		for (Node node = first; node != null; node = node.next) {

		}*/
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public void in() {
		throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public String representation() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Node node = first; node != null; node = node.next) {
			if (node.next == null) stringBuilder.append(node.card);
			else stringBuilder.append(node.card).append(" ");
		}
		return stringBuilder.toString();
	}

	@Override
	public boolean isInOrder() {
		Node node = first;
		for (Suit suit : Suit.values()) {
			for (int card = Card.ACE; card <= size()/4; card++) {
				String beginning = String.valueOf(card);
				for (HonorCards unique : HonorCards.values()) {
					if (card == unique.position) beginning = unique.getCharacter();
				}
				if (!node.card.toString().equals(beginning + suit.abbreviation())) {
					return false;
				}

				node = node.next;
			}
		}

		return true;
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public boolean isInReverseOrder() {
		throw new RuntimeException("To Be Completed By Student");
	}
}
