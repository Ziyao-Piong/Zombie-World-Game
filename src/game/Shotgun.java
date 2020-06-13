package game;

/**
 * A class representing Shotgun that extends Super class Guns which is also a type of weapon.
 *
 * @author Yi Kin Heng
 */
public class Shotgun extends Gun {

    private static ShotgunAmmo shotgunAmmo;

    /**
     * Constructor that initialises the instance variables name, displayChar , damage and the verb of the Shotgun.
     * Added GunCapability.SHOTGUN
     */
    public Shotgun() {
        super("Shotgun", 's', 40, "blasts", shotgunAmmo);
        addCapability(GunCapability.SHOTGUN);
    }
}
