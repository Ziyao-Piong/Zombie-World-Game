package game;

import java.util.Random;

/**
 * A class for SniperAmmo which is a type of Ammo that is required to used Sniper weapon.
 *
 * @author Yi Kin Heng
 */

public class SniperAmmo extends Ammo {
    /***
     * Constructor that calls Super class's constructor to initialise the name and displayChar of the SniperAmmo item.
     */
    public SniperAmmo() {
        super("SniperAmmo", '*');
//        Random random = new Random();
//        int initAmmoCount = 5 + random.nextInt(6);
//        setAmmoCount(initAmmoCount);
    }
}
