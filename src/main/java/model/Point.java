package model;

import java.util.Objects;

public class Point {
    private final Integer x;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(x, point.x) &&
                Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    private final Integer y;

    public Point(Integer x, Integer y) {
        if (x == null || x < 0 || y == null || y < 0) {
           throw new IllegalArgumentException("Only positive values");
        }
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        if (p == null) {
            throw new IllegalArgumentException("Point must be provided");
        }
        if (p.getX() == null || p.getX() < 0 || p.getY() == null || p.getY() < 0) {
            throw new IllegalArgumentException("Only positive values");
        }
        this.x = p.getX();
        this.y = p.getY();
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
