package com.coding;

import java.util.List;

public class NoPrefixSet {

    static class TrieNode {
        // Since input only contains 'a' through 'j', an array of size 10 works
        TrieNode[] children = new TrieNode[10];
        boolean isEnd = false;
    }

    private static boolean find(TrieNode root, String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
            // Condition 1: A previous word is a prefix of this word
            if (current.isEnd) {
                return true;
            }
        }
        // Condition 2: This word is a prefix of a previous word
        // (already has children) or it's a duplicate.
        for (TrieNode child : current.children) {
            if (child != null) {
                return true;
            }
        }
        current.isEnd = true;
        return false;
    }

    public static void noPrefix(List<String> words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            if (find(root, word)) {
                System.out.println("BAD SET");
                System.out.println(word);
                System.out.println();
                return;
            }
        }
        System.out.println("GOOD SET");
        System.out.println();
    }

    public static void main(String[] args) {
        List<String> words1 = List.of("abcd", "bcd", "abcde", "bcde");
        List<String> words2 = List.of("ab", "bc", "cd");
        List<String> words3 = List.of("aab", "defgab", "abcde", "aabcde", "bbbbbbbbbb", "jabjjjad");
        noPrefix(words1);
        noPrefix(words2);
        noPrefix(words3);
    }
}

