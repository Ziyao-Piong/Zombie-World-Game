package game;


/**
 * The main class for the zombie apocalypse game.
 */
public class Application {

	/**
	 * The starting point into the game. it sets up the game and run
	 * the game once.
	 * @param args	the static void main method
	 */
	public static void main(String[] args) {
		GameSettings gameSettings = new GameSettings();
		NewWorld newWorld = gameSettings.setUpGame();
		newWorld.run();
	}
}
