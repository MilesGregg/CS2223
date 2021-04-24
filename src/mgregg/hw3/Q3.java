package mgregg.hw3;

import algs.hw3.ShakespearePlay;

import java.util.Iterator;

public class Q3 {


    public static void main(String[] args) {

        for (int i = 1; i <= 38; i++) {
            ShakespearePlay shakespearePlay = new ShakespearePlay(i);
            BST bst = new BST();
            for (String s : shakespearePlay) {
                if (bst.get(s) != null) {
                    bst.put(s, bst.get(s) + 1);
                } else {
                    bst.put(s, 1);
                }
            }
            StringBuilder outputString = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                String mostFrequent = bst.mostFrequent();
                outputString.append(mostFrequent).append("\t\t");
                bst.delete(mostFrequent);
            }
            outputString.append(shakespearePlay.getTitle());
            System.out.println(outputString);
        }

        /*for (int i = 1; i <= 38; i++) {
            ShakespearePlay shakespearePlay = new ShakespearePlay(i);
            BST bst = new BST();
            for (String s : shakespearePlay) {
                if (bst.get(s) != null) {
                    bst.put(s, bst.get(s) + 1);
                } else {
                    bst.put(s, 1);
                }
            }
            StringBuilder outputString = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                String mostFrequent = bst.mostFrequent();
                outputString.append(mostFrequent).append("\t\t");
                bst.delete(mostFrequent);
            }
            outputString.append(shakespearePlay.getTitle());
            System.out.println(outputString);
        }*/











        /*while (iterator.hasNext()) {
            String target = iterator.next();
            bst.put(target, bst.get(target));
        }
        for (int i = 0; i < 5; i++) {
            String word = bst.mostFrequent();
            if ()
        }*/

        /*Iterator<String> iterator = shakespearePlay.iterator();
        while (iterator.hasNext()) {

            for (String s : shakespearePlay) {

            }

            iterator.next();
        }*/
    }
}
