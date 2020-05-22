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


	public void targetIsDead(Actor actor, GameMap map) {

		if (target.hasCapability(ZombieCapability.UNDEAD)){
			Item corpse = new PortableItem("dead " + target, '%');
			map.locationOf(target).addItem(corpse);
		}
        else {
			//This is used to create a zombie when target dies.(Only for Humans though)
			Item humanCorpse = new HumanCorpse(target.toString());
			map.locationOf(target).addItem(humanCorpse);
		}

		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)
			drop.execute(target, map);
		map.removeActor(target);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
