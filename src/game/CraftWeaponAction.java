package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Craft a primitive weapon such as arm and leg into a superior weapon
 * only if the weapon is an upgradable weapon.
 * 
 * @author ziyaopiong
 *
 */
public class CraftWeaponAction extends Action {
	
	private UpgradableWeapon oldWeapon;
	
	/**
	 * Constructor
	 * @param oldWeapon	the weapon prior to upgrade
	 */
	public CraftWeaponAction(UpgradableWeapon oldWeapon) {
		this.oldWeapon = oldWeapon;
	}
	
	/**
	 * Remove the old weapon from the inventory and add the upgraded weapon into 
	 * the inventory.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(oldWeapon);
		actor.addItemToInventory(oldWeapon.craftWeapon());
		return actor + " crafted " + oldWeapon + " into " + oldWeapon.craftWeapon();
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return "Craft " + oldWeapon + " into " + oldWeapon.craftWeapon();
	}
}
