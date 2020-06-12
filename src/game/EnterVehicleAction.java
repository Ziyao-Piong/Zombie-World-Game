package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * An action that checks if the player has the key required to unlock the vehicle,
 * and move the actor to the location of the vehicle if the player has the key.
 * 
 * @author ziyaopiong
 *
 */
public class EnterVehicleAction extends Action {
	
	private Helipad parking;
	private Location location;
	
	/**
	 * Constructor
	 * @param parking	the helipad where the helicopter is on
	 * @param location	location of the helipad
	 */
	public EnterVehicleAction(Helipad parking, Location location) {
		this.parking = parking;
		this.location = location;
	}
	
	/**
	 * Checks if the actor has the keys required
	 * @param actor	the actor
	 * @return	a boolean indicates if this player has the key or not
	 */
	private boolean hasKey(Actor actor) {
		List<Item> keys = parking.getKey();
		for (Item key: keys) {
			if (actor.getInventory().contains(key)) {
				return true;
			}
		} return false;
		
	}
	
	/**
	 * Checks if the player has the key, if the player has the key,
	 * move the player to the location of the helipad.
	 */
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
	
	/**
	 * A string indicates the option for the player to unlock the helicopter.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " unlocks the helicopter";
	}

}
