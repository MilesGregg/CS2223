package mgregg.hw2;

public class Q1 {
    private static final int MAX_RANK = 20;

    private static void Q1_1() {
        System.out.println("Q1.1. How many in() shuffles to return deck to original position[10 points]");
        System.out.println("    max_rank          #in()");
        for (int i = 1; i <= MAX_RANK; i++) {
            MyDeck myDeck = new MyDeck(i);
            int times = 0;
            myDeck.in();
            times++;
            while (!myDeck.isInOrder()) {
                myDeck.in();
                times++;
            }

            System.out.printf("%12d\t%11d%n", i, times);
        }
    }

    private static void Q1_2() {
        System.out.println("\n" + "Q1.2. How many out() shuffles to return deck to original position[10 points]");
        System.out.println("    max_rank          #in()");
        for (int i = 1; i <= MAX_RANK; i++) {
            MyDeck myDeck = new MyDeck(i);
            int times = 0;
            myDeck.out();
            times++;
            while (!myDeck.isInOrder()) {
                myDeck.out();
                times++;
            }

            System.out.printf("%12d\t%11d%n", i, times);
        }
    }

    private static void Q1_3() {
        System.out.println("\n" + "Q1.3. How many in() shuffles to reverse the state of a deck[8 points]");
        System.out.println("    max_rank          #in()");
        MyDeck myDeck = new MyDeck(13);
        int times = 0;
        while (!myDeck.isInReverseOrder()) {
            myDeck.in();
            times++;
        }

        System.out.printf("%12s\t%11d%n", "13", times);
    }

    private static void Q1_3_1() {
        System.out.println("\n" + "Q1.3.1. What is the smallest max_rank for" +
                " which no amount of in() shuffles produce the reversed ordering?");
        System.out.println("    max_rank          #in()");

        final int MAX_TIME = 15;
        boolean exit = false;

        for (int i = 1; i <= MAX_RANK; i++) {
            MyDeck myDeck = new MyDeck(i);
            int times = 0;
            long time = System.currentTimeMillis();
            while (!myDeck.isInReverseOrder()) {
                myDeck.in();
                times++;

                if (System.currentTimeMillis() > time + (MAX_TIME*1000)) {
                    System.err.println("max_rank of " + i + " cannot find how many reverse ordering!");
                    exit = true;
                    break;
                }
            }

            if (exit) break;

            System.out.printf("%12d\t%11d%n", i, times);
        }
    }

    public static void main(String[] args) {
        Q1_1();
        Q1_2();
        Q1_3();
        Q1_3_1();
    }
}
