package validation;

import model.*;

public class ShipsAreSideBySide implements Validator {
    PlayerBoard board;

    @Override
    public boolean isValidate(Ship ship, PlayerBoard playerBoard) {
        board = playerBoard;
        int length = ship.getShipType().size().intValue();
        boolean result = true;

        for (int i = 0; i < length; i++) {
            if (!result) {
                return false;
            }
            Point point;
            if (ship.getOrientation() == Orientation.HORIZONTAL) {
                point = new Point(ship.getPoint().getX() + i, ship.getPoint().getY());
            } else {
                point = new Point(ship.getPoint().getX(), ship.getPoint().getY() + i);
            }
            result = checkPoint(point);
        }
        return result;
    }

    private boolean checkPoint(Point point) {
        if (board.getMapElement(point) == BoardField.SHIP) return false;
        if (board.getMapElement(new Point(point.getX()-1, point.getY())) == BoardField.SHIP) return false;
        if (board.getMapElement(new Point(point.getX()-1, point.getY()-1)) == BoardField.SHIP) return false;
        if (board.getMapElement(new Point(point.getX()-1, point.getY()+1)) == BoardField.SHIP) return false;
        if (board.getMapElement(new Point(point.getX()+1, point.getY())) == BoardField.SHIP) return false;
        if (board.getMapElement(new Point(point.getX()+1, point.getY()-1)) == BoardField.SHIP) return false;
        if (board.getMapElement(new Point(point.getX()+1, point.getY()+1)) == BoardField.SHIP) return false;
        if (board.getMapElement(new Point(point.getX(), point.getY()-1)) == BoardField.SHIP) return false;
        if (board.getMapElement(new Point(point.getX(), point.getY()+1)) == BoardField.SHIP) return false;
        return true;
    }
}
