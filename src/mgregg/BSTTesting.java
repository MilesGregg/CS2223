package mgregg;

import edu.princeton.cs.algs4.Graph;

public class BSTTesting {
    public static class BST {
        Node root;

        static class Node {
            int key;
            Node left, right;
        }

        public void deleteMax() {
            Node max = deleteMax(root);
            System.out.println(max.key);
        }

        public Node deleteMax(Node node) {
            //System.out.println("HERE: " + node.key);
            if (root == null) { return null; }
            if (node.right == null) { return node; }
            return deleteMax(node.right);
        }



        public int countBetween(int lo, int hi) {
            return countBetween(lo, hi, root);
        }

        public int countBetween(int lo, int hi, Node node) {
            if (node == null) {
                return 0;
            }

            if (node.key <= lo && node.key >= hi) {
                return 1 + countBetween(lo, hi, node.left) + countBetween(lo, hi, node.right);
            } else if (node.key < lo) {
                return countBetween(lo, hi, node.right);
            } else {
                return countBetween(lo, hi, node.left);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        BST.Node node1 = new BST.Node();
        node1.key = 1;
        BST.Node node2 = new BST.Node();
        node2.key = 2;
        BST.Node node3 = new BST.Node();
        node3.key = 3;
        BST.Node node4 = new BST.Node();
        node4.key = 4;

        bst.root = node1;
        bst.root.right = node2;
        bst.root.right.left = node3;
        bst.root.right.right = node4;

        bst.deleteMax();
    }

}
