package user;

import model.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConsoleUITest {

    @Test
    public void shouldReturnNullWhenIncorrectInput() {
        //given
        ConsoleUI console = new ConsoleUI();
        //when
        Point point = console.convertToPoint("eqw");
        //then
        assertFalse(point != null);
    }

    @Test
    public void shouldReturnNotNullWhenCorrectInput() {
        //given
        ConsoleUI console = new ConsoleUI();
        //when
        Point point = console.convertToPoint("e1");
        //then
        assertFalse(point == null);
    }
}