package game;

public class CoinPouch extends PortableItem{
	
	private int coins = 0;
	
	public CoinPouch() {
		super("CoinPouch", 'p');
	}
	
	public CoinPouch(int coins) {
		super("CoinPouch", 'p');
		this.coins = coins;
	}
	
	public void addCoins(int coins) {
		this.coins += coins;
	}
	
	public void useCoins(int coins) throws IllegalArgumentException {
		if (coins > this.coins) {
			throw new IllegalArgumentException("Does not have enought coins.");
		}
		this.coins -= coins;
	}
	
	public int getCoins() {
		return coins;
	}

}
