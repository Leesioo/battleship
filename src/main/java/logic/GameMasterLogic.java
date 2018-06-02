package logic;

import model.*;
import user.Player;

import java.util.List;

public class GameMasterLogic {
    private final Player humanPlayer;
    private final Player computerPlayer;
    private GameBoard gameBoard;
    private List<ShipType> listOfShip;
    private Player currentPlayer;
    private Long humanPlayerShipsLeft;
    private Long computerPlayerShipsLeft;

    public GameMasterLogic(GameBoard gameBoard, Player humanPlayer, Player computerPlayer, List<ShipType> listOfShips) {
        this.gameBoard = gameBoard;
        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.listOfShip = listOfShips;
        this.currentPlayer = humanPlayer;
        this.humanPlayerShipsLeft = countShipFields();
        this.computerPlayerShipsLeft = this.humanPlayerShipsLeft;
    }

    private Long countShipFields() {
        Long result = 0L;
        for (ShipType shipType : listOfShip) {
            result =+ shipType.size();
        }
        return result;
    }

    public void generateComputerShips() {
        for (ShipType shipType : listOfShip) {
            Ship playerShip;
            boolean wasPlaced;
            do {
                playerShip = computerPlayer.getNextShip(shipType);
                wasPlaced = gameBoard.addShipOnSecondBoard(playerShip);
            } while (!wasPlaced);
        }
    }

    public void askForUserShips() {
        for (ShipType shipType : listOfShip) {
            Ship playerShip;
            boolean wasPlaced;
            do {
                playerShip = humanPlayer.getNextShip(shipType);
                wasPlaced = gameBoard.addShipOnFirstBoard(playerShip);
                if (!wasPlaced) {
                    humanPlayer.sendMessage("Can't place ship in that point. Type again !!!");
                }
            } while (!wasPlaced);
        }
    }

    public void askForShoot() {
        Point point = currentPlayer.getNextShot();
        BoardField field;
        if (isCurrentPlayerHuman()) {
            field = gameBoard.shootAtSecondPlayerBoard(point);
        } else {
            field = gameBoard.shootAtFirstPlayerBoard(point);
        }
        if (field != BoardField.SHIP) {
            changeCurrentPlayer();
        } else {
            if (isCurrentPlayerHuman()) {
                computerPlayerShipsLeft--;
            } else {
                humanPlayerShipsLeft--;
            }
        }

    }

    private void changeCurrentPlayer() {
        if (isCurrentPlayerHuman()) {
            currentPlayer = computerPlayer;
        } else {
            currentPlayer = humanPlayer;
        }
    }

    private boolean isCurrentPlayerHuman() {
        return currentPlayer == humanPlayer;
    }

    public Player getWinner() {
        if (humanPlayerShipsLeft <= 0L) {
            return computerPlayer;
        } else if (computerPlayerShipsLeft <= 0L) {
            return humanPlayer;
        } else {
            return null;
        }
    }

    public void printWinner() {
        if (getWinner() == humanPlayer) {
            humanPlayer.sendMessage("You WIN !!!");
        } else {
            humanPlayer.sendMessage("LOOSER :)");
        }
    }
}
