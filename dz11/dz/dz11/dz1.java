package semester1.dz11;

import java.util.ArrayList;
import java.util.List;

public class dz1 {

    public static void inspectIntegerCache(int[] testValues) {
        System.out.println("[1. INTEGER CACHE (== та equals())]:");
        for (int val : testValues) {
            Integer first = Integer.valueOf(val);
            Integer second = Integer.valueOf(val);

            System.out.printf("  Значення: %6d | (a == b): %-5s | a.equals(b): %-5s%n",
                    val, first == second, first.equals(second));
        }
    }

    public static void demonstrateAutoboxingUnderTheHood() {
        System.out.println("\n[2. AUTOBOXING ТА UNBOXING]:");

        int number = 42;
        Integer boxed = Integer.valueOf(number);
        int unboxed = boxed.intValue();

        double price = 15.5;
        Double boxedPrice = Double.valueOf(price);
        double unboxedPrice = boxedPrice.doubleValue();

        boolean active = true;
        Boolean boxedActive = Boolean.valueOf(active);
        boolean unboxedActive = boxedActive.booleanValue();

        System.out.println("  Integer.valueOf(42) -> " + boxed + " | intValue() -> " + unboxed);
        System.out.println("  Double.valueOf(15.5) -> " + boxedPrice + " | doubleValue() -> " + unboxedPrice);
        System.out.println("  Boolean.valueOf(true) -> " + boxedActive + " | booleanValue() -> " + unboxedActive);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(45);
        numbers.add(null);
        numbers.add(-12);
        numbers.add(88);
        numbers.add(null);
        numbers.add(102);

        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            Integer value = numbers.get(i);
            if (value != null) {
                sum += value.intValue();
            } else {
                System.out.println("  Пропущено null на індексі " + i);
            }
        }
        System.out.println("  Сума валідних елементів: " + sum);
    }

    public static List<Integer> parseTelemetryStream(String[] rawPackets) {
        List<Integer> result = new ArrayList<>();

        for (String packet : rawPackets) {
            if (packet == null || packet.trim().isEmpty()) {
                System.out.println("  Порожній або null-пакет відхилено");
                continue;
            }

            try {
                int value = Integer.parseInt(packet.trim());
                result.add(value);
            } catch (NumberFormatException e) {
                System.out.println("  Пакет '" + packet + "' відхилено (NumberFormatException)");
            }
        }

        return result;
    }

    public static void printExtremesAndStats(List<Integer> validData) {
        System.out.println("\n[3. PARSING ТА ПОШУК ЕКСТРЕМУМІВ]:");

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        long sum = 0;

        for (int value : validData) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
            sum += value;
        }

        System.out.println("  Початкові межі: min = " + Integer.MAX_VALUE
                + ", max = " + Integer.MIN_VALUE);
        System.out.println("  Список валідних чисел: " + validData);

        if (!validData.isEmpty()) {
            System.out.println("  Мінімум: " + min);
            System.out.println("  Максимум: " + max);
            System.out.println("  Сума: " + sum);
        }

        List<Integer> copy = new ArrayList<>(validData);
        if (copy.contains(-30)) {
            copy.remove(Integer.valueOf(-30));
            System.out.println("  Після remove(Integer.valueOf(-30)): " + copy);
        }
    }

    public static void main(String[] args) {
        System.out.println("=============== ДОСЛІДЖЕННЯ WRAPPER CLASSES ===============");

        int[] testValues = {-128, -50, 0, 127, 128, 500, 2026};
        inspectIntegerCache(testValues);

        demonstrateAutoboxingUnderTheHood();

        String[] rawPackets = {"  145 ", "-30", "ERR_404", "980", "", null,
                "2147483647", "-85"};
        List<Integer> validData = parseTelemetryStream(rawPackets);
        printExtremesAndStats(validData);

        System.out.println("===========================================================");
    }
}
