package game;

public class Sniper extends Gun {
    private static SniperAmmo sniperAmmo;

    /**
     * Constructor
     */
    public Sniper() {
        super("Sniper", 'S', 50, "snipes", sniperAmmo);
        addCapability(GunCapability.SNIPER);
    }
}
