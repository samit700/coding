package com.sjain.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private final Lock lock = new ReentrantLock();

    private final String name;

    public LockTest(String name) {
        this.name = name;
    }

    public void hello(LockTest other) {
        try {
            boolean selfLock = lock.tryLock(10, TimeUnit.MILLISECONDS);
            boolean otherLock = other.lock.tryLock(10, TimeUnit.MILLISECONDS);

            try {
                if (selfLock && otherLock)
                    System.out.printf("%s says hello to %s\n", this.name, other.name);
            } finally {
                if (selfLock)
                    lock.unlock();
                if (otherLock)
                    other.lock.unlock();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void test() throws InterruptedException {
        final LockTest l1 = new LockTest("samit");
        final LockTest l2 = new LockTest("sandeep");
        final LockTest l3 = new LockTest("mummy");

        Thread t1 = new Thread(() -> l1.hello(l2));
        Thread t2 = new Thread(() -> l2.hello(l3));
        Thread t3 = new Thread(() -> l3.hello(l1));

        t1.start();
        t2.start();
        t3.start();
    }
}
