package game;

import java.util.*;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

/**
 * An action for other actors to attack zombie. 
 * This action also handles the consequences of a successful hit on the zombie such as
 * the limbs dropped by the zombie will land around it.
 * @author ziyaopiong
 *
 */
public class AttackZombieAction extends AttackAction {
	
	private Random rand = new Random();
	
	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackZombieAction(Actor target) {
		super(target);
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if (target.hasCapability(ZombieCapability.UNDEAD)) {
			Zombie zombieTarget = (Zombie) target;
			
			Weapon weapon = actor.getWeapon();

			if (rand.nextBoolean()) {
				return actor + " misses " + zombieTarget + ".";
			}

			int damage = weapon.damage();
			String result = actor + " " + weapon.verb() + " " + zombieTarget + " for " + damage + " damage.";

			zombieTarget.hurt(damage);
			String limb = zombieTarget.zombieIsAttacked();
			
			if (limb != null) {
				dropLimbs(limb, zombieTarget, map);
			}
			
			if (!zombieTarget.isConscious()) {
				targetIsDead(zombieTarget, map);
				result += System.lineSeparator() + zombieTarget + " is killed.";
			}
			return result;
		} 
		return null;
	}
	
	/**
	 * Drop the limbs at the location adjacent to the zombie with a probability
	 * to drop the weapon it's holding if the limb is an arm.
	 * @param limb			a string represent the limb to be dropped
	 * @param zombieTarget	the zombie which is attacked
	 * @param map			the game map the zombie is on
	 */
	private void dropLimbs(String limb, Zombie zombieTarget, GameMap map) {
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(zombieTarget).getExits());
		Location dropLocation = exits.get(rand.nextInt(exits.size())).getDestination();
		
		if (limb == "Arm") {
			if (zombieTarget.getArm() == 1 && rand.nextDouble() < 0.5) {
				List<Item> inventory = zombieTarget.getInventory();
				if (inventory.size() > 0) {
					inventory.get(0).getDropAction().execute(zombieTarget, map);
				}
			} else if (zombieTarget.getArm() == 0) {
				dropWeapon(zombieTarget, map);
			}
			dropLocation.addItem(new Arm());
		} else if (limb == "Leg") {
			dropLocation.addItem(new Leg());
		}
	}
	
}
