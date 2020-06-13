package game;

import edu.monash.fit2099.engine.Action;

import java.util.List;

/**
 * An class representing Shotgun that extends Super class Guns which is also a type of weapon.
 *
 * @author Yi Kin Heng
 */
public class Shotgun extends Gun {
    private static ShotgunAmmo shotgunAmmo;

    /**
     * Constructor that initialises the instance variables name, displayChar , damage and the verb of the Shotgun.
     */
    public Shotgun() {
        super("Shotgun", 's', 20, "blasts", shotgunAmmo);
    }


    @Override
    public List<Action> getAllowableActions() {
        return super.getAllowableActions();
    }
}
