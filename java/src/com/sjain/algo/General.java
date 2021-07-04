package com.sjain.algo;

import com.sjain.ds.BinaryTree;
import com.sjain.ds.Graph;
import com.sjain.ds.MaxHeap;
import com.sjain.ds.MinHeap;

import java.util.*;
import java.util.stream.Collectors;

import static com.sjain.algo.StringAlgos.minInsertsForPalinHelper;

public class General {

    public static boolean checkSubsetSum(int k, int[] a) {
        Set<Integer> s = new HashSet<>();
        int cumSum = 0;
        for (int i : a) {
            cumSum += i;
            if (cumSum == k || s.contains(cumSum - k))
                return true;
            s.add(cumSum);
        }
        return false;
    }

    public static boolean checkBalancedParens(String expr, Parenthesis paren) {
        if (expr == null || paren == null)
            return true;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (paren.isOpeningBrace(c)) {
                stack.push(c);
            } else if (paren.isClosingBraces(c)) {
                if (stack.isEmpty())
                    return false;
                char lastOpen = stack.pop();
                if (c != paren.lookup(lastOpen).getClose())
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public static int maxNumericValue(String s, int start, int end) {
        if (start >= end || s.length() < end - start)
            throw new IllegalArgumentException("invalid bounds");

        while (start < end && s.charAt(start) == '0') ++start;
        while (start < end && s.charAt(end - 1) == '0') --end;

        int startVal = 0;
        while (start < end && startVal <= 1) {
            startVal += s.charAt(start) - '0';
            if (startVal <= 1)
                ++start;
        }

        int endVal = 0;
        while (start < end && endVal <= 1) {
            endVal += s.charAt(end - 1) - '0';
            if (endVal <= 1)
                --end;
        }

        int v = 0;
        for (int i = start; i < end; i++) {
            int e = s.charAt(i) - '0';
            if (i == start)
                e = startVal;
            if (i == end - 1)
                e = endVal;

            int n = 0;
            if (i + 1 < end) {
                if (i + 1 == end - 1)
                    n = endVal;
                else
                    n = s.charAt(i + 1) - '0';
            }

            if (e == 0)
                continue;

            if (v == 0) {
                v = e;
            } else if (e == 1) {
                if (n > v)
                    v = n * (v + 1);
                else
                    v = (n + 1) * v;
                i++;
            } else {
                v = v * e;
            }
        }

        return v;
    }

    public static BinaryTree<String> ternaryExprTree(String expr) {
        BinaryTree<String> tree = new BinaryTree<String>();
        tree.root = ternaryExprTreeHelper(expr, 0, expr.length());
        return tree;
    }

    private static BinaryTree.Node<String> ternaryExprTreeHelper(String expr, int start, int end) {
        // a?b:c?d:e

        //condition
        int i = start, j = i;
        while (j < end && expr.charAt(j) != '?') ++j;
        if (i == j)
            throw new IllegalArgumentException("invalid expr");
        BinaryTree.Node<String> node = new BinaryTree.Node<String>(expr.substring(i, j));

        if (j == end)
            return node;

        //true value
        i = ++j;
        int skip = 0;
        while (j < end) {
            if (expr.charAt(j) == ':') {
                if (skip == 0)
                    break;
                --skip;
            }
            if (expr.charAt(j) == '?')
                ++skip;

            ++j;
        }
        if (i == j || j == end)
            throw new IllegalArgumentException("invalid expr");
        node.left = ternaryExprTreeHelper(expr, i, j);

        //false value
        i = ++j;
        node.right = ternaryExprTreeHelper(expr, i, end);

        return node;
    }

    //quick sort
    public static int[] sort(int[] arr, int from, int to) {
        if (from < to) {
            int partition = partition(arr, from, to);
            sort(arr, from, partition - 1);
            sort(arr, partition + 1, to);
        }
        return arr;
    }

    private static int partition(int[] arr, int from, int to) {
        int pivot = to;
        int i = from;

        for (int j = from; j <= to - 1; j++) {
            if (arr[j] < arr[pivot]) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }

        int tmp = arr[pivot];
        arr[pivot] = arr[i];
        arr[i] = tmp;

        return i;
    }

    public static int[] moveZeros(int[] a) {
        int i = 0, j = 0;
        while (i < a.length) {
            if (a[i] != 0) {
                if (i > j)
                    a[j] = a[i];
                ++j;
            }
            ++i;
        }

        while (j < a.length) {
            a[j] = 0;
            ++j;
        }

        return a;
    }

    public static int reverseInt(int num) {
        int n = Math.abs(num);
        int i = 0;
        while (n != 0) {
            n /= 10;
            ++i;
        }

        int res = 0;
        n = Math.abs(num);
        while (i > 0) {
            res += (n % 10) * Math.pow(10, i - 1);
            n /= 10;
            --i;
        }
        return (num < 0 ? -1 : 1) * res;
    }



    public static List<String> braceExpansion(String expr) {
        Objects.requireNonNull(expr);
        List<String> l = braceExpansionHelper(expr, 0, expr.length());
        return l.stream().sorted().distinct().collect(Collectors.toList());
    }

    private static final Set<Character> specialChars = Collections.unmodifiableSet(new HashSet<>(Arrays.asList('{','}',',')));

    private static List<String> braceExpansionHelper(String expr, int start, int end) {
        List<String> l = new ArrayList<>();
        if (start > expr.length() || end > expr.length() || start >= end)
            return l;

        int i = start;
        while (i < end && (!specialChars.contains(expr.charAt(i))))
            ++i;

        if (i == end) {
            l.add(expr.substring(start, end));
        } else if (expr.charAt(i) == ',') {
            l.add(expr.substring(start, i));
            l.addAll(braceExpansionHelper(expr, i+1, end));
        } else if (expr.charAt(i) == '}') {
            throw new IllegalArgumentException("Invalid Formatted Expression");
        } else if (expr.charAt(i) == '{') {
            int j = i;
            while (!(expr.charAt(j) == '}'))
                ++j;
            if (j == end)
                throw new IllegalArgumentException("Invalid Formatted Expression");
            String s1 = expr.substring(start, i);
            List<String> l2 = braceExpansionHelper(expr, i + 1, j);
            l = l2.stream().map(s -> s1 + s).collect(Collectors.toList());
            if (++j < end) {
                if (expr.charAt(j) == ',') {
                    l.addAll(braceExpansionHelper(expr, j + 1, end));
                } else {
                    List<String> l4 = new ArrayList<>();
                    List<String> l3 = braceExpansionHelper(expr, j, end);
                    for (String s : l)
                        for (String s3 : l3)
                            l4.add(s + s3);
                    l = l4;
                }
            }
        }
        return l;
    }

    public static boolean canTransformWithReplaces(String s1, String s2) {
        Objects.requireNonNull(s1);
        Objects.requireNonNull(s2);

        assert s1.length() == s2.length() : "strings not of same length";

        BitSet charSet = new BitSet(26);
        for (int i = 0; i < s1.length(); i++) {
            int idx = 'z' - s1.charAt(i);
            if (idx < 0 || idx > 25)
                throw new IllegalArgumentException("strings must contain lowercase chars");
            charSet.set(idx);
        }

        // if at least one additional char is available, we can resolve all cycles
        if (charSet.cardinality() < 26)
            return true;

        // there should be no cycles in graph
        Graph<Character> charGraph = new Graph<>();
        for (int i = 0; i < s1.length(); i++) {
            charGraph.addEdge(s1.charAt(i), s2.charAt(i));
        }

        if (charGraph.isCyclic())
            return false;
        return true;
    }

    private static final class DailyPrice implements Comparable<DailyPrice> {
        final int day;
        final double price;

        DailyPrice(int day, double price) {
            this.day = day;
            this.price = price;
        }

        @Override
        public int compareTo(DailyPrice other) {
            return Double.compare(price, other.price);
        }
    }


    public static double maxSpan(double[] prices) {
        Objects.requireNonNull(prices);
        if (Arrays.stream(prices).anyMatch(price -> price < 0))
            throw new IllegalArgumentException("Invalid 1 or more Prices");

        double maxSpan = 0.;
        double minPrice = Double.MAX_VALUE;
        for (double price : prices) {
            if (Double.compare(price, minPrice) < 0) {
                minPrice = price;
            } else {
                double profit = price - minPrice;
                if (Double.compare(profit, maxSpan) > 0)
                    maxSpan = profit;
            }
        }
        return maxSpan;
    }

    public static double maxProfit(double[] prices) {
        Objects.requireNonNull(prices);

        // running max and min
        int n = prices.length;
        double[] maxp = new double[n];
        double[] minp = new double[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                minp[0] = prices[0];
                maxp[n-1] = prices[n-1];
            } else {
                minp[i] = Math.min(minp[i-1], prices[i]);
                maxp[n-1-i] = Math.max(maxp[n-i], prices[n-1-i]);
            }
        }

        // span
        double span = 0;
        int from = -1;
        int to = -1;
        for (int i = 0; i < n-1; i++) {
            double spani = maxp[i+1] - minp[i];
            if (spani > span) {
                span = spani;
            }
        }

        return span;
    }

    public static int maxSubsetSum(int[] arr, int sum) {
        if (arr == null || arr.length == 0)
            return 0;
        return maxSubsetSumInternal(arr, sum, 0, arr.length);
    }

    private static int maxSubsetSumInternal(int[] arr, int sum, int from, int to) {
        if (sum == 0)
            return 1;
        if (sum < 0 || from >= to)
            return 0;

        return maxSubsetSumInternal(arr, sum-arr[from], from+1, to)
                + maxSubsetSumInternal(arr, sum, from+1, to);
    }

    public static boolean subsetSum(int[] arr, int sum) {
        if (arr == null || arr.length == 0)
            return false;
        return subsetSumInternal(arr, sum, 0, arr.length);
    }

    private static boolean subsetSumInternal(int[] arr, int sum, int from, int to) {
        if (sum == 0)
            return true;
        if (sum < 0 || from >= to)
            return false;

        return subsetSumInternal(arr, sum-arr[from], from+1, to)
                || subsetSumInternal(arr, sum, from+1, to);
    }



}