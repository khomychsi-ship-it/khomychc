package semester1.practice11.homework;

import java.util.*;

class Point2D {
    private final int x, y;
    public Point2D(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    public double distanceFromOrigin() { return Math.sqrt(x * x + y * y); }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2D)) return false;
        Point2D p = (Point2D) o;
        return x == p.x && y == p.y;
    }
    public int hashCode() { return Objects.hash(x, y); }
    public String toString() { return "(" + x + "; " + y + ")"; }
}

class PointSetUtils {
    public static HashSet<Point2D> union(HashSet<Point2D> a, HashSet<Point2D> b) {
        HashSet<Point2D> result = new HashSet<>(a); result.addAll(b); return result;
    }
    public static HashSet<Point2D> intersection(HashSet<Point2D> a, HashSet<Point2D> b) {
        HashSet<Point2D> result = new HashSet<>(a); result.retainAll(b); return result;
    }
    public static HashSet<Point2D> difference(HashSet<Point2D> a, HashSet<Point2D> b) {
        HashSet<Point2D> result = new HashSet<>(a); result.removeAll(b); return result;
    }
    public static int removeFarPoints(HashSet<Point2D> points, double maxRadius) {
        int removed = 0;
        Iterator<Point2D> it = points.iterator();
        while (it.hasNext()) {
            if (it.next().distanceFromOrigin() > maxRadius) { it.remove(); removed++; }
        }
        return removed;
    }
}

public class Task1UniquePointsAndSets {
    public static void main(String[] args) {
        ArrayList<Point2D> rawA = new ArrayList<>(Arrays.asList(
                new Point2D(0,0), new Point2D(3,4), new Point2D(1,2), new Point2D(3,4),
                new Point2D(6,8), new Point2D(5,0), new Point2D(1,2)));
        ArrayList<Point2D> rawB = new ArrayList<>(Arrays.asList(
                new Point2D(3,4), new Point2D(5,0), new Point2D(2,2), new Point2D(8,6),
                new Point2D(2,2), new Point2D(0,10)));
        HashSet<Point2D> a = new HashSet<>(rawA), b = new HashSet<>(rawB);
        System.out.println("Сенсор A: " + rawA.size() + " -> " + a.size() + " " + a);
        System.out.println("Сенсор B: " + rawB.size() + " -> " + b.size() + " " + b);
        HashSet<Point2D> all = PointSetUtils.union(a, b);
        System.out.println("Об'єднання: " + all);
        System.out.println("Перетин: " + PointSetUtils.intersection(a, b));
        System.out.println("Різниця A-B: " + PointSetUtils.difference(a, b));
        int removed = PointSetUtils.removeFarPoints(all, 7.0);
        System.out.println("Видалено точок: " + removed);
        System.out.println("Залишилось: " + all);
    }
}
