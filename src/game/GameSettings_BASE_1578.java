package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class GameSettings {

	private Display display;
	private NewWorld newWorld;
	private GameMap compound;
	private GameMap town;
	private FancyGroundFactory groundFactory;
	private Player player;
	private Location compoundVehicleLocation;
	private Location townVehicleLocation;
	private Helipad helipad = new Helipad();
	private List<PortableItem> keyList = new ArrayList<>();
	private List<Zombie> zombieList = new ArrayList<>();
	private Random rand = new Random();
	
	
	
	public GameSettings() {
		display = new Display();
		this.newWorld = new NewWorld(display);
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
		newWorld.addGameMap(compound);
		
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
		newWorld.addGameMap(town);
	
		this.player = new Player("Player", '@', 10000);
		newWorld.addPlayer(player, compound.at(42, 15));
	}
	
	private void setUpVehicles() {
		compoundVehicleLocation = compound.at(2, 23);
		townVehicleLocation = town.at(1, 1);
		
		Helicopter compoundVehicle = new Helicopter();
		Helicopter townVehicle = new Helicopter();
		compoundVehicle.setDestination(townVehicleLocation, "to town");
		townVehicle.setDestination(compoundVehicleLocation, "to compound");
		
		compoundVehicleLocation.setGround(helipad);
		compoundVehicleLocation.addItem(compoundVehicle);
		townVehicleLocation.setGround(helipad);
		townVehicleLocation.addItem(townVehicle);
		
		PortableItem key1 = new PortableItem("key1", 'k');
		PortableItem key2 = new PortableItem("key2", 'k');
		keyList.add(key1);
		keyList.add(key2);
		helipad.addKey(key1);
		helipad.addKey(key2);
	}
	
	private void setUpCompoundHumans() {
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
	
		String[] farmers ={"_Clem","_Jacob"};
		for (String name : farmers) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (compound.at(x, y).containsAnActor());
			compound.at(x,  y).addActor(new Farmer(name));
		}
	}
	
	private void setUpCompoundZombies() {
		Zombie zombie1 = new Zombie("Groan");
		Zombie zombie2 = new Zombie("Boo");
		Zombie zombie3 = new Zombie("Uuuurgh");
		Zombie zombie4 = new Zombie("Mortalis");
		Zombie zombie5 = new Zombie("Gaaaah");
		Zombie zombie6 = new Zombie("Aaargh");
		Zombie zombie7 = new Zombie("Baaahhh");
		Zombie zombie8 = new Zombie("Yeeeeet");
		
		compound.at(30, 20).addActor(zombie1);
		compound.at(30,  18).addActor(zombie2);
		compound.at(10,  4).addActor(zombie3);
		compound.at(50, 18).addActor(zombie4);
		compound.at(1, 10).addActor(zombie5);
		compound.at(62, 12).addActor(zombie6);
		compound.at(70, 5).addActor(zombie7);
		compound.at(5, 6).addActor(zombie8);
		
		zombieList.add(zombie1);
		zombieList.add(zombie2);
		zombieList.add(zombie3);
		zombieList.add(zombie4);
		zombieList.add(zombie5);
		zombieList.add(zombie6);
		zombieList.add(zombie7);
		zombieList.add(zombie8);
	}
	
	private void setUpTownZombies() {
		town.at(3, 12).addActor(new Zombie("Blurrrr"));
		town.at(18, 9).addActor(new Zombie("Tehehehe"));
		town.at(31, 5).addActor(new Zombie("Daggggg"));
		town.at(14, 13).addActor(new Zombie("Lurgh"));
		town.at(23, 1).addActor(new Zombie("Raaaa"));
		town.at(33, 9).addActor(new Zombie("Wohoooooo"));
	}
	
	private void setUpKeys() {
		ArrayList<Zombie> zombies = new ArrayList<>(zombieList);
		int numberOfKeys = keyList.size();
		for (int i = 0; i < numberOfKeys; i++) {
			Zombie zombie = zombies.get(rand.nextInt(zombies.size()));
			zombie.addItemToInventory(keyList.get(i));
			zombies.remove(zombie);
		}
	}
	
	private void setUpMerchant() {
		CoinPouch pouch = new CoinPouch();
		player.addItemToInventory(pouch);
		
		for (Zombie zombie: zombieList) {
			ImmovableItem coin = new ImmovableItem("coin", '$', true);
			coin.addAction(new PickUpCoinAction(coin, pouch, 5 + rand.nextInt(4)));
			zombie.addItemToInventory(coin);
		}
		ImmovableItem shop = new ImmovableItem("Shop", '&', false);
		shop.addAction(new VisitShopAction(pouch, display));
		compound.at(76, 2).addItem(shop);
		town.at(38, 1).addItem(shop);
	}
	
	
	public NewWorld setUpGame() {
		setUpVehicles();
		setUpCompoundHumans();
		setUpCompoundZombies();
		setUpTownZombies();
		setUpKeys();
		setUpMerchant();
		return newWorld;
	}
}



