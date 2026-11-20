package semester1.practice10;

public class dz1{

    interface Scalable2D {
        double MIN_SCALE_FACTOR = 0.1;
        void scale(double factor);
    }

    interface Rotatable2D {
        void rotate(double deltaDegrees);
        double getRotationAngle();
    }

    abstract static class AbstractShape2D {
        private final String name;
        private double centerX;
        private double centerY;

        AbstractShape2D(String name, double centerX, double centerY) {
            this.name = name;
            this.centerX = centerX;
            this.centerY = centerY;
        }

        public String getName() {
            return name;
        }

        public abstract double getArea();
        public abstract double getPerimeter();

        public final double getIsoperimetricQuotient() {
            return 4 * Math.PI * getArea() / (getPerimeter() * getPerimeter());
        }

        public String toString() {
            return getClass().getSimpleName() + "['" + name + "', center=("
                    + centerX + "; " + centerY + "), S="
                    + String.format("%.2f", getArea()) + ", P="
                    + String.format("%.2f", getPerimeter()) + ", Q="
                    + String.format("%.4f", getIsoperimetricQuotient()) + "]";
        }
    }

    static class CircleShape extends AbstractShape2D implements Scalable2D {
        private double radius;

        CircleShape(String name, double x, double y, double radius) {
            super(name, x, y);
            this.radius = radius;
        }

        public double getArea() {
            return Math.PI * radius * radius;
        }

        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        public void scale(double factor) {
            if (factor < MIN_SCALE_FACTOR) {
                factor = MIN_SCALE_FACTOR;
            }
            radius = radius * factor;
        }

        public String toString() {
            return "CircleShape['" + getName() + "', r=" + String.format("%.2f", radius)
                    + " | S=" + String.format("%.2f", getArea())
                    + ", P=" + String.format("%.2f", getPerimeter())
                    + ", Q=" + String.format("%.4f", getIsoperimetricQuotient()) + "]";
        }
    }

    static class RectangleShape extends AbstractShape2D implements Scalable2D, Rotatable2D {
        private double width;
        private double height;
        private double rotationAngle;

        RectangleShape(String name, double x, double y, double width, double height) {
            super(name, x, y);
            this.width = width;
            this.height = height;
            rotationAngle = 0;
        }

        public double getArea() {
            return width * height;
        }

        public double getPerimeter() {
            return 2 * (width + height);
        }

        public void scale(double factor) {
            if (factor < MIN_SCALE_FACTOR) {
                factor = MIN_SCALE_FACTOR;
            }
            width = width * factor;
            height = height * factor;
        }

        public void rotate(double deltaDegrees) {
            rotationAngle = (rotationAngle + deltaDegrees) % 360;
        }

        public double getRotationAngle() {
            return rotationAngle;
        }

        public String toString() {
            return "RectangleShape['" + getName() + "', " + String.format("%.2f", width)
                    + "x" + String.format("%.2f", height) + ", angle="
                    + String.format("%.1f", rotationAngle) + "° | S="
                    + String.format("%.2f", getArea()) + ", P="
                    + String.format("%.2f", getPerimeter()) + ", Q="
                    + String.format("%.4f", getIsoperimetricQuotient()) + "]";
        }
    }

    public static final class ImmutableReferenceSquare extends AbstractShape2D {
        private final double side;

        ImmutableReferenceSquare(String name, double x, double y, double side) {
            super(name, x, y);
            this.side = side;
        }

        public double getArea() {
            return side * side;
        }

        public double getPerimeter() {
            return 4 * side;
        }

        public String toString() {
            return "ImmutableReferenceSquare['" + getName() + "', side="
                    + String.format("%.2f", side) + " | S="
                    + String.format("%.2f", getArea()) + ", P="
                    + String.format("%.2f", getPerimeter()) + ", Q="
                    + String.format("%.4f", getIsoperimetricQuotient()) + "]";
        }
    }

    public static double calculateTotalArea(AbstractShape2D[] shapes) {
        double sum = 0;
        for (AbstractShape2D shape : shapes) {
            sum = sum + shape.getArea();
        }
        return sum;
    }

    public static void transformScene(AbstractShape2D[] shapes, double scaleFactor, double rotateDeg) {
        for (AbstractShape2D shape : shapes) {
            if (shape instanceof Scalable2D) {
                Scalable2D s = (Scalable2D) shape;
                s.scale(scaleFactor);
            }

            if (shape instanceof Rotatable2D) {
                Rotatable2D r = (Rotatable2D) shape;
                r.rotate(rotateDeg);
            }
        }
    }

    public static void main(String[] args) {
        AbstractShape2D[] shapes = {
                new CircleShape("Коло-Лінза", 0, 0, 5),
                new RectangleShape("Рамка-А", 2, 3, 4, 6),
                new ImmutableReferenceSquare("Еталон-Квадрат", 10, 10, 5)
        };

        System.out.println("ДО ТРАНСФОРМАЦІЇ:");
        for (AbstractShape2D shape : shapes) {
            System.out.println(shape);
        }

        System.out.println("Сумарна площа: " + calculateTotalArea(shapes));

        transformScene(shapes, 2.0, 45.0);

        System.out.println("\nПІСЛЯ ТРАНСФОРМАЦІЇ:");
        for (AbstractShape2D shape : shapes) {
            System.out.println(shape);
        }

        System.out.println("Сумарна площа: " + calculateTotalArea(shapes));
    }
}
