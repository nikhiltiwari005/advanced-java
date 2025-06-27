package org.nikhiltiwari005.streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(10,1,2,3,4,5,6,7,8,9);

        //        Map<String, List<Integer>> grouped = nums.stream()
        //                .sorted()
        //                .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "Even" : "Odd"));

        System.out.println(nums.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0)));

        //        nums.parallelStream().forEachOrdered(System.out::println);



        //        1.	Convert a List<String> to a List<String> where all strings are uppercase.
        Arrays.asList("john", "jane").stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        //        2.	Filter out all even numbers from a List<Integer>.
        Arrays.asList(1,2,3,4,5).stream().filter(i -> i % 2 == 0).collect(Collectors.toList()).forEach(System.out::println);


        //        3.	Count how many strings in a list have length greater than 5.
        Arrays.asList("Nikhil", "tow").stream().filter(s -> s.length() > 5).collect(Collectors.toList()).forEach(System.out::println);


        //        4.	Convert a List<Integer> into a comma-separated String.
        String s = Arrays.asList(1, 2, 3, 4).stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(s);

        //        5.	Find the sum of all elements in a List<Integer>.
        Arrays.asList(1,2,4).stream().reduce(Integer::sum).ifPresent(System.out::println);
        int sum = Arrays.asList(99, 3, 4, 4).stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        //        6.	Convert an array of integers to a List<Integer>.
        List<Integer> listOfInt = Arrays.stream(new int[]{1, 2, 3, 4}).boxed().collect(Collectors.toList());
        System.out.println(listOfInt);

        //        7.	Remove duplicates from a List<String>.
        List<String> distinctList = Arrays.asList("a", "a", "b", "c").stream().distinct().collect(Collectors.toList());
        System.out.println(distinctList);


        //        8.	Sort a list of strings alphabetically.
        List<String> sortedAlpha = Arrays.asList("giud", "edun", "adcb", "ieiu", "werc", "eciu").stream().sorted().collect(Collectors.toList());
        System.out.println(sortedAlpha);

        //        9.	Convert a List<String> into a Set<String>.
        Set<String> setOfString = Arrays.asList("a", "a", "v", "b", "c", "d", "b").stream().collect(Collectors.toSet());
        System.out.println(setOfString);


        //        10.	Create a List<Integer> from a List<String> representing numbers.
        List<Integer> listOfInteger = Arrays.asList("1", "3", "2", "4").stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(listOfInteger);
    }
}
