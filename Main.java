import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);



        // ПЗ 1


        // ПЗ 1 - Завдання 1
        int age = 17;
        double height = 1.76;
        char firstLetter = 'С';
        boolean isStudent = true;

        System.out.println(age);
        System.out.println(height);
        System.out.println(firstLetter);
        System.out.println(isStudent);


        // ПЗ 1 - Завдання 2
        byte temperature = -15;
        short currentYear = 2026;
        int cityPopulation = 1200000;
        long worldPopulation = 8100000000L;

        System.out.println(temperature);
        System.out.println(currentYear);
        System.out.println(cityPopulation);
        System.out.println(worldPopulation);


        // ПЗ 1 - Завдання 3
        float floatValue = 1.1234567f;
        double doubleValue = 1.123456789012345;

        System.out.println(floatValue);
        System.out.println(doubleValue);


        // ПЗ 1 - Завдання 4
        char symbol1 = 'J';
        char symbol2 = 74;
        char symbol3 = '\u004A';

        System.out.println(symbol1);
        System.out.println(symbol2);
        System.out.println(symbol3);



        // ПЗ 2


        // ПЗ 2 - Завдання 17
        System.out.println();
        System.out.println("=== ПЗ 2 - Завдання 17 ===");

        System.out.print("Введіть два цілих числа: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        if (x > y) {
            System.out.println("Більше число: " + x);
        } else {
            System.out.println("Більше число: " + y);
        }


        // ПЗ 2 - Завдання 18
        System.out.println();
        System.out.println("=== ПЗ 2 - Завдання 18 ===");

        System.out.print("Введіть число: ");
        int n = scanner.nextInt();

        if (n < 0) {
            n = -n;
        }

        System.out.println("Абсолютне значення: " + n);


        // ПЗ 2 - Завдання 19
        System.out.println();
        System.out.println("=== ПЗ 2 - Завдання 19 ===");

        System.out.print("Введіть число: ");
        int num = scanner.nextInt();

        if (num % 2 == 0) {
            System.out.println("Парне");
        } else {
            System.out.println("Непарне");
        }



        // ПЗ 3


        // ПЗ 3 - Завдання 1
        System.out.println();
        System.out.println("=== ПЗ 3 - Завдання 1 ===");

        System.out.println("Калькулятор поїздки");

        System.out.print("Введіть відстань: ");
        double distance = scanner.nextDouble();

        System.out.print("Введіть витрату: ");
        double fuel = scanner.nextDouble();

        System.out.print("Введіть тип пального: ");
        int type = scanner.nextInt();

        if (distance <= 0 || fuel <= 0) {

            System.out.println("Помилка");

        } else {

            double price = 0;
            String name = "";

            switch (type) {
                case 1:
                    price = 56.50;
                    name = "А-95";
                    break;

                case 2:
                    price = 52.80;
                    name = "Дизель";
                    break;

                case 3:
                    price = 29.40;
                    name = "Газ";
                    break;

                default:
                    System.out.println("Помилка");
            }

            if (price > 0) {

                double liters = distance * fuel / 100;
                double cost = liters * price;

                double result;

                if (cost >= 2000) {
                    result = cost * 0.95;
                } else {
                    result = cost;
                }

                double discount = cost >= 2000 ? cost * 0.05 : 0;

                System.out.println("Пальне: " + name);
                System.out.println("Потрібно: " + liters);
                System.out.println("Ціна: " + cost);
                System.out.println("Знижка: " + discount);
                System.out.println("До сплати: " + result);
            }
        }


        // ПЗ 3 - Завдання 2
        System.out.println();
        System.out.println("=== ПЗ 3 - Завдання 2 ===");

        System.out.println("Доставка");

        System.out.print("Введіть вагу: ");
        double weight = scanner.nextDouble();

        System.out.print("Введіть відстань: ");
        int distance2 = scanner.nextInt();

        System.out.print("Введіть тип доставки: ");
        int type2 = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Є Premium: ");
        String premium = scanner.nextLine();

        if (weight <= 0 || weight > 50) {

            System.out.println("Помилка");

        } else if (distance2 <= 0) {

            System.out.println("Помилка");

        } else {

            double price2 = 0;
            String delivery = "";

            switch (type2) {

                case 1:
                    price2 = 50;
                    delivery = "Відділення";
                    break;

                case 2:
                    delivery = "Поштомат";

                    if (weight > 15) {
                        System.out.println("Помилка");
                    } else {
                        price2 = 60;
                    }

                    break;

                case 3:
                    price2 = 100;
                    delivery = "Кур'єр";
                    break;

                default:
                    System.out.println("Помилка");
            }

            if (price2 > 0) {

                double distancePrice;

                if (distance2 <= 50) {
                    distancePrice = 0;
                } else if (distance2 <= 200) {
                    distancePrice = 35;
                } else {
                    distancePrice = 80;
                }

                double sum = price2 + distancePrice;

                boolean isPremium = premium.equalsIgnoreCase("так");

                double result2 = isPremium ? sum * 0.8 : sum;

                System.out.println("Доставка: " + delivery);
                System.out.println("Ціна: " + price2);
                System.out.println("Відстань: " + distancePrice);
                System.out.println("Сума: " + sum);
                System.out.println("До сплати: " + result2);
            }
        }



        // ДЗ


        // ДЗ - Завдання 1
        System.out.println();
        System.out.println("=== ДЗ - Завдання 1 ===");

        int number;

        do {
            System.out.print("Введіть число N: ");
            number = scanner.nextInt();

            if (number <= 0) {
                System.out.println("Помилка");
            }

        } while (number <= 0);

        int totalSum = 0;
        int evenSum = 0;
        int oddSum = 0;
        int evenCount = 0;
        int oddCount = 0;

        for (int i = 1; i <= number; i++) {

            totalSum += i;

            if (i % 2 == 0) {
                evenSum += i;
                evenCount++;
            } else {
                oddSum += i;
                oddCount++;
            }
        }

        double average = (double) totalSum / number;

        System.out.println("Загальна сума: " + totalSum);
        System.out.println("Сума парних: " + evenSum);
        System.out.println("Кількість парних: " + evenCount);
        System.out.println("Сума непарних: " + oddSum);
        System.out.println("Кількість непарних: " + oddCount);
        System.out.printf("Середнє: %.2f%n", average);


        // ДЗ - Завдання 2
        System.out.println();
        System.out.println("=== ДЗ - Завдання 2 ===");

        System.out.print("Введіть число: ");
        int number2 = scanner.nextInt();

        int originalNumber = number2;
        int temp = number2;

        int digitCount = 0;
        int digitSum = 0;
        int maxDigit = 0;
        int reversedNumber = 0;

        while (temp > 0) {

            int digit = temp % 10;

            digitCount++;
            digitSum += digit;

            if (digit > maxDigit) {
                maxDigit = digit;
            }

            reversedNumber = reversedNumber * 10 + digit;

            temp = temp / 10;
        }

        System.out.println("Початкове число: " + originalNumber);
        System.out.println("Кількість цифр: " + digitCount);
        System.out.println("Сума цифр: " + digitSum);
        System.out.println("Найбільша цифра: " + maxDigit);
        System.out.println("Перевернуте число: " + reversedNumber);

        if (originalNumber == reversedNumber) {
            System.out.println("Це паліндром");
        } else {
            System.out.println("Це не паліндром");
        }

        scanner.close();
    }
}