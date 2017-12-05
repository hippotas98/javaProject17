package w5;
import java.util.*;
public class p2
{
    public static void main (String [] args)
    {
        Shape sp = new Shape();
        System.out.println(sp.toString());
        Circle cl = new Circle(2.0);
        System.out.println(cl.getArea() + "   " + cl.getPerimeter());
        Rectangle rg = new Rectangle(2,3);
        System.out.println(rg.getArea() + "   " + rg.getPerimeter());
        Square sq = new Square(2.0);
        System.out.println(sq.getArea() + "   " + sq.getPerimeter());
    }
}
class Shape 
{
    String color;
    boolean filled;
    public Shape()
    {
        color = "red";
        filled = true;
    }
    public Shape(String color, boolean filled)
    {
        this.color = color;
        this.filled = filled;
    }
    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }
    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
    public boolean isFilled()
    {
        return this.filled;
    }
    /**
     * @param filled the filled to set
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public String toString()
    {
        return "Shape with " + color + "color";
    }
}
class Circle extends Shape
{
    double radius;
    final double PI = 3.14;
    public Circle()
    {
        super();
        radius=1.0;
    }
    public Circle(double radius)
    {
        super();
        this.radius = radius;
    }
    public Circle(double radius,String color, boolean filled)
    {
        super(color,filled);
        this.radius = radius;
    }
    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }
    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getArea()
    {
        return radius*radius*PI;
    }
    public double getPerimeter()
    {
        return radius*2*PI;
    }
    @Override
    public String toString()
    {
        StringBuilder temp = new StringBuilder("Circle radius = ");
        temp.append(radius);
        return temp.toString();
    }
}
class Rectangle extends Shape
{
    double width;
    double length;
    public Rectangle()
    {
        super();
        this.width = 1.0;
        this.length = 1.0;
    }
    public Rectangle(double width, double length)
    {
        super();
        this.width = width;
        this.length = length;
    }
    public Rectangle(double width, double length, String color, boolean filled)
    {
        super(color,filled);
        //Rectangle(width, length);
        this.width = width;
        this.length = length;
    }
    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }
    /**
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
    }
    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }
    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }
    public double getArea()
    {
        return width*length;
    }
    public double getPerimeter()
    {
        return (width + length)*2;
    }
    @Override
    public String toString()
    {
        return "Rectangle";
    }
}
class Square extends Rectangle
{
    //double side;
    public Square()
    {
        super();
        super.length = 1.0;
        super.width = 1.0;
    }
    public Square(double side)
    {
        super();
        super.width = super.length = side;
    }
    public Square(double side, String color, boolean filled)
    {
        super(side, side, color, filled);
        //super.width = Supplier.length = side;
    }
    /**
     * @return the side
     */
    public double getSide() {
        return super.length;
    }
    /**
     * @param side the side to set
     */
    public void setSide(double side) {
        this.width = this.length = side;
    }
    @Override
    public void setWidth(double side)
    {
        super.setWidth(side);
    }
    @Override
    public void setLength(double side)
    {
        super.setLength(side);
    }
    @Override
    public String toString()
    {
        return "Square";
    }
}