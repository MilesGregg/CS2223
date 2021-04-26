package mgregg.hw3;

import algs.hw3.ShakespearePlay;

import java.util.Arrays;

public class Q3 {

    private static void question3() {
        BST mostCommonBst = new BST();
        System.out.println("Please wait loading.....................\n");
        for (int i = 1; i <= 38; i++) {
            for (String s : new ShakespearePlay(i)) {
                if (mostCommonBst.get(s) != null) {
                    mostCommonBst.put(s, mostCommonBst.get(s) + 1);
                } else {
                    mostCommonBst.put(s, 1);
                }
            }
        }
        String mostCommon = mostCommonBst.mostFrequent();
        System.out.println("Q3: ");
        System.out.println("\tThe most common word in all 38 plays is: " + mostCommon + "\n");
        System.out.println("\tThe top 5 most common words in each play is: ");

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
            StringBuilder outputMostCommon = new StringBuilder().append("\t\t");
            StringBuilder outputWithoutTop = new StringBuilder();
            boolean mostCommonHere = false;
            for (int j = 0; j < 5; j++) {
                String mostFrequent = bst.mostFrequent();
                outputMostCommon.append(String.format("%-10s\t", mostFrequent));
                if (j == 4) {
                    outputMostCommon.append(String.format("%-10s\t", shakespearePlay.getTitle()));
                    System.out.println(outputMostCommon);
                }
                if (mostFrequent.equals(mostCommon) && !mostCommonHere) {
                    outputWithoutTop = new StringBuilder();
                    mostCommonHere = true;
                } else if (j == 4  && !mostCommonHere) {
                    outputWithoutTop.append(String.format("%-10s\t", mostFrequent));
                    notMostCommon.append(outputWithoutTop).append(String.format("%-10s\t", shakespearePlay.getTitle()));
                } else {
                    outputWithoutTop.append(String.format("%-10s\t", mostFrequent));
                }
                bst.delete(mostFrequent);
            }
        }
        System.out.println("\n\tTop 5 without the most common word " + mostCommon + ": ");
        System.out.println("\t\t" + notMostCommon);
    }

    private static void shakespeareLongissimum() {
        String longestWord = "";
        for (int i = 1; i <= 38; i++) {
            for (String s : new ShakespearePlay(i)) {
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
        System.out.println("\n(BONUS QUESTION)Q3.1: ");
        System.out.println("\tLongest Word: " + longestWord);
    }

    public static String[] addWord(String[] words, String newWord, int position) {
        String[] output = new String[words.length];
        for (int i = 0; i < position; i++)
            output[i] = words[i];
        output[position] = newWord;
        for (int i = position - 1; i >= 0; i--)
            output[i] = words[i + 1];
        return output;
    }

    private static void shakespeareNumerorumMysteria(int[] pattern) {
        String[] past = new String[pattern.length];
        int counter = 0;
        System.out.println("\nQ3.2(BONUS QUESTION): ");
        for (int i = 1; i <= 38; i++) {
            ShakespearePlay shakespearePlay = new ShakespearePlay(i);
            for (String s : shakespearePlay) {
                past = addWord(past, s, past.length-1);
                if (past[0] != null) {
                    for (int k = 0; k < pattern.length; k++) {
                        if (past[k].length() != pattern[k]) {
                            break;
                        } else if (k == pattern.length-1) {
                            System.out.println("\t" + Arrays.toString(past) + ": " + shakespearePlay.getTitle());
                            counter++;
                        }
                    }
                }
            }
        }
        System.out.println("\n\tShakespeare uses this pattern: " + counter + " times");
    }

    public static void main(String[] args) {
        question3();
        shakespeareLongissimum();
        shakespeareNumerorumMysteria(new int[]{ 3, 1, 4, 1, 5 });
    }
}
