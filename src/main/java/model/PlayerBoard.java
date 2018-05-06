package model;

import java.util.*;

public class PlayerBoard {
    private final List<Ship> shipList;
    private final Map<Point, BoardField> map;

    public static class Builder {
        private List<Ship> builderShipList;
        private Map<Point, BoardField> builderMap;

        public Builder(Map<Point, BoardField> map, List<Ship> shipList) {
            this.builderShipList = new ArrayList<>(shipList);
            this.builderMap = new HashMap<>(map);
        }

        private Builder seaElement(Point p, BoardField field) {
            if (p == null) {
                throw new IllegalArgumentException("No such point");
            } else if (p.getX() < 1 || p.getX() > 10 || p.getY() < 1 || p.getY() > 10) {
                throw new IllegalArgumentException("Point outside board");
            }
            builderMap.put(p, field);
            return this;
        }

        private Builder addShip(Ship ship) {
            int startX = ship.getPoint().getX();
            int startY = ship.getPoint().getY();
            Long length = ship.getShipType().size();
            builderShipList.add(ship);
            if (ship.getOrientation() == Orientation.VERTICAL) {
                for (int i = 0; i < length; i++) {
                    builderMap.put(new Point(startX, startY+i), BoardField.SHIP);
                }
            } else {
                for (int i = 0; i < length; i++) {
                    builderMap.put(new Point(startX + i, startY), BoardField.SHIP);
                }
            }
            return this;
        }

        public PlayerBoard build() {
            return new PlayerBoard(this);
        }
    }

    private void Builder(Map<Point, BoardField> map, List<Ship> shipList) {
        Builder(map, shipList);
    }

    private PlayerBoard(Builder builder) {
        shipList = builder.builderShipList;
        map = builder.builderMap;
    }

    public static PlayerBoard fresh() {
        Map<Point, BoardField> tmpMap = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tmpMap.put(new Point(i,j), BoardField.WATER);
            }
        }
        List<Ship> tmpShips = new ArrayList<>();
        return new Builder(tmpMap, tmpShips).build();
    }

    public BoardField getMapElement(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("No such point");
        }
        return map.getOrDefault(point, BoardField.NONE);
    };

    public List<Ship> getShips() {
        return shipList;
    }

    public PlayerBoard updateMap(Point point, BoardField field) {
        return new Builder(map, shipList).seaElement(point, field).build();
    }

    public PlayerBoard addShip(Ship ship) {
        return new Builder(map, shipList).addShip(ship).build();
    }
}
