package user;

import model.Point;
import model.Ship;
import model.ShipType;

public interface Player {
    Ship getNextShip(ShipType shipType);
    Point getNextShot();
    void sendMessage(String message);
}
