package game;

import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * A class that generate a pickUpItemAction if the item is 
 * portable and can be weaponized.
 * 
 * @author Peir Jing Chuang
 *
 */
public class PickUpWeaponBehaviour implements Behaviour {
	
	
	private Random random = new Random();
	
	
	/**
	 * Returns an pickUpItemAction if the item
	 * is portable and can be weaponized.
	 * 
	 * @param actor The actor performing the action
	 * @param map The map the actor is on.
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Item item: map.locationOf(actor).getItems()){
			if ((item.getPickUpAction() != null) && (item.asWeapon() != null)) {
				actions.add(item.getPickUpAction());
			}
		}
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}
		
		
	}
		
		
	

}
