package game;

import java.util.ArrayList;
import java.util.HashMap;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An action when the player visits the shop, it displays a submenu with a list
 * of items with their corresponding price, player can use the coins in their
 * coin pouch to purchase the item listed in the shop if the player has enough
 * coins.
 * 
 * @author ziyaopiong
 *
 */
public class VisitShopAction extends Action {
	
	private HashMap<Item, Integer> catalogue = new HashMap<>();
	private CoinPouch pouch;
	private AmmunitionBag bag;
	private Display display;
	
	/**
	 * Constructor, it will print a submenu of items with prices.
	 * 
	 * @param pouch		the coin pouch of the player
	 * @param bag		the ammunition bag of the player
	 * @param display	the display
	 */
	public VisitShopAction(CoinPouch pouch, AmmunitionBag bag, Display display) {
		this.pouch = pouch;
		this.display = display;
		addGoods();
	}
	
	/**
	 * Add these items to the submenu 
	 */
	public void addGoods() {
		Item food = new Wheat();
		catalogue.put(food, 10);
		
		Ammo shotgunAmmo = new ShotgunAmmo(bag);
		catalogue.put(shotgunAmmo, 10);
		
		Ammo sniperAmmo = new SniperAmmo(bag);
		catalogue.put(sniperAmmo, 10);
	}
	
	/**
	 * Display the items available in this shop and get an input from 
	 * the player 
	 * 
	 * @param actor	the player
	 * @return		the item the player has chosen to buy of player quits
	 */
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
	
	/**
	 * First display the submenu and get an input from the user, if the player chose
	 * to leaves the shop without buying anything, it will return a string indicating
	 * the decision of the player. If the player has sufficient coins and he chose to
	 * buy something from the shop, it will deduct the coins from the pouch and add the
	 * item to the player's inventory.
	 */
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
