package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Set up the game 
 * @author ziyaopiong
 *
 */
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
	protected List<Zombie> zombieList = new ArrayList<>();
	private Random rand = new Random();
	private AmmunitionBag bag;
	private CoinPouch pouch;
	
	/**
	 * Constructor, also set up the compound map and town map, then add both map into new world,
	 * then, it will create a player and add it to the compound map and add a quit button to the 
	 * player's inventory. 
	 */
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
	
		this.player = new Player("Player", '@', 1000);
		newWorld.addPlayer(player, compound.at(42, 15));
		ImmovableItem quitButton = new ImmovableItem("Quit Button",'q',false);
		quitButton.addAction(new TerminateAction(newWorld));
		player.addItemToInventory(quitButton);
	}
	
	/**
	 * Set up the helicopters and helipad in the compound map and the town map.
	 * A set of keys is then created and added to the helipad.
	 */
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
	
	/**
	 * Create a number of humans and farmers and randomly place them in the 
	 * area bounded by fence.
	 */
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
	
	/**
	 * Create a number of zombies and put them in compound map.
	 */
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
	
	/**
	 * Create a number of zombies and place them in town map.
	 */
	private void setUpTownZombies() {
		Zombie zombie1 = new Zombie("Blurrrr");
		Zombie zombie2 = new Zombie("Tehehehe");
		Zombie zombie3 = new Zombie("Daggggg");
		Zombie zombie4 = new Zombie("Lurgh");
		Zombie zombie5 = new Zombie("Raaaa");
		Zombie zombie6 = new Zombie("Wohoooooo");
		
		town.at(3, 12).addActor(zombie1);
		town.at(18, 9).addActor(zombie2);
		town.at(31, 5).addActor(zombie3);
		town.at(14, 13).addActor(zombie4);
		town.at(23, 1).addActor(zombie5);
		town.at(33, 9).addActor(zombie6);
		
		zombieList.add(zombie1);
		zombieList.add(zombie2);
		zombieList.add(zombie3);
		zombieList.add(zombie4);
		zombieList.add(zombie5);
		zombieList.add(zombie6);
	}
	
	/**
	 * Randomly place the keys into the inventory of selected zombies.
	 */
	private void setUpKeys() {
		ArrayList<Zombie> zombies = new ArrayList<>(zombieList);
		int numberOfKeys = keyList.size();
		for (int i = 0; i < numberOfKeys; i++) {
			Zombie zombie = zombies.get(rand.nextInt(zombies.size()));
			zombie.addItemToInventory(keyList.get(i));
			zombies.remove(zombie);
		}
		zombieList.get(3).addItemToInventory(keyList.get(0));
	}
	
	/**
	 * Create a coin pouch and add it into the player's inventory, then for each 
	 * zombie, a coin that has PickUpCoinAction is added to their inventory. Then,
	 * create a shop and place it in the compound map and the town map.
	 */
	private void setUpMerchant() {
		pouch = new CoinPouch();
		player.addItemToInventory(pouch);
		
		for (Zombie zombie: zombieList) {
			ImmovableItem coin = new ImmovableItem("coin", '$', true);
			coin.addAction(new PickUpCoinAction(coin, pouch, 5 + rand.nextInt(4)));
			zombie.addItemToInventory(coin);
		}
		ImmovableItem shop = new ImmovableItem("Shop", '&', false);
		shop.addAction(new VisitShopAction(pouch, bag, display));
		compound.at(43, 20).addItem(shop);
		town.at(38, 1).addItem(shop);
	}
	
	/**
	 * Create a number of shotgun ammo and sniper ammo and place them in 
	 * the compound map and the town map.
	 */
	private void setUpWeapons() {
		bag = new AmmunitionBag();
		player.addItemToInventory(bag);

		town.at(13, 16).addItem(new ShotgunAmmo(bag));
		town.at(2, 9).addItem(new ShotgunAmmo(bag));
		town.at(33, 10).addItem(new ShotgunAmmo(bag));
		town.at(25, 2).addItem(new ShotgunAmmo(bag));

		town.at(13, 16).addItem(new SniperAmmo(bag));
		town.at(2, 9).addItem(new SniperAmmo(bag));
		town.at(33, 10).addItem(new SniperAmmo(bag));
		town.at(25, 2).addItem(new SniperAmmo(bag));

		compound.at(42, 16).addItem(new ShotgunAmmo(bag));
		compound.at(79, 0).addItem(new ShotgunAmmo(bag));
		compound.at(79, 24).addItem(new ShotgunAmmo(bag));
		compound.at(0, 0).addItem(new ShotgunAmmo(bag));

		compound.at(42, 16).addItem(new SniperAmmo(bag));
		compound.at(42, 9).addItem(new SniperAmmo(bag));
		compound.at(0, 15).addItem(new SniperAmmo(bag));
		compound.at(79, 15).addItem(new SniperAmmo(bag));
	}

	/**
	 * Set up the game
	 * @return	a NewWorld
	 */
	public NewWorld setUpGame() {
		setUpVehicles();
		setUpCompoundHumans();
		setUpCompoundZombies();
		setUpTownZombies();
		setUpKeys();
		setUpWeapons();
		setUpMerchant();
		return newWorld;
	}
}



