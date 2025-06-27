package org.nikhiltiwari005.functionalprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Confusion {

    public static void main(String[] args) {

        // In functional programming, we need to specify the data type based of the input and output
        // Unlike objects where we only have to mention which data type this object is holding.
        // In functional programming, we have to mention the data type of params and return type in function interface.
        // Which is confusing as hell

        List<String> players = Arrays.asList("Virat", "Sachin", "Shaurabh");
        System.out.println(players);
        // Sweet and simple

        // Now functional
        Function<String, Integer> numInt = n -> Integer.valueOf(n);
        System.out.println(numInt.apply("123"));
        System.out.println(numInt.apply("345583828"));

    }
}
