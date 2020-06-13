package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates a SpawnZombieAction every 10 turns after it is appeared
 * 
 * @author Peir Jing Chuang
 *
 */
public class SpawnZombieBehaviour implements Behaviour {

	private int countdown = 9;
	
	public void tick(GameMap map) {
        if (countdown > 0) {
        	countdown -=1;
        }
       
    }
	

	/**
	 * Returns an SpawnZombieACtion that spawn 5 zombies on the compound map
	 * 
	 * Actor is able to spawn every 10 turns after it is appeared.
	 * @param actor actor that is going to carrying out action
	 * @param map map that actor is on 
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
