package semester1.practice11;

import java.util.*;

public class pz12Task123 {
    public static void main(String[] args) {
        System.out.println("ПЗ 1. HashSet ");
        task1();
        System.out.println("\n ПЗ 2. HashSet, LinkedHashSet, TreeSet ");
        task2();
        System.out.println("\nПЗ 3. Видалення дублікатів зі збереженням порядку ");
        task3();
    }

    private static void task1() {
        Set<String> langs = new HashSet<>();
        String[] names = {"Java", "Python", "C++", "Java", "Kotlin", "Python"};
        for (String name : names) System.out.println(name + " -> " + langs.add(name));
        System.out.println("Кількість мов: " + langs.size());
        System.out.println("Є Java: " + langs.contains("Java"));
        System.out.println("Є Rust: " + langs.contains("Rust"));
        langs.remove("C++");
        System.out.println("Після видалення C++: " + langs);
    }

    private static void task2() {
        String[] cities = {"Львів", "Київ", "Одеса", "Дніпро", "Харків", "Київ", "Львів", "Чернівці"};
        HashSet<String> hash = new HashSet<>();
        LinkedHashSet<String> linked = new LinkedHashSet<>();
        TreeSet<String> tree = new TreeSet<>();
        for (String city : cities) { hash.add(city); linked.add(city); tree.add(city); }
        System.out.println("HashSet: " + hash);
        System.out.println("LinkedHashSet: " + linked);
        System.out.println("TreeSet: " + tree);
        System.out.println("HashSet не гарантує порядок, LinkedHashSet зберігає порядок додавання, TreeSet сортує за алфавітом.");
    }

    private static List<String> deduplicatePreserveOrder(List<String> input) {
        return new ArrayList<>(new LinkedHashSet<>(input));
    }

    private static void task3() {
        List<String> rawLogs = new ArrayList<>(Arrays.asList(
                "192.168.1.10", "192.168.1.11", "10.0.0.5", "192.168.1.10",
                "172.16.0.2", "10.0.0.5", "192.168.1.12", "192.168.1.11",
                "10.0.0.8", "172.16.0.2", "192.168.1.10", "10.0.0.9"));
        List<String> result = deduplicatePreserveOrder(rawLogs);
        System.out.println("До: " + rawLogs.size() + " " + rawLogs);
        System.out.println("Після: " + result.size() + " " + result);
        System.out.println("Видалено дублікатів: " + (rawLogs.size() - result.size()));
    }
}
