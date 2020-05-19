package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

// HumanCorpse is an item.
public class HumanCorpse extends Item {

    // countdown is the turns required for the dead Human to spawn into a zombie.
    // deadHumanName is a String name of the Human that died.
    private int countdown = 5;
    private String deadHumanName;

    // X is used to represent dead Human. It is not portable.
    public HumanCorpse(String deadHumanName) {
        super("humanCorpse", 'X', false);
        this.deadHumanName = deadHumanName;
    }

    // Override tick method in Location class to decrease countdown every turn.
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        countdown -= 1;
        if (countdown <= 0) {
            spawnZombie(currentLocation);

        }
    }

    //Spawns a zombie at the currentLocation where the Human died with Zombified in front of their previous name.
    public void spawnZombie(Location currentLocation) {
        if (!(currentLocation.containsAnActor())) {
            currentLocation.addActor(new Zombie("Zombified " + deadHumanName));
            currentLocation.removeItem(this);
        }

    }
}