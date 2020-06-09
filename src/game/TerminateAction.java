package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class TerminateAction extends Action  {
	private NewWorld newWorld;
	
	public TerminateAction(NewWorld newWorld) {
		this.newWorld = newWorld;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		newWorld.quitGame();
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Quit Game";
	}
	
}


