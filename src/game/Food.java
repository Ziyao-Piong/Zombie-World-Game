package game;
/**
 * Interface for food items.
 *
 * As well as providing methods needed by foods, this interface is used in Item to
 * determine whether an item can be used as a food.
 */
public interface Food {
    /**
     * The amount of health points the food will recover.
     *
     * @return the amount of health points recovered.
     */
    public int hpRecover();

    /**
     * A verb to use when displaying the results of using this Food
     *
     * @return String, e.g. "eats", "chews"
     */
    public String verb();
}