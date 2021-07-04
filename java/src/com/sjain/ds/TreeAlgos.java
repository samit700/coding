package com.sjain.ds;

import java.util.*;

public class TreeAlgos {

    public static <E> int countUnivalTree(BinaryTree.Node<E> root) {
        return univalHelper(root).getValue();
    }

    private static <E> Pair<Boolean, Integer> univalHelper(BinaryTree.Node<E> node) {
        if (node == null)
            return new Pair<Boolean, Integer>(true, 0);

        Pair<Boolean, Integer> left = univalHelper(node.left);
        Pair<Boolean, Integer> right = univalHelper(node.right);

        if (((node.left == null) || ((node.value == node.left.value) && left.getKey()))
                && ((node.right == null) || ((node.value == node.right.value) && right.getKey())))
            return new Pair<Boolean, Integer>(true, 1 + left.getValue() + right.getValue());

        return new Pair<Boolean, Integer>(false, left.getValue() + right.getValue());
    }


    public static <E> boolean isUnivalTree(BinaryTree.Node<E> root) {
        return ((root.left == null) || (isUnivalTree(root.left) && (root.value == root.left.value)))
                && ((root.right == null) || (isUnivalTree(root.right) && (root.value == root.right.value)));
    }

    public static long stepsPermutation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Invalid n");
        return stepsPermutationHelper(n, new HashMap<Integer, Long>());
    }

    private static long stepsPermutationHelper(int n, Map<Integer, Long> cache) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        if (cache.containsKey(n))
            return cache.get(n);

        long count = stepsPermutationHelper(n - 1, cache) + stepsPermutationHelper(n - 2, cache);
        cache.put(n, count);
        return count;
    }

    public static long stepsPermutationIter(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Invalid n");

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(n);

        Map<Integer, Long> cache = new HashMap<Integer, Long>();
        cache.put(1, 1L);
        cache.put(2, 2L);

        while (!stack.isEmpty()) {
            int i = stack.pop();
            if (cache.containsKey(i))
                continue;

            if (cache.containsKey(i - 1) && cache.containsKey(i - 2)) {
                cache.put(i, cache.get(i - 1) + cache.get(i - 2));
            } else {
                stack.push(i);
                if (!cache.containsKey(i - 1))
                    stack.push(i - 1);
                if (!cache.containsKey(i - 2))
                    stack.push(i - 2);
            }
        }

        return cache.get(n);
    }

    public static long stepsPermutation2(int n) {
        long[] m = new long[n];
        m[0] = 1;
        m[1] = 2;
        for (int i = 2; i < n; i++)
            m[i] = m[i - 1] + m[i - 2];
        return m[n - 1];
    }

    public static <E> List<E> getAncestors(BinaryTree.Node<E> root, E val) {
        List<E> l = new ArrayList<E>();
        buildAncestorsHelper(root, val, l);
        return l;
    }

    private static <E> boolean buildAncestorsHelper(BinaryTree.Node<E> node, E val, List<E> l) {
        if (node == null)
            return false;
        if (node.value == val)
            return true;

        if (buildAncestorsHelper(node.left, val, l) || buildAncestorsHelper(node.right, val, l)) {
            l.add(node.value);
            return true;
        }
        return false;
    }

    public static <E> int depth(BinaryTree.Node<E> root, E val) {
        if (root == null)
            return -1;

        if (root.value == val)
            return 0;

        int left = depth(root.left, val);
        if (left >= 0)
            return 1 + left;

        int right = depth(root.right, val);
        if (right >= 0)
            return 1 + right;

        return -1;
    }

    public static <E> int distance(BinaryTree.Node<E> root, E val1, E val2) {
        MutablePair<Integer> res = new MutablePair<>();
        distanceHelper(root, val1, val2, res);
        if (res.first >= 0 && res.second >= 0)
            return Math.abs(res.first - res.second);
        return -1;
    }

    private static <E> void distanceHelper(BinaryTree.Node<E> node, E val1, E val2, MutablePair<Integer> res) {
        if (node == null) {
            res.set(-1, -1);
            return;
        }

        int d1 = -1;
        int d2 = -1;
        if (node.value == val1)
            d1 = 0;
        if (node.value == val2)
            d2 = 0;

        if (d1 >= 0 && d2 >= 0) {
            res.set(d1, d2);
            return;
        }

        distanceHelper(node.left, val1, val2, res);
        int l1 = res.first;
        int l2 = res.second;
        if (l1 >= 0 && l2 >= 0) {
            res.set(1 + l1, 1 + l2);
            return;
        }

        distanceHelper(node.right, val1, val2, res);
        int r1 = res.first;
        int r2 = res.second;
        if (r1 >= 0 && r2 >= 0) {
            res.set(1 + r1, 1 + r2);
            return;
        }

        if (l1 >= 0 && r2 >= 0)
            res.set(2 + l1 + r2, 0);
        else if (l2 >= 0 && r1 >= 0)
            res.set(2 + l2 + r1, 0);
        else if (l1 >= 0)
            res.set(1 + l1, d2);
        else if (r1 >= 0)
            res.set(1 + r1, d2);
        else if (l2 >= 0)
            res.set(d1, 1 + l2);
        else if (r2 >= 0)
            res.set(d1, 1 + r2);
        else
            res.set(d1, d2);
    }

    public static boolean isBfs(BinaryTree.Node<Integer> node) {
        return isBfs(node, new MutablePair<Integer>());

    }

    private static boolean isBfs(BinaryTree.Node<Integer> node, MutablePair<Integer> minMax) {
        if (node == null)
            return true;

        int min = node.value;
        int max = node.value;

        if (node.left != null) {
            if ((!isBfs(node.left, minMax)) || node.value < minMax.second)
                return false;
            else
                min = minMax.first;
        }

        if (node.right != null) {
            if ((!isBfs(node.right, minMax)) || node.value > minMax.first)
                return false;
            else
                max = minMax.second;
        }

        minMax.set(min, max);
        return true;
    }

    public static boolean isBalancedTree(BinaryTree.Node<Integer> node) {
        return isBalancedTree(node, new MutablePair<Integer>());
    }

    private static boolean isBalancedTree(BinaryTree.Node<Integer> node, MutablePair<Integer> depth) {
        if (node == null) {
            depth.first = 0;    //depth
            depth.second = 0;   //imbalance factor <=1
            return true;
        }

        boolean isLeftBalan = isBalancedTree(node.left, depth);
        int lDepth = depth.first;
        int lImb = depth.second;

        if (!isLeftBalan)
            return false;

        boolean isRightBalan = isBalancedTree(node.right, depth);
        int rDepth = depth.first;

        if (!isRightBalan)
            return false;

        int imb = lDepth - rDepth;
        if (imb < lImb || imb > 1)
            return false;

        depth.first = 1 + lDepth;
        depth.second = imb;
        return true;
    }

    public static boolean isBalancedTreeIter(BinaryTree.Node<Integer> root) {
        if (root == null)
            return true;

        CircularQueue<BinaryTree.Node<Integer>> currLvlQueue = new CircularQueue<>();
        currLvlQueue.add(root);
        int i = 0;
        while (!currLvlQueue.isEmpty()) {
            if (currLvlQueue.size() < Math.pow(2, i)) {
                // if a level has less than 2^i elements, there should be no nodes in next level
                while (!currLvlQueue.isEmpty()) {
                    BinaryTree.Node<Integer> node = currLvlQueue.poll();
                    if (node.left != null || node.right != null)
                        return false;
                }
            } else {
                //append all nodes of next level to queue, must be in order left to right with no gaps
                CircularQueue<BinaryTree.Node<Integer>> nextLvlQueue = new CircularQueue<>();
                boolean expectNoMore = false;
                while (!currLvlQueue.isEmpty()) {
                    BinaryTree.Node<Integer> node = currLvlQueue.poll();
                    if (expectNoMore && node.left != null)
                        return false;

                    if (node.left == null && node.right != null)
                        return false;

                    if (node.left == null || node.right == null)
                        expectNoMore = true;

                    if (node.left != null)
                        nextLvlQueue.add(node.left);
                    if (node.right != null)
                        nextLvlQueue.add(node.right);
                }
                currLvlQueue = nextLvlQueue;
            }
            ++i;
        }
        return true;
    }

    public static int deepestLeavesSum(BinaryTree.Node<Integer> root) {
        MutablePair<Integer> maxDepthSum = new MutablePair<>();
        maxDepthSum.set(0, 0);
        deepestLeavesSumHelper(root, 0, maxDepthSum);
        return maxDepthSum.second;
    }

    private static void deepestLeavesSumHelper(BinaryTree.Node<Integer> node, int currdepth, MutablePair<Integer> maxDepthSum) {
        if (node.left == null && node.right == null) {
            if (currdepth == maxDepthSum.first) {
                maxDepthSum.second += node.value;
            } else if (currdepth > maxDepthSum.first) {
                maxDepthSum.first = currdepth;
                maxDepthSum.second = node.value;
            }
            return;
        }

        if (node.left != null)
            deepestLeavesSumHelper(node.left, currdepth + 1, maxDepthSum);

        if (node.right != null)
            deepestLeavesSumHelper(node.right, currdepth + 1, maxDepthSum);
    }


}
