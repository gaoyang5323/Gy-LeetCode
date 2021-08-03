package com.kakuiwong.algorithm;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: gaoyang
 * @Description: 503. 下一个更大元素 II
 */
public class Medium503 {

    //官方 11 ms	40.1 MB
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }

    //10ms	40.1MB
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }

    public static void main(String[] args) {
        Medium503 medium503 = new Medium503();
        int[] nums = {5, 4, 3, 2, 1};
        nums = medium503.nextGreaterElements2(nums);
        Arrays.stream(nums).forEach(num -> System.out.println(num));
    }
}
