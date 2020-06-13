package game;

/**
 * A class representing Sniper that extends Super class Guns which is also a type of weapon.
 *
 * @author Yi Kin Heng
 */
public class Sniper extends Gun {
    private static SniperAmmo sniperAmmo;

    /**
     * Constructor that initialises the instance variables name, displayChar , damage and the verb of the Sniper.
     */
    public Sniper() {
        super("Sniper", 'S', 50, "snipes", sniperAmmo);
    }
}
