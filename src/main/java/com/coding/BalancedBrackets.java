package com.coding;

import java.util.Stack;

public class BalancedBrackets {

    public static String isBalanced(String s) {
        // Write your code here
        Stack<Character> stack = new Stack<>();
        if(s.length() % 2 != 0) {
            return "NO";
        }

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return "NO";
                }
                char current = stack.peek();
                if ((c == ')' && current != '(') || (c == '}' && current != '{') || (c == ']' && current != '[')) {
                    return "NO";
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) {
        String case1 = "{[()]}";
        String case2 = "{[(])}";
        String case3 = "{{[[(())]]}}";
        System.out.println(isBalanced(case1)); // Output: YES
        System.out.println(isBalanced(case2)); // Output: NO
        System.out.println(isBalanced(case3)); // Output: YES
    }
}
