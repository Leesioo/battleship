package model;

import validation.AndValidator;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<GameBoardObserver> observers;
    private PlayerBoard firstPlayerBoard;
    private PlayerBoard secondPlayerBoard;
    private AndValidator validator;

    public GameBoard(PlayerBoard firstPlayerBoard, PlayerBoard secondPlayerBoard, AndValidator validator) {
        this.firstPlayerBoard = firstPlayerBoard;
        this.secondPlayerBoard = secondPlayerBoard;
        this.validator = validator;
        observers = new ArrayList<>();
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
        updateObservers();
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
        updateObservers();
        return actualField;
    }

    public Boolean addShipOnFirstBoard(Ship ship) {
        boolean result = validator.isValidate(ship, firstPlayerBoard);
        if (result) {
            firstPlayerBoard = firstPlayerBoard.addShip(ship);
            updateObservers();
        }
        return result;
    }

    public Boolean addShipOnSecondBoard(Ship ship) {
        boolean result = validator.isValidate(ship, secondPlayerBoard);
        if (result) {
            secondPlayerBoard = secondPlayerBoard.addShip(ship);
            // informujemy wszystkich obserwatorów o zmianie planszy
            updateObservers();
        }
        return result;
    }

    private void updateObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    public Integer getFirstPlayerShipsCount() {
        return firstPlayerBoard.getShips().size();
    }

    public Integer getSecondPlayerShipsCount() {
        return secondPlayerBoard.getShips().size();
    }

    public void register(GameBoardObserver observer) {
        observers.add(observer);
    }

    public void unregister(GameBoardObserver observer) {
        observers.remove(observer);

    }
}
