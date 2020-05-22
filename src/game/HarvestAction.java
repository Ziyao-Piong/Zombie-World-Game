package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class HarvestAction extends Action {
//    private Crop crop;
    private Location harvestLocation;


//    public HarvestAction(Crop crop, Location harvestLocation) {
//        this.crop = crop;
//        this.harvestLocation = harvestLocation;
//    }
    public HarvestAction(Location harvestLocation){
        this.harvestLocation = harvestLocation;
    }

    //REMEMBER POINT 4 EXIT E CHANGE MAP.LOCATION TO EXIT E.
    @Override
    public String execute(Actor actor, GameMap map) {
        harvestLocation.setGround(new Dirt());
        if (actor instanceof Player) {
            actor.addItemToInventory(new Wheat());
        } else if (actor instanceof Farmer){
            harvestLocation.addItem(new Wheat());
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests a ripe crop ";
    }
}