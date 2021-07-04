package com.sjain.ds;

import java.util.*;
import java.util.Stack;

import static java.lang.Integer.max;


public class ListAlgos {

    public static int binarySearch(int[] arr, int val) {
        if (arr == null || arr.length == 0)
            return -1;
        int i = 0, j = arr.length;
        while (i <= j) {
            int mid = (i+j)/2;
            if (arr[mid] == val)
                return mid;
            if (arr[mid] < val)
                i = mid + 1;
            else
                j = mid - 1;
        }
        return -1;
    }

    public static int[] calculateSpan(int[] prices, int n) {
        int[] span = new int[n];
        for (int i = 0; i < n; i++) {

            int s = 0;
            for (int j = i; j >= 0; j--) {
                if (prices[j] > prices[i])
                    break;
                ++s;
            }
            span[i] = s;
        }
        return span;
    }

    public static int[] stockSpan(int[] p, int n) {
        if (n > p.length)
            throw new IllegalArgumentException("n > length");

        if (n == 0)
            return new int[0];

        int[] span = new int[n];
        span[0] = 1;

        java.util.Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < n; i++) {
            if (p[i] < p[i - 1])
                stack.push(i);

            int j = i - 1;
            while (p[i] > p[j])
                j = stack.pop();

            span[i] = i - j;
            stack.push(j);
        }

        return span;
    }

    public static int maxSubArraySum(int[] a, int n, int k) {
        int ksum = 0;
        for (int i = 0; i < k; i++) {
            ksum += a[i];
        }

        int maxSum = ksum;
        for (int i = n-k-1,j=0; i < n; i++,j++) {
            ksum = ksum - a[j] + a[i];
            maxSum = max(maxSum, ksum);
        }
        return maxSum;
    }

    public static int maxSubArraySum(int[] a) {
        int maxSumi = a[0];
        int maxSumStarti = 0;

        int maxSum = maxSumi;
        int maxSumStart = maxSumStarti;
        int maxSumEnd = maxSumStarti;

        for (int i = 1; i < a.length; i++) {
            if (a[i] > maxSumi + a[i]) {
                maxSumi = a[i];
                maxSumStarti = i;
            } else {
                maxSumi = maxSumi + a[i];
            }

            if (maxSumi > maxSum) {
                maxSum = maxSumi;
                maxSumStart = maxSumStarti;
                maxSumEnd = i;
            }
        }
        System.out.println("[Max List Sum] Start: " + maxSumStart + "; End: " + maxSumEnd);
        return maxSum;
    }

    public static int[] maxSubArrays(int[] a, int n, int k) {
        int[] maxs = new int[n+1-k];

        Deque<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < k-1; i++) {
            while ((!queue.isEmpty()) && (a[i] >= a[queue.peekLast()]))
                queue.removeLast();

            queue.addLast(i);
        }

        for (int i = k-1, j = 0; i < n; i++, j++) {
            while ((!queue.isEmpty()) && (queue.peekFirst() <= i-k))
                queue.removeFirst();

            while ((!queue.isEmpty()) && (a[i] >= a[queue.peekLast()]))
                queue.removeLast();

            queue.addLast(i);

            maxs[j] = a[queue.peekFirst()];
        }

        return maxs;
    }


    public static <E> E[] reverse(E[] arr) {
        int i = 0, j = arr.length-1;
        while (i < j) {
            E tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            ++i;
            --j;
        }
        return arr;
    }

    public static int sumList(List<? extends Integer> l) {
        int sum = 0;
        for (Integer i : l)
            sum += i;
        return sum;
    }

    public static <T> void copyList(List<? extends T> from, List<? super T> to) {
        to.clear();
        for (T e : from) {
            to.add(e);
        }
    }

    public static void radixSort(int[] arr) {
        int[] output = new int[arr.length];

        int k = maxDigits(arr);
        int[] counts = new int[10];

        int exp = 1;
        for (int i = 0; i < k; i++, exp *= 10) {
            Arrays.fill(counts, 0);

            for (int j : arr)
                ++counts[(j / exp) % 10];

            for (int j = 1; j < 10; j++)
                counts[j] += counts[j-1];

            for (int j =  arr.length - 1; j > 0; j--) {
                int idx = (arr[j] / exp) % 10;
                output[counts[idx]-1] = arr[j];
                --counts[idx];
            }

            for (int j = 0; j < arr.length; j++)
                arr[j] = output[j];
        }

    }

    public static int maxDigits(int[] arr) {
        int max = arr[0];
        for (int i : arr)
            max = Math.max(max, i);

        int i = 0;
        for (; max > 0; i++, max /= 10);
        return i;
    }

    public static void sortByFrequency(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : arr) {
            Integer fi = freq.get(i);
            freq.put(i, (fi == null) ? 1 : fi + 1);
        }

        List<Pair<Integer, Integer>> plist = new ArrayList<>(arr.length);
        for (int i : arr)
            plist.add(new Pair<>(i, freq.get(i)));

        Comparator<Pair<Integer, Integer>> comparator = new Comparator<Pair<Integer, Integer>>() {
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };

        Comparator<Pair<Integer, Integer>> comparator1 = (p1, p2) -> Integer.compare(p1.getValue(), p2.getValue());

        plist.sort(comparator1);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = plist.get(i).getKey();
        }
    }

}
