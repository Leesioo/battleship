package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void shouldBeAbleToCreatePointFromTwoIntegers() {
        // given
        Integer x = 3;
        Integer y = 5;
        // when
        Point point = new Point(x, y);
        // then
        assertEquals(point.getX(), (Integer)3);
        assertEquals(point.getY(), (Integer)5);
    }

    @Test
    public void shouldBeAbleToCreatePointFromPoint() {
        // given
        Point given = new Point(3, 5);
        // when
        Point point = new Point(given);
        // then
        assertEquals(point.getX(), (Integer)3);
        assertEquals(point.getY(), (Integer)5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenOneArgumentIsNull() {
        // given
        Integer x = 3;
        Integer y = null;
        // when
        Point point = new Point(x, y);
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenOneArgumentIsNegative() {
        // given
        Integer x = 3;
        Integer y = -1;
        // when
        Point point = new Point(x, y);
        // then
    }
}