class Book {
    private String title;
    private String author;
    private int year;
    private double price;

    public Book() {
        title = "Невідомо";
        author = "Невідомий";
        year = 2000;
        price = 0.0;
    }

    public Book(String title, String author, int year, double price) {
        this.title = title;
        this.author = author;

        if (year < 0) {
            this.year = 2000;
        } else {
            this.year = year;
        }

        if (price < 0) {
            this.price = 0.0;
        } else {
            this.price = price;
        }
    }

    public void printInfo() {
        System.out.println(title + " " + author + " " + year + " " + price);
    }
}


// Завдання 7
class Rectangle {
    private double width;
    private double height;

    public Rectangle() {
        this(1.0, 1.0);
    }

    public Rectangle(double size) {
        this(size, size);
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public boolean isSquare() {
        return width == height;
    }
}


// Завдання 17
class MathOperations {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public String add(String a, String b) {
        return a + " " + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public String multiply(String text, int times) {
        String result = "";

        for (int i = 0; i < times; i++) {
            result = result + text;
        }

        return result;
    }
}


public class Main {
    public static void main(String[] args) {

        // Завдання 1
        Book book1 = new Book();
        Book book2 = new Book("Кобзар", "Шевченко", 1840, 250);

        book1.printInfo();
        book2.printInfo();


        // Завдання 7
        Rectangle rectangle = new Rectangle(4, 6);
        Rectangle square = new Rectangle(5);

        System.out.println(rectangle.getArea());
        System.out.println(rectangle.getPerimeter());

        System.out.println(square.getArea());
        System.out.println(square.getPerimeter());

        System.out.println(rectangle.isSquare());
        System.out.println(square.isSquare());


        // Завдання 17
        MathOperations math = new MathOperations();

        System.out.println(math.add(2, 3));
        System.out.println(math.add(2.5, 3.5));
        System.out.println(math.add(1, 2, 3));
        System.out.println(math.add("Привіт", "світ"));

        System.out.println(math.multiply(2, 3));
        System.out.println(math.multiply(2.5, 3.0));
        System.out.println(math.multiply("Так", 3));
    }
}
