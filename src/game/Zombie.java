package game;

import edu.monash.fit2099.engine.Action;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import java.util.Random; 

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private Behaviour[] behaviours = {
			new PickUpWeaponBehaviour(),
			new ShoutBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new CrippledBehaviour(),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	private int arm = 2;
	private int leg = 2;
	private boolean isCrippled = false;
	private Random rand = new Random ();

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}
	
	private void lostArm(int armLost) {
		arm -= armLost;
	}
	
	private void lostLeg(int legLost) {
		leg -= legLost;
	}
	
	public int getArm() {
		return arm;
	}
	
	public int getLeg() {
		return leg;
	}
	
	public boolean getIsCrippled() {
		return this.isCrippled;
	}
	
	public void negateIsCrippled() {
		this.isCrippled = !isCrippled;
	}
	
	public String zombieIsAttacked() {
		if (rand.nextDouble() < 0.25) {
			if (arm > 0 && leg > 0) {
				if (rand.nextBoolean()) {
					this.lostArm(1);
					return "Arm";
				} else {
					this.lostLeg(1);
					return "Leg";
				}
			} else if (arm > 0) {
				this.lostArm(1);
				return "Arm";
			} else if (leg > 0) {
				this.lostLeg(1);
				return "Leg";
			}
		} return null;
	}
	
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
	
		if (rand.nextDouble() < 0.5 * (arm/2)) {
			return new IntrinsicWeapon(20, "bites");
		}
		else {
			return new IntrinsicWeapon(10, "punches");
		}
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
}
