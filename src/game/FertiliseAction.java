package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class FertiliseAction extends Action {
    private Crop crop;

    public FertiliseAction(Crop crop) {
        this.crop = crop;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        crop.decrementCountdown(10);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fertilised a crop";
    }
}
