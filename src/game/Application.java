package game;


/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		GameSettings gameSettings = new GameSettings();
		NewWorld newWorld = gameSettings.setUpGame();
		newWorld.run();
	}
}
