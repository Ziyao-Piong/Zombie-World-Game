package game;

import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Returns a DoNothingAction in alternating turn if the zombie only has one leg,
 * or every turn if zombie has no leg
 *  
 * @author ziyaopiong
 *
 */
public class CrippledBehaviour implements Behaviour {
	
	private Zombie zombie;
	
	public Action getAction(Actor actor, GameMap map) {
		if (actor.hasCapability(ZombieCapability.UNDEAD)) {
			this.zombie = (Zombie) actor;
		}
		int leg = zombie.getLeg();
		if (leg == 0) {
			return new DoNothingAction();
		} 
		else if (leg == 1) {
			if (!zombie.getIsCrippled()) {
				zombie.negateIsCrippled();
				return new DoNothingAction();
			} 
			zombie.negateIsCrippled();
		} 
		return null;
	}

}
