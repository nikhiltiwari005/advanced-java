package org.nikhiltiwari005.streamapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamInterviewTasks {
    public static void main(String[] args) {
        // ✅ 1. Convert an array to a list using Streams
        String[] array = {"apple", "banana", "cherry"};
        List<String> collect = Stream.of(array).collect(Collectors.toList());
        System.out.println(collect instanceof ArrayList);

        // ✅ 2. Find the max/min number in a list
        List<Integer> numbers = Arrays.asList(12, 5, 89, 34, 22);
        IntSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingInt(e -> e));
        System.out.println(stats.getMin() + " " + stats.getMax());

        // ✅ 3. Count the number of empty strings in a list
        List<String> stringsWithEmpty = Arrays.asList("a", "", "hello", "", "world");
        long count = stringsWithEmpty.stream().filter(String::isEmpty).count();
        System.out.println(count);


        // ✅ 4. Remove all null or empty elements from a list
        List<String> listWithNulls = Arrays.asList("java", null, "", "spring", null, "boot");
        List<String> nonNullNonEmptyListOfString = listWithNulls.stream().filter(Objects::nonNull).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println(nonNullNonEmptyListOfString);


        // ✅ 5. Convert a list of strings to uppercase
        List<String> names = Arrays.asList("nikhil", "sneha", "rahul");
        System.out.println(names.stream().map(String::toUpperCase).collect(Collectors.toList()));

        // ✅ 6. Sort a list of integers in descending order using Stream API
        List<Integer> unsortedList = Arrays.asList(10, 3, 5, 1, 8);
        System.out.println(unsortedList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

        // ✅ 7. Check if all strings in a list start with a specific prefix
        List<String> urlList = Arrays.asList("https://google.com", "https://openai.com", "https://github.com", "http://facebook.com");
        System.out.println(urlList.stream().filter(s -> s.startsWith("https://")).collect(Collectors.toList()));

        // ✅ 8. Convert a list of strings to a single comma-separated string
        List<String> techList = Arrays.asList("Java", "Kotlin", "Scala");
        System.out.println(techList.stream().collect(Collectors.joining(", ", "It's a String: [", "]")));

        // ✅ 9. Filter only palindromes from a list
        List<String> palindromes = Arrays.asList("madam", "racecar", "hello", "world", "level");
        System.out.println(Stream.of(palindromes).flatMap(l -> l.stream())
                .filter(Objects::nonNull).filter(s -> new StringBuilder(s).reverse().toString().equals(s)).collect(Collectors.toList()));

        // ✅ 10. Group employees by department and calculate average salary
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "HR", 50000),
                new Employee("Bob", "Engineering", 75000),
                new Employee("Charlie", "Engineering", 82000),
                new Employee("Diana", "HR", 55000)
        );
//        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summarizingDouble(Employee::getSalary))));
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))));

        // ✅ 11. Find duplicate elements in a list
        List<Integer> numsWithDuplicates = Arrays.asList(1, 2, 3, 4, 2, 5, 1, 6);
        System.out.println(numsWithDuplicates.stream().collect(Collectors.toMap(Function.identity(), i -> 1, (i, j) -> i + j))
                .entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList()));

        // ✅ 12. Flatten a list of lists and remove duplicates
        List<List<Integer>> nestedNums = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(3, 4, 5),
                Arrays.asList(5, 6, 7)
        );
        System.out.println(nestedNums.stream().flatMap(List::stream).distinct().collect(Collectors.toList()));


        // ✅ 13. Get the second-highest number from a list
        List<Integer> rankedScores = Arrays.asList(10, 50, 30, 70, 90);
        System.out.println(rankedScores.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst());

        // ✅ 14. Get the first non-repeating character in a string
        String sample = "swiss";
        Map<Character, Long> charCount = sample.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Optional<Map.Entry<Character, Long>> firstNonRepeatingChar = charCount.entrySet().stream().filter(e -> e.getValue().equals(1L)).findFirst();
        System.out.println(firstNonRepeatingChar.get().getKey());
        //        Map<Character, List<Character>> charCount = sample.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity()));
//        System.out.println(charCount.entrySet().stream().filter(v -> v.getValue().size() == 1).findFirst().get().getValue());

        // ✅ 15. Partition students into pass and fail (score >= 40)
        List<Student> students = Arrays.asList(
                new Student("Amit", 35),
                new Student("Sneha", 85),
                new Student("Nikhil", 60),
                new Student("Ravi", 30)
        );
        Map<Boolean, List<Student>> s = students.stream().collect(Collectors.partitioningBy(e -> e.getScore() >= 40));
        System.out.println(s);

        // ✅ 16. Return top 3 largest files from a list of File objects
        List<File> fileList = Arrays.asList(
                new File("file1.txt"),
                new File("file2.txt"),
                new File("file3.txt")
        );


        // ✅ 17. Count frequency of each character in a string
        String paragraph = "functional programming in java";
        Map<Character, Integer> frequency = paragraph.chars().mapToObj(c -> (char) c).collect(Collectors.toMap(Function.identity(), e -> 1, (i, j) -> i+j));
        System.out.println(frequency);

        // ✅ 18. Return the oldest employee in each department
        List<Employee> empList = Arrays.asList(
                new Employee("E1", "IT", 45),
                new Employee("E2", "Finance", 39),
                new Employee("E3", "IT", 51),
                new Employee("E4", "Finance", 42)
        );

        // ✅ 19. Sort a map by values using streams
        Map<String, Integer> mapToSort = new HashMap<>();
        mapToSort.put("java", 8);
        mapToSort.put("python", 12);
        mapToSort.put("go", 3);

        Map<String, Integer> sortedMap = mapToSort.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(sortedMap);

        // ✅ 20. Convert CSV lines to POJOs using Streams
        List<String> csvLines = Arrays.asList(
                "1,John,Developer",
                "2,Sneha,Manager",
                "3,Amit,Designer"
        );
        List<EmployeeX> listOfEX = csvLines.stream().map(str -> {
            String[] ss = str.split(",");
            return new EmployeeX(Integer.parseInt(ss[0]), ss[1], ss[2]);
        }).collect(Collectors.toList());
        System.out.println(listOfEX);

    }

    @ToString
    @Data
    @AllArgsConstructor
    static class EmployeeX {
        private Integer id;
        private String name;
        private String department;
    }

    // --- Helper Classes ---
    static class Employee {
        String name;
        String department;
        int salary;

        Employee(String name, String department, int salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }
        public int getSalary() { return salary; }
    }

    @ToString
    static class Student {
        String name;
        int score;

        Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() { return name; }
        public int getScore() { return score; }
    }
}
