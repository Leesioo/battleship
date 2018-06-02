package user;

import model.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleUITest {

    private ConsoleUI console;
    @Mock
    private JavaConsoleDelegate javaConsoleDelegate;

    @Before
    public void init() {
        console = new ConsoleUI(javaConsoleDelegate);
    }

    @Test
    public void whenUserShootAtC4ThenPointIsX3Y4() {
        //given
        String userInput = "c4";
        when(javaConsoleDelegate.readFromConsole()).thenReturn(userInput);
        //when
        Point shotLocation = console.askUserForShot();
        //then
        assertEquals((Integer) 3, shotLocation.getX());
        assertEquals((Integer) 4, shotLocation.getY());
    }

    @Test
    public void whenUserShootAtAAAThenPointIsX0Y0() {
        //given
        String userInput = "aAa";
        when(javaConsoleDelegate.readFromConsole()).thenReturn(userInput);
        //when
        Point shotLocation = console.askUserForShot();
        //then
        assertEquals((Integer) 0, shotLocation.getX());
        assertEquals((Integer) 0, shotLocation.getY());
    }

    @Test
    public void whenUserNeedsToBeNotifiedThenInvokeConsoleDelegate() {
        // given
        String tekst = "cokolwiek";
        // when
        console.notifyUser(tekst);
               // then
        verify(javaConsoleDelegate).printToConsole(eq(tekst));
    }
}