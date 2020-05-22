package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A type of limb which is also a weapon.
 * 
 * @author ziyaopiong
 *
 */
public class Arm extends Limb{
	
	private WeaponItem upgradedWeapon = new ZombieClub();

	public Arm() {
		super("arm", 'A');
	}
	
	@Override
	public WeaponItem craftWeapon() {
		return upgradedWeapon;
	}

}
