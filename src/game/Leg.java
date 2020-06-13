package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A type of upgradable weapon.
 * 
 * @author ziyaopiong
 *
 */
public class Leg extends UpgradableWeapon{
	
	//The weapon leg will become a ZombieMace if upgraded
	private WeaponItem upgradedWeapon = new ZombieMace();
	
	/**
	 * Constructor
	 */
	public Leg() {
		super("leg", 'L');
	}
	
	@Override
	public WeaponItem craftWeapon() {
		return upgradedWeapon;
	}

}
