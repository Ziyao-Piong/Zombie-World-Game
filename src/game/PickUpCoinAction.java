package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class PickUpCoinAction extends Action {
	
	private ImmovableItem coinsDropped;
	private CoinPouch pouch;
	private int coins;
	
	public PickUpCoinAction(ImmovableItem coinsDropped, CoinPouch pouch, int coins) {
		this.coinsDropped = coinsDropped;
		this.pouch = pouch;
		this.coins = coins;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.getInventory().contains(pouch)) {
			pouch.addCoins(coins);
			map.locationOf(actor).removeItem(coinsDropped);
			return actor + " collected " + coins + " coins.";
		} else {
			return actor + " does not have a coin pouch to collect coins!";
		}
		
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + "picks up the coins";
	}

}
