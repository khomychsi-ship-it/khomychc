package pz10;

public class pz10 {

    abstract static class PaymentInstrument {
        private final String payerName;

        PaymentInstrument(String payerName) {
            this.payerName = payerName;
        }

        public String getPayerName() {
            return payerName;
        }

        public abstract double calculateFee(double amount);

        public double processPayment(double amount) {
            return amount + calculateFee(amount);
        }
    }

    static class BankCardInstrument extends PaymentInstrument {
        BankCardInstrument(String name) {
            super(name);
        }

        public double calculateFee(double amount) {
            return amount * 0.015 + 5;
        }
    }

    static class CryptoTokenInstrument extends PaymentInstrument {
        private final double gasFeeUah;

        CryptoTokenInstrument(String name, double gasFeeUah) {
            super(name);
            this.gasFeeUah = gasFeeUah;
        }

        public double calculateFee(double amount) {
            return gasFeeUah;
        }
    }

    static class IbanDirectInstrument extends PaymentInstrument {
        IbanDirectInstrument(String name) {
            super(name);
        }

        public double calculateFee(double amount) {
            if (amount <= 50000) {
                return 0;
            }
            return amount * 0.005;
        }
    }

    abstract static class Shape2D {
        public abstract double getArea();
        public abstract double getPerimeter();
    }

    static class CircleShape extends Shape2D {
        private final double radius;

        CircleShape(double radius) {
            this.radius = radius;
        }

        public double getArea() {
            return Math.PI * radius * radius;
        }

        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }
    }

    static class RectangleShape extends Shape2D {
        private final double a;
        private final double b;

        RectangleShape(double a, double b) {
            this.a = a;
            this.b = b;
        }

        public double getArea() {
            return a * b;
        }

        public double getPerimeter() {
            return 2 * (a + b);
        }
    }

    static class EquilateralTriangleShape extends Shape2D {
        private final double a;

        EquilateralTriangleShape(double a) {
            this.a = a;
        }

        public double getArea() {
            return Math.sqrt(3) * a * a / 4;
        }

        public double getPerimeter() {
            return 3 * a;
        }
    }

    public static Shape2D findMaxAreaShape(Shape2D[] shapes) {
        Shape2D max = shapes[0];

        for (Shape2D shape : shapes) {
            if (shape.getArea() > max.getArea()) {
                max = shape;
            }
        }

        return max;
    }

    static class VectorMathOverload {
        static double norm(double x) {
            return Math.abs(x);
        }

        static double norm(double x, double y) {
            return Math.sqrt(x * x + y * y);
        }

        static double norm(double[] v) {
            double sum = 0;
            for (double x : v) {
                sum = sum + x * x;
            }
            return Math.sqrt(sum);
        }
    }

    abstract static class BinaryOperation {
        public abstract double apply(double a, double b);
    }

    static class AddOp extends BinaryOperation {
        public double apply(double a, double b) {
            return a + b;
        }
    }

    static class MultiplyOp extends BinaryOperation {
        public double apply(double a, double b) {
            return a * b;
        }
    }

    static class HypotenuseOp extends BinaryOperation {
        public double apply(double a, double b) {
            return Math.sqrt(a * a + b * b);
        }
    }

    public static void main(String[] args) {
        System.out.println("ПРАКТИЧНА РОБОТА 10");

        System.out.println("\nЗавдання 1:");
        PaymentInstrument[] payments = {
                new BankCardInstrument("Банківська картка"),
                new CryptoTokenInstrument("Криптовалюта", 40),
                new IbanDirectInstrument("IBAN")
        };

        double total = 0;
        for (PaymentInstrument p : payments) {
            double price = p.processPayment(10000);
            System.out.println(p.getPayerName() + ": " + price + " грн");
            total = total + price;
        }
        System.out.println("Разом: " + total + " грн");

        System.out.println("\nЗавдання 2:");
        Shape2D[] shapes = {
                new CircleShape(5),
                new RectangleShape(4, 6),
                new EquilateralTriangleShape(5)
        };

        for (Shape2D shape : shapes) {
            System.out.println(shape.getClass().getSimpleName()
                    + ": площа = " + shape.getArea()
                    + ", периметр = " + shape.getPerimeter());
        }

        Shape2D max = findMaxAreaShape(shapes);
        System.out.println("Найбільша площа у: " + max.getClass().getSimpleName());

        System.out.println("\nЗавдання 3:");
        System.out.println("norm(5) = " + VectorMathOverload.norm(5));
        System.out.println("norm(3, 4) = " + VectorMathOverload.norm(3, 4));
        System.out.println("norm([2, 3, 6]) = "
                + VectorMathOverload.norm(new double[]{2, 3, 6}));

        BinaryOperation[] operations = {
                new AddOp(),
                new MultiplyOp(),
                new HypotenuseOp()
        };

        for (BinaryOperation op : operations) {
            System.out.println(op.getClass().getSimpleName() + " = " + op.apply(3, 4));
        }
    }
}
