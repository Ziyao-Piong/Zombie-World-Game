package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A type of upgradable weapon.
 * 
 * @author ziyaopiong
 *
 */
public class Arm extends UpgradableWeapon{
	
	// The weapon arm will become a ZombieClub if upgraded
	private WeaponItem upgradedWeapon = new ZombieClub();
	
	/**
	 * Constructor
	 */
	public Arm() {
		super("arm", 'A');
	}
	
	@Override
	public WeaponItem craftWeapon() {
		return upgradedWeapon;
	}

}
