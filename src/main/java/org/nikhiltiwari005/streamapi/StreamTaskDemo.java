package org.nikhiltiwari005.streamapi;

import java.io.File;
import java.io.Reader;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import java.nio.file.*;
import java.io.IOException;

public class StreamTaskDemo {
    // --- Main Execution ---

    public static void main(String[] args) throws IOException {

        // 🧠 11. Group a list of strings by their lengths
        List<String> words = Arrays.asList("cat", "table", "go", "laptop", "cup", "java");
        Map<Integer, List<String>> groupedByLen = words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(groupedByLen);

        // 🧠 12. Find the longest string in a list
        List<String> phrases = Arrays.asList("short", "a bit longer", "the longest string in the list", "tiny");
        phrases.stream().max(Comparator.comparing(String::length)).ifPresent(System.out::println);

        // 🧠 13. Convert a List<List<String>> into a flat List<String>
        List<List<String>> nested = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("cherry"),
                Arrays.asList("date", "fig", "grape")
        );
        List<String> fruitList = nested.stream().flatMap(e -> e.stream()).collect(Collectors.toList());
        System.out.println(fruitList);

        // 🧠 14. Get the first non-null string in a list
        List<String> possiblyNull = Arrays.asList(null, null, "firstValid", "secondValid");
        possiblyNull.stream().filter(Objects::nonNull).findFirst().ifPresent(System.out::println);

        // 🧠 15. Convert a list of Person objects into a Map<String, Integer> (name → age)
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35)
        );
        Map<String, Integer> peopleMap = people.stream().collect(Collectors.toMap(p -> p.getName(), p -> p.getAge()));
        System.out.println(peopleMap);

        // 🧠 16. Filter employees who are older than 30 and collect their names
        List<Employee> employees = Arrays.asList(
                new Employee("Nikhil", 28),
                new Employee("Sneha", 32),
                new Employee("Rahul", 40)
        );

        List<String> employeeAbove30 = employees.stream().filter(e -> e.getAge() > 30).map(e -> e.getName()).collect(Collectors.toList());
        System.out.println(employeeAbove30);

        // 🧠 17. Count how many times each word appears in a list
        List<String> repeatedWords = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        Map<String, Integer> wordCount = repeatedWords.stream().collect(Collectors.toMap(i -> i, i -> 1, (i, j) -> i + j));
        System.out.println(wordCount);

        // 🧠 18. Partition a list of integers into even and odd numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Map<Boolean, List<Integer>> evenOddMap = numbers.stream().collect(Collectors.partitioningBy(v -> v % 2 == 0));
        System.out.println(evenOddMap);

        // 🧠 19. From a list of transactions, calculate the total amount for a given user
        List<Transaction> transactions = Arrays.asList(
                new Transaction("Nikhil", 100),
                new Transaction("Sneha", 250),
                new Transaction("Nikhil", 50),
                new Transaction("Rahul", 75)
        );
        Map<String, Integer> userAmount = transactions.stream().collect(Collectors.toMap(t -> t.getUser(), t -> t.getAmount(), (t, t1) -> t + t1));
        System.out.println(userAmount);

        // 🧠 20. Check if any element in a list matches a condition
        List<String> cities = Arrays.asList("Delhi", "Mumbai", "Bangalore", "Chennai");
        Predicate<String> isDelhi = s -> s.equals("Delhi");
        boolean anyMatch = cities.stream().anyMatch(isDelhi);
        System.out.println(anyMatch);


        // 🔥 21. Build a map of role → list of users from a list of users with roles
        List<User> users = Arrays.asList(
                new User("Nikhil", "ADMIN"),
                new User("Sneha", "USER"),
                new User("Ravi", "ADMIN"),
                new User("Divya", "MODERATOR")
        );

//        Map<String, List<User>> usersByRole = users.stream().collect(Collectors.groupingBy(User::getRole));
//        System.out.println(usersByRole);
//        Map<String, List<String>> usernameByRole = users.stream().collect(Collectors.groupingBy(User::getRole, Collectors.mapping(User::getName, Collectors.toList())));
//        System.out.println(usernameByRole);
        Map<String, ArrayList<User>> listOfUserByRole = users.stream().collect(Collectors.toMap(User::getRole, u -> new ArrayList<User>(Arrays.asList(u)), (u, u1) -> {
            u.addAll(u1);
            return u;
        }));
        System.out.println(listOfUserByRole);


        // 🔥 22. Extract and flatten all IP addresses from log lines
        List<String> logs = Arrays.asList(
                "ERROR 10.0.0.1 failed login",
                "INFO 172.16.5.4 user connected",
                "WARN 192.168.0.2 timeout"
        );

        Function<String, String> ipByRegex = input -> {
            String regex = "(\\d+\\.\\d+\\.\\d+\\.\\d+)";
            Pattern p = Pattern.compile(regex);
            Matcher matcher = p.matcher(input);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        };
        logs.stream().map(ipByRegex).filter(Objects::nonNull).forEach(System.out::println);


        // 🔥 23. From orders with nested items, create a flat list of item names
        List<Order> orders = Arrays.asList(
                new Order(Arrays.asList("pen", "pencil")),
                new Order(Arrays.asList("notebook")),
                new Order(Arrays.asList("eraser", "sharpener"))
        );
        List<String> itemList = orders.stream().flatMap(o -> o.getItems().stream()).collect(Collectors.toList());
        System.out.println(itemList);


        // 🔥 24. Build a histogram of character frequencies from a paragraph
        String paragraph = "Java streams are powerful and expressive.";
//        paragraph.chars().collect(Collectors.toMap(c -> c, () -> 1, (v, v1) -> v + v1)); ///////////////////////////////////////////
        Map<Character, Long> histogram = paragraph.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // 🔥 25. Find the top 3 longest strings in a list
        List<String> texts = Arrays.asList("hi", "medium length", "a very very long string", "tiny", "slightly bigger");
        List<String> top3LongestString = texts.stream().sorted((s1, s2) -> s2.length() - s1.length()).limit(3).collect(Collectors.toList());
        System.out.println(top3LongestString);

        // 🔥 26. Group people by age brackets (0–20, 21–40, 41+)
        List<Person> agePeople = Arrays.asList(
                new Person("A", 18),
                new Person("B", 29),
                new Person("C", 42),
                new Person("D", 35),
                new Person("E", 55)
        );
        Map<String, List<String>> peopleByAge = agePeople.stream().collect(Collectors.groupingBy(p -> {
            if (p.getAge() <= 20) {
                return "0-20";
            }
            if (p.getAge() <= 40) {
                return "21-40";
            }
            return "41+";
        }, Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println(peopleByAge);


        // 🔥 27. Return top N most frequent words from a list of sentences
        List<String> sentences = Arrays.asList(
                "java streams are cool",
                "java is powerful",
                "streams are powerful"
        );
//        sentences.stream().flatMap(Stream::of). /////////////////////////////////////////////////////////////

        // 🔥 28. Normalize all phone numbers (strip non-digits, ensure country code)
        List<String> phones = Arrays.asList("+91-9876543210", "09876543210", "91 98765 43210", "+91 98765-43210");
        List<String> phoneNumbers = phones.stream().map(s -> s.replaceAll("[^0-9]", "")).collect(Collectors.toList());
        System.out.println(phoneNumbers);


        // 🔥 29. Read and combine all lines from a list of files
        List<Path> files = Arrays.asList(
                Paths.get("file1.txt"),
                Paths.get("file2.txt")
        );
        String allFilesData = files.stream().flatMap(f -> {
            try {
                return Files.lines(f);
            } catch (IOException e) {
                return Stream.empty();
            }
        }).collect(Collectors.joining());

        // 🔥 30. From a stream of events, find the first that satisfies a chain of conditions
        List<Event> events = Arrays.asList(
                new Event("LOGIN", true),
                new Event("DOWNLOAD", false),
                new Event("UPLOAD", true)
        );

        events.stream().filter(e -> e.getType().equals("LOGIN")).filter(Event::isSuccess).findFirst().ifPresent(System.out::println);

        // 🔧 Add your stream solutions below for each task
    }


    // --- Stub Classes ---

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

    static class Employee {
        String name;
        int age;

        Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
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

    static class User {
        String name;
        String role;

        User(String name, String role) {
            this.name = name;
            this.role = role;
        }

        public String getName() { return name; }
        public String getRole() { return role; }
    }

    static class Order {
        List<String> items;

        Order(List<String> items) {
            this.items = items;
        }

        public List<String> getItems() { return items; }
    }

    static class Event {
        String type;
        boolean success;

        Event(String type, boolean success) {
            this.type = type;
            this.success = success;
        }

        public String getType() { return type; }
        public boolean isSuccess() { return success; }
    }

}