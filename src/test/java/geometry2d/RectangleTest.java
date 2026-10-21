package geometry2d;

import exceptions.InvalidFigureParameterException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    private static final double EPSILON = 1e-9;

    @Test
    void areaOfThreeByFourRectangleIsTwelve() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(12.0, rect.area(), EPSILON);
    }

    @Test
    void perimeterOfThreeByFourRectangleIsFourteen() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(14.0, rect.perimeter(), EPSILON);
    }

    @Test
    void areaOfSquareIsCorrect() {
        Rectangle square = new Rectangle(5.0, 5.0);
        assertEquals(25.0, square.area(), EPSILON);
    }



    @Test
    void constructorThrowsOnZeroOrNegative() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(0, 4));
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(-3, 4));
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(3, 0));
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(3, -4));
    }

    @Test
    void constructorThrowsOnNaNOrInfinity() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(Double.NaN, 4));
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(Double.POSITIVE_INFINITY, 4));
    }



    @Test
    void gettersReturnCorrectValues() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(3.0, rect.getWidth(), EPSILON);
        assertEquals(4.0, rect.getHeight(), EPSILON);
    }

    @Test
    void toStringContainsTypeAndParams() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        String str = rect.toString();
        assertTrue(str.contains("Rectangle"));
        assertTrue(str.contains("3.0"));
        assertTrue(str.contains("4.0"));
    }
}