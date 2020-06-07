package game;

import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		GameSettings gameSettings = new GameSettings();
		World world = gameSettings.setUpGame();
		world.run();
	}
}
