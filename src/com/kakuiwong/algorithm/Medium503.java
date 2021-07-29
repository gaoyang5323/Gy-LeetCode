package com.kakuiwong.algorithm;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author: gaoyang
 * @Description: 503. 下一个更大元素 II
 */
public class Medium503 {
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

    public static void main(String[] args) {
        Medium503 medium503 = new Medium503();
        int[] nums = {5, 4, 3, 2, 1};
        nums = medium503.nextGreaterElements(nums);
        Arrays.stream(nums).forEach(num-> System.out.println(num));
    }
}
