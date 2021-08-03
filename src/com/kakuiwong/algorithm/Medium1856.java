package com.kakuiwong.algorithm;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: gaoyang
 * @Description: 1856. 子数组最小乘积的最大值
 */
public class Medium1856 {

    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;

        // 数组前缀和
        long[] pre = new long[n + 1];  // 存储下标“之前”的元素和
        pre[0] = nums[0];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }

        // 单递增调栈
        Stack<Integer> stack = new Stack<>();
        // 求元素右边第一个比其小的
        int[] rightLower = new int[n];
        Arrays.fill(rightLower, n);  // 默认为n，即没发现
        for (int i = 0; i < n; i++) {
            // 单调递增栈
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int t = stack.pop();
                rightLower[t] = i;
            }
            stack.push(i);
        }
        // 求元素左边第一个比其小的
        int[] leftLower = new int[n];
        Arrays.fill(leftLower, -1);  // 默认为-1，即没发现
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int t = stack.pop();
                leftLower[t] = i;
            }
            stack.push(i);
        }

        // 在前缀和及单调栈基础上，求最终解
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int r = rightLower[i];
            int l = leftLower[i] + 1;
            long t = pre[r] - pre[l];
            ans = Math.max(ans, t * nums[i]);
        }
        long mod = (long) 1e9 + 7;
        return (int) (ans % mod);
    }

    public int maxSumMinProduct2(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        // 对于每一个i 找到右侧连续的不小于它的元素，记录最后一个连续的，不小于它的元素对应下标
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                right[stack.pop()] = i - 1;
            }
            stack.push(i);//存放的是下标
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = len - 1;
        }

        // 对于每一个i 找到左侧连续的不小于它的元素，记录最后一个连续的，不小于它的元素对应下标
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left[stack.pop()] = i + 1;
            }
            stack.push(i);//存放的是下标
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = 0;
        }

        //前缀和,用long来存放，防止相加时溢出
        long[] dp = new long[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }

        long result = 0;
        for (int i = 0; i < len; i++) {
            result = Math.max(result, nums[i] * (dp[right[i]] - dp[left[i]] + nums[left[i]]));
        }//right[i]为右坐标 left[i]为左坐标，从left[i]到right[i]所有元素的和应该是dp[right[i]]-dp[left[i]]+nums[left[i]]
        return (int) (result % 1000000007);
    }

    //前缀和
    public int[] winSum(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] sum = new int[n];
        sum[0] = nums[0];
        // 计算前缀和
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        k--;
        int[] res = new int[n - k];         //分配空间用来承载输出
        for (int i = k; i < n; i++) {
            res[i - k] = sum[i] - sum[i - k] + nums[i - k];
        }
        return res;
    }

    //
   public static void main(String[] args) {
        int[] nums = {1,2,7,8,5};
        int k = 3;
       Medium1856 medium1856 = new Medium1856();
       int[] ints = medium1856.winSum(nums, 3);
       Arrays.stream(ints).forEach(i-> System.out.println(i));
    }


    /**
     * 输入：nums = [1,2,3,2]
     * 输出：14
     * 解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
     * 2 * (2+3+2) = 2 * 7 = 14 。
     *
     * @param args
     */
    //public static void main(String[] args) {
    //    int[] nums = {1, 2, 3, 2};
    //    Medium1856 medium1856 = new Medium1856();
    //    int i = medium1856.maxSumMinProduct2(nums);
    //    System.out.println(i);
    //}
}
