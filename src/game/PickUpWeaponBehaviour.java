package game;

import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class PickUpWeaponBehaviour implements Behaviour {
	
	private Random random = new Random();
	
	
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
