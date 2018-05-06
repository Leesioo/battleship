package user;

import model.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerRandomUI implements UserInterface, GameBoardObserver {
    private GameBoard gameBoard;
    private Random random;
    private Set<Point> possible;

    public ComputerRandomUI() {
        random = new Random();
        possible = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Point point = new Point(i, j);
                possible.add(point);
            }
        }
    }

    @Override
    public void update(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void printMaps() {

    }

    @Override
    public void notifyUser(String message) {

    }

    @Override
    public Point askUserForShot() {
        Integer count = possible.size();
        Point[] points = possible.toArray(new Point[count]);
        Integer element = random.nextInt(count);
        Point choosen = points[element];
        possible.remove(choosen);
        return choosen;
    }

    @Override
    public Ship askUserForShip(ShipType type) {
        Orientation orientation;
        Integer choice = random.nextInt(100);
        if (choice % 2 == 0) {
            orientation = Orientation.VERTICAL;
        } else {
            orientation = Orientation.HORIZONTAL;
        }
        Integer x = random.nextInt(10)+1;
        Integer y = random.nextInt(10)+1;

        return new Ship(type, orientation, new Point(x,y));
    }
}
