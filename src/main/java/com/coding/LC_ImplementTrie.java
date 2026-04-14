package com.coding;

public class LC_ImplementTrie {
    static class Trie {
        private Node root;

        static class Node {
            Node[] children = new Node[26];
            boolean isEnd = false;
        }

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node current = root;
            for(char c : word.toCharArray()){
                int index = c - 'a';
                if(current.children[index] == null) {
                    current.children[index] = new Node();
                }
                current = current.children[index];
            }
            current.isEnd = true;
        }

        public boolean search(String word) {
            Node node = find(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return find(prefix) != null;
        }

        private Node find(String s) {
            Node current = root;
            for(char c : s.toCharArray()) {
                int index = c - 'a';
                if(current.children[index] == null) {
                    return null;
                }
                current = current.children[index];
            }
            return current;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}
