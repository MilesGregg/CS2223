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
        assertEquals(myDeck.size(), 12);
        assertEquals(myDeck.peekTop().toString(), "AC");
        assertEquals(myDeck.peekBottom().toString(), "3S");
    }

    @Test
    public void matchTest() {
        MyDeck myDeck = new MyDeck(3);

        assertTrue(myDeck.match(new Card("2S"), 11));
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

        System.out.println("\n-----------------------------------------------------\n");

        MyDeck myDeck1 = new MyDeck(3);
        myDeck1.out();
        Deck newDeck1 = myDeck1.copy();
        System.out.println(newDeck1.size());
        System.out.println(newDeck1.representation());
        assertEquals(myDeck1.representation(), newDeck1.representation());
        assertEquals(myDeck1.size(), newDeck1.size());
    }

    @Test
    public void isInOrder() {
        MyDeck myDeck = new MyDeck(3);
        assertTrue(myDeck.isInOrder());
    }

    @Test
    public void isReverseOrder() {
        MyDeck myDeck = new MyDeck(3);
        System.out.println("Representation Before: " + myDeck.representation());
        int index = 0;
        while (!myDeck.isInReverseOrder()) {
            myDeck.in();
            index++;
        }
        System.out.println("Representation After: " + myDeck.representation());
        System.out.println("Index: " + index);
        //assertTrue(myDeck.isInReverseOrder());
    }

    @Test
    public void outTest() {
        System.out.println("out() Test --------------------------------------------------------" + "\n");
        MyDeck myDeck = new MyDeck(2);
        System.out.println("Before out() Deck Shuffle: " + myDeck.representation());
        myDeck.out();
        String representationOutput = myDeck.representation();
        assertEquals("AC AH 2C 2H AD AS 2D 2S", representationOutput);
        System.out.println("After out() Deck Shuffle: " + myDeck.representation());
        System.out.println("\n" + "-------------------------------------------------------------------");

        System.out.println("\nout() Test --------------------------------------------------------" + "\n");
        MyDeck myDeck1 = new MyDeck(13);
        System.out.println("Before out() Deck Shuffle: " + myDeck1.representation());
        Deck newDeck1 = myDeck1.copy();
        Deck newDeck2 = myDeck1.copy();

        assertEquals(myDeck1.representation(), newDeck1.representation());
        assertEquals(myDeck1.representation(), newDeck2.representation());

        System.out.println("New Deck Copy 1: " + newDeck1.representation());
        System.out.println("New Deck Copy 2: " + newDeck2.representation());

        newDeck2.out();
        newDeck1.in();

        System.out.println("After out() Deck Shuffle:  " + newDeck1.representation());

        /*String representationOutput1 = myDeck1.representation();
        assertEquals("AC AH AD AS", representationOutput1);
        System.out.println("After out() Deck Shuffle: " + myDeck1.representation());*/
        System.out.println("\n" + "-------------------------------------------------------------------");
    }

    @Test
    public void inTest() {
        System.out.println("in() Test ---------------------------------------------------------" + "\n");
        MyDeck myDeck = new MyDeck(2);
        System.out.println("Before in() Deck Shuffle: " + myDeck.representation());
        myDeck.in();
        String representationOutput = myDeck.representation();
        assertEquals("AH AC 2H 2C AS AD 2S 2D", representationOutput);
        System.out.println("After in() Deck Shuffle: " + representationOutput);
        System.out.println("\n" + "-------------------------------------------------------------------");
    }
}
