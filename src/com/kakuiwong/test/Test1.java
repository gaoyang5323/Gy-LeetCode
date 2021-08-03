package com.kakuiwong.test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: gaoyang
 * @Description:
 */
public class Test1 {

    public static int[] t503(int[] nums) {
        int length = nums.length;
        //新建结果集,并初始化为-1
        int[] ret = new int[length];
        Arrays.fill(ret, -1);
        //单调递增栈
        Deque<Integer> stack = new LinkedList<>();
        //循环两边对比
        for (int i = 0; i < length * 2; i++) {
            while (!stack.isEmpty() && nums[i % length] > nums[stack.peek()]) {
                ret[stack.pop()] = nums[i % length];
            }
            stack.push(i % length);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        Arrays.stream(t503(nums)).forEach(i -> System.out.println(i));
    }

}
