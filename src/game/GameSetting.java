package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

public class GameSetting {
	
	private World world;
	private GameMap compound;
	private GameMap town;
	private FancyGroundFactory groundFactory;
	private Player player;
	private Location compoundVehicleLocation;
	private Location townVehicleLocation;
	
	public GameSetting() {
		this.world = new World(new Display());
		groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
	
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
		
		this.compound = new GameMap(groundFactory, compoundMap);
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
		
		this.town = new GameMap(groundFactory, townMap);
		world.addGameMap(town);
	
		this.player = new Player("Player", '@', 10000);
		world.addPlayer(player, compound.at(42, 15));
	}

	
	public void setUpHumanAndZombie() {
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (compound.at(x, y).containsAnActor());
			compound.at(x,  y).addActor(new Human(name));
		}
	
		String[] farmers ={"_Clem","_Jacob","_Winter"};
		for (String name : farmers) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (compound.at(x, y).containsAnActor());
			compound.at(x,  y).addActor(new Farmer(name));
		}
		
		compound.at(30, 20).addActor(new Zombie("Groan"));
		compound.at(30,  18).addActor(new Zombie("Boo"));
		compound.at(10,  4).addActor(new Zombie("Uuuurgh"));
		compound.at(50, 18).addActor(new Zombie("Mortalis"));
		compound.at(1, 10).addActor(new Zombie("Gaaaah"));
		compound.at(62, 12).addActor(new Zombie("Aaargh"));
	}
	
	
	public void setUpVehicles() {
		compoundVehicleLocation = compound.at(24, 1);
		townVehicleLocation = town.at(1, 1);
		compound.at(compoundVehicleLocation.x(), compoundVehicleLocation.y()).setGround(new Helipad(player));
		town.at(townVehicleLocation.x(), townVehicleLocation.y()).setGround(new Helipad(player));
		
		Helicopter compoundVehicle = new Helicopter("Helicopter", '^');
		compoundVehicle.setDestination(townVehicleLocation, "to town");
		
		Helicopter townVehicle = new Helicopter("Helicopter", '^');
		townVehicle.setDestination(compoundVehicleLocation, "to compound");
		
	}

	public World setUpGame() {
		setUpHumanAndZombie();
		return world;
	}

}
