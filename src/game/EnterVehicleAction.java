package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class EnterVehicleAction extends Action {
	
	private Helipad parking;
	private Location location;
	
	public EnterVehicleAction(Helipad parking, Location location) {
		this.parking = parking;
		this.location = location;
	}
	
	private boolean hasKey(Actor actor) {
		List<Item> keys = parking.getKey();
		for (Item key: keys) {
			if (actor.getInventory().contains(key)) {
				return true;
			}
		} return false;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		if (hasKey(actor)) {
			(new MoveActorAction(location, "")).execute(actor, map); //Move the player onto the location of the car
			 result += menuDescription(actor);
		} else {
			result += actor + " does not have the correct key!";
		}
		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " unlocks the helicopter";
	}

}
