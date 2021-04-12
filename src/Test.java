import mgregg.hw2.MyDeck;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        int len = 16;

        int lo = 0;
        int hi = len-1;
        int m = (lo + hi) / 2;
        int count = 0;

        while (hi > lo) {
            count++;
            System.out.println("HERE: " + count);
            m = (lo + hi) / 2;
            hi = m;
        }



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

        //int[] a = new int[10];
        //for (int i = 0; i < 10; i++) { a[i] = i; }

        // initiate the request on an array of size n, containing values from 0 to n-1
        // using indices of lo=0 and hi=n-1
        //long val = proc(a, 0, 10-1);
        //double val2 = proc2(a, 0, 10-1);

        //System.out.println(val);
        //System.out.println(val2);


        /*for (int i = 0; i < 6; i += 3) {
            System.out.println(i);
        }*/
    }
}
