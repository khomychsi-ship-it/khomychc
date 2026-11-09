package semester1.practice08;

class Student {
    private String name;
    private int course;
    private double gpa;

    public Student(String name, int course, double gpa) {
        this.name = name;
        setCourse(course);
        setGpa(gpa);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if (course >= 1 && course <= 6) {
            this.course = course;
        } else {
            this.course = 1;
        }
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa >= 0 && gpa <= 100) {
            this.gpa = gpa;
        } else {
            this.gpa = 60.0;
        }
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', course=" + course + ", gpa=" + gpa + "}";
    }
}

class Product {
    private String sku;
    private String title;
    private double price;
    private int stock;

    public Product(String sku, String title, double price, int stock) {
        this.sku = sku;
        this.title = title;
        setPrice(price);
        setStock(stock);
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 0;
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            this.stock = 0;
        }
    }

    public double getTotalCost() {
        return price * stock;
    }

    @Override
    public String toString() {
        return String.format("[SKU: %s] %s | Ціна: %.2f грн | Залишок: %d шт. | Разом: %.2f грн",
                sku, title, price, stock, getTotalCost());
    }
}

class Circle {
    private double radius;

    public Circle(double radius) {
        setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = 1.0;
        }
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Circle[r=%.2f, S=%.2f, C=%.2f]",
                radius, getArea(), getCircumference());
    }
}

public class Practice08 {
    public static void main(String[] args) {
        System.out.println("Завдання 1");
        Student student = new Student("Степан", 2, 85.5);
        System.out.println(student);

        System.out.println();
        System.out.println("Завдання 2");
        Product product1 = new Product("A-101", "Монітор", 6499.50, 4);
        Product product2 = new Product("B-205", "Миша", 599.00, 10);
        System.out.println(product1);
        System.out.println(product2);

        System.out.println();
        System.out.println("Завдання 3");
        Circle circle = new Circle(-5);
        System.out.println(circle);
        circle.setRadius(3);
        System.out.println(circle);
    }
}
