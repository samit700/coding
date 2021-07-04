package com.sjain.algo;

import java.io.Serializable;
import java.util.*;

public class SecurityPriceCache implements Serializable {

    private int size;

    static class Node implements Serializable {
        Node prev;
        Node next;
        String ticker;
        double price;

        public Node(Node prev, Node next, String ticker, double price) {
            this.prev = prev;
            this.next = next;
            this.ticker = ticker;
            this.price = price;
        }
    }

    private Node head;
    private Node tail;

    Map<String, Node> priceMap = new HashMap<>();

    public SecurityPriceCache(int size) {
        this.size = size;
    }

    public void updatePrice(String ticker, double price) {
        if (priceMap.containsKey(ticker)) {
            Node n = priceMap.get(ticker);
            moveToFront(n);
        } else {
            if (priceMap.size() == size) {
                tail = tail.prev;
                tail.next = null;
                priceMap.remove(tail.ticker);
            }

            head = new Node(null, head, ticker, price);
            priceMap.put(ticker, head);
        }
    }

    private void moveToFront(Node n) {
        if (n == head)
            return;

        n.prev.next = n.next;
        if (n.next != null)
            n.next.prev = n.prev;

        n.next = head;
        head = n;
    }

    public double getPrice(String ticker) {
        if (priceMap.containsKey(ticker)) {
            Node n = priceMap.get(ticker);
            moveToFront(n);
            return n.price;
        }
        return Double.NaN;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node n = head;
        while (n != null) {
            sb.append(n.ticker).append(':').append(n.price);
            if (n.next != null)
                sb.append(';');
            n = n.next;
        }
        return sb.toString();
    }

}
