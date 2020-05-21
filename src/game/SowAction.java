package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


public class SowAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).setGround(new Crop());
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sows a crop ";
    }
}
