package mgregg.hw3;

import algs.days.day18.AVL;
import edu.princeton.cs.algs4.StdRandom;

public class Q4 {
    public static void main(String[] args) {
        System.out.println("N" + "\tLargest Height" + "\t\tNumber Found");
        for (int i = 1; i <= 40; i++) {
            int largestHeight = 0;
            int numberFound = 0;
            for (int j = 0; j < 10000; j++) {
                AVL<Double> avl = new AVL<Double>();
                for (int k = 0; k < i; k++) {
                    double randomNum = StdRandom.uniform();
                    avl.insert(randomNum);
                }
                int avlHeight = avl.height();
                if (avlHeight > largestHeight) {
                    largestHeight = avlHeight;
                    numberFound = 1;
                } else if (avlHeight == largestHeight) {
                    numberFound += 1;
                }
            }

            System.out.println(String.format("%d\t%d\t\t%17d", i, largestHeight, numberFound));
            //System.out.println(i + "\t\t" + largestHeight + "\t\t" + numberFound);
        }
    }
}
