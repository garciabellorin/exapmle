package com.coding;

import java.util.ArrayList;
import java.util.List;

public class Contacts {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int size = 0;

        private void add(String s) {
            add(s, 0);
        }

        private void add(String s, int index) {
            size++;
            if (index == s.length()) {
                return;
            }
            int charIndex = s.charAt(index) - 'a';
            if (children[charIndex] == null) {
                children[charIndex] = new TrieNode();
            }
            children[charIndex].add(s, index + 1);
        }

        private int findAndCount(String s, int index) {
            if (index == s.length()) {
                return size;
            }
            TrieNode child = children[s.charAt(index) - 'a'];
            if (child == null) {
                return 0;
            }
            return child.findAndCount(s, index + 1);
        }

        public static List<Integer> contacts(List<List<String>> queries) {
            TrieNode root = new TrieNode();
            List<Integer> ans = new ArrayList<>();
            for (List<String> q : queries) {
                String operation = q.get(0);
                String word = q.get(1);
                if (operation.equals("add")) {
                    root.add(word);
                } else {
                    ans.add(root.findAndCount(word, 0));
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        List<List<String>> q1 = List.of(
                List.of("add", "ed"),
                List.of("add", "eddie"),
                List.of("add", "edward"),
                List.of("find", "ed"),
                List.of("add", "edwina"),
                List.of("find", "edw"),
                List.of("find", "a")
        );

        List<List<String>> q2 = List.of(
                List.of("add", "hack"),
                List.of("add", "hackerrank"),
                List.of("find", "hac"),
                List.of("find", "hak")
        );

        List<Integer> result1 = TrieNode.contacts(q1);
        List<Integer> result2 = TrieNode.contacts(q2);
        System.out.println(result1);
        System.out.println(result2);
    }

}
