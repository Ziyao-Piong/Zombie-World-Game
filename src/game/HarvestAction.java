package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Special Action for Actors to harvest Ripe Crops
 *
 * @author Yi Kin Heng
 */
public class HarvestAction extends Action {
    private Location harvestLocation;

    /**
     * HarvestAction Constructor initialises the harvestLocation variable.
     *
     * @param harvestLocation: the location of the Crop to be harvested.
     */
    public HarvestAction(Location harvestLocation) {
        this.harvestLocation = harvestLocation;
    }


    /**
     * Overrides the super class's execute method which harvests the Crop and dropping a new Wheat item if
     * it is a Farmer. If it is the Player, it is automatically added to the Player's inventory.
     *
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        harvestLocation.setGround(new Dirt());
        if (actor instanceof Player) {
            actor.addItemToInventory(new Wheat());
        } else if (actor instanceof Farmer) {
            harvestLocation.addItem(new Wheat());
        }
        return menuDescription(actor);
    }

    /**
     * Overrides the super class's menuDescription method
     *
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests a ripe crop ";
    }
}