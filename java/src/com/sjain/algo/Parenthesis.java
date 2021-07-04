package com.sjain.algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Parenthesis {

    public static class Braces {
        public final char open;
        public final char close;

        public Braces(char open, char close) {
            this.open = open;
            this.close = close;
        }

        public char getOpen() {
            return open;
        }

        public char getClose() {
            return close;
        }
    }

    private Map<Character, Braces> parens = new HashMap<>();
    private Set<Character> closingBraces = new HashSet<>();

    public Parenthesis(char[][] grammar) {
        for (char[] entry : grammar) {
            char open = entry[0];
            char close = entry[1];
            Braces braces = new Braces(open, close);
            parens.put(open, braces);
            closingBraces.add(close);
        }
    }
    public Braces lookup(char openingBrace) {
        return parens.get(openingBrace);
    }

    public boolean isOpeningBrace(char c) {
        return parens.containsKey(c);
    }

    public boolean isClosingBraces(char c) {
        return closingBraces.contains(c);
    }
}
