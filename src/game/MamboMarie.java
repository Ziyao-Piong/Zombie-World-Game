/**
 * 
 */
package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * @author Peir Jing
 *
 */
public class MamboMarie extends ZombieActor {

	// TODO Auto-generated constructor stub
	private int countdown = 0;
	protected Random random = new Random();

	public MamboMarie(String name) {
		super(name, 'M', 150, ZombieCapability.UNDEAD);
		// TODO Auto-generated constructor stub
	}

	private Behaviour[] behaviours = { new SpawnZombieBehaviour(),
			//new ShoutBehaviour(),
			//new PickUpWeaponBehaviour(),
			//new AttackBehaviour(ZombieCapability.ALIVE),
			//new HuntBehaviour(Human.class, 10),
			new WanderBehaviour() };


	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// TODO Auto-generated method stub
		if (countdown <30) {
			countdown += 1;
		}
		else {
			map.removeActor(this);
			countdown = 0;
		}
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}

}
