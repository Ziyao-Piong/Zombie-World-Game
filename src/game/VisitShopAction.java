package game;

import java.util.ArrayList;
import java.util.HashMap;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class VisitShopAction extends Action {
	
	private HashMap<Item, Integer> catalogue = new HashMap<>();
	private CoinPouch pouch;
	private Display display;
	
	public VisitShopAction(CoinPouch pouch, Display display) {
		this.pouch = pouch;
		this.display = display;
		addGoods();
	}
	public void addGoods() {
		Item food = new Wheat();
		catalogue.put(food, 10);
	}
	
	public Item displayMenu(Actor actor) {
		int coinsInPouch = pouch.getCoins();
		display.println(actor + " has " + coinsInPouch + " coins in the pouch.");
		
		ArrayList<Character> chars = new ArrayList<>();
		for (char i='a'; i <= 'z'; i++) {
			chars.add(i);
		}
		HashMap<Character, Item> charToItem= new HashMap<>();
		for (Item item: catalogue.keySet()) {
			char c = chars.get(0);
			display.println(c + ". " + item.toString() + " , Price = " + catalogue.get(item));
			charToItem.put(c, item);
			chars.remove(0);
		}
		char c = chars.get(0);
		charToItem.put(c, null);
		display.println(c + ". " + actor + " leaves the shop");
		
		char input;
		do {
			input = display.readChar();
		} while (!charToItem.containsKey(input));
		
		return charToItem.get(input);
	}
	
	public String execute(Actor actor, GameMap map) {
		Item itemSelected = displayMenu(actor);
		String result = "";
		
		if (itemSelected == null) {
			result += actor + " leaves the shop without buying anything";
			return result;
		}
		int coinsInPouch = pouch.getCoins();
		int itemPrice = catalogue.get(itemSelected);
		
		if (coinsInPouch < itemPrice) {
			result += actor + " does not have enough coins to buy " + itemSelected;
			return result;
		} else {
			pouch.useCoins(itemPrice);
			actor.addItemToInventory(itemSelected);
			result += actor + " bought " + itemSelected + " with " + itemPrice + " coins.";
			return result;
		} 
	}
	
	public String menuDescription(Actor actor) {
		return actor + " visits shop";
	}

	
}
