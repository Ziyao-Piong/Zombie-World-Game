package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;


/**
 * Special class that allows actor to sow Crops
 *
 * @author Yi Kin Heng
 */
public class SowAction extends Action {
    private Exit exit;

    /**
     * SowAction constructor
     *
     * @param exit: the surrounding exit of the actor
     */
    public SowAction(Exit exit) {
        this.exit = exit;
    }
    
    /**
     * Overrides the super class's execute method to sow Crop by changing the Dirt to Crop.
     * returns  a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        exit.getDestination().setGround(new Crop());
        return menuDescription(actor);
    }

    /**
     * Overrides the super class's execute method and returns a description of what happened
     * that can be displayed to the user.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sows a crop ";
    }
}
