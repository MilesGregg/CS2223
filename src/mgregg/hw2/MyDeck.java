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
	private int N = 0;

	/**
	 * Ensure that no one OUTSIDE of this class invokes the no-argument constructor. You will find
	 * it useful to have this constructor within the copy() method since it must return an accurate
	 * copy of the current Deck, and it will first need to construct an "empty" MyDeck object
	 * without using the MyDeck(int max_rank) constructor.
	 *
	 */
	protected MyDeck() {
		this.N = 0;
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
		if (max_rank < Card.ACE) throw new IllegalArgumentException("max_rank must be above " + Card.ACE);
		//if (max_rank < Card.ACE || max_rank > Card.KING) { throw new IllegalArgumentException("max_rank must be between " + Card.ACE + " and " + Card.KING + " respectively"); }

		for (Suit suit : Suit.values()) {
			for (int card = Card.ACE; card <= max_rank; card++) {
				Node newNode = new Node(new Card(suit, card));
				if (first == null) {
					first = newNode;
				} else {
					last.next = newNode;
				}
				last = newNode;
				this.N++;
			}
		}
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public Card peekTop() {
		return first.card;
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public Card peekBottom() {
		return last.card;
		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public boolean match(Card c, int n) {
		int currentIndex = 1;
		boolean output = false;
		for (Node node = first; node != null; node = node.next) {
			if (currentIndex == n) {
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
		System.out.println("First Card New: " + newDeck.first);
		for (Node node = first; node != null; node = node.next) {
			if (newDeck.first == null) {
				newDeck.first = node;
			} else {
				newDeck.last.next = node;
			}
			newDeck.last = node;
			newDeck.N++;
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
		/*Node fast = first;
		Node slow = first;
		while (fast != null) {
			slow = slow.next;
			if (slow == null || slow.next == null) {
				Node secondHalf = fast.next;
				fast.next = null;
				this.N /= 2;
				return secondHalf;
			}
			fast = fast.next;
			slow = slow.next;
		}

		return null;*/

		Node firstNode = first;
		Node fast = first;
		while (fast.next.next != null) {
			fast = fast.next.next;
			firstNode = firstNode.next;
		}

		Node secondHalf = firstNode.next;
		firstNode.next = null;
		this.N /= 2;
		return secondHalf;

		//throw new RuntimeException("To Be Completed By Student");
	}

	public void cutTest() {
		System.out.println("Mid: " + cutInHalf().card);
	}

	@Override
	public void out() {
		Node firstNode = first;
		Node secondNode = cutInHalf();
		System.out.println("Half Way Point for out(): " + secondNode.card.toString());

		//System.out.println("FirstNode: " + firstNode.card);
		//System.out.println("secondNode: " + secondNode.card);

		Node head = null;
		Node tail = null;

		while (firstNode != null) {
			if (head == null) {
				head = firstNode;
				firstNode = firstNode.next;
				head.next = secondNode;
			} else {
				tail.next = firstNode;
				firstNode = firstNode.next;
				tail = tail.next;
				tail.next = secondNode;
			}
			tail = secondNode;
			secondNode = secondNode.next;
		}

		first = head;
		last = tail;
		this.N *= 2;

		//throw new RuntimeException("To Be Completed By Student");
	}

	@Override
	public void in() {
		Node firstNode = first;
		Node secondNode = cutInHalf();
		System.out.println("Half Way Point for in(): " + secondNode.card.toString());

		//System.out.println("FirstNode: " + firstNode.card);
		//System.out.println("secondNode: " + secondNode.card);

		Node head = null;
		Node tail = null;

		while (firstNode != null) {
			if (head == null) {
				head = secondNode;
				secondNode = secondNode.next;
				head.next = firstNode;
			} else {
				tail.next = secondNode;
				secondNode = secondNode.next;
				tail = tail.next;
				tail.next = firstNode;
			}
			tail = firstNode;
			firstNode = firstNode.next;
		}

		first = head;
		last = tail;
		this.N *= 2;

		//throw new RuntimeException("To Be Completed By Student");
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
				if (node.card.suit != suit || node.card.rank != card) {
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
		Node node = first;
		for (int suit = Suit.values().length-1; suit > 0; suit--) {
			for (int card = size()/4; card > 0; card--) {
				if (node.card.suit != Suit.values()[suit] || node.card.rank != card) {
					return false;
				}
				node = node.next;
			}
		}
		return true;
		//throw new RuntimeException("To Be Completed By Student");
	}
}
