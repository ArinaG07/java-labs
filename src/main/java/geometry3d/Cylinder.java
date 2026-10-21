package geometry3d;

import exceptions.InvalidFigureParameterException;
import geometry2d.Figure;
import java.io.Serializable;

public class Cylinder implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Figure base;
    private final double height;

    public Cylinder(Figure base, double height) {
        if (base == null) {
            throw new InvalidFigureParameterException("Основание цилиндра не может быть null.");
        }
        if (Double.isNaN(height) || Double.isInfinite(height) || height <= 0) {
            throw new InvalidFigureParameterException("Высота должна быть положительным конечным числом. Получено: " + height);
        }
        this.base = base;
        this.height = height;
    }

    public Figure getBase() { return base; }
    public double getHeight() { return height; }

    public double volume() {
        return base.area() * height;
    }

    @Override
    public String toString() {
        return "Cylinder{base=" + base + ", height=" + height + "}";
    }
}