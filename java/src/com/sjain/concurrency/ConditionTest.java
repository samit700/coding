package com.sjain.concurrency;

import com.sjain.ds.CircularQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    static class BoundedBuffer {
        CircularQueue<Integer> q = new CircularQueue<>();

        private static final int MAX_SIZE = 10;

        Lock lock = new ReentrantLock();
        Condition notEmptyCond = lock.newCondition();
        Condition notFullCond = lock.newCondition();

        void produce(Integer item) {
            lock.lock();

            try {
                while (q.size() >= MAX_SIZE) {
                    try {
                        notFullCond.await();
                    } catch (InterruptedException ex) {
                    }
                }
                q.offer(item);
                System.out.println("Produced " + item);
                notEmptyCond.signalAll();
            } finally {
                lock.unlock();
            }
        }

        Integer consume() {
            lock.lock();

            try {
                while (q.isEmpty()) {
                    try {
                        notEmptyCond.await();
                    } catch (InterruptedException ex) {
                    }
                }
                Integer item = q.poll();
                System.out.println("Consumed " + item);
                notFullCond.signalAll();
                return item;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void test() {
        final BoundedBuffer buf = new BoundedBuffer();

        //producers
        for (int i = 0; i < 100; i++) {
            final int item = i;
            new Thread(() -> buf.produce(item)).start();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }

        //consumers
        for (int i = 0; i < 100; i++) {
            final int item = i;
            new Thread(() -> buf.consume()).start();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }

}
