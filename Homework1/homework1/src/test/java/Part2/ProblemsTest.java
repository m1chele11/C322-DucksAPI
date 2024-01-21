package Part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemsTest {

    @Test
    void problem1() {
            Problems solution = new Problems();

            // Test case 1: There exists a pair that adds up to the target
            int[] nums1 = {2, 7, 11, 15};
            int target1 = 9;
            int[] expected1 = {0, 1};
            assertArrayEquals(expected1, solution.problem1(nums1, target1));

            // Test case 2: No pair adds up to the target
            int[] nums2 = {3, 2, 4};
            int target2 = 8;
            assertNull(solution.problem1(nums2, target2));

            // Test case 3: Edge case with an empty array
            int[] nums3 = {};
            int target3 = 5;
            assertNull(solution.problem1(nums3, target3));
        }



    @Test
    void binarySearch() {

        Problems BinarySearch = new Problems();
        // Test case 1: Square root of a perfect square
        int x1 = 25;
        assertTrue(Problems.binarySearch(x1));

        // Test case 2: Non-perfect square
        int x2 = 30;
        assertFalse(Problems.binarySearch(x2));

        // Test case 3: Edge case with 0
        int x3 = 0;
        assertTrue(Problems.binarySearch(x3));

        // Test case 4: Edge case with 1
        int x4 = 1;
        assertTrue(Problems.binarySearch(x4));
    }

    @Test
    void longestCommonPrefix() {

        Problems solution = new Problems();

        // Test case 1: Common prefix exists
        String[] strs1 = {"flower", "flow", "flight"};
        assertEquals("fl", Problems.longestCommonPrefix(strs1));

        // Test case 2: No common prefix
        String[] strs2 = {"dog", "racecar", "car"};
        assertEquals("", Problems.longestCommonPrefix(strs2));

        // Test case 3: Empty array
        String[] strs3 = {};
        assertEquals("", Problems.longestCommonPrefix(strs3));
    }

    @Test
    void romanToInt() {

        Problems Solution = new Problems();

        // Test case 1: Valid Roman numeral
        String roman1 = "III";
        assertEquals(3, Problems.romanToInt(roman1));

        // Test case 2: Another valid Roman numeral
        String roman2 = "IX";
        assertEquals(9, Problems.romanToInt(roman2));

    }

    @Test
    void isValid() {

        Problems solution = new Problems();

        // Test case 1: Valid brackets
        String s1 = "()";
        assertTrue(Problems.isValid(s1));

        // Test case 2: Valid nested brackets
        String s2 = "{[]}";
        assertTrue(Problems.isValid(s2));

        // Test case 3: Invalid brackets
        String s3 = "([)]";
        assertFalse(Problems.isValid(s3));
    }


    @Test
    public void testMergeTwoLists() {

        Problems.ListNode l1 = new Problems.ListNode(1);
        l1.next = new Problems.ListNode(2);
        l1.next.next = new Problems.ListNode(4);

        Problems.ListNode l2 = new Problems.ListNode(1);
        l2.next = new Problems.ListNode(3);
        l2.next.next = new Problems.ListNode(4);

        assertEquals(1, l1.val);
        assertEquals(2, l1.next.val);
        assertEquals(4, l1.next.next.val);
        assertEquals(1, l2.val);
        assertEquals(3, l2.next.val);
    }
    @Test
    void plusOne() {

        Problems solution = new Problems();


        // Test case 1: Regular addition
        int[] digits1 = {1, 2, 3};
        int[] result1 = solution.plusOne(digits1);
        int[] expected1 = {1, 2, 4};
        assertArrayEquals(expected1, result1);

        // Test case 2: Addition with carry-over
        int[] digits2 = {9, 9, 9};
        int[] result2 = solution.plusOne(digits2);
        int[] expected2 = {1, 0, 0, 0};
        assertArrayEquals(expected2, result2);
    }
}