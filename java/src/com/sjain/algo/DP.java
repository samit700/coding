package com.sjain.algo;

import java.util.Arrays;

public class DP {

    //longest non-continuous sequence using recursion
    public static int longestPalindromeNonCont1(String s, int from, int to) {
        if (s == null || from > to)
            return 0;
        if (from == to)
            return 1;

        if (s.charAt(from) == s.charAt(to))
            return 2 + longestPalindromeNonCont1(s, from + 1, to - 1);

        return Math.max(longestPalindromeNonCont1(s, from + 1, to), longestPalindromeNonCont1(s, from, to - 1));
    }

    //longest palindrom non-cnt sequence using DP
    public static int longestPalindromeNonCont2(String s, int from, int to) {
        if (s == null || from > to)
            return 0;
        if (from == to)
            return 1;

        int n = to - from + 1;
        int[][] m = new int[n][n];

        //init length 1 - diagonal elements
        for (int i = 0; i < n; i++) {
            m[i][0] = 1;
        }

        /* bottom up evaluation using DP */
        //for length 2 and above..
        for (int l = 1; l < n; l++) {
            //for each element..
            for (int i = from; i <= to - l; i++) {
                if (s.charAt(i) == s.charAt(i + l)) {
                    if (l == 1)
                        m[i][l] = 2;
                    else
                        m[i][l] = 2 + m[i + 1][l - 2];
                } else {
                    m[i][l] = Math.max(m[i][l - 1], m[i + 1][l - 1]);
                }
            }
        }

        return m[from][n - 1];
    }


    //longest palindrom cnt sequence using DP
    public static int longestPalindromeCont1(String s, int from, int to) {
        if (s == null || from > to)
            return 0;
        if (from == to)
            return 1;

        int n = to - from + 1;
        int[][] m = new int[n][n];
        int maxp = 0;

        //init length 1 - diagonal elements
        for (int i = 0; i < n; i++) {
            m[i][0] = 1;
        }

        /* bottom up evaluation using DP */
        //for length 2 and above..
        for (int l = 1; l < n; l++) {
            //for each element..
            for (int i = from; i <= to - l; i++) {
                if (s.charAt(i) == s.charAt(i + l)) {
                    if (l == 1)
                        m[i][l] = 2;
                    else if (m[i + 1][l - 2] == 0)
                        m[i][l] = 0;
                    else
                        m[i][l] = 2 + m[i + 1][l - 2];
                } else {
                    m[i][l] = 0;
                }

                maxp = Math.max(maxp, m[i][l]);
            }
        }

        return maxp;
    }

    public static int minPalindromeSplits(String s, int from, int to) {
        if (from >= to)
            return 0;

        if (StringAlgos.isPalindrome(s, from, to))
            return 0;

        int minSplits = Integer.MAX_VALUE;
        for (int i = from; i < to; i++) {
            minSplits = Math.min(minSplits, 1 + minPalindromeSplits(s, from, i) + minPalindromeSplits(s, i+1, to));
        }
        return minSplits;
    }

    public static int minPalindromeSplitsDP(String s) {
        int n = s.length();
        if (n <= 1)
            return 0;

        int[][] minSplits = new int[n][n];
        for (int i = 0; i < n; i++)
            minSplits[i][0] = 0;

        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n; i++) {
                if (i + len >= n)
                    continue;
                if (s.charAt(i) == s.charAt(i + len) &&
                        (len == 1 || minSplits[i + 1][len - 2] == 0)) {
                    minSplits[i][len] = 0;
                } else {
                    int minSplit = Integer.MAX_VALUE;
                    for (int k = 0; k < len; k++)
                        minSplit = Math.min(minSplit, 1 + minSplits[i][k] + minSplits[i+k+1][len-k-1]);
                    minSplits[i][len] = minSplit;
                }
            }
        }
        return minSplits[0][n - 1];
    }

    public static long stepsPermutation(int n) {
        if (n <= 0)
            return 0;

        long[] m = new long[n];

        for (int i = 0; i < n; i++) {
            if (i == 0)
                m[i] = 1;
            else if (i == 1)
                m[i] = 2;
            else
                m[i] = m[i - 1] + m[i - 2];
        }

        return m[n - 1];
    }

    public static int longestIncreasingSeqRecur(int[] arr, int n) {
        int maxLis = 0;
        for (int i = 0; i < n; i++) {
            maxLis = Math.max(maxLis, 1 + lis(arr, i, i+1, n));
        }
        return maxLis;
    }

    private static int lis(int[] arr, int i, int j, int n) {
        if (j >= n)
            return 0;

        if (arr[j] > arr[i])
            return Math.max(1 + lis(arr, j, j + 1, n), lis(arr, i, j + 1, n));

        return lis(arr, i, j + 1, n);
    }

    public static int longestIncreasingSeq(int[] arr, int n) {
        int[] lis = new int[n];
        for (int i = 0; i < n; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;
            }
        }

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, lis[i]);
        }
        return maxLen;
    }

    public static int maximizeTheft(int[] h) {
        int n = h.length;
        int[][] m = new int[n][n];

        for (int i = 0; i < n; i++)
            m[i][0] = h[i];

        for (int i = 0; i < n-1; i++)
            m[i][1] = Math.max(h[i], h[i+1]);

        for (int gap = 2; gap < n; gap++) {
            for (int i = 0; i < n-gap; i++) {
                m[i][gap] = Math.max(h[i+gap] + m[i][gap-2], m[i][gap-1]);
            }
        }

        return m[0][n-1];
    }

    public static int coinChangeCount(int[] arr, int sum) {
        if (arr == null || arr.length == 0)
            return 0;

        int[] sums = new int[sum+1];
        Arrays.fill(sums, 0);
        for (int elem : arr) {
            int x = elem;
            for (int i = elem; i <= sum; i++) {
                sums[i] += (i == elem)? 1 : sums[i-elem];
            }
        }
        return sums[sum];
    }

    public static int subsetSumCount(int[] arr, int sum) {
        if (arr == null || arr.length == 0)
            return 0;

        int[] freqs = new int[sum+1];
        Arrays.fill(freqs, 0);
        for (int elem : arr) {
            if (elem <= sum)
                ++freqs[elem];
        }

        int[] sums = new int[sum+1];
        Arrays.fill(sums, 0);
        int[] tmpArr = new int[sum+1];
        for (int i: arr) {
            Arrays.fill(tmpArr, 0);
            for (int j = i; j <= sum; j++) {
                if (j == i)
                    sums[j] += 1;
                if (tmpArr[j-i] < freqs[i] && sums[j - i] > 0) {
                    sums[j] += sums[j - i];
                    tmpArr[j] = tmpArr[j - i] + 1;
                }
            }
        }
        return sums[sum];
    }

    public static int longestCommonSubseqRecur(int[] arr, int[] barr, int ai, int bi, int count) {
        if (ai >= arr.length || bi >= barr.length)
            return count;

        if (arr[ai] == barr[bi])
            return longestCommonSubseqRecur(arr, barr, ai+1, bi+1, count+1);

        return Math.max(count,
                Math.max(longestCommonSubseqRecur(arr, barr, ai, bi+1, 0),
                        longestCommonSubseqRecur(arr, barr, ai+1, bi, 0)));
    }

    public static int longestCommonSubseq(int[] arr, int[] barr, int ai, int bi, int count) {
        int[][] t = new int[arr.length][barr.length];
        for (int[] ti : t)
            Arrays.fill(ti, 0);

        // f(i,j) = (i==j)? 1+f(i-1,j-1) : 0

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < barr.length; j++) {
                if (arr[i] == barr[j]) {
                    if (i == 0 || j == 0)
                        t[i][j] = 1;
                    else
                        t[i][j] = 1 + t[i-1][j-1];
                } else {
                    t[i][j] = 0;
                }
            }
        }

        int maxCount = 0;
        for (int[] ti : t)
            for (int i = 0; i < ti.length; i++)
                maxCount = Math.max(maxCount, ti[i]);

        return maxCount;
    }




    }