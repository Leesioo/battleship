package model;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class PlayerBoardTest {

    @Test
    public void shouldNewBoardHasNoShips() {
        // given
        PlayerBoard board = PlayerBoard.fresh();
        // when
        List<Ship> ships = board.getShips();
        // then
        assertEquals(ships.size(), 0);
    }

    @Test
    public void shouldNewBoardContainsOnlyWater() {
        // given
        PlayerBoard board = PlayerBoard.fresh();
        // when
        Set<BoardField> types = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                types.add(board.getMapElement(new Point(i,j)));
            }
        }
        // then
        assertFalse(types.contains(BoardField.MISS));
        assertFalse(types.contains(BoardField.SHIP));
        assertFalse(types.contains(BoardField.SHIP_HIT));
        assertFalse(types.contains(BoardField.NONE));
    }

    @Test
    public void shouldBeAbleToChangeSeaElementValue() {
        // given
        PlayerBoard board = PlayerBoard.fresh();
        Point point = new Point(3,7);
        BoardField miss = BoardField.MISS;
        // when
        PlayerBoard newBoard = board.updateMap(point, miss);
        // then
        assertEquals(newBoard.getMapElement(point), miss);
        assertNotEquals(board.getMapElement(point), miss);
    }

    @Test
    public void shouldBeAbleToAddNewShip() {
        // given
        PlayerBoard board = PlayerBoard.fresh();
        Ship ship = new Ship(ShipType.SUBMARINE, Orientation.VERTICAL, new Point(1,1));
        Integer shipCount = board.getShips().size();
        // when
        PlayerBoard newBoard = board.addShip(ship);
        // then
        assertEquals(newBoard.getShips().size(), shipCount+1);
    }

    @Test
    public void shouldReturnNewInstanceAfterSeaChange() {
        // given
        PlayerBoard board = PlayerBoard.fresh();
        Point point = new Point(3,7);
        BoardField miss = BoardField.MISS;
        // when
        PlayerBoard newBoard = board.updateMap(point, miss);
        // then
        assertTrue(newBoard != board);
    }

    @Test
    public void shouldReturnNewInstanceAfterAddNewShip() {
        // given
        PlayerBoard board = PlayerBoard.fresh();
        Ship ship = new Ship(ShipType.SUBMARINE, Orientation.VERTICAL, new Point(1,1));
        Integer shipCount = board.getShips().size();
        // when
        PlayerBoard newBoard = board.addShip(ship);
        // then
        assertEquals(newBoard.getShips().size(), shipCount+1);
        assertEquals(board.getShips().size(), shipCount+0);
        assertTrue(newBoard != board);
    }

    @Test
    public void shouldReturnNoneWhenRightBoundaryIsExceeded() {
        // given
        PlayerBoard board = PlayerBoard.fresh();
        // when
        BoardField seaElement = board.getMapElement(new Point(11,5));
        // then
        assertEquals(BoardField.NONE, seaElement);
    }

    @Test
    public void shouldMarkShipOnSea() {
        // given
        PlayerBoard board = PlayerBoard.fresh();
        Point point = new Point(3,6);
        Ship ship = new Ship(ShipType.DESTROYER, Orientation.HORIZONTAL, point);
        // when
        PlayerBoard newBoard = board.addShip(ship);
        // then
        assertEquals(BoardField.SHIP, newBoard.getMapElement(point));
        assertEquals(BoardField.SHIP, newBoard.getMapElement(new Point(4,6)));
    }

    @Test
    public void shouldNotBePossibleToChangeElementBeyondBoard() {
        // given
        PlayerBoard board = PlayerBoard.fresh();
        Point point = new Point(13,7);
        BoardField miss = BoardField.MISS;
        // when
        PlayerBoard newBoard = board.updateMap(point, miss);
        // then
        assertEquals(BoardField.NONE, newBoard.getMapElement(point));
    }
}