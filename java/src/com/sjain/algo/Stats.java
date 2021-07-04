package com.sjain.algo;

import java.util.*;

public class Stats {

    public static double linearInterpolate(int n, List<Double> xKnots, List<Double> yKnots, double xInput) {
        if (xKnots.size() < n || xKnots.size() != yKnots.size())
            throw new IllegalArgumentException("Invalid Inputs n=" + n +
                    " xKnots.size=" + xKnots.size() + " yKnots.size=" + yKnots.size());

        if (n == 0)
            return Double.NaN;

        if (n == 1) {
            if (xKnots.get(0) == xInput)
                return yKnots.get(0);
            return Double.NaN;
        }

        int xLeftIdx = -1, xRightIdx = -1;
        double xLeftDist = Double.MAX_VALUE, xRightDist = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            double xi = xKnots.get(i);
            double yi = yKnots.get(i);
            if (xi < xInput) {
                double dist = xInput - xi;
                if (dist < xLeftDist || (dist == xLeftDist && yi > yKnots.get(xLeftIdx))) {
                    xLeftIdx = i;
                    xLeftDist = dist;
                }
            } else if (xi > xInput) {
                double dist = xi - xInput;
                if (dist < xRightDist || (dist == xRightDist && yi < yKnots.get(xRightIdx))) {
                    xRightIdx = i;
                    xRightDist = dist;
                }
            } else {
                return yi;
            }
        }

        assert (xLeftIdx != -1 || xRightIdx != -1);

        // find nearest point for extrapolation
        if (xLeftIdx == -1) {
            double xDist = Double.MAX_VALUE;
            xLeftIdx = xRightIdx;
            for (int i = 0; i < n; i++) {
                double xi = xKnots.get(i);
                double dist = xi - xKnots.get(xLeftIdx);
                if (dist < xDist) {
                    xRightIdx = i;
                    xDist = dist;
                }
            }
        } else if (xRightIdx == -1) {
            double xDist = Double.MAX_VALUE;
            xRightIdx = xLeftIdx;
            for (int i = 0; i < n; i++) {
                double xi = xKnots.get(i);
                double dist = xKnots.get(xRightIdx) - xi;
                if (dist < xDist) {
                    xLeftIdx = i;
                    xDist = dist;
                }
             }
        }

        return yKnots.get(xLeftIdx) +
                (xInput - xKnots.get(xLeftIdx)) *
                        ((yKnots.get(xRightIdx) - yKnots.get(xLeftIdx)) / (xKnots.get(xRightIdx) - xKnots.get(xLeftIdx)));

    }

    public static class Interval {
        int startTime;
        int endTime;
        double price;

        public Interval(int startTime, int endTime, double price) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.price = price;
        }

        @Override
        public String toString() {
            return "(" + startTime + "," + endTime + "," + price + ")";
        }
    }

    public static List<Interval> getLowestPriceIntervals(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();

        if (intervals == null || intervals.size() == 0)
            return res;

        // sort intervals by start time and length
        Collections.sort(intervals,
                (Interval i1, Interval i2) ->
                    {
                        int diff = Integer.compare(i1.startTime, i2.startTime);
                        if (diff == 0)
                            return Integer.compare(i1.endTime, i2.endTime);
                        return diff;
                    });

        Interval last = null;
        for (int i = 0; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (last == null || last.endTime <= curr.startTime) {
                res.add(curr);
                last = curr;
            } else if (Double.compare(last.price, curr.price) <= 0) {
                if (last.endTime < curr.endTime) {
                    last = new Interval(last.endTime, curr.endTime, curr.price);
                    res.add(last);
                }
            } else {
                if (last.startTime == curr.startTime) {
                    last.price = curr.price;
                    last.endTime = curr.endTime;
                    last = curr;
                } else {
                    last.endTime = curr.startTime;
                    res.add(curr);
                    last = curr;
                }
            }
        }

        return res;
    }

    public static long addOne(int[] a) {
        if (a.length == 0)
            return 0;

        long res = 0;
        int carryForward = 0;
        int d = 0;
        for (int i = a.length-1; i >= 0; i--) {
            d = a[i];
            if (i == a.length - 1)
                d = d + 1;
            d = d + carryForward;

            if (d >= 10)
                carryForward = 1;
            else
                carryForward = 0;
            res = res + (d % 10) * (long)Math.pow(10, a.length - 1 - i);
        }

        if (carryForward == 1)
            res = res + (long)Math.pow(10, a.length);

        return res;
    }

    public static String addBigNumbers(String s1, String s2) {
        Objects.requireNonNull(s1);
        Objects.requireNonNull(s2);

        if (s1.isEmpty())
            return s2;
        if (s2.isEmpty())
            return s1;

        StringBuffer res = new StringBuffer();
        int l1 = s1.length();
        int l2 = s2.length();
        int sign1 = s1.length() > 0 && ('-' == s1.charAt(0)) ? 1 : 0;
        int sign2 = s2.length() > 0 && ('-' == s2.charAt(0)) ? 1 : 0;

        if (sign1 == sign2) {
            boolean carry = false;
            l1 -= sign1;
            l2 -= sign2;
            int l = Math.max(l1, l2);
            for (int i = 0; i < l; i++) {
                int d1 = i < l1 ? 0 + (s1.charAt(s1.length() - 1 - i) - '0') : 0;
                int d2 = i < l2 ? 0 + (s2.charAt(s2.length() - 1 - i) - '0') : 0;
                if (d1 < 0 || d1 > 9 || d2 < 0 || d2 > 9)
                    throw new IllegalArgumentException("invalid character, must contain 0-9 with sign in front optionally");
                int si = d1 + d2 + (carry? 1: 0);
                carry = si >= 10;
                res.append((char)('0' + (si % 10)));
            }
            if (carry)
                res.append('1');
            if (sign1 == 1)
                res.append('-');
        } else {
            String bigger = s1;
            String smaller = s2;
            if (l2 - sign2 > l1 - sign1) {
                bigger = s2;
                smaller = s1;
            } else if (l2 - sign2 == l1 - sign1) {
                int i = 0;
                while (i < (l1 - sign1) && s1.charAt(i+sign1) == s2.charAt(i+sign2))
                    ++i;
                if (i < (l1 - sign1) && s2.charAt(i+sign2) > s1.charAt(i+sign1)) {
                    bigger = s2;
                    smaller = s1;
                }
            }

            l1 = bigger.length();
            l2 = smaller.length();
            sign1 = ('-' == bigger.charAt(0)) ? 1 : 0;
            sign2 = ('-' == smaller.charAt(0)) ? 1 : 0;
            l1 -= sign1;
            l2 -= sign2;
            boolean borrow = false;
            for (int i = 0; i < l1; i++) {
                int d1 = i < l1 ? 0 + (bigger.charAt(bigger.length() - 1 - i) - '0') : 0;
                int d2 = i < l2 ? 0 + (smaller.charAt(smaller.length() - 1 - i) - '0') : 0;
                if (d1 < 0 || d1 > 9 || d2 < 0 || d2 > 9)
                    throw new IllegalArgumentException("invalid character, must contain 0-9 with '-' in front optionally");
                if (borrow)
                    --d1;
                borrow = false;
                if (d1 < d2) {
                    borrow = true;
                    d1 += 10;
                }
                int di = d1 - d2;
                if (di == 0 && i == (l1 - 1))
                    break;
                res.append((char) ('0' + di));
            }
            if (sign1 == -1)
                res.append('-');
        }

        return res.reverse().toString();
    }


}
