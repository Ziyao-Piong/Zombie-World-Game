package game;

import java.util.*;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

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
				// The limb can be dropped around the zombie.
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
			
			if (!zombieTarget.isConscious()) {
				targetIsDead(zombieTarget, map);
				result += System.lineSeparator() + zombieTarget + " is killed.";
			}
			return result;
		} 
		return null;
	}

	
	
}
