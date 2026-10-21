package geometry2d;

import exceptions.InvalidFigureParameterException;
import java.io.Serializable;

public class Rectangle implements Figure, Serializable {
    private static final long serialVersionUID = 1L;
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        if (Double.isNaN(width) || Double.isInfinite(width) || width <= 0 ||
                Double.isNaN(height) || Double.isInfinite(height) || height <= 0) {
            throw new InvalidFigureParameterException("Стороны прямоугольника должны быть положительными конечными числами. Получено: " + width + ", " + height);
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }

    @Override
    public double area() { return width * height; }

    @Override
    public double perimeter() { return 2 * (width + height); }

    @Override
    public String toString() {
        return "Rectangle{width=" + width + ", height=" + height + "}";
    }
}