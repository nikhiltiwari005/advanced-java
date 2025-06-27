package org.nikhiltiwari005.functionalprogramming;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaces {

    public static void main(String[] args) {

        Predicate<Integer> isEven = num -> num % 2 == 0;
        isEven.test(9327389);

        Function<String, Integer> convertToInteger = n -> Integer.valueOf(n);
        convertToInteger.apply("32324");

        Consumer<String> print = s -> System.out.println(s);
        print.accept("Hi from Consumer");

        Supplier<String> defaultString = () -> "default string";
        defaultString.get();


    }
}
