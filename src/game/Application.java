package game;

import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		GameSetting gameSetting = new GameSetting();
		World world = gameSetting.setUpGame();
		world.run();
	}
}
