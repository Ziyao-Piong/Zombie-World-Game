package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Weapon;
/**
 * An Action that allows the Actor to attack.
 */
public class AttackHumanAction extends AttackAction {
	
	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackHumanAction(Actor target) {
		super(target);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Allow the Actor to attack target.
	 *
	 * Overrides Action.execute()
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of the Action suitable for the menu
	 */

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Weapon weapon = actor.getWeapon();
		if (weapon == new IntrinsicWeapon(20, "bites")) {
			if (!(rand.nextDouble()<0.25)) {
				return actor + "misses "+ target;
			}	
			else {
				actor.heal(5);
			}
		}
		if (rand.nextBoolean()) {
			return actor + " misses " + target;
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
	
	}


