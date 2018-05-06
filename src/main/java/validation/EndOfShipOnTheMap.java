package validation;

import model.Orientation;
import model.PlayerBoard;
import model.Point;
import model.Ship;

public class EndOfShipOnTheMap implements Validator {
    @Override
    public boolean isValidate(Ship ship, PlayerBoard playerBoard) {
        Point point = ship.getEndPoint();
        if (point != null && point.getX() >= 1 && point.getX() <= 10 && point.getY() >= 1 && point.getY() <= 10) {
            return true;
        } else {
            return false;
        }
    }
}
