package game;

import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * An immovable item that cannot be picked up or dropped and allows the 
 * player to move between maps.
 * 
 * @author ziyaopiong
 *
 */
public class Helicopter extends ImmovableItem {

	/**
	 * Constructor
	 */
	public Helicopter() {
		super("Helicopter", '^', false);
	}
	
	/**
	 * Set the destination of this helicopter.
	 * @param location	the location of the destination
	 * @param string	the direction of the destination
	 */
	public void setDestination(Location location, String string) {
		this.allowableActions.add(new MoveActorAction(location, string));
	}

}
