import logic.GameMasterLogic;
import logic.GameMasterRunnable;
import model.*;
import user.*;
import validation.AndValidator;
import validation.EndOfShipOnTheMap;
import validation.FrontOfShipOnTheMap;
import validation.ShipsAreSideBySide;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JavaConsoleDelegate console = new JavaConsoleDelegate();
        ConsoleUI consoleUI = new ConsoleUI(console);
        ComputerRandomUI computerUI = new ComputerRandomUI();
        Player humanPlayer = new DefaultPlayer(consoleUI);
        Player computerPlayer = new DefaultPlayer(computerUI);
        List<ShipType> listOfShips = getListOfShips();
        GameBoard gameBoard = new GameBoard(PlayerBoard.fresh(), PlayerBoard.fresh(), new AndValidator(new FrontOfShipOnTheMap(), new EndOfShipOnTheMap(), new ShipsAreSideBySide()));
        gameBoard.register(consoleUI);
        gameBoard.register(computerUI);
        GameMasterLogic gameMasterLogic = new GameMasterLogic(gameBoard, humanPlayer, computerPlayer, listOfShips);
        GameMasterRunnable gameMasterRunnable = new GameMasterRunnable(gameMasterLogic);

        Thread game = new Thread(gameMasterRunnable);
        game.start();


//        testMain(consoleUI, gameBoard);
    }

    private static List<ShipType> getListOfShips() {
        List<ShipType> ships = new ArrayList<>();
        ships.add(ShipType.BATTLESHIP);
        ships.add(ShipType.CRUISER);
        ships.add(ShipType.CRUISER);
        ships.add(ShipType.DESTROYER);
        ships.add(ShipType.DESTROYER);
        ships.add(ShipType.DESTROYER);
        ships.add(ShipType.SUBMARINE);
        ships.add(ShipType.SUBMARINE);
        ships.add(ShipType.SUBMARINE);
        ships.add(ShipType.SUBMARINE);
        return ships;
    }

    private static void testMain(ConsoleUI consoleUI, GameBoard gameBoard) {
        Ship humanShip1 = new Ship(ShipType.BATTLESHIP, Orientation.HORIZONTAL, new Point(2,1));
        Ship humanShip2 = new Ship(ShipType.DESTROYER, Orientation.VERTICAL, new Point(4,5));
        gameBoard.addShipOnFirstBoard(humanShip1);
        gameBoard.addShipOnFirstBoard(humanShip2);

        Ship comupterShip1 = new Ship(ShipType.BATTLESHIP, Orientation.VERTICAL, new Point(4,1));
        Ship computerShip2 = new Ship(ShipType.SUBMARINE, Orientation.HORIZONTAL, new Point(5,5));
        gameBoard.addShipOnSecondBoard(comupterShip1);
        gameBoard.addShipOnSecondBoard(computerShip2);

        gameBoard.shootAtFirstPlayerBoard(new Point(4,1));
        gameBoard.shootAtFirstPlayerBoard(new Point(5,9));

        gameBoard.shootAtSecondPlayerBoard(new Point(6,2));
        gameBoard.shootAtSecondPlayerBoard(new Point(5,5));

        consoleUI.update(gameBoard);
    }
}
