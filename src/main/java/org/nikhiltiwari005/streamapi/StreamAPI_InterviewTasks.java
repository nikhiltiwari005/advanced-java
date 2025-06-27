package org.nikhiltiwari005.streamapi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamAPI_InterviewTasks {
    public static void main(String[] args) {

        // 🔥 Q1. Group employees by department and calculate average salary in each.
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", 70000),
                new Employee("Bob", "Engineering", 85000),
                new Employee("Charlie", "HR", 60000),
                new Employee("David", "HR", 65000),
                new Employee("Eve", "Sales", 55000)
        );
        Map<String, IntSummaryStatistics> summaryStatisticsMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summarizingInt(Employee::getSalary)));
        System.out.println(summaryStatisticsMap);

        // 🔥 Q2. Find the second highest salary among all employees.
        employees.stream()
                .distinct()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(1).findFirst().map(Employee::getName).ifPresent(System.out::println);

        // 🔥 Q3. Return names of employees whose salary is above average.
        IntSummaryStatistics averageOfAllEmp = employees.stream().collect(Collectors.summarizingInt(Employee::getSalary));
        List<String> aboveAvgEmp = employees.stream()
                .filter(e -> e.getSalary() > averageOfAllEmp.getAverage())
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(aboveAvgEmp);

        // 🔥 Q4. Flatten a list of departments where each department has a list of employees, return all employee names.
        List<Department> departments = Arrays.asList(
                new Department("Engineering", Arrays.asList("Alice", "Bob")),
                new Department("HR", Arrays.asList("Charlie", "David")),
                new Department("Sales", Arrays.asList("Eve"))
        );
        List<String> flattenEmpNames = departments.stream().flatMap(d -> d.getEmployees().stream()).collect(Collectors.toList());
        System.out.println(flattenEmpNames);

        // 🔥 Q5. Count frequency of characters in a string (case-insensitive)
        String text = "Java Streams Rock";
        Map<Character, Long> frequencyOfChar = text.toLowerCase().chars()
                .filter(Character::isLetterOrDigit)
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(frequencyOfChar);

        // 🔥 Q6. Find duplicate elements in a list of integers.
        List<Integer> nums = Arrays.asList(1, 2, 3, 2, 4, 5, 1);
        List<Integer> duplicates = nums.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        System.out.println(duplicates);

        // 🔥 Q7. From a list of transactions, find total spent per user.
        List<Transaction> transactions = Arrays.asList(
                new Transaction("Alice", 300),
                new Transaction("Bob", 150),
                new Transaction("Alice", 200),
                new Transaction("Charlie", 400)
        );
        Map<String, Integer> totalSpentPerUser = transactions.stream().collect(Collectors.groupingBy(Transaction::getUser, Collectors.summingInt(Transaction::getAmount)));
        System.out.println(totalSpentPerUser);
        Map<String, Integer> totalSpentPerUser2 = transactions.stream().collect(Collectors.toMap(Transaction::getUser, Transaction::getAmount, (i, j) -> i + j));
        System.out.println(totalSpentPerUser2);

        // 🔥 Q8. Find the longest palindrome word in a list of strings.
        List<String> words = Arrays.asList("racecar", "level", "hello", "world", "noon", "stream");
        words.stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .filter(s -> new StringBuilder(s).reverse().toString().equals(s))
                .findFirst().ifPresent(System.out::println);

        // 🔥 Q9. Given a list of people with names and ages, group by age bracket: (<=20), (21-40), (41+)
        List<Person> people = Arrays.asList(
                new Person("Tom", 18),
                new Person("Jerry", 25),
                new Person("Spike", 45),
                new Person("Tyke", 35)
        );
        Map<String, Set<String>> personGroupedByAgeGroup = people.stream().collect(Collectors.groupingBy(p -> {
            if (p.getAge() <= 20) return "<=20>";
            if (p.getAge() <= 40) return "21-40";
            return "41+";
        }, Collectors.mapping(Person::getName, Collectors.toSet())));
        System.out.println(personGroupedByAgeGroup);

        // 🔥 Q10. From a list of full names, extract unique first names, sorted.
        List<String> fullNames = Arrays.asList("Alice Smith", "Bob Jones", "Alice Cooper", "Charlie Brown");
        List<String> uniqueNames = fullNames.stream()
                .map(s -> s.split(" "))
                .filter(s -> s.length > 0)
                .map(s -> s[0])
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(uniqueNames);
    }

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

    static class Department {
        String name;
        List<String> employees;

        Department(String name, List<String> employees) {
            this.name = name;
            this.employees = employees;
        }

        public String getName() { return name; }
        public List<String> getEmployees() { return employees; }
    }

    static class Transaction {
        String user;
        int amount;

        Transaction(String user, int amount) {
            this.user = user;
            this.amount = amount;
        }

        public String getUser() { return user; }
        public int getAmount() { return amount; }
    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }
}
