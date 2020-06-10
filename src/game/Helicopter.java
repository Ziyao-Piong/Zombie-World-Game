package game;

import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class Helicopter extends ImmovableItem {


	public Helicopter() {
		super("Helicopter", '^', false);
	}

	public void setDestination(Location location, String string) {
		this.allowableActions.add(new MoveActorAction(location, string));
	}

}
