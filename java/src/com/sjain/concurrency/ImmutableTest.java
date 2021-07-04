package com.sjain.concurrency;

import java.util.Arrays;

public final class ImmutableTest {
    private final int a;
    private final long b;
    private final boolean[] c;

    public ImmutableTest(int a, long b, boolean[] c) {
        this.a = a;
        this.b = b;
        this.c = Arrays.copyOf(c, c.length);
    }

    public int getA() {
        return a;
    }

    public long getB() {
        return b;
    }

    public boolean[] getC() {
        return Arrays.copyOf(c, c.length);
    }

}
