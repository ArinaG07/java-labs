package geometry3d;

import exceptions.InvalidFigureParameterException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {
    private static final double EPSILON = 1e-9;

    @Test
    void volumeWithCircleBase() {
        Figure base = new Circle(2.0);
        Cylinder cylinder = new Cylinder(base, 5.0);
        assertEquals(20.0 * Math.PI, cylinder.volume(), EPSILON);
    }

    @Test
    void volumeWithRectangleBase() {
        Figure base = new Rectangle(3.0, 4.0);
        Cylinder cylinder = new Cylinder(base, 10.0);
        assertEquals(120.0, cylinder.volume(), EPSILON);
    }

    @Test
    void constructorThrowsOnNullBase() {
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(null, 5.0));
    }

    @Test
    void constructorThrowsOnInvalidHeight() {
        Figure base = new Circle(2.0);
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(base, 0));
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(base, -5));
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(base, Double.NaN));
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(base, Double.POSITIVE_INFINITY));
    }

    @Test
    void gettersReturnCorrectValues() {
        Figure base = new Circle(2.0);
        Cylinder cylinder = new Cylinder(base, 5.0);
        assertSame(base, cylinder.getBase());
        assertEquals(5.0, cylinder.getHeight(), EPSILON);
    }

    @Test
    void toStringContainsTypeAndParams() {
        Figure base = new Circle(2.0);
        Cylinder cylinder = new Cylinder(base, 5.0);
        String str = cylinder.toString();
        assertTrue(str.contains("Cylinder"));
        assertTrue(str.contains("5.0"));
    }
}
