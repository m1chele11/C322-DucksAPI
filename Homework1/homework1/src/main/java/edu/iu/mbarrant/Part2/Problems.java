package edu.iu.mbarrant.Part2;

import java.util.Stack;

public class Problems {


        public int[] problem1(int[] nums, int target) {
            int [] result = null;

            int i = 0;
            int j = 1;
            while(i < nums.length) {
                while(j < nums.length) {
                    if(nums[i] + nums[j] == target) {
                        result = new int[] {i,j};
                        return result;
                    }
                    j++;
                }
                i++;
                j = i+1;
            }
            return result;
        }



        //given an integer x, return true if x is a palindrome, and false otherwise.
        //binary search

        public static boolean binarySearch(int x){
            int left = 0;
            int right = x;
            int mid = (left + right) / 2;
            while(left <= right){
                if(mid * mid == x){
                    return true;
                }
                else if(mid * mid < x){
                    left = mid + 1;
                }
                else{
                    right = mid - 1;
                }
                mid = (left + right) / 2;
            }
            return false;
        }



        public static String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }

            int minLength = getMinLength(strs);

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < minLength; i++) {
                char currentChar = strs[0].charAt(i);

                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].charAt(i) != currentChar) {
                        return result.toString();
                    }
                }

                result.append(currentChar);
            }

            return result.toString();
        }

        private static int getMinLength(String[] strs) {
            int minLength = Integer.MAX_VALUE;

            for (String str : strs) {
                minLength = Math.min(minLength, str.length());
            }

            return minLength;
        }



        public static int romanToInt(String s) {
            int result = 0;

            for (int i = 0; i < s.length(); i++) {
                char currentSymbol = s.charAt(i);

                // Handle subtraction cases
                if (i < s.length() - 1) {
                    char nextSymbol = s.charAt(i + 1);
                    if (getValue(nextSymbol) > getValue(currentSymbol)) {
                        result += getValue(nextSymbol) - getValue(currentSymbol);
                        i++; // Skip the next symbol since it has been accounted for
                        continue;
                    }
                }

                // Handle non-subtraction cases
                result += getValue(currentSymbol);
            }

            return result;
        }

        private static int getValue(char symbol) {
            switch (symbol) {
                case 'I':
                    return 1;
                case 'V':
                    return 5;
                case 'X':
                    return 10;
                case 'L':
                    return 50;
                case 'C':
                    return 100;
                case 'D':
                    return 500;
                case 'M':
                    return 1000;
                default:
                    return 0; // Invalid symbol
            }
        }



        //given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
        //An input string is valid if:
        //Open brackets must be closed by the same type of brackets.
        //Open brackets must be closed in the correct order.

        public static boolean isValid(String s) {
            // Use a stack to keep track of opening brackets
            Stack<Character> stack = new Stack<>();

            // Iterate through each character in the input string
            for (char ch : s.toCharArray()) {
                if (ch == '(' || ch == '{' || ch == '[') {
                    // If it's an opening bracket, push it onto the stack
                    stack.push(ch);
                } else {
                    // If it's a closing bracket, check if the stack is not empty
                    if (stack.isEmpty()) {
                        return false; // No corresponding opening bracket
                    }

                    // Check if the current closing bracket matches the last opening bracket
                    char lastOpening = stack.pop();
                    if ((ch == ')' && lastOpening != '(') ||
                            (ch == '}' && lastOpening != '{') ||
                            (ch == ']' && lastOpening != '[')) {
                        return false; // Mismatched brackets
                    }
                }
            }

            // The stack should be empty if all brackets are matched
            return stack.isEmpty();
        }



    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    current.next = l1;
                    l1 = l1.next;
                } else {
                    current.next = l2;
                    l2 = l2.next;
                }
                current = current.next;
            }

            // If one of the lists is not fully processed, append the remaining nodes.
            if (l1 != null) {
                current.next = l1;
            } else {
                current.next = l2;
            }

            return dummy.next;
        }




        public int[] plusOne(int[] digits) {
            int n = digits.length;

            // Traverse the digits from right to left
            for (int i = n - 1; i >= 0; i--) {
                // Increment the current digit
                digits[i]++;

                // If the current digit becomes 10, set it to 0 and carry over to the next digit
                if (digits[i] == 10) {
                    digits[i] = 0;
                } else {
                    // If the current digit is less than 10, no need to carry over, break the loop
                    break;
                }
            }

            // Check if there is a carry over after the loop
            if (digits[0] == 0) {
                // If the leftmost digit is 0, we need to add a new digit at the beginning
                int[] result = new int[n + 1];
                result[0] = 1;
                return result;
            }

            return digits;
        }

        // Helper method to print an array
        private static void printArray(int[] arr) {
            System.out.print("[");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
                if (i < arr.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }


}
