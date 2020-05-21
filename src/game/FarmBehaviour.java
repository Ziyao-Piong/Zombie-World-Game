package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A class that allows Farmer to interact with Crops.
 *
 * @author Yi Kin Heng
 */
public class FarmBehaviour implements Behaviour {
    private Location currentLocation;
    private Ground groundTarget;
    private Random random = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<Action>();
        List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
        Collections.shuffle(exits);
        for (Exit e : exits) {
            if ((e.getDestination().getGround().getDisplayChar() == '.') && (random.nextInt(3) == 0)) {

            }
        }
        return null;
    }
}

