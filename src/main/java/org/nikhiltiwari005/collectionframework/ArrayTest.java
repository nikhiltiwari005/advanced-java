package org.nikhiltiwari005.collectionframework;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayTest {

    public static void main(String[] args) {

        // https://leetcode.com/problems/search-insert-position/description/?envType=problem-list-v2&envId=array
        int[] nums = {1,3,5,6,7,9};
        int target = 7;
        int n = Arrays.binarySearch(nums, target);
        int res = n < 0 ? Math.abs(n) - 1 : n;
        System.out.println(res);

        // https://leetcode.com/problems/plus-one/?envType=problem-list-v2&envId=array

        int[] nums2 = {1,3,5,6,7,9};
        String numStr = Arrays.stream(nums2).mapToObj(String::valueOf).collect(Collectors.joining(""));
        Integer numStrPlus1 = Integer.valueOf(numStr) + 1;

        Arrays.stream(numStrPlus1.toString().split("")).mapToInt(Integer::parseInt).toArray();

        IntStream.of(numStrPlus1).toArray();

    }
}
