package game;

import edu.monash.fit2099.engine.Item;

import java.util.Random;

/**
 * An abstract class for Ammo which is a type of Item that is used by Gun.
 *
 * @author Yi Kin Heng
 */
public abstract class Ammo extends Item {
    private int ammoCount;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public Ammo(String name, char displayChar) {
        super(name, displayChar, false);
        Random random = new Random();
        int initAmmoCount = 5 + random.nextInt(6);
        setAmmoCount(initAmmoCount);
    }

    public int getAmmoCount() {
        return ammoCount;
    }

    public void setAmmoCount(int ammoCount) {
        this.ammoCount = ammoCount;
    }

    public void addAmmoCount(int ammoCount) {
        this.ammoCount += ammoCount;
    }
}
