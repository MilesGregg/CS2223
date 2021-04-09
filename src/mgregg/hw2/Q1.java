package mgregg.hw2;

public class Q1 {
    private static final int MAX_RANK = 20;

    private static void Q1_1() {
        System.out.println("Q1.1. How many in() shuffles to return deck to original position[10 points]");
        for (int i = 1; i <= MAX_RANK; i++) {
            MyDeck myDeck = new MyDeck(i);
            int times = 0;
            myDeck.in();
            times++;
            while (!myDeck.isInOrder()) {
                myDeck.in();
                times++;
            }

            String output = String.valueOf(i);
            System.out.println("max_rank = " + output + (output.length() == 1 ? " " : "") + "      #in() = " + times);
        }
    }

    private static void Q1_2() {
        System.out.println("\n" + "Q1.2. How many in() shuffles to return deck to original position[10 points]");
        for (int i = 1; i <= MAX_RANK; i++) {
            MyDeck myDeck = new MyDeck(i);
            int times = 0;
            myDeck.out();
            times++;
            while (!myDeck.isInOrder()) {
                myDeck.out();
                times++;
            }

            String output = String.valueOf(i);
            System.out.println("max_rank = " + output + (output.length() == 1 ? " " : "") + "      #in() = " + times);
        }
    }

    private static void Q1_3() {
        System.out.println("\n" + "Q1.3. How many in() shuffles to reverse the state of a deck[8 points]");
        for (int i = 1; i <= MAX_RANK; i++) {
            MyDeck myDeck = new MyDeck(i);
            int times = 0;
            while (!myDeck.isInReverseOrder()) {
                myDeck.in();
                times++;
            }

            String output = String.valueOf(i);
            System.out.println("max_rank = " + output + (output.length() == 1 ? " " : "") + "      #in() = " + times);
        }
    }

    public static void main(String[] args) {
        Q1_1();
        Q1_2();
        Q1_3();
    }
}
