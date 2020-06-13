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
 * 
 * A Mambo Marie is created with the ability to wander around and spawn zombie
 * 
 * 
 * @author Peir Jing Chuang
 *
 */

public class MamboMarie extends ZombieActor {

	protected Random random = new Random();
	private Behaviour[] behaviours = { new SpawnZombieBehaviour(),
			new WanderBehaviour() 
	};

	/**
	 * Constructor
	 * @param name	Name of the Mambo Marie
	 */
	public MamboMarie(String name) {
		super(name, 'M', 100, ZombieCapability.UNDEAD, IdentityCapability.MAMBOMARIE);
	}
	

	/**
	 * If a Mambo Marie can spawn zombies, it will.  If not, it will wander around.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Mambo Marie is
	 * @param display the Display where the Mambo Marie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		
		return new DoNothingAction();	
	}

}
