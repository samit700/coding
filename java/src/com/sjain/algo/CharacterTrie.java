package com.sjain.algo;

import com.sjain.ds.Stack;

import java.util.*;

public class CharacterTrie {

    TrieNode root = new TrieNode('.');

    public static class TrieNode {
        char value;
        TrieNode[] children = new TrieNode[26];

        String word;

        public TrieNode(char value) {
            this.value = value;
            this.word = word;
        }
    }

    public void insert(String s) {
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int charIndex = c - 'a';
            TrieNode child = node.children[charIndex];
            if (child == null) {
                child = new TrieNode(c);
                node.children[charIndex] = child;
            }
            if (i == s.length() - 1)
                child.word = s;
            node = child;
        }
    }

    public void printWordsMatchingPrefix(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int charIndex = c - 'a';
            node = node.children[charIndex];
            if (node == null)
                return;
        }
        Stack<TrieNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.word != null)
                System.out.print(node.word + ' ');
            for (TrieNode child: node.children) {
                if (child != null)
                    stack.push(child);
            }
        }
    }

    public boolean isValidWord(String s) {
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int charIndex = c - 'a';
            if (charIndex < 0 || charIndex > 26)
                return false;
            node = node.children[charIndex];
            if (node == null)
                return false;
        }
        return (node.word != null);
    }

    public boolean wordBreak(String str) {
        return workBreakHelper(str, 0, root);
    }

    private boolean workBreakHelper(String str, int idx, TrieNode node) {
        if (idx == str.length())
            return node.word != null;

        if (node.word != null && workBreakHelper(str, idx, root))
            return true;

        char c = str.charAt(idx);
        int charIndex = c - 'a';
        TrieNode child = node.children[charIndex];
        if (child == null)
            return false;
        return workBreakHelper(str, idx + 1, child);
    }


    public List<List<String>> wordBreakWords(String str) {
        int n = str.length();
        List[][] dp = new List[n][n];

        for (int gap = 0; gap < n; gap++)
            for (int i = 0; i < n - gap; i++)
                dp[i][gap] = new ArrayList<ArrayList<String>>();

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0; i < n - gap; i++) {
                for (int j = 0; j < gap; j++) {
                    List l1 = dp[i][j];
                    List l2 = dp[i+j+1][gap-j-1];
                    if ((!l1.isEmpty()) && (!l2.isEmpty())) {
                        for (Object o1 : l1) {
                            for (Object o2 : l2) {
                                List<String> l = new ArrayList<>();
                                l.addAll((List) o1);
                                l.addAll((List) o2);
                                dp[i][gap].add(l);
                            }
                        }
                    }
                }

                String s = str.substring(i, i + gap + 1);
                if (isValidWord(s)) {
                    List<String> l = new ArrayList<>();
                    l.add(s);
                    dp[i][gap].add(l);
                }
            }
        }

        return dp[0][n - 1];
    }


    public List<String> findAllWords(String s) {
        Map<Character, Integer> cMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Integer f = cMap.get(c);
            cMap.put(c, f == null ? 1 : f + 1);
        }
        return findWordsHelper(cMap, root);
    }

    private List<String> findWordsHelper(Map<Character, Integer> cMap, TrieNode node) {
        List<String> matches = new ArrayList<>();
        for (TrieNode n : node.children) {
            if (n != null) {
                Integer f = cMap.get(n.value);
                if (f != null) {
                    --f;
                    if (f == 0)
                       cMap.remove(n.value);
                    else
                        cMap.put(n.value, f);
                    if (cMap.isEmpty()) {
                        if (n.word != null)
                            matches.add(n.word);
                    } else {
                        matches.addAll(findWordsHelper(cMap, n));
                    }
                    //backtrack
                    cMap.put(n.value, f+1);
                }
            }
        }
        return matches;
    }
}

