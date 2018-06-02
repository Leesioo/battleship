package logic;

public class GameMasterRunnable implements Runnable {
    private GameMasterLogic gml;

    public GameMasterRunnable(GameMasterLogic gml) {
        this.gml = gml;
    }

    @Override
    public void run() {
        gml.askForUserShips();
        gml.generateComputerShips();
        while (gml.getWinner() == null) {
            gml.askForShoot();
        }
        gml.printWinner();
    }
}
