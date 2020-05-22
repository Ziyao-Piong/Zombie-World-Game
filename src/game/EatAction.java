package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action that allows actor to consume Food
 * <p>
 * Instance variable int countdown: the amount of turn before Crop ripens
 *
 * @author Yi Kin Heng
 */

public class EatAction extends Action {
    private FoodItem foodItem;

    /**
     * @param foodItem : the FoodItem to be consumed
     */
    public EatAction(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    /**
     * Overrides Execute method in Action to use the FoodItem to remove the item consumed from inventory
     * and heal the actor by the amount indicated by hpRecover
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(foodItem);
        actor.removeItemFromInventory(foodItem);
        actor.heal(foodItem.hpRecover());
        return menuDescription(actor);
    }

    /**
     * Overrides super class's menuDescription method to return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " " + foodItem.verb() + " " + foodItem.name() + " for " + foodItem.hpRecover() + " health points ";
    }
}
