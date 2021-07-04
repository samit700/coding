package com.sjain.ds;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MaxHeap<E extends Comparable<E>> {

    /*
        children -> 2*i + 1, 2*i + 2
        parent -> (i-1)/2
     */

    private static int DEFAULT_CAPACITY = 100;

    private E[] buf;

    int size = 0;

    public MaxHeap(int capacity) {
        buf = (E[]) new Comparable[capacity];
    }

    public MaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    private void swap(int i, int j) {
        E tmp = buf[i];
        buf[i] = buf[j];
        buf[j] = tmp;
    }

    private void siftDown(int i) {
        if (i >= size-1)
            return;

        int swapIdx = i;

        int leftChild = 2*i + 1;
        if (leftChild < size && buf[i].compareTo(buf[leftChild]) < 0)
            swapIdx = leftChild;

        int rightChild = 2*i + 2;
        if (rightChild < size && buf[swapIdx].compareTo(buf[rightChild]) < 0)
            swapIdx = rightChild;

        if (swapIdx != i) {
            swap(i, swapIdx);
            siftDown(swapIdx);
        }
    }

    private void siftUp(int i) {
        if (i == 0)
            return;

        int swapIdx = i;

        int parent = (i-1)/2;
        if (parent >= 0 && buf[i].compareTo(buf[parent]) > 0)
            swapIdx = parent;

        if (swapIdx != i) {
            swap(i, swapIdx);
            siftUp(swapIdx);
        }
    }

    public E pop() {
        if (size == 0)
            throw new NoSuchElementException("stack underflow");

        E e = buf[0];
        buf[0] = buf[--size];
        siftDown(0);

        return e;
    }

    public void push(E e) {
        if (size == buf.length)
            throw new IllegalArgumentException("stack overflow");

        buf[size++] = e;
        siftUp(size-1);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public String toString() {
        return IntStream.range(0, size)
                    .mapToObj(i -> String.valueOf(buf[i]))
                    .collect(Collectors.joining(","));
    }

}
