package game;

public class Shotgun extends Gun {
	
    private static ShotgunAmmo shotgunAmmo;

    /**
     * Constructor.
     */
    public Shotgun() {
        super("Shotgun", 's', 40, "blasts", shotgunAmmo);
        addCapability(GunCapability.SHOTGUN);
    }

}
