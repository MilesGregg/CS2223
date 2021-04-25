package mgregg.hw3;

import algs.hw3.ShakespearePlay;

public class Q3 {

    private static String mostCommon;

    private static void findMostCommonInAll() {
        BST bst = new BST();
        for (int i = 1; i <= 38; i++) {
            ShakespearePlay shakespearePlay = new ShakespearePlay(i);
            for (String s : shakespearePlay) {
                if (bst.get(s) != null) {
                    bst.put(s, bst.get(s) + 1);
                } else {
                    bst.put(s, 1);
                }
            }
        }
        mostCommon = bst.mostFrequent();
        System.out.println("The most common word in all 38 plays is: " + mostCommon);
    }

    private static void part2() {
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
                outputString.append(String.format("%-10s\t", mostFrequent));
                bst.delete(mostFrequent);
            }
            outputString.append(String.format("%-10s\t", shakespearePlay.getTitle()));
            System.out.println(outputString);
        }
    }

    private static void part3() {
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
                outputString.append(String.format("%s\t", mostFrequent));
                bst.delete(mostFrequent);
            }
            outputString.append(String.format("%15s", shakespearePlay.getTitle()));
            System.out.println(outputString);
        }
    }

    private static void findLongestNonHyphenated() {
        String longestWord = "";
        for (int i = 1; i <= 38; i++) {
            ShakespearePlay shakespearePlay = new ShakespearePlay(i);
            for (String s : shakespearePlay) {
                if (s.length() > longestWord.length()) {
                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == '-') {
                            break;
                        } else if (j == s.length() - 1) {
                            longestWord = s;
                        }
                    }
                }
            }
        }

        System.out.println("Longest Word: " + longestWord);
    }

    public static void main(String[] args) {
        findLongestNonHyphenated();
        //findMostCommonInAll();
        //part2();



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
