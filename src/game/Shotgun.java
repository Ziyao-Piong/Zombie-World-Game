package game;

public class Shotgun extends Gun {
    private static ShotgunAmmo shotgunAmmo;

    /**
     * Constructor.
     */
    public Shotgun() {
        super("Shotgun", 's', 40, "blasts", shotgunAmmo);
    }


//    @Override
//    public boolean trigger() {
//        return shotgunAmmo.getAmmoCount() > 0;
//    }
}
