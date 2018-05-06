package validation;

import model.PlayerBoard;
import model.Point;
import model.Ship;

public class FrontOfShipOnTheMap implements Validator {
    @Override
    public boolean isValidate(Ship ship, PlayerBoard playerBoard) {
        Point point = ship.getPoint();
        if (point != null && point.getX() >= 1 && point.getX() <= 10 && point.getY() >= 1 && point.getY() <= 10) {
            return true;
        } else {
            return false;
        }
    }
}
