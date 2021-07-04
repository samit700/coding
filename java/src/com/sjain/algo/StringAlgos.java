package com.sjain.algo;

import java.util.*;

public class StringAlgos {

    public static boolean wordsInOrder(List<String> alpha, List<String> words) {
        if (alpha == null || words == null || words.size() <= 1 || alpha.size() <= 1)
            return true;

        Map<String, Integer> alphaMap = new HashMap<>();
        for (int i = 0; i < alpha.size(); i++) {
            alphaMap.put(alpha.get(i), i);
        }

        for (int i = 1; i < words.size(); i++) {
            String word1 = words.get(i - 1);
            String word2 = words.get(i);
            int l = Math.min(word1.length(), word2.length());
            boolean compareLengths = true;
            for (int j = 0; j < l; j++) {
                if (word1.charAt(j) < word2.charAt(j)) {
                    compareLengths = false;
                    break;
                }
                if (word1.charAt(j) > word2.charAt(j))
                    return false;
            }
            if (compareLengths && word1.length() > word2.length())
                return false;
        }

        return true;
    }

    public static boolean sentenceInOrder(List<Character> dict, String sentence) {
        // create dict score map
        Map<Character, Integer> dictScores = new HashMap<>();
        for (int i  = 0; i < dict.size(); i++) {
            dictScores.put(dict.get(i), i);
        }

        final int n = sentence.length();
        int i = 0;
        while (i < n) {
            // seek to next alphabet
            while (i < n && Character.isWhitespace(sentence.charAt(i)))
                ++i;

            int j = i;
            // seek to next whitespace
            while (j < n && Character.isAlphabetic(sentence.charAt(j)))
                ++j;
            if (j >= n)
                return true;
            int endi = j;

            // seek to next alphabet
            while (j < n && Character.isWhitespace(sentence.charAt(j)))
                ++j;

            boolean inOrder = false;
            while (i < endi && j < n) {
                int scorei = dictScores.get(sentence.charAt(i));
                int scorej = dictScores.get(sentence.charAt(j));
                if (scorei > scorej)
                    return false;
                if (scorei < scorej) {
                    inOrder = true;
                    break;
                }
                ++i;
                ++j;
            }

            if ((!inOrder) && i != endi)
                return false;

            // seek to next whitespace
            while (i < n && Character.isAlphabetic(sentence.charAt(i)))
                ++i;
        }
        return true;
    }

    public static String sortWord(List<Character> alpha, String word) {
        if (alpha == null || word == null || alpha.size() <= 1 || word.length() <= 1)
            return word;

        Map<Character, Integer> alphaMap = new HashMap<>();
        for (int i = 0; i < alpha.size(); i++) {
            alphaMap.put(alpha.get(i), i);
        }

        int[] count = new int[alpha.size()];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (alphaMap.containsKey(c)) {
                int cidx = alphaMap.get(c);
                ++count[cidx];
            } else {
                // if missing in alphabet, return word as is
                return word;
            }
        }

        for (int i = 1; i < count.length; i++)
            count[i] = count[i] + count[i - 1];

        StringBuffer output = new StringBuffer(word);
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int cidx = alphaMap.get(c);
            output.setCharAt(count[cidx] - 1, c);
            --count[cidx];
        }

        return output.toString();
    }

    public static void sortWords(List<Character> alpha, List<String> words) {
        if (alpha == null || words == null || alpha.size() <= 1 || words.size() <= 1)
            return;

        Map<Character, Integer> alphaMap = new HashMap<>();
        for (int i = 0; i < alpha.size(); i++) {
            alphaMap.put(alpha.get(i), i);
        }

        int maxLength = 0;
        for (String word : words)
            maxLength = Math.max(maxLength, word.length());

        String[] output = new String[words.size()];
        int[] counts = new int[1 + alpha.size()];  // reserve 1st bucket for blanks due to varying lengths

        for (int i = maxLength - 1; i >= 0; i--) {
            Arrays.fill(counts, 0);

            for (int j = 0; j < words.size(); j++) {
                String word = words.get(j);
                int cidx = 0;
                if (word.length() - 1 >= i) {
                    char c = word.charAt(i);
                    cidx = 1 + alphaMap.get(c);
                }
                ++counts[cidx];
            }

            for (int j = 1; j < counts.length; j++)
                counts[j] = counts[j - 1] + counts[j];

            for (int j = words.size() - 1; j >= 0; j--) {
                String word = words.get(j);
                int cidx = 0;
                if (word.length() - 1 >= i) {
                    char c = word.charAt(i);
                    cidx = 1 + alphaMap.get(c);
                }
                output[counts[cidx] - 1] = word;
                --counts[cidx];
            }

            for (int j = 0; j < words.size(); j++) {
                words.set(j, output[j]);
            }
        }
    }

    public static void printWordsMatchingPrefix(String[] words, String prefix) {
        CharacterTrie dict = new CharacterTrie();
        for (String word : words)
            dict.insert(word);
        dict.printWordsMatchingPrefix(prefix);
    }

    public static boolean wordBreak(String[] words, String str) {
        CharacterTrie dict = new CharacterTrie();
        for (String word : words)
            dict.insert(word);
        return dict.wordBreak(str);
    }

    public static List<String> findMatchingWords(String[] dict, String s) {
        if (dict.length == 0 || s.length() == 0)
            return new ArrayList<>();

        CharacterTrie dictTree = new CharacterTrie();
        for (String word : dict)
            dictTree.insert(word);
        return dictTree.findAllWords(s);
    }

    public static boolean isPalindrome(String s, int i, int j) {
        if (i > j)
            throw new IllegalStateException("Invalid indexes");
        while ((i < j) && (s.charAt(i) == s.charAt(j))) {
            ++i;
            --j;
        }

        if (i < j)
            return false;
        return true;
    }

    public static boolean isPalindromeOnlyAlpha(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (!Character.isAlphabetic(s.charAt(i)))
                ++i;
            while (!Character.isAlphabetic(s.charAt(j)))
                --j;
            if (i < j && s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }

    //longest palindrome continuius sequence
    public static int longestPalindromeContinuous(String s) {
        // abcddcee --> cddc (4)
        if (s == null || s.length() == 0)
            return 0;

        int maxp = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.length() - i < maxp)
                break;
            for (int j = s.length() - 1; j > i; j--) {
                if (j - i + 1 <= maxp)
                    break;
                if (s.charAt(i) == s.charAt(j) && isPalindrome(s, i, j)) {
                    maxp = j - i + 1;
                    break;
                }
            }
        }

        return maxp;
    }

    public static void printPermutations(String s) {
        printPermutationsHelper(s.toCharArray(), 0);
    }

    private static void swap(char[] carr, int i, int j) {
        if (carr[i] == carr[j])
            return;
        char c = carr[i];
        carr[i] = carr[j];
        carr[j] = c;
    }

    private static void printPermutationsHelper(char[] carr, int idx) {
        for (int i = idx; i < carr.length; i++) {
            swap(carr, idx, i);
            if (i == 0 || i != idx)
                System.out.println(Arrays.toString(carr));
            printPermutationsHelper(carr, idx + 1);
            swap(carr, i, idx);  //backtrack
        }
    }

    public static String getNumString(int n) {
        if (n < 0)
            return "negative ";

        n = Math.abs(n);

        if (n == 0)
            return "zero";
        if (n == 1)
            return "one";
        if (n == 2)
            return "two";
        if (n == 3)
            return "three";
        if (n == 4)
            return "four";
        if (n == 5)
            return "five";
        if (n == 6)
            return "six";
        if (n == 7)
            return "seven";
        if (n == 8)
            return "eight";
        if (n == 9)
            return "nine";
        if (n == 10)
            return "ten";

        if (n == 10)
            return "ten";
        if (n == 11)
            return "eleven";
        if (n == 12)
            return "twelve";
        if (n == 13)
            return "thirteen";
        if (n == 14)
            return "fourteen";
        if (n == 15)
            return "fifteen";
        if (n == 16)
            return "sixteen";
        if (n == 17)
            return "seventeen";
        if (n == 18)
            return "eighteen";
        if (n == 19)
            return "nineteen";

        if (n < 30)
            return "twenty" + getNumString(n % 10);
        if (n < 40)
            return "thirty " + getNumString(n % 10);
        if (n < 50)
            return "forty " + getNumString(n % 10);
        if (n < 60)
            return "fifty " + getNumString(n % 10);
        if (n < 70)
            return "sixty " + getNumString(n % 10);
        if (n < 80)
            return "seventy " + getNumString(n % 10);
        if (n < 90)
            return "eighty " + getNumString(n % 10);
        if (n < 100)
            return "ninety " + getNumString(n % 10);

        if (n < 1000) {
            int r1 = n / 100;
            int r2 = n % 100;

            String s = getNumString(r1);
            s += " hundred ";
            s += getNumString(r2);
            s += " ";

            return s;
        }

        String s = "";
        while (n != 0) {
            int r = n % 1000;

            String s2 = getNumString(r);

            String offset = "";
            if (n < 1000000)
                offset = "thousand";
            else if (n < 1000000000)
                offset = "million";
            s2 += offset;
            s2 += " ";

            s = s2 + s;
            n = n / 1000;
        }
        return s;
    }

    private static Map<Integer, String> numberString = new HashMap<>();
    static {
        numberString.put(0, "zero");
        numberString.put(1, "one");
        numberString.put(2, "two");
        //..
        numberString.put(19, "nineteen");

        numberString.put(20, "twenty");
        numberString.put(30, "thirty");
        //..
        numberString.put(90, "ninety");

        numberString.put(100, "hundred");
        numberString.put(1000, "thousand");
        numberString.put(1000000, "million");
        numberString.put(1000000000, "billion");
    }

    public static void reverseString(char[] buf) {
        for (int i = 0, j = buf.length - 1; i < j; i++, j--) {
            char tmp = buf[i];
            buf[i] = buf[j];
            buf[j] = tmp;
        }
    }

    public static List<String> chunkedPalindrome(String s) {
        Objects.requireNonNull(s);
        List<String> lst = new ArrayList<>();
        chunkedPalindromeHelper(s, 0, s.length()-1, lst);
        return lst;
    }

    private static void chunkedPalindromeHelper(String s, int from, int to, List<String> lst) {
        if (from > to)
            return;

        int i = from;
        int j = to;
        while (i < j) {
            if (s.charAt(j) == s.charAt(i)) {
                int ki = i;
                int kj = j;
                while (kj <= to && s.charAt(ki) == s.charAt(kj)) {
                    ++ki;
                    ++kj;
                }
                if (kj > to) {
                    lst.add(s.substring(i, i + (to - j) + 1));
                    chunkedPalindromeHelper(s, i + (to - j) + 1, j - 1, lst);
                    break;
                } else {
                    --j;
                }
            } else {
                --j;
            }
        }

        if (i >= j)
            lst.add(s.substring(from, to + 1));
    }

    public static int subString(String str, String pat) {
        if (pat.length() == 0)
            return 0;
        if (str.length() == 0)
            return -1;

        int patlen = pat.length();
        int[] li = new int[patlen];
        li[0] = 0;
        int pi = 0, i = 1;
        while (i < patlen) {
            if (pat.charAt(i) == pat.charAt(pi)) {
                ++pi;
                li[i] = pi;
                ++i;
            } else if (pi == 0) {
                li[i] = 0;
                ++i;
            } else {
                pi = li[pi - 1];
            }
        }

        //System.out.println(Arrays.toString(li));

        i = 0;
        int j = 0;
        int start = -1;
        while (i < str.length()) {
            if (str.charAt(i) == pat.charAt(j)) {
                if (start == -1)
                    start = i;
                ++i;
                ++j;
            } else {
                start = -1;
                if (j == 0)
                    ++i;
                else
                    j = li[j - 1];
            }

            if (j == pat.length())
                return start;

        }

        return -1;
    }

    public static int minInsertsForPalin(String s) {
        if (s.length() <= 1)
            return 0;

        int left = 0, right = s.length() - 1;
        while (++left < --right) ;
        return minInsertsForPalinHelper(s, right, left);
    }

    public static int minInsertsForPalinHelper(String s, int left, int right) {
        if (left < 0 && right > s.length() - 1)
            return 0;

        if (left < 0)
            return s.length() - right;
        if (right > s.length() - 1)
            return left + 1;

        if (left != right && s.charAt(left) == s.charAt(right))
            return 0;

        int min = Integer.MAX_VALUE;
        if (left == right)
            min = minInsertsForPalinHelper(s, left - 1, right + 1);

        min = Math.min(min, 1 + minInsertsForPalinHelper(s, left, right + 1));
        min = Math.min(min, 1 + minInsertsForPalinHelper(s, left - 1, right));

        return min;
    }

    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char ci = Character.toLowerCase(s.charAt(i));
            char cj = Character.toLowerCase(s.charAt(j));

            if (ci >= 'a' && ci <= 'z' && cj >= 'a' && cj <= 'z') {
                if (ci != cj)
                    return false;
                ++i;
                --j;
            } else {
                if (ci < 'a' || ci > 'z')
                    ++i;
                if (cj < 'a' || cj > 'z')
                    --j;
            }
        }
        return true;
    }

    public static int getLinesNeededForText(String text, int width) {
        if (text == null || text.length() == 0)
            return 0;

        int lines = 0;
        int currLineAvailWidth = width;
        int i = 0;
        while (i < text.length()) {
            // seek to next non-white space character
            while (i < text.length() && Character.isWhitespace(text.charAt(i)))
                ++i;
            int wStart = i;

            // seek to next white space character
            while (i < text.length() && (!Character.isWhitespace(text.charAt(i))))
                ++i;
            int wEnd = i;

            int wordSize = wEnd - wStart;
            if (wordSize > width)
                throw new IllegalArgumentException("Word '" + text.substring(wStart, wEnd)
                        + "' is longer than given width");
            if (lines == 0 || (1 + wordSize) > currLineAvailWidth) {
                // start a new line
                ++lines;
                currLineAvailWidth = width - wordSize;
            } else {
                // fit in current line
                currLineAvailWidth -= (1 + wordSize);
            }
        }

        return lines;
    }

}
