package game;

/**
 * A class for SniperAmmo which is a type of Ammo that is required to used Sniper weapon.
 *
 * @author Yi Kin Heng
 */

public class SniperAmmo extends Ammo {
    /***
     * Constructor that calls Super class's constructor to initialise the name and displayChar of the SniperAmmo item.
     * The SniperAmmo can also be picked up by the player.
     * @param ammunitionBag the ammunition bag where this ammo is added to
     */
    public SniperAmmo(AmmunitionBag ammunitionBag) {
        super("SniperAmmo", '*');
        this.allowableActions.add(new PickUpAmmoAction(this, ammunitionBag));
    }
}
