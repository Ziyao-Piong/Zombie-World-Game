package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

public class VisitShopAction extends Action {
	
	private HashMap<Item, Integer> catalogue = new HashMap<>();
	
	public void addGoods() {
		Item crop = new Wheat();
		catalogue.put(crop, 10);
		
	}
	
	public String menuDescription(Actor actor) {
		return actor + " visits shop";
	}
}
