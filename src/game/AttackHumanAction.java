package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Weapon;

public class AttackHumanAction extends AttackAction {
	

	public AttackHumanAction(Actor target) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Weapon weapon = actor.getWeapon();
		if (weapon == new IntrinsicWeapon(20, "bites")) {
			if (!(rand.nextDouble()<0.25)) {
				return actor + "misses "+ target;
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
				result = System.lineSeparator() + target + " is killed.";
			}
		return result;
		}		
	
	}


