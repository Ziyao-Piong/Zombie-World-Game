package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action that allows Actors to spawn zombies.
 */
public class SpawnZombieAction extends Action {
	
	/**
	 * Spawn zombies at random location on compound map.
	 *
	 * @param actor The actor performing the action
	 * @param map The map the actor is on
	 * @return a description of the action suitable for feedback in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int i;
		for (i=1; i<6; i++) {
			int x, y;
			do {
				x = (int)Math.floor(Math.random() * map.getXRange().max()+1);
				y = (int)Math.floor(Math.random() * map.getYRange().max()+1);
			}
			while (map.at(x, y).containsAnActor());
			map.at(x,  y).addActor(new Zombie("SpawnedZombie" + Integer.toString(i)));
		
		}
		return menuDescription(actor);
	}

	/**
	 * A string describing the action suitable for displaying in the UI menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Mambo Marie has spawned 5 zombies"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " has spwaned 5 zombies.";
	}
	

}
