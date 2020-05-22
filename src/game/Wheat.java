package game;

/**
 * A type of food dropped when crop is harvested.
 *
 * @author Yi kin Heng
 */
public class Wheat extends FoodItem {

    public Wheat() {
        super("wheat", 'w', 20, "chews");
        allowableActions.add(new EatAction(this ));
    }
}