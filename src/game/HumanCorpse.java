package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


/**
 * Class that represent HumanCorpse
 *
 * @author Yi Kin Heng
 */
public class HumanCorpse extends Item {

    private int countdown = 5;
    private String deadHumanName;


    /**
     * Calls super class's constructor method which sets the name, displayChar and if the item is portable
     *
     * @param deadHumanName: the name of the Human that died.
     */
    public HumanCorpse(String deadHumanName) {
        super("humanCorpse", 'X', false);
        this.deadHumanName = deadHumanName;
    }

    /**
     * Overrides the super class's tick method which decreases the countdown by 1 every turn.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        countdown -= 1;
        if (countdown <= 0) {
            spawnZombie(currentLocation);

        }
    }

    /**
     * Method that spawns a zombie by creating a new Zombie instance at currentLocation
     *
     * @param currentLocation the location where the Zombie will spawn.
     */
    public void spawnZombie(Location currentLocation) {
        if (!(currentLocation.containsAnActor())) {
            currentLocation.addActor(new Zombie("Zombified " + deadHumanName));
            currentLocation.removeItem(this);
        }
    }
}