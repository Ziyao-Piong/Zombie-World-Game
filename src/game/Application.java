package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;


/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		GameSettings gameSettings = new GameSettings();
		//World world = gameSettings.setUpGame();
		NewWorld newWorld = gameSettings.setUpGame();
		//NewWorld newWorld = new NewWorld(new Display());
		newWorld.run();
		//world.run();
	}
}
