package game;

/**
 * An immovable item that collects coins for the player,
 *  
 * @author ziyaopiong
 *
 */
public class CoinPouch extends ImmovableItem{
	
	private int coins = 0;
	
	/**
	 * Constructor, it cannot be dropped by the player.
	 */
	public CoinPouch() {
		super("CoinPouch", 'p', false);
	}
	
	/**
	 * Constructor
	 * @param coins	the amount of coins this pouch initially has
	 */
	public CoinPouch(int coins) {
		super("CoinPouch", 'p', false);
		this.coins = coins;
	}
	
	/**
	 * Add coins to this pouch
	 * @param coins	the amount of coins to be added to this pouch
	 */
	public void addCoins(int coins) {
		this.coins += coins;
	}
	
	/**
	 * Deduct the coins used from the pouch
	 * @param coins	the number of coins used
	 * @throws IllegalArgumentException	when the coins used if greater than the number of
	 * 									coins this pouch has
	 */
	public void useCoins(int coins) throws IllegalArgumentException {
		if (coins > this.coins) {
			throw new IllegalArgumentException("Does not have enought coins.");
		}
		this.coins -= coins;
	}
	
	/**
	 * Return the number of coins this pouch has.
	 * @return	the number of coins this pouch has
	 */
	public int getCoins() {
		return coins;
	}

}
