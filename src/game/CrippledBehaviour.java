package game;

import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class CrippledBehaviour implements Behaviour {
	
	Zombie zombie;
	
	public Action getAction(Actor actor, GameMap map) {
		if (actor instanceof Zombie) {
			zombie = (Zombie) actor;
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
		} 
		return null;
	}

}
