package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An Action that makes Actor shout.
 */
public class ShoutAction extends Action {
	private String word;
	
	/**
	 * Constructor to create an Action that will allow the Actor to shout the word
	 * 
	 *
	 * @param word the word that Actor is going to shout 
	 */
	public ShoutAction(String word) {
		this.word = word;
	}

	/**
	 * Allow the Actor to shout.
	 *
	 * Overrides Action.execute()
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of the Action suitable for the menu
	 */

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor) ;
	}

	/**
	 * Returns a description of this shout action suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Player shouts Brainnn"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shouts " + word;
	}
	
	

}
