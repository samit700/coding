package com.sjain.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements Iterable<E>  {

    private Node<E> root;

    private int size;

    private static class Node<E> {
        private Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        private E value;
        private Node<E> next;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void push(E value) {
        Node<E> newNode = new Node<E>(value, root);
        root = newNode;
        ++size;
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack Underflow");
        }
        E value = root.value;
        root = root.next;
        --size;
        return value;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack Underflow");
        }
        return root.value;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (E v : this) {
            buf.append(v);
            buf.append(' ');
        }
        return buf.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> {
        private Node<E> curr = root;

        @Override
        public boolean hasNext() {
            return (curr != null);
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E value = curr.value;
            curr = curr.next;
            return value;
        }
    }

}

