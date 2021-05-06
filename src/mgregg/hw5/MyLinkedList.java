package mgregg.hw5;

import edu.princeton.cs.algs4.LinkedQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList implements Iterable {
    Node sorted;

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    //Represent the head and tail of the singly linked list
    public Node head = null;

    //addNode() will add a new node to the list
    public Node addNode(int val) {
        Node newNode = new Node(val);
/*
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            //tail.next = newNode;
            //tail = newNode;
        }*/
        if (head == null || head.data >= newNode.data) {
            newNode.next = head;
            head = newNode;
            return head;
        }

        // locate the node before the point of insertion
        Node current = head;
        while (current.next != null && current.next.data < newNode.data) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;

        return head;
    }

    public void removeElement(int val) {
        Node currentNode = head;
        Node previousNode = null;
        while (currentNode != null) {
            if (val == currentNode.data) {
                if (previousNode  == null) {
                    head = currentNode.next;
                } else {
                    previousNode.next = currentNode.next;
                }
            } else {
                previousNode = currentNode;
            }
            currentNode = currentNode.next;
        }
    }

    public Iterator<Integer> iterator()  {
        return new LinkedIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Integer> {
        private Node current = head;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Integer item = current.data;
            current = current.next;
            return item;
        }
    }

    public void printHeadTail() {
        System.out.println("Head: " + head.data); //+ " Tail: " + tail.data);
    }

    public void display() {
        //Node current will point to head
        Node current = head;

        if(head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Nodes of singly linked list: ");
        while(current != null) {
            //Prints each node by incrementing pointer
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
