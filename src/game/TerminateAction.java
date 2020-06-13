package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


/**
 * Special Action that allows Actors to terminate game.
 */
public class TerminateAction extends Action  {
	
	private NewWorld newWorld;
	
	/**
	 * Constructor.
	 *
	 * @param newWorld the game in newWorld
	 */
	public TerminateAction(NewWorld newWorld) {
		this.newWorld = newWorld;
	}

	/**
	 * Terminate the game.
	 *
	 * @param actor The actor performing the action
	 * @param map The map the actor is on
	 * @return a description of the action suitable for feedback in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		newWorld.quitGame();
		return menuDescription(actor);
	}
	
	/**
	 * A string describing the action suitable for displaying in the UI menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Player quits game"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " quits Game";
	}
	
}


