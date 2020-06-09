package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SpawnZombieAction extends Action {
	
		
		
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		int i;
		for (i=1; i<6; i++) {
			int x, y;
			do {
				x = (int) Math.floor(Math.random());
				y = (int) Math.floor(Math.random());
			}
			while (map.at(x, y).containsAnActor());
			map.at(x,  y).addActor(new Zombie("SpawnedZombie" + Integer.toString(i)));
		
		}
		return menuDescription(actor);
			
		
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " has spwaned 5 zombies.";
	}
	

}
