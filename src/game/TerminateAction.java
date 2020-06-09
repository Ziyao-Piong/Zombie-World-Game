package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class TerminateAction extends Action  {
	protected String hotKey;
	public NewWorld newWorld;

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		newWorld.quitGame();
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Quit Game";
	}
	
	/**
	 * Returns this Action's hotkey.
	 *
	 * @return the hotkey
	 */
	@Override
	public String hotkey() {
		return hotKey;
	}
}


