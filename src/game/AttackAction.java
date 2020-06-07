package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Special Action for attacking other Actors.
 */
public abstract class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public abstract String execute(Actor actor, GameMap map);

	/**
	 * Remove the actor, drop everything the actor is holding 
	 * and creates a corpse when the Zombie is dead and creates a HumanCorpse when Human is dead.
	 * @param actor	the actor that is dead
	 * @param map	the map the actor is on
	 */
	protected void targetIsDead(Actor actor, GameMap map) {

		if (target.hasCapability(ZombieCapability.ALIVE)){
			//This is used to create a zombie when target dies.(Only for Humans though)
			Item humanCorpse = new HumanCorpse(target.toString());
			map.locationOf(target).addItem(humanCorpse);
		}

		dropAllItem(target, map);
		map.removeActor(target);
	}
	
	/**
	 * Drop all the items this actor currently has in its inventory
	 * @param actor	the actor 
	 * @param map	the map where the actor is on
	 */
	private void dropAllItem(Actor actor,GameMap map) {
		Actions dropActions = new Actions();
		for (Item item : actor.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)		
			drop.execute(actor, map);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
