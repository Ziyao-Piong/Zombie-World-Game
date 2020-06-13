package game;

/**
 * A class for ShotGunAmmo which is a type of Ammo that is required to used 
 * Shotgun weapon.
 *
 * @author Yi Kin Heng
 */
public class ShotgunAmmo extends Ammo {

    /***
     * Constructor that calls Super class's constructor to initialise the 
     * name and displayChar of the ShotgunAmmo item.
     */
    public ShotgunAmmo(AmmunitionBag ammunitionBag) {
        super("Shotgun Ammo", '*');
        this.allowableActions.add(new PickUpAmmoAction(this, ammunitionBag));
    }

}
