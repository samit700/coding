package com.sjain.ds;

import java.util.NoSuchElementException;

public class CircularQueue<E>  {

    private static int DEFAULT_CAPACITY = 100;

    private E[] queue;

    private int front;
    private int rear;
    private int size;

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularQueue(int capacity) {
        queue = (E[]) new Object[capacity];
        front = rear = size = 0;
    }

    
    public boolean add(E item) {
        if (size == queue.length) {
            extendCapacity();
        }
        queue[rear] = item;
        rear = (rear + 1) % queue.length;
        ++size;
        return true;
    }

    
    public void clear() {
        while (!isEmpty())
            poll();
    }

    
    public boolean offer(E item) {
        boolean added = false;
        try {
            added = add(item);
        } catch (IllegalStateException e) {
            added = false;
        }
        return added;
    }

    
    public E remove() {
        if (size == 0) {
            throw new NoSuchElementException("Stack Underflow");
        }

        E item = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        --size;
        return item;
    }

    
    public E poll() {
        E item = null;
        try {
            item = remove();
        } catch (NoSuchElementException e) {
        }
        return item;
    }

    
    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }
    
    public boolean contains(Object o) {
        for (int i = front; i != rear; i = (i + 1) % queue.length) {
            E e = queue[i];
            if (o == null) {
                if (e == null)
                    return true;
            }
            if (o.equals(e))
                return true;
        }
        return false;
    }


    
    public E element() {
        if (size == 0) {
            throw new NoSuchElementException("Stack Underflow");
        }

        return queue[front];
    }

    
    public E peek() {
        E item = null;
        try {
            item = element();
        } catch (NoSuchElementException e) {
        }
        return item;
    }

    private void extendCapacity() {
        E[] newQueue = (E[]) new Object[2 * queue.length];

        int i = 0;
        while (!isEmpty()) {
            E item = poll();
            newQueue[i] = item;
            ++i;
        }

        queue = newQueue;
        front = 0;
        rear = size;
    }
}