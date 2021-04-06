import mgregg.hw2.MyDeck;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        /*int[][] array = {
                {0, 1, 2, 3, 4},
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6},
                {3, 4, 5, 6, 7},
                {4, 5, 6, 7, 8}};

        System.out.println(array[0].length);

        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                if ((column+row) == (3-1)) {
                    System.out.println("output: " + array[row][column]);
                }
            }
        }*/

        /*long x = 81;
        long y = x / 2;
        long z = x % 2;

        System.out.println("x: " + y);
        System.out.println("other value: " + (y+z));*/

        MyDeck deck = new MyDeck(3);

        System.out.println("Top Card: " + deck.peekTop());

        /*for (int i = 0; i < 6; i += 3) {
            System.out.println(i);
        }*/
    }
}
