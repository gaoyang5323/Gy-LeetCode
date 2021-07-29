package com.kakuiwong.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author: gaoyang
 * @Description: 503. 下一个更大元素 II
 */
public class Medium503 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length < 1 || nums.length > 10000) {
            return nums;
        }
        int retArray[] = new int[nums.length];

        int[] sortedNums = Arrays.stream(nums).distinct().sorted().toArray();
        Map<Integer, Integer> sortMap = new HashMap<>();
        for (int i = 0; i < sortedNums.length; i++) {
            sortMap.put(sortedNums[i], i);
        }

        int sortedLength = sortedNums.length;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer index = sortMap.get(num);
            if (index < sortedLength - 1) {
                retArray[i] = sortedNums[index + 1];
            } else {
                retArray[i] = -1;
            }
        }
        return retArray;
    }

    public static void main(String[] args) {
        Medium503 medium503 = new Medium503();
        int[] nums = {5, 4, 3, 2, 1};
        nums = medium503.nextGreaterElements(nums);
        Arrays.stream(nums).forEach(num-> System.out.println(num));
    }
}
