package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class Helicopter extends Item {
	
	
	public Helicopter(String name, char displayChar) {
		super(name, displayChar, false);
	}

	public void setDestination(Location location, String string) {
		this.allowableActions.add(new MoveActorAction(location, string));
	}

}
