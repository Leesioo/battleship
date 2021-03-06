package user;

import model.Point;
import model.Ship;
import model.ShipType;

public interface UserInterface {
    void printMaps();
    void notifyUser(String message);
    Point askUserForShot();
    Ship askUserForShip(ShipType type);
}
