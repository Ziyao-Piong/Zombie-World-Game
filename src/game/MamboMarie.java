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
import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * @author Peir Jing
 *
 */
public class MamboMarie extends ZombieActor {

	// TODO Auto-generated constructor stub
	protected Random random = new Random();
	private Behaviour[] behaviours = { new SpawnZombieBehaviour(),
			new ShoutBehaviour(),
			new PickUpWeaponBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour() 
	};

	public MamboMarie(String name) {
		super(name, 'M', 100, ZombieCapability.UNDEAD);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Return the intrinsic weapon of this zombie.
	 * 
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		if (random.nextDouble() < 0.5) {
			return new IntrinsicWeapon(10, "punches");
		}
		else {
			return new IntrinsicWeapon(20, "bites");
		}
	}



	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// TODO Auto-generated method stub
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		
		return new DoNothingAction();	
	}

}
