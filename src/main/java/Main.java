import model.GameBoard;
import model.PlayerBoard;
import user.ConsoleUI;
import validation.AndValidator;

public class Main {
    public static void main(String[] args) {
        PlayerBoard firstBoard = PlayerBoard.fresh();
        PlayerBoard secondBoard = PlayerBoard.fresh();
        AndValidator validator = new AndValidator();

        GameBoard gameBoard = new GameBoard(firstBoard, secondBoard, validator);
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.update(gameBoard);
        consoleUI.printMaps();
    }
}
