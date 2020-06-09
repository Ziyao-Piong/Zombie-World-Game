package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SpawnZombieBehaviour implements Behaviour {

	private int countdown = 9;
	
	public void tick(GameMap map) {
        if (countdown > 0) {
        	countdown -=1;
        }
       
    }
	/**
	 * Returns a spawn zombie action .
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		//ArrayList<Action> actions = new ArrayList<Action>();
		
		if (countdown == 0) {
			countdown = 9;
			return new SpawnZombieAction();
		}
		
		else {
			tick(map);
			return null;
		}
		
	}

}
