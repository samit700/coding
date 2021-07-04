package com.sjain.algo;

public class Binary {

    public static int countBits(int n) {
        int nBits = 0;
        while (n != 0) {
            nBits += n & 0x1;
            n >>>= 1;
        }
        return nBits;
    }

}
