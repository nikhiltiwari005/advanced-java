package org.nikhiltiwari005.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {

        // Predicate has test as its functional method and, or, and, and negate are default methods. And finally, isEqual as a static method.
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println(isEven.test(2));
        System.out.println(isEven.test(3));

        // Negate
        System.out.println(isEven.negate().test(3));

        Predicate<Integer> isNull = num -> Optional.ofNullable(num).isPresent();

        Integer n = null;
        System.out.println(isNull.and(isEven).test(n));
        try {
            System.out.println(isEven.and(isNull).test(n));
        } catch (NullPointerException e){}

        // isEqual
        System.out.println(Predicate.isEqual(new ArrayList<>()).test(new ArrayList<>()));
        System.out.println(Objects.equals(new ArrayList<>(), new ArrayList<>()));
        System.out.println(Objects.equals(new ArrayList<>(Arrays.asList("1")), new ArrayList<>()));
        System.out.println(new ArrayList<>().equals(new ArrayList<>()));


    }
}
