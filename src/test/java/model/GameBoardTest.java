package model;

import org.junit.Before;
import org.junit.Test;
import validation.AndValidator;

import static org.junit.Assert.*;

public class GameBoardTest {

    private GameBoard gameBoard;

    @Before
    public void init() {
        PlayerBoard firstBoard = PlayerBoard.fresh();
        PlayerBoard secondBoard = PlayerBoard.fresh();
        AndValidator validator = new AndValidator();
        gameBoard = new GameBoard(firstBoard, secondBoard, validator);
    }

    @Test
    public void shouldChengeFieldToMissWhenFindWater() {
        // given
        Point point = new Point(3,7);
        // when
        BoardField actualFirstField = gameBoard.shootAtFirstPlayerBoard(point);
        BoardField actualSecondField = gameBoard.shootAtSecondPlayerBoard(point);
        BoardField firstPlayerBoard = gameBoard.getFirstPlayerBoard(point);
        BoardField secondPlayerBoard = gameBoard.getSecondPlayerBoard(point);
        // then
        assertEquals(BoardField.MISS, firstPlayerBoard);
        assertEquals(BoardField.MISS, secondPlayerBoard);
        assertEquals(BoardField.WATER, actualFirstField);
        assertEquals(BoardField.WATER, actualSecondField);
    }

    @Test
    public void shouldChengeFieldToShipHitWhenFindShip() {
        // given
        Point point = new Point(3,7);
        gameBoard.addShipOnFirstBoard(new Ship(ShipType.BATTLESHIP,Orientation.VERTICAL,point));
        gameBoard.addShipOnSecondBoard(new Ship(ShipType.BATTLESHIP,Orientation.HORIZONTAL,point));
        // when
        BoardField actualFirstField = gameBoard.shootAtFirstPlayerBoard(point);
        BoardField actualSecondField = gameBoard.shootAtSecondPlayerBoard(point);
        BoardField firstPlayerBoard = gameBoard.getFirstPlayerBoard(point);
        BoardField secondPlayerBoard = gameBoard.getSecondPlayerBoard(point);
        // then
        assertEquals(BoardField.SHIP_HIT, firstPlayerBoard);
        assertEquals(BoardField.SHIP_HIT, secondPlayerBoard);
        assertEquals(BoardField.SHIP, actualFirstField);
        assertEquals(BoardField.SHIP, actualSecondField);
    }

    @Test
    public void shouldAddShipWhenValidatorReturnTrue() {
        // given
        Point point = new Point(3,7);
        Integer firstCount = gameBoard.getFirstPlayerShipsCount()+1;
        Integer secondCount = gameBoard.getSecondPlayerShipsCount()+1;
        // when
        Boolean resultOfFirst = gameBoard.addShipOnFirstBoard(new Ship(ShipType.BATTLESHIP,Orientation.HORIZONTAL,point));
        Boolean resultOfSecond = gameBoard.addShipOnSecondBoard(new Ship(ShipType.BATTLESHIP,Orientation.VERTICAL,point));
        // then
        assertEquals(firstCount,gameBoard.getFirstPlayerShipsCount());
        assertEquals(secondCount,gameBoard.getSecondPlayerShipsCount());
        assertTrue(resultOfFirst);
        assertTrue(resultOfSecond);
    }

    @Test
    public void shouldNotAddShipWhenValidatorReturnFalse() {
        // given
        Point point = new Point(8,8);
        Integer firstCount = gameBoard.getFirstPlayerShipsCount();
        Integer secondCount = gameBoard.getSecondPlayerShipsCount();
        // when
        Boolean resultOfFirst = gameBoard.addShipOnFirstBoard(new Ship(ShipType.BATTLESHIP,Orientation.HORIZONTAL,point));
        Boolean resultOfSecond = gameBoard.addShipOnSecondBoard(new Ship(ShipType.BATTLESHIP,Orientation.VERTICAL,point));
        // then
        assertEquals(firstCount,gameBoard.getFirstPlayerShipsCount());
        assertEquals(secondCount,gameBoard.getSecondPlayerShipsCount());
        assertFalse(resultOfFirst);
        assertFalse(resultOfSecond);
    }
}