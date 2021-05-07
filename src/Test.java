import algs.days.day04.FixedCapacityStack;
import algs.days.day05.FixedCapacityQueue;
import algs.days.day18.AVL;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.MinPQ;
import mgregg.hw2.MyDeck;
import mgregg.hw5.MyLinkedList;

public class Test {

    /*boolean contains(int[] collection, int target) {
        int low = 0;
        int high = collection.length-1;

        while (low <= high) {
            //Calculate the mid element of the collection.
            int mid = (low+high)/2;
            // Compare the key items with the mid element.
            int rc = collection[mid] - target;
            if (rc < 0) {
                // If target < 0, then the key is in the upper half of the collection.
                low = mid+1;
            } else if (rc > 0) {
                //Else If target > 0, then the key lies in the right half of the collection.
                high = mid-1;
            } else {
                // If key = middle element, then we return the mid index position for the key found.
                return true;
            }
        }
        return false;
    }*/

    public static int findGap(int[] a) {
        int low = 1;
        int high = a.length-2;

        System.out.println("High: " + high);

        while (low<=high) {
            int mid = (low+high)/2;
            if (a[mid] > mid) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        return low;
    }

    public static FixedCapacityStack<Integer> copy(FixedCapacityStack<Integer> stack) {
        FixedCapacityStack<Integer> extra = new FixedCapacityStack<>(100);
        FixedCapacityQueue<Integer> queue = new FixedCapacityQueue<>(100);

        while (!stack.isEmpty()) {
            extra.push(stack.pop());
        }
        FixedCapacityStack<Integer> copy = new FixedCapacityStack<>(100);

        while (!extra.isEmpty()) {
            int x = extra.pop();
            stack.push(x);
            copy.push(x);
        }

        return copy;
    }

    public static String[] addPos(String[] a, int pos, String string) {
        String[] result = new String[a.length];
        for (int i = 0; i < pos; i++)
            result[i] = a[i];
        result[pos] = string;
        for (int i = pos - 1; i >= 0; i--)
            result[i] = a[i + 1];
        return result;
    }

    public static void main(String[] args) {
        //String[] past = {  "a", "b", "d", "f" };
        /*String[] past = new String[5];
        past = addPos(past, past.length-1, "z");
        past = addPos(past, past.length-1, "a");
        System.out.println(Arrays.toString(past));*/

        //String string = "+-*/";
        //System.out.println(string.contains("2"));

//        int i = 25;
//        char test = (char) ('A' + i);
//        System.out.println(test);

        for (int j = 0; j < 4; j++) {
            System.out.println(j);
        }

        /*AVL<Integer> testing = new AVL<Integer>();
        testing.insert(5);
        testing.insert(3);
        testing.insert(2);
        testing.insert(-6);
        testing.insert(8);
        testing.insert(5);
        Iterable<Integer> iterable = testing.keys();
        for (int i : iterable) {
            System.out.println(i);
        }

        /*MyLinkedList testing = new MyLinkedList();
        testing.addNode(5);
        testing.addNode(4);
        testing.addNode(8);
        testing.addNode(6);
        //testing.removeElement(6);
        Iterator<Integer> iterator = testing.iterator();
        for (Iterator<Integer> it = iterator; it.hasNext(); ) {
            int i = it.next();

            System.out.println(i);
        }
       // testing.display();
       // testing.printHeadTail();

        /*FixedCapacityStack<Integer> stack = new FixedCapacityStack<>(100);
        stack.push(2);
        stack.push(8);
        stack.push(7);
        stack.push(5);
        FixedCapacityStack<Integer> copy = copy(stack);
        Iterator<Integer> iterator = copy.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            index++;
            System.out.println(index + ": " + copy.pop().toString());
            iterator.next();
        }*/

        /*for (int i = 1048576; i <= 16777216; i *= 2) {
            System.out.println(i);
        }

        System.out.println("\nDivided: \n");

        for (int i = 16777216; i >= 1048576; i/=2) {
            System.out.println(i);
        }*/



       /* for (int n = 2; n <= 4096; n *= 2) {

            int len = n;

            int lo = 0;
            int hi = len - 1;
            int m = (lo + hi) / 2;
            int count = 0;

            System.out.println("--------------");
            System.out.println("size of n: " + n);

            count = 0;
            lo = 0;

            System.out.println("      ");

            while (hi > lo) {
                count++;
                System.out.println("count: " + count);
                m = (lo + hi) / 2;
                hi = m;
            }
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
