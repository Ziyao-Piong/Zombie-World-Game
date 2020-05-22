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
//    private Location currentLocation;
//    private Crop cropTarget = new Crop();
    private Random random = new Random();

//    double z = Math.random() * 100;

    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
        Collections.shuffle(exits);
        boolean doSowAction = false;
        if (random.nextInt(3) == 0) {
            doSowAction = true;
        }
        System.out.println("doSowAction is " + doSowAction);
        for (Exit e : exits) {
            if ((doSowAction) && (!(e.getDestination().containsAnActor())) && (e.getDestination().getGround().getDisplayChar() == '.')) {
                return new SowAction(e);
            } else if ((e.getDestination().getGround().getDisplayChar() == 'C') && !(e.getDestination().containsAnActor())) {
//                return new HarvestAction((Crop) e.getDestination().getGround(), e.getDestination());
                return new HarvestAction(e.getDestination());
            }
        }
        if (map.locationOf(actor).getGround().getDisplayChar() == 'c') {
            return new FertiliseAction((Crop) map.locationOf(actor).getGround());
        }
        if (map.locationOf(actor).getGround().getDisplayChar() == 'C') {
//            return new HarvestAction((Crop) map.locationOf(actor).getGround(), map.locationOf(actor));
            return new HarvestAction(map.locationOf(actor));
        }
        return null;
    }
}

