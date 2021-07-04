package com.sjain.concurrency;

import com.sjain.ds.CircularQueue;

public class ProducerConsumer {

    static class PCQueue<E> {

        private final CircularQueue<E> q;

        private static final int DEFAULT_CAPACITY = 10;

        private static final int BACK_PRESSURE_SIZE = 100;

        public PCQueue() {
            this(DEFAULT_CAPACITY);
        }

        public PCQueue(int capacity) {
            this.q = new CircularQueue<>(capacity);
        }

        public synchronized void offer(E item) {
            while (q.size() >= BACK_PRESSURE_SIZE) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    continue;
                }
            }
            q.offer(item);
            notifyAll();
        }

        public synchronized E poll() {
            while (q.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    continue;
                }
            }
            E item = q.poll();
            notifyAll();
            return item;
        }

        public synchronized boolean isEmpty() {
            return q.isEmpty();
        }

        public synchronized int size() {
            return q.size();
        }
    }

    public static void test() throws Exception {
        final PCQueue<Integer> q = new PCQueue<>();

        Thread producer = new Thread(() ->
            {
               for (int i = 0; i < 100; i++) {
                   q.offer(i);
                   System.out.println("Produced " + i);
                   try {
                       Thread.sleep(10);
                   } catch (InterruptedException ex) {}
               }

            });

        Thread consumer = new Thread(() ->
        {
            for (int i = 0; i < 100; i++) {
                int item = q.poll();
                System.out.println("Consumed " + item);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {}
            }

        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        assert q.isEmpty();
    }

}
