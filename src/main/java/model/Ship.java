package model;

public class Ship {
    private final ShipType shipType;
    private final Orientation orientation;
    private final Point point;

    public Ship(ShipType shipType, Orientation orientation, Point point) {
        this.shipType = shipType;
        this.orientation = orientation;
        this.point = point;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Point getPoint() {
        return point;
    }

    public Point getEndPoint() {
        if (orientation == Orientation.HORIZONTAL) {
            return new Point(point.getX() + shipType.size().intValue() - 1, point.getY());
        } else {
            return new Point(point.getX(), point.getY() + shipType.size().intValue() - 1);
        }
    }
}
