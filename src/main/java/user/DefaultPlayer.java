package user;

import model.Point;
import model.Ship;
import model.ShipType;

public class DefaultPlayer implements Player {
    UserInterface userInterface;

    public DefaultPlayer(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public Ship getNextShip(ShipType shipType) {
        return null;
    }

    @Override
    public Point getNextShot() {
        return null;
    }

    @Override
    public void sendMessage(String message) {

    }
}
