package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * An abstract class for zombie's limb which is also a type of weapon.
 * 
 * @author ziyaopiong
 *
 */
public abstract class UpgradableWeapon extends WeaponItem{
	
	/**
	 * Constructor for upgradable weapon so that all of its instances
	 * will have a damage of 20, and can perform CraftWeaponAction on them.
	 * @param name the name of the weapon
	 * @param displayChar the display character of the weapon
	 */
	public UpgradableWeapon(String name, char displayChar) {
		super(name, displayChar, 20, "hits");
		allowableActions.add(new CraftWeaponAction(this));
	}
	
	/**
	 * Craft the weapon into a superior weapon.
	 * @return	an upgraded weapon to this weapon
	 */
	public abstract WeaponItem craftWeapon();

}
