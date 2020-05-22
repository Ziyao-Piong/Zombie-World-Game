package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

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
    private Random random = new Random();


    /**
     * Overrides super class's getAction method which return an Action that actor can perform, such as SowAction,
     * FertiliseAction and HarvestAction or null if actor can't do this.
     */
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
                return new HarvestAction(e.getDestination());
            }
        }
        if (map.locationOf(actor).getGround().getDisplayChar() == 'c') {
            return new FertiliseAction((Crop) map.locationOf(actor).getGround());
        }
        if (map.locationOf(actor).getGround().getDisplayChar() == 'C') {
            return new HarvestAction(map.locationOf(actor));
        }
        return null;
    }
}

