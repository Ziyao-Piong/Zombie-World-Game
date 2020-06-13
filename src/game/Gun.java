package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * An abstract class for Guns which is also a type of weapon.
 *
 * @author Yi Kin Heng
 */
public abstract class Gun extends WeaponItem {
    
	private Ammo ammoType;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param ammoType	  the type of ammo
     */
    public Gun(String name, char displayChar, int damage, String verb, Ammo ammoType) {
        super(name, displayChar, damage, verb);
        this.ammoType = ammoType;
    }
    
    /**
     * Check if this gun still has ammo in it.
     * @return a boolean indicating if this gun still has ammo
     */
    public boolean hasAmmo() {
        return ammoType.getAmmoCount() > 0;
    }
    
    /**
	 * Add an action to the gun's list of allowable actions
	 * @param action	an action to be added the gun's list of allowable actions
	 */
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}


}
