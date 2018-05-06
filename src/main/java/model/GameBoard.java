package model;

import validation.AndValidator;

public class GameBoard {
    private PlayerBoard firstPlayerBoard;
    private PlayerBoard secondPlayerBoard;
    private GameBoardObserver firstBoardObserver;
    private GameBoardObserver secondBoardObserver;
    private AndValidator validator;

    public GameBoard(PlayerBoard firstPlayerBoard, PlayerBoard secondPlayerBoard, AndValidator validator) {
        this.firstPlayerBoard = firstPlayerBoard;
        this.secondPlayerBoard = secondPlayerBoard;
        this.validator = validator;
    }

    public BoardField getFirstPlayerBoard(Point point) {
        return firstPlayerBoard.getMapElement(point);
    }

    public BoardField getSecondPlayerBoard(Point point) {
        return secondPlayerBoard.getMapElement(point);
    }

    public BoardField shootAtFirstPlayerBoard(Point point) {
        BoardField actualField = firstPlayerBoard.getMapElement(point);
        switch (actualField) {
            case WATER:
                firstPlayerBoard = firstPlayerBoard.updateMap(point, BoardField.MISS);
                break;
            case SHIP:
                firstPlayerBoard = firstPlayerBoard.updateMap(point, BoardField.SHIP_HIT);
                break;
        }
        return actualField;
    }

    public BoardField shootAtSecondPlayerBoard(Point point) {
        BoardField actualField = secondPlayerBoard.getMapElement(point);
        switch (actualField) {
            case WATER:
                secondPlayerBoard = secondPlayerBoard.updateMap(point, BoardField.MISS);
                break;
            case SHIP:
                secondPlayerBoard = secondPlayerBoard.updateMap(point, BoardField.SHIP_HIT);
                break;
        }
        return actualField;
    }

    public Boolean addShipOnFirstBoard(Ship ship) {
        boolean result = validator.isValidate(ship, firstPlayerBoard);
        if (result) {
            firstPlayerBoard = firstPlayerBoard.addShip(ship);
        }
        return result;
    }

    public Boolean addShipOnSecondBoard(Ship ship) {
        boolean result = validator.isValidate(ship, secondPlayerBoard);
        if (result) {
            secondPlayerBoard = secondPlayerBoard.addShip(ship);
        }
        return result;
    }

    public Integer getFirstPlayerShipsCount() {
        return firstPlayerBoard.getShips().size();
    }

    public Integer getSecondPlayerShipsCount() {
        return secondPlayerBoard.getShips().size();
    }
}
