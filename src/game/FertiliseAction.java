package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action for Actors to fertilise Crops
 *
 * @author Yi Kin Heng
 */
public class FertiliseAction extends Action {
    private Crop crop;

    /**
     * Constructor that initialises the Crop instance Variable
     *
     * @param crop: the Crop to be fertilised
     */
    public FertiliseAction(Crop crop) {
        this.crop = crop;
    }

    /**
     * Overrides the super class's execute method and fertilise the Crop by decreasing the countdown
     * by 10 turns.
     *
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        crop.decrementCountdown(10);
        return menuDescription(actor);
    }

    /**
     * Overrides the super class's menuDescription method and
     *
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fertilised a crop";
    }
}
