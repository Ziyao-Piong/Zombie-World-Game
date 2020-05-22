package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class EatAction extends Action {
    private FoodItem foodItem;

    public EatAction(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(foodItem);
        actor.removeItemFromInventory(foodItem);
        actor.heal(foodItem.hpRecover());
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" "+  foodItem.verb() + " " + foodItem.name() + " for " + foodItem.hpRecover() + " health points ";
    }
}
