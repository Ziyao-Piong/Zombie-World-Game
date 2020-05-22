package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A type of limb which is also a weapon.
 * 
 * @author ziyaopiong
 *
 */
public class Leg extends Limb{
	
	private WeaponItem upgradedWeapon = new ZombieMace();
	
	public Leg() {
		super("leg", 'L');
	}
	
	@Override
	public WeaponItem craftWeapon() {
		return upgradedWeapon;
	}

}
