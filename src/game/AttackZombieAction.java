package game;

import java.util.*;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

public class AttackZombieAction extends AttackAction {
	
	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackZombieAction(Actor target) {
		super(target);
	}
	
	
	public String execute(Actor actor, GameMap map) {
		
		if (target instanceof Zombie) {
			Zombie zombieTarget = (Zombie) target;
			
			Weapon weapon = zombieTarget.getWeapon();

			if (rand.nextBoolean()) {
				return actor + " misses " + zombieTarget + ".";
			}

			int damage = weapon.damage();
			String result = actor + " " + weapon.verb() + " " + zombieTarget + " for " + damage + " damage.";

			zombieTarget.hurt(damage);
			String limb = zombieTarget.zombieIsAttacked();
			if (limb != null) {
				List<Exit> exits = map.locationOf(zombieTarget).getExits();
				Random rand = new Random();
				Location dropLocation = exits.get(rand.nextInt(exits.size())).getDestination();
				
				if (limb == "Arm") {
					if (zombieTarget.getArm() == 1 && Math.random() < 0.5) {
						List<Item> inventory = zombieTarget.getInventory();
						if (inventory.size() > 0) {
							inventory.get(0).getDropAction().execute(zombieTarget, map);
						}
					} else if (zombieTarget.getArm() == 0) {
						zombieTarget.dropWeapon(map);
					}
					dropLocation.addItem(new Arm());
				} else if (limb == "Leg") {
					dropLocation.addItem(new Leg());
				}
			}
			
			if (!zombieTarget.isConscious()) {
				targetIsDead(zombieTarget, map);
				result = System.lineSeparator() + zombieTarget + " is killed.";
			}
			return result;
		} 
		return null;
	}

	
	
}
