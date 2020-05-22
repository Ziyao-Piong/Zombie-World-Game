package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;


public class SowAction extends Action {
    private Exit exit;

    public SowAction(Exit exit) {
        this.exit = exit;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        exit.getDestination().setGround(new Crop());
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sows a crop ";
    }
}
