package game;

import edu.monash.fit2099.engine.Action;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import java.util.Random; 

/**
 * A Zombie has two intrinsic weapons, namely punches and bites.
 * A Zombie is created with 2 arms and 2 legs and it has a tendency to drop its limb
 * when being attacked and shout nonsense every now and then.
 * 
 * 
 * @author ram, ziyaopiong
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

	/**
	 * Constructor
	 * @param name	Name of the zombie
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}
	
	/**
	 * Decrease the number of arms of this zombie
	 * @param armLost		an integer represent the arm lost
	 * @throws Exception 	if arm lost is larger than the number of arms this zombie has
	 */
	private void lostArm(int armLost) throws Exception {
		if (armLost > arm) {
			throw new Exception("The zombie cannot lose more arms than it currently has.");
		}
		arm -= armLost;
	}
	
	/**
	 * Decrease the number of legs of this zombie
	 * @param armLost		an integer represent the leg lost
	 * @throws Exception 	if leg lost is larger than the number of legs this zombie has
	 */
	private void lostLeg(int legLost) throws Exception {
		if (legLost > leg) {
			throw new Exception("The zombie cannot lose more legs than it currently has.");
		}
		leg -= legLost;
	}
	
	/**
	 * Get the number of arms this zombie currently has.
	 * The number of arms control the probability of this zombie dropping 
	 * the weapon it's holding.
	 * @return	the number of arms 
	 */
	public int getArm() {
		return arm;
	}
	
	/**
	 * Get the number of legs this zombie currently has.
	 * The number of legs controls this zombie's ability to move
	 * @return	the number of legs
	 */
	public int getLeg() {
		return leg;
	}
	
	/**
	 * Get the boolean isCrippled which determines if this zombie 
	 * is able to move or not in this turn.
	 * @return a boolean determines if whether this zombie can move or not
	 */
	public boolean getIsCrippled() {
		return isCrippled;
	}
	
	/**
	 * Negate the isCrippled value of this zombie so it can achieve 
	 * the ability to move in alternating turns.
	 */
	public void negateIsCrippled() {
		this.isCrippled = !isCrippled;
	}
	
	/**
	 * Determines if this zombie will lose a limb when it's being attacked.
	 * @return	a string that represents the limb lost
	 */
	public String zombieIsAttacked() {
		if (rand.nextDouble() < 0.25) {
			if (arm > 0 && leg > 0) {
				if (rand.nextBoolean()) {
					try {
						this.lostArm(1);
						return "Arm";
					} catch (Exception e) {
						System.out.println(e);
					}
				} else {
					try {
						this.lostLeg(1);
						return "Leg";
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			} else if (arm > 0) {
				try {
					this.lostArm(1);
					return "Arm";
				} catch (Exception e) {
					System.out.println(e);
				}
			} else if (leg > 0) {
				try {
					this.lostLeg(1);
					return "Leg";
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} return null;
	}
	
	/**
	 * Return the intrinsic weapon of this zombie.
	 * The probability of returning a punch depends on the number of arms of this zombie.
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		if (rand.nextDouble() < 0.5 * (arm/2)) {
			return new IntrinsicWeapon(10, "punches");
		}
		else {
			return new IntrinsicWeapon(20, "bites");
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
