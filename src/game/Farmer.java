package game;

import edu.monash.fit2099.engine.*;
/**
 * Class representing a farmer.
 *
 *
 * @author Yi Kin Heng
 *
 */
public class  Farmer extends Human {
    private Behaviour behaviour= new FarmBehaviour();
    /**
     * The default constructor creates default Farmers by calling the super constructors from Human Class
     *
     * @param name the human's display name
     */
    public Farmer(String name) {
        super(name,'F',50);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//        for (Behaviour behaviour : behaviours) {
//            Action action = behaviour.getAction(this, map);
//            if (action != null)
//                return action;
//        }
        return new DoNothingAction();
    }
}
