package mgregg.hw3;

import algs.hw3.ShakespearePlay;

public class Q3 {

    private static String mostCommon;

    /*private static void findMostCommonInAll() {
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
    }*/

    /*private static void part3() {
        System.out.println("\nTop 5 without the most common word " + mostCommon + ": ");
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
                if (mostFrequent.equals(mostCommon)) { break; }
                else if (j == 4) { outputString.append(String.format("%-10s\t", shakespearePlay.getTitle()));
                    System.out.println(outputString); }
                outputString.append(String.format("%-10s\t", mostFrequent));
                bst.delete(mostFrequent);
            }
        }
    }*/

    private static void question3() {
        BST mostCommonBst = new BST();
        System.out.println("Please wait loading.....................\n");
        for (int i = 1; i <= 38; i++) {
            ShakespearePlay shakespearePlay = new ShakespearePlay(i);
            for (String s : shakespearePlay) {
                if (mostCommonBst.get(s) != null) {
                    mostCommonBst.put(s, mostCommonBst.get(s) + 1);
                } else {
                    mostCommonBst.put(s, 1);
                }
            }
        }
        mostCommon = mostCommonBst.mostFrequent();
        System.out.println("The most common word in all 38 plays is: " + mostCommon + "\n");
        System.out.println("The top 5 most common words in each play is: ");

        StringBuilder notMostCommon = new StringBuilder();
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
            StringBuilder outputString = new StringBuilder().append("\t");
            StringBuilder outputString2 = new StringBuilder();
            boolean test = false;
            for (int j = 0; j < 5; j++) {
                String mostFrequent = bst.mostFrequent();
                if (j == 4) {
                    outputString.append(String.format("%-10s\t", mostFrequent));
                    outputString.append(String.format("%-10s\t", shakespearePlay.getTitle()));
                    System.out.println(outputString);
                } else {
                    outputString.append(String.format("%-10s\t", mostFrequent));
                }
                if (mostFrequent.equals(mostCommon) && !test) {
                    outputString2 = new StringBuilder();
                    test = true;
                } else if (j == 4  && !test) {
                    outputString2.append(String.format("%-10s\t", mostFrequent));
                    notMostCommon.append(outputString2).append(String.format("%-10s\t", shakespearePlay.getTitle()));
                } else {
                    outputString2.append(String.format("%-10s\t", mostFrequent));
                }
                bst.delete(mostFrequent);
            }
            //outputString.append(String.format("%-10s\t", shakespearePlay.getTitle()));
            //System.out.println(outputString);
        }
        //System.out.println(notMostCommon + "\n");
        System.out.println("\nTop 5 without the most common word " + mostCommon + ": ");
        System.out.println("\t" + notMostCommon);
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

        System.out.println("\nLongest Word: " + longestWord);
    }

    public static String[] addWord(String[] a, int pos, String string) {
        String[] result = new String[a.length];
        for (int i = 0; i < pos; i++)
            result[i] = a[i];
        result[pos] = string;
        for (int i = pos - 1; i >= 0; i--)
            result[i] = a[i + 1];
        return result;
    }

    private static void shakespeareNumerorum() {
        int[] pattern = { 3, 1, 4, 1, 5 };
        String[] past = new String[5];
        for (int i = 1; i <= 38; i++) {
            ShakespearePlay shakespearePlay = new ShakespearePlay(i);
            for (int j = 0; j < shakespearePlay.size(); j++) {
                //past = addWord(past, past.length-1, shakespearePlay.);
            }
        }
    }

    public static void main(String[] args) {
        //findMostCommonInAll();
        question3();
        findLongestNonHyphenated();
        //findMostCommonInAll();
        //part2();
    }
}
