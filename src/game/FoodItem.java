package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing items that can be used as a food.
 */
public abstract class FoodItem extends Item implements Food {

    private int hpRecover;
    private String verb;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param hpRecover amount of health points recovered when consuming this food.
     * @param verb verb to use for this food, e.g. "chews", "eats"
     */
    public FoodItem(String name, char displayChar, int hpRecover, String verb) {
        super(name, displayChar, true);
        this.hpRecover = hpRecover;
        this.verb = verb;
    }

    /**
     * method to return health points recovered by this food.
     *
     * @return the health points recovered
     */
    public int hpRecover() {
        return hpRecover;
    }

    /**
     * Method that returns the verb used when consuming this food, so that it can be displayed
     * in the UI.
     *
     * @return a third-person present tense verb, e.g. "eats", "chews"
     */
    public String verb() {
        return verb;
    }

    /**
     * Method that returns the food name when consuming this food, so that it can be displayed
     * in the UI.
     *
     * @return a string name of food, e.g. "Wheat",
     */
    public String name() {
        return name;
    }
}
