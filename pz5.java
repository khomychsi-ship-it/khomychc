public class Main {

    public static void main(String[] args) {

        // Завдання 4 пз
        Rectangle rectangle = new Rectangle();
        rectangle.width = 7.5;
        rectangle.height = 4.0;

        double area = rectangle.width * rectangle.height;
        double perimeter = 2 * (rectangle.width + rectangle.height);

        System.out.println("площа: " + area);
        System.out.println("периметр: " + perimeter);


        // Завдання 5 пз
        Product product = new Product();
        product.name = "Монітор";
        product.price = 6500.0;
        product.quantity = 8;

        double totalPrice = product.price * product.quantity;

        System.out.println("Товар: " + product.name);
        System.out.println(" вартість: " + totalPrice + " грн");


        // Завдання 6 пз
        Smartphone smartphone = new Smartphone();
        smartphone.model = "Samsung";
        smartphone.batteryLevel = 100;
        smartphone.isTurnedOn = true;

        smartphone.batteryLevel -= 35;

        if (smartphone.batteryLevel == 0) {
            smartphone.isTurnedOn = false;
        }

        System.out.println("Модель: " + smartphone.model);
        System.out.println("Заряд батареї: " + smartphone.batteryLevel + "%");
        System.out.println("Увімкнений: " + smartphone.isTurnedOn);
    }
}

class Rectangle {
    double width;
    double height;
}

class Product {
    String name;
    double price;
    int quantity;
}

class Smartphone {
    String model;
    int batteryLevel;
    boolean isTurnedOn;
}

































