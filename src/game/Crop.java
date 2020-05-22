package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents crops that turns into a ripe crop.
 */
public class Crop extends Ground {
    private int countdown;
    private boolean ripe;

    /***
     * Calls super Class Item Constructor.

     */
    public Crop() {
        super('c');
        this.countdown = 20;
        this.ripe = false;
    }


    public int getCountdown() {
        return countdown;
    }

    public void decrementCountdown(int number) {
//        if number<= 0  exception
        this.countdown -= number;
    }

    public boolean getRipe() {
        return ripe;
    }

    public void setRipe(boolean ripe) {
        this.ripe = ripe;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        decrementCountdown(1);
        if (getCountdown() <= 0) {
            cropRipens(currentLocation);
        }
    }


    public void cropRipens(Location currentLocation) {
        setRipe(true);
        this.displayChar = 'C';
//        currentLocation.setGround(new Dirt());
//        currentLocation.addItem(new RipeCrop(currentLocation));
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (location.getGround().getDisplayChar() == 'C') {
            actions.add(new HarvestAction(location));
        }
//        if (location.map().locationOf(actor).getGround().getDisplayChar() == 'C')
//            actions.add(new HarvestAction(location));
        return actions;
    }
}

