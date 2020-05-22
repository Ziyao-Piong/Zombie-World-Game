package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents crops that turns into a ripe crop.
 * Instance variable int countdown: the amound of turn before Crop ripens
 */
public class Crop extends Ground {
    private int countdown;
//    private boolean ripe;

    /**
     * Calls super class constructor
     */
    public Crop() {
        super('c');
        this.countdown = 20;

//        this.ripe = false;
    }

    /**
     * Setter for countdown instance variable
     *
     * @returns : countdown
     */
    public int getCountdown() {
        return countdown;
    }

    /**
     * Calls super class constructor
     *
     * @param number : decreases the number of turns required for Crop to ripens
     */
    public void decrementCountdown(int number) {
//        if number<= 0  exception
        this.countdown -= number;
    }

//    public boolean getRipe() {
//        return ripe;
//    }
//
//    public void setRipe(boolean ripe) {
//        this.ripe = ripe;
//    }

    /**
     * @param currentLocation The location of the Crop
     * @Overrides tick method from Ground class so Crop can also experience the joy of time.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if (getCountdown() <= 0) {
            cropRipens(currentLocation);
        }
        decrementCountdown(1);
    }

    /**
     * Turns Crop into ripe Crop by updating its displayChar to 'C'
     *
     * @param currentLocation The location of the Crop
     */
    public void cropRipens(Location currentLocation) {
//        setRipe(true);
        this.displayChar = 'C';
//        currentLocation.setGround(new Dirt());
//        currentLocation.addItem(new RipeCrop(currentLocation));
    }

    /**
     * @param actor     The location of the Crop
     * @param location  The location of the Crop
     * @param direction The direction of the Crop
     * @Overrides allowableActions method from Ground class which returns a new collection of actions
     */
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

