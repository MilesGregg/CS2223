import algs.hw2.Card;
import algs.hw2.Deck;
import mgregg.hw2.MyDeck;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MyDeckTester {

    @Test
    public void constructorTest() {
        MyDeck myDeck = new MyDeck(3);
        String output = "AC 2C 3C AD 2D 3D AH 2H 3H AS 2S 3S";

        assertEquals(myDeck.representation(), output);
        assertEquals(myDeck.size(), 13);
        assertEquals(myDeck.peekTop().toString(), "AC");
        assertEquals(myDeck.peekBottom().toString(), "3S");
    }

    @Test
    public void matchTest() {
        MyDeck myDeck = new MyDeck(3);

        assertTrue(myDeck.match(new Card("3H"), 4));
    }

    @Test
    public void cutInHalfTest() {
        MyDeck myDeck = new MyDeck(3);
        System.out.println("Before Cut: " + myDeck.representation());
        myDeck.cutTest();
        System.out.println("After Cut: " + myDeck.representation());
    }

    @Test
    public void copyTest() {
        MyDeck myDeck = new MyDeck(3);
        Deck newDeck = myDeck.copy();
        System.out.println(newDeck.size());
        System.out.println(newDeck.representation());
        assertEquals(myDeck.representation(), newDeck.representation());
        assertEquals(myDeck.size(), newDeck.size());
    }

    @Test
    public void isInOrder() {
        MyDeck myDeck = new MyDeck(3);
        assertTrue(myDeck.isInOrder());
    }

    @Test
    public void outTest() {
        MyDeck myDeck = new MyDeck(3);
        System.out.println(myDeck.representation());
        myDeck.out();
        System.out.println(myDeck.representation());
    }
}
