package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	
	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	/**
	 * If actor is a zombie, it will either use a weapon, bite or punch, where bite has a lower
	 * hit rate than the other two. If the actor is a human or player, a successful hit on the 
	 * zombie will have a possibility to cause the zombie to lose a limb.
	 * If target died, all the items it holds will be dropped onto the ground
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		
		if (actor.hasCapability(ZombieCapability.UNDEAD) && target.hasCapability(ZombieCapability.ALIVE)) {
			if (weapon == new IntrinsicWeapon(20, "bites")) {
				if (rand.nextDouble() < 0.75) {	// bite action has a lower hit rate
					return actor + "misses "+ target;
				} else {
					actor.heal(5); // a successful bite will restore 5 hp to the zombie
				}
			} else {
				if (rand.nextBoolean()) {
					return actor + " misses " + target;
				}
			}
		}	
		else if (actor.hasCapability(ZombieCapability.ALIVE) && target.hasCapability(ZombieCapability.UNDEAD)) {
			if (rand.nextDouble() < 0.4) {
				return actor + " misses " + target + ".";
			} 
			if (target.hasCapability(IdentityCapability.ZOMBIE)) {
				Zombie zombieTarget = (Zombie) target;
				
				String limb = zombieTarget.zombieIsAttacked();
				if (limb != null) {
					dropLimb(limb, zombieTarget, map);
				}
			}
		}
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage. ";
		
		target.hurt(damage);
		if (!target.isConscious()) {
			targetIsDead(target, map);
			result += System.lineSeparator() + target + " is killed.";
		}
		return result;
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
	private void targetIsDead(Actor actor, GameMap map) {

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
		return actor + " attacks " + target;
	}
}
