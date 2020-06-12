package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * A ground where a helicopter lands, holds a list of keys required to enter 
 * the helicopter, will move the player to the location of this helipad if 
 * the player has the corresponding key to unlock the helicopter.
 * 
 * @author ziyaopiong
 *
 */
public class Helipad extends Ground {
	
	private Actor player;
	private List<Item> keys = new ArrayList<>();
	
	/**
	 * Constructor
	 */
	public Helipad() {
		super('*');
	}
	
	/**
	 * constructor
	 * @param player the player that is allowed to enter this helipad
	 */
	public Helipad(Actor player) {
		super('*');
		this.player = player;
	}
	
	/**
	 * Add the key to the key list.
	 * @param key the key to be added to the list
	 */
	public void addKey(Item key) {
		keys.add(key);
	}
	
	/**
	 * Getter for the key list.
	 * @return an unmodifiablelist of keys
	 */
	public List<Item> getKey() {
		return Collections.unmodifiableList(keys);
	}
	
	/**
	 * Only the Player is allowed to enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor == player) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Helipad blocks any thrown objects.
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
	
	/**
	 * Return EnterVehicleAction as its allowable action.
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return new Actions(new EnterVehicleAction(this, location));
	}

}
