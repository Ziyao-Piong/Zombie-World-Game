package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class CraftWeaponAction extends Action {
	
	private Limb oldWeapon;
	
	public CraftWeaponAction(Limb oldWeapon) {
		this.oldWeapon = oldWeapon;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(oldWeapon);
		actor.addItemToInventory(oldWeapon.craftWeapon());
		return menuDescription(actor);
	}
	
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + "crafted" + oldWeapon + "into" + oldWeapon.craftWeapon();
	}
}
