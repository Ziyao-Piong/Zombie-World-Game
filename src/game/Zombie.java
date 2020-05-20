package game;

import edu.monash.fit2099.engine.Action;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
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
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	private int arm = 2;
	private int leg = 2;
	protected boolean isCrippled = false;
	protected Random rand = new Random ();

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}
	
	public void lostArm(int armLost) {
		arm -= armLost;
	}
	
	public void lostLeg(int legLost) {
		leg -= legLost;
	}
	
	public int getArm() {
		return arm;
	}
	
	public int getLeg() {
		return leg;
	}
	
	public boolean getIsCrippled() {
		return isCrippled;
	}
	
	public void negateIsCrippled() {
		isCrippled = !isCrippled;
	}
	
	public String zombieIsAttacked() {
		if (Math.random() < 0.25) {
			if (Math.random() < 0.5) {
				if (arm > 0) {
					this.lostArm(1);
					return "Arm";
				} else if (leg > 0) {
					this.lostLeg(1);
					return "Leg";
				}
			}
			else {
				if (leg > 0) {
					this.lostLeg(1);
					return "Arm";
				} else if (arm > 0) {
					this.lostArm(1);
					return "Leg";
				}
			}
		} return "Null";
	}

	public void dropWeapon(GameMap map) {
		Actions dropActions = new Actions();
		for (Item item : this.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)		
			drop.execute(this, map);
	}
	
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		if (rand.nextBoolean()) 
		{
			return new IntrinsicWeapon(20, "bites");
		}
		else 
		{
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
