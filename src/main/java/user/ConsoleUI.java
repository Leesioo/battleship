package user;

import model.*;

public class ConsoleUI implements UserInterface, GameBoardObserver {
    private JavaConsoleDelegate console;
    private GameBoard gameBoard;
    private boolean isBoardChanged;

    public ConsoleUI(JavaConsoleDelegate console) {
        this.console= console;
    }

    @Override
    public void update(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        isBoardChanged = true;
        printMaps();
    }

    @Override
    public void printMaps() {
        printMapsHorizontal();
        //printMapsVertical();
    }

    private void printMapsVertical() {
        console.printToConsole("Your board:            ");
        console.printToConsole("   A B C D E F G H I J ");
        console.printToConsole("  +-------------------+");
        for (int i = 1; i <= 10; i++) {
            String empty;
            if (i == 10) {
                empty = "";
            } else {
                empty = " ";
            }
            console.printToConsole(empty + i + "|" + convertBoardToMapLine(i, 1) + "|");
        }
        console.printToConsole("  +-------------------+");
        console.printToConsole("");
        console.printToConsole("Your opponent board:");
        console.printToConsole("   A B C D E F G H I J");
        console.printToConsole("  +-------------------+");
        for (int i = 1; i <= 10; i++) {
            String empty;
            if (i == 10) {
                empty = "";
            } else {
                empty = " ";
            }
            console.printToConsole(empty + i + "|" + convertBoardToMapLine(i, 2) + "|");
        }
        console.printToConsole("  +-------------------+");
    }

    private void printMapsHorizontal() {
        console.printToConsole("Your board:                 Your opponent board:");
        console.printToConsole("   A B C D E F G H I J         A B C D E F G H I J ");
        console.printToConsole("  +-------------------+       +-------------------+");
        for (int i = 1; i <= 10; i++) {
            String empty;
            if (i == 10) {
                empty = "";
            } else {
                empty = " ";
            }
            console.printToConsole(empty + i + "|" + convertBoardToMapLine(i, 1) + "|     " + empty + i + "|" + convertBoardToMapLine(i, 2) + "|");
        }
        console.printToConsole("  +-------------------+       +-------------------+");
    }

    private String convertBoardToMapLine(int x, int lp) {
        String result = "";
        BoardField field;
        for (int i = 1; i <= 10; i++) {
            if (lp == 1) {
                field = gameBoard.getFirstPlayerBoard(new Point(i, x));
            } else {
                field = gameBoard.getSecondPlayerBoard(new Point(i, x));
                if (field == BoardField.SHIP) {
                    field = BoardField.WATER;
                }
            }
            switch (field) {
                case SHIP:
                    result = result + "O";
                    break;
                case WATER:
                    result = result + " ";
                    break;
                case MISS:
                    result = result + ".";
                    break;
                case SHIP_HIT:
                    result = result + "X";
                    break;
            }
            if (i < 10) {
                result = result + " ";
            }
        }
        return result;
    }

    @Override
    public void notifyUser(String message) {
        console.printToConsole(message);
    }

    @Override
    public Point askUserForShot() {
        boolean dataOk = false;
        Point outputPoint = null;
        while (!dataOk) {
            notifyUser("Take a shot Xxx (X: A-J, xx:1-10):");
            String result = console.readFromConsole();
            outputPoint = convertToPoint(result);
            dataOk = outputPoint != null;
        }
        return outputPoint;
    }

    public Point convertToPoint(String result) {
        if (result.length() < 2) {
            return null;
        }
        Character character = result.toUpperCase().charAt(0);
        String column = result.substring(1);
        Integer x, y;
        x = character - 'A' + 1;

        try {
            y = Integer.parseInt(column);
        } catch (NumberFormatException e) {
            y = 0;
        }

        if (x > 0 && x < 11 && y > 0 && y < 11) {
            return new Point(x, y);
        } else
            return new Point(0, 0);
    }

    @Override
    public Ship askUserForShip(ShipType type) {
        boolean dataOk = false;
        Point outputPoint = null;
        Orientation orientation;
        while (!dataOk) {
            notifyUser("Place ship at Xxx (X: A-J, xx:1-10):");
            String result = console.readFromConsole();
            outputPoint = convertToPoint(result);
            dataOk = outputPoint != null;
        }

        notifyUser("Is ship horizontal? (Y,Yes/Other)");
        String result = console.readFromConsole().toUpperCase();
        if (result.equals("Y") || result.equals("YES")) {
            orientation = Orientation.HORIZONTAL;
        } else {
            orientation = Orientation.VERTICAL;
        }
        return  new Ship(type, orientation, outputPoint);
    }
}
