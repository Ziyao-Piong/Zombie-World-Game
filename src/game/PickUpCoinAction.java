package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An action that adds the coins on the ground to the player's coin pouch then
 * remove the coins from the map.
 * 
 * @author ziyaopiong
 *
 */
public class PickUpCoinAction extends Action {
	
	private ImmovableItem coinsDropped;
	private CoinPouch pouch;
	private int coins;
	
	/**
	 * Constructor
	 * @param coinsDropped	the item contains coins
	 * @param pouch			the coin pouch of the player
	 * @param coins			the number of coins this item contains
	 */
	public PickUpCoinAction(ImmovableItem coinsDropped, CoinPouch pouch, int coins) {
		this.coinsDropped = coinsDropped;
		this.pouch = pouch;
		this.coins = coins;
	}
	
	/**
	 * If the player has a coin pouch, it will add the number of coins this item 
	 * contains into the pouch, then remove the coins item from the map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.getInventory().contains(pouch)) {
			pouch.addCoins(coins);
			map.locationOf(actor).removeItem(coinsDropped);
			return actor + " collected " + coins + " coins";
		} else {
			return actor + " does not have a coin pouch to collect coins!";
		}
		
	}
	
	/**
	 * A string indicates player to collect coins.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " collects " + coins + " coins";
	}

}
