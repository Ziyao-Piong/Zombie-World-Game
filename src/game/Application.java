package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());

		List<String> compoundMap = Arrays.asList(
				"................................................................................",
				"................................................................................",
				"....................................##########..................................",
				"..........................###########........#####..............................",
				"............++...........##......................########.......................",
				"..............++++.......#..............................##......................",
				".............+++...+++...#...............................#......................",
				".........................##..............................##.....................",
				"..........................#...............................#.....................",
				".........................##...............................##....................",
				".........................#...............................##.....................",
				".........................###..............................##....................",
				"...........................####......................######.....................",
				"..............................#########.........####............................",
				"............+++.......................#.........#...............................",
				".............+++++....................#.........#...............................",
				"...............++........................................+++++..................",
				".............+++....................................++++++++....................",
				"............+++.......................................+++.......................",
				"................................................................................",
				".........................................................................++.....",
				"........................................................................++.++...",
				".........................................................................++++...",
				"..........................................................................++....",
				"................................................................................");
		GameMap compound = new GameMap(groundFactory, compoundMap);
		world.addGameMap(compound);
		
		List<String> townMap = Arrays.asList(
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................",
				"........................................");
		
		GameMap town = new GameMap(groundFactory, townMap);
		world.addGameMap(town);

		Actor player = new Player("Player", '@', 10000);
		world.addPlayer(player, compound.at(42, 15));

		// Place some random humans
		// actual codes are here.

		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));
		}

		String[] farmers ={"_Clem","_Jacob","_Winter"};
		for (String name : farmers) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Farmer(name));
		}


		// Place some random humans and farmers.
//		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
//				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
//		int x, y;
//		for (String name : humans) {
//			do {
//
//				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
//				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
//			}
//			while (gameMap.at(x, y).containsAnActor());
//			double z = Math.random() * 100;
//			if (z > 30) {
//				gameMap.at(x, y).addActor(new Human(name));
//			} else
//				gameMap.at(x, y).addActor(new Farmer(name));
//		}


		// place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());

		// FIXME: Add more zombies!
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));
		world.run();
	}
}
