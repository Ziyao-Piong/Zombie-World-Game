package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class HarvestAction extends Action {
    private Crop crop;

    public HarvestAction(Crop crop) {
        this.crop = crop;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).setGround(new Dirt());
        if (actor instanceof Player) {
            actor.addItemToInventory(new Wheat());
        } else {
            map.locationOf(actor).addItem(new Wheat());
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests a ripe crop ";
    }
}