package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing a farmer.
 *
 * @author Yi Kin Heng
 */
public class Farmer extends Human {
    private Behaviour[] behaviours = {
            new FarmBehaviour(),
            behaviour
    };

    /**
     * The default constructor creates default Farmers by calling the super constructors from Human Class
     * with a different displayChar 'F'.
     *
     * @param name the human's display name
     */
    public Farmer(String name) {
        super(name, 'F', 50);
    }

    /**
     * Overrides super class's playTurn method which selects and return an action to perform on the
     * current turn.
     *
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
