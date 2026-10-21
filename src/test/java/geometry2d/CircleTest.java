package geometry2d;

import exceptions.InvalidFigureParameterException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    private static final double EPSILON = 1e-9;

    @Test
    void areaOfRadiusTwoIsCorrect() {
        Circle circle = new Circle(2.0);
        assertEquals(Math.PI * 4.0, circle.area(), EPSILON);
    }

    @Test
    void perimeterOfRadiusTwoIsCorrect() {
        Circle circle = new Circle(2.0);
        assertEquals(4.0 * Math.PI, circle.perimeter(), EPSILON);
    }

    @Test
    void constructorThrowsOnInvalidRadius() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(0));
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(-1.5));
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(Double.NaN));
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(Double.POSITIVE_INFINITY));
    }

    @Test
    void getterReturnsCorrectValue() {
        Circle circle = new Circle(2.0);
        assertEquals(2.0, circle.getRadius(), EPSILON);
    }

    @Test
    void toStringContainsTypeAndParams() {
        Circle circle = new Circle(2.0);
        String str = circle.toString();
        assertTrue(str.contains("Circle"));
        assertTrue(str.contains("2.0"));
    }
}
