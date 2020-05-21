package game;

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
//        countdown -= 1;
        decrementCountdown(1);
        if (getCountdown() <= 0) {
            cropRipens();
        }
    }


    public void cropRipens() {
        setRipe(true);
        this.displayChar = 'C';
    }
}

