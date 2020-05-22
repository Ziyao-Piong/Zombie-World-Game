package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * An abstract class for zombie's limb which is also a type of weapon.
 * 
 * @author ziyaopiong
 *
 */
public abstract class Limb extends WeaponItem{
	
	/**
	 * Constructor for limb so that all limbs will have a damage of 20, 
	 * and can perform CraftWeaponAction on them.
	 */
	public Limb(String name, char displayChar) {
		super(name, displayChar, 20, "hits");
		allowableActions.add(new CraftWeaponAction(this));
	}
	
	/**
	 * Craft limb into a superior weapon.
	 * @return	an upgraded weapon to this limb
	 */
	public abstract WeaponItem craftWeapon();

}
