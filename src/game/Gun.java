package game;

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
     */
    public Gun(String name, char displayChar, int damage, String verb, Ammo ammoType) {
        super(name, displayChar, damage, verb);
        this.ammoType = ammoType;
    }

    public boolean hasAmmo() {
        return ammoType.getAmmoCount() > 0;
    }

}
