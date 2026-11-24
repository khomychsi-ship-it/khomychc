package semester1.practice11.homework;

import java.util.*;

abstract class Shape {
    private final String id, color;
    public Shape(String id, String color) { this.id=id; this.color=color; }
    public String getId(){return id;} public String getColor(){return color;}
    public abstract double getArea(); public abstract double getPerimeter();
    public boolean equals(Object o){return o instanceof Shape && id.equals(((Shape)o).id);}
    public int hashCode(){return Objects.hash(id);}
}
class Circle extends Shape {
    private double radius;
    public Circle(String id,String color,double r){super(id,color);if(r<=0)throw new IllegalArgumentException();radius=r;}
    public double getArea(){return Math.PI*radius*radius;} public double getPerimeter(){return 2*Math.PI*radius;}
    public String toString(){return "Circle["+getId()+", "+getColor()+", r="+radius+"]";}
}
class Rectangle extends Shape {
    private double width,height;
    public Rectangle(String id,String color,double w,double h){super(id,color);if(w<=0||h<=0)throw new IllegalArgumentException();width=w;height=h;}
    public double getArea(){return width*height;} public double getPerimeter(){return 2*(width+height);}
    public String toString(){return "Rectangle["+getId()+", "+getColor()+", "+width+"x"+height+"]";}
}
class RightTriangle extends Shape {
    private double a,b;
    public RightTriangle(String id,String color,double a,double b){super(id,color);if(a<=0||b<=0)throw new IllegalArgumentException();this.a=a;this.b=b;}
    public double getArea(){return a*b/2;} public double getPerimeter(){return a+b+Math.sqrt(a*a+b*b);}
    public String toString(){return "RightTriangle["+getId()+", "+getColor()+", "+a+"x"+b+"]";}
}
class GeometryCanvas {
    private String title; private ArrayList<Shape> shapes=new ArrayList<>(); private HashSet<String> usedIds=new HashSet<>();
    public GeometryCanvas(String title){this.title=title;}
    public boolean addShape(Shape s){if(s==null||!usedIds.add(s.getId()))return false;shapes.add(s);return true;}
    public int removeSmallShapes(double minArea){int n=0;Iterator<Shape> it=shapes.iterator();while(it.hasNext()){Shape s=it.next();if(s.getArea()<minArea){it.remove();usedIds.remove(s.getId());n++;}}return n;}
    public double getTotalArea(){double s=0;for(Shape x:shapes)s+=x.getArea();return s;}
    public double getTotalPerimeter(){double s=0;for(Shape x:shapes)s+=x.getPerimeter();return s;}
    public HashSet<String> getUniqueColors(){HashSet<String>s=new HashSet<>();for(Shape x:shapes)s.add(x.getColor());return s;}
    public ArrayList<Shape> getShapesCopy(){return new ArrayList<>(shapes);}
}
public class Task3GeometryCanvasAndStats {
    public static void main(String[] args){
        GeometryCanvas canvas=new GeometryCanvas("Головне полотно");
        Shape[] figures={new Circle("C-1","Red",3),new Circle("C-1","Blue",5),new Rectangle("R-1","Blue",4,5),new Rectangle("R-2","Green",2,3),new RightTriangle("T-1","Red",3,4),new RightTriangle("T-2","Yellow",6,8)};
        for(Shape s:figures)System.out.println((canvas.addShape(s)?"Додано: ":"Відхилено, дублікат ID: ")+s);
        show(canvas,"До очищення");
        System.out.println("Видалено фігур: "+canvas.removeSmallShapes(10));
        show(canvas,"Після очищення");
        HashSet<String> c1=canvas.getUniqueColors();
        HashSet<String> c2=new HashSet<>(Arrays.asList("Blue","Yellow","Purple"));
        HashSet<String> both=new HashSet<>(c1);both.retainAll(c2);
        HashSet<String> only=new HashSet<>(c1);only.removeAll(c2);
        HashSet<String> all=new HashSet<>(c1);all.addAll(c2);
        System.out.println("Спільні кольори: "+both);System.out.println("Тільки перше полотно: "+only);System.out.println("Усі кольори: "+all);
    }
    private static void show(GeometryCanvas c,String title){System.out.printf("%s: фігур %d, площа %.2f, периметр %.2f, кольори %s%n",title,c.getShapesCopy().size(),c.getTotalArea(),c.getTotalPerimeter(),c.getUniqueColors());}
}
