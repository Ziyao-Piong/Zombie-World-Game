package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

public class ShootAction extends Action {
	
	private AmmunitionBag bag;
	private Gun gun;
	private Display display;
	private Random rand;
	
	public ShootAction(AmmunitionBag bag, Gun gun, Display display) {
		this.bag = bag;
		this.gun = gun;
		this.display = display;
	}
	
	private Exit displayMenu(Actor actor, GameMap map) {
		if (gun.hasCapability(GunCapability.SHOTGUN)) {
			int shotgunAmmo = bag.getShotgunAmmo();
			List<Exit> directions = map.locationOf(actor).getExits();
			
			display.println("Shotgun ammo: " + shotgunAmmo);
			
			ArrayList<Character> chars = new ArrayList<>();
			for (char i='a'; i <= 'z'; i++) {
				chars.add(i);
			}
			HashMap<Character, Exit> charToDirection= new HashMap<>();
			for (Exit direction: directions) {
				char c = chars.get(0);
				display.println(c + ". " + direction.getName());
				charToDirection.put(c, direction);
				chars.remove(0);
			}
			
			char input;
			do {
				input = display.readChar();
			} while (!charToDirection.containsKey(input));
			
			return charToDirection.get(input);
		}
		else {
			return null;
		}
	}
	
	public String execute(Actor actor, GameMap map) {
		Location direction = displayMenu(actor, map).getDestination();
		Location actorLocation = map.locationOf(actor);
		int damage = gun.damage();
		String result = "";
		
		if (gun.hasCapability(GunCapability.SHOTGUN)) {
			ArrayList<Location> attackArea = attackArea(direction, actorLocation, map);
			for (Location location: attackArea) {
				if (location.containsAnActor()) {
					System.out.println(location.toString());
					Actor target = location.getActor();
					
					if (rand.nextDouble() < 0.75) {
						System.out.println(2);
						if (target.hasCapability(IdentityCapability.ZOMBIE)) {
							System.out.println(3);
							Zombie zombieTarget = (Zombie) target;
							String limb = zombieTarget.zombieIsAttacked();
							if (limb != null) {
								dropLimb(limb, zombieTarget, map);
							}
						}
						bag.useShotgunAmmo();
						target.hurt(damage);
						result += actor + " " + gun.verb() + " " + target + " for " + damage + " damage. \n";
						if (!target.isConscious()) {
							targetIsDead(target, map);
							result += System.lineSeparator() + target + " is killed.";
						}
					} else {
						result += actor + " misses " + target + ".";
					}
				}
			}
		}
		if (result == "") {
			return actor + "did not hit anybody";
		}
		return result;
	}
	
	public ArrayList<Location> attackArea(Location direction, Location actorLocation, GameMap map) {
		int xDirection = direction.x();
		int yDirection = direction.y();
		int x = actorLocation.x();
		int y = actorLocation.y();
		
		
		if (xDirection > x && yDirection > y) { // North-east
			xDirection += 1;
			yDirection += 1;
		} 
		else if (xDirection > x && yDirection == y) { // East
			xDirection += 1;
		}
		else if (xDirection > x && yDirection < y) { // South-east
			xDirection += 1;
			yDirection -= 1;
		}
		else if (xDirection == x && yDirection < y) { // South
			yDirection -= 1;
		}
		else if (xDirection < x && yDirection < y) { // South-west
			xDirection -= 1;
			yDirection -= 1;
		}
		else if (xDirection < x && yDirection == y) { // West
			xDirection -= 1;
		}
		else if (xDirection < x && yDirection > y) { // North-west
			xDirection -= 1;
			yDirection += 1;
		}
		else if (xDirection == x && yDirection > y) { // North
			yDirection += 1;
		}
		
		Location centerOfAttack = map.at(xDirection, yDirection);		
		ArrayList<Location> attackArea = new ArrayList<>();
		attackArea.add(centerOfAttack);
		
		List<Exit> area = centerOfAttack.getExits();
		for (Exit exit: area) {
			attackArea.add(exit.getDestination());
		}
		return attackArea;
	}
	
	/**
	 * Place the limb dropped by the zombie on random location adjacent to the zombie.
	 * Zombie might drop one item it's holding if it loses one arm and has another arm
	 * left attached, and it will drop all the item it's holding if it loses both arms.
	 * @param limb	the limb dropped by the zombie
	 * @param zombieTarget	the zombie	
	 * @param map	the game map
	 */
	private void dropLimb(String limb, Zombie zombieTarget, GameMap map) {
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(zombieTarget).getExits());
		Location dropLocation = exits.get(rand.nextInt(exits.size())).getDestination();
		
		if (limb == "Arm") {
			List<Item> inventory = zombieTarget.getInventory();
			if (zombieTarget.getArm() == 1 && rand.nextDouble() < 0.5) {
				for (Item item: inventory) {
					if (item instanceof Weapon) {
						item.getDropAction().execute(zombieTarget, map);
						break;
					}
				}
			} else if (zombieTarget.getArm() == 0) {
				for (Item item: inventory) {
					if (item instanceof Weapon) {
						item.getDropAction().execute(zombieTarget, map);
					}
				}
			}
			dropLocation.addItem(new Arm());
		} else if (limb == "Leg") {
			dropLocation.addItem(new Leg());
		}
	}

	/**
	 * Remove the actor, drop everything the actor is holding 
	 * and creates a corpse when the Zombie is dead and creates a HumanCorpse when Human is dead.
	 * @param actor	the actor that is dead
	 * @param map	the map the actor is on
	 */
	private void targetIsDead(Actor target, GameMap map) {

		if (target.hasCapability(ZombieCapability.ALIVE)){
			//This is used to create a zombie when target dies.(Only for Humans though)
			Item humanCorpse = new HumanCorpse(target.toString());
			map.locationOf(target).addItem(humanCorpse);
		}

		dropAllItem(target, map);
		map.removeActor(target);
	}
	
	/**
	 * Drop all the items this actor currently has in its inventory
	 * @param actor	the actor 
	 * @param map	the map where the actor is on
	 */
	private void dropAllItem(Actor actor, GameMap map) {
		Actions dropActions = new Actions();
		for (Item item : actor.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)		
			drop.execute(actor, map);
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " uses " + gun.toString();
	}

}

















