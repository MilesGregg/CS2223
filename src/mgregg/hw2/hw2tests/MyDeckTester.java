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
        assertEquals(myDeck.peekTop().toString(), "3S");
        assertEquals(myDeck.peekBottom().toString(), "AC");
    }
}
