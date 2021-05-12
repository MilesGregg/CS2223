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

        void insert(int key) {
            root = insertRec(root, key);
        }

        Node insertRec(Node root, int key) {
            if (root == null)
            {
                root = new Node();
                root.key = key;
                return root;
            }

            if (key < root.key)
                root.left = insertRec(root.left, key);
            else if (key > root.key)
                root.right = insertRec(root.right, key);

            return root;
        }

        public int countBetween(int lo, int hi) {
            return countBetween(lo, hi, root);
        }

        public int countBetween(int lo, int hi, Node node) {
            if (node == null) {
                return 0;
            }

            if (node.key <= hi && node.key >= lo) {
                return 1 + countBetween(lo, hi, node.left) + countBetween(lo, hi, node.right);
            } else if (node.key < lo) {
                return countBetween(lo, hi, node.right);
            } else {
                return countBetween(lo, hi, node.left);
            }
        }
    }

//    public void allSources() {
//        dfsMain(3);                        // O(V + E)
//        for (int v = 0; v < V; v++) {      // O(V)
//            if (marked[v] == true) {
//                System.out.println(v);
//            }
//        }
//    }

    public static void main(String[] args) {
        BST tree = new BST();
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
       // tree.insert(80);
        System.out.println("Size: " + tree.countBetween(40, 70));
        tree.deleteMax();

        /*BST bst = new BST();
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

        bst.deleteMax();*/
    }

}
