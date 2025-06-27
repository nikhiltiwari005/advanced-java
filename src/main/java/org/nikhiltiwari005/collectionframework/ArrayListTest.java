package org.nikhiltiwari005.collectionframework;

import org.w3c.dom.ranges.Range;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class ArrayListTest {

    public static void main(String[] args) {

        ArrayList<String> nameList = new ArrayList<>(asList("Nikhil", "Saroj", "Swati"));
        nameList.remove("Nikhil");                  // based on data type remove can either using index and value to remove the element
        System.out.println(nameList);

        ArrayList<Integer> nums = new ArrayList<>(asList(1, 2, 3, 4));
        nums.add(5);                                    // add at the end of list
        int index = nums.indexOf(2);                    // get index by value
        nums.remove(index);                             // remove using index
        nums.add(1, 6);                   // insert to the index and sift the existing value
        nums.set(1, 8);                                 // replace the indexed value

        System.out.println("Contains: " + nums.contains(5));
        System.out.println("Get By Index: " + nums.get(nums.size() - 1));
        System.out.println("Num of elements (size): " + nums.size());

        nums.removeIf(e -> e % 2 == 0);         // remove all even numbers
        nums.addAll(Arrays.asList(9,10,11,12,13));
        System.out.println("Print: " + nums.toString());

        List<Integer> subbedList = nums.subList(1, 3);  // return list of Object to (inclusive) from (exclusive).
        System.out.println("SubList: " + subbedList);

        nums.sort(Collections.reverseOrder());
        System.out.println("Reverse sorted: " + nums);          // Sorted by revers order (DESC)

        nums.sort(Comparator.naturalOrder());
        System.out.println("Natural Order sorting: " + nums);   // Sorted by natural order (ASC)

        nums.retainAll(Arrays.asList(1,2,3,4,5,6));
        System.out.println(nums);                               // retain the common elements

        List<String> reversedList = Arrays.stream(new StringBuilder(nums.stream().map(String::valueOf).collect(Collectors.joining())).reverse().toString().split("")).collect(Collectors.toList());
        System.out.println("Reverse: " + reversedList);         // Reverse the list overengineered way

        Collections.reverse(reversedList);
        System.out.println("Simple Reverse: " + reversedList);  // Reverse the list

        Collections.swap(nums, 0, nums.size() - 1);
        System.out.println("Swap first and last el: " + nums);  // simple swap of el using indexes

        Collections.rotate(nums, 1);
        System.out.println("Rotate list by 1: " + nums);        // rotating the list el

        Collections.addAll(nums, 1,3,4,5);
        System.out.println("Adding Elements on list: " + nums);     // adding el using collections

        System.out.println("Frequency of 5: " + Collections.frequency(nums, 5));      // Get frequency of an el

        System.out.println("Max of list: " + Collections.max(nums));  // max of list
        System.out.println("Max of list: " + Collections.max(nums, Comparator.comparing(el -> el > 2 && el < 4)));  // max of list using comparator

        System.out.println("Min of list: " + Collections.min(nums));  // min of list

        Collections.shuffle(nums);
        System.out.println("List Shuffled: " + nums);               // Shuffling the list

        List<String> testString = Collections.nCopies(2, "s");
        System.out.println(testString);                             // creating the list using collection, fixed value fixed size

        Collections.copy(nums, Collections.nCopies(2, 45));
        System.out.println("List is copied to dest list: " + nums);

        System.out.println(Collections.disjoint(nums, Collections.nCopies(2, 0)));      // true no element in common
        System.out.println(Collections.disjoint(nums, Arrays.asList(5)));                     // false element in common

        nums.clear();
        System.out.println(nums);

    }
}
