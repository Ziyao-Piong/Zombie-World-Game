package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Vehicle extends Ground {
	
	private Actor player;
	private List<Item> keys = new ArrayList<>();
	
	public Vehicle() {
		super('*');
	}
	
	
	public Vehicle(Actor player) {
		super('*');
		this.player = player;
	}
	
	public void addKey(Item key) {
		keys.add(key);
	}
	
	public List<Item> getKey() {
		return Collections.unmodifiableList(keys);
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor == player) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return new Actions(new OpenCarAction(this, location));
	}

}
