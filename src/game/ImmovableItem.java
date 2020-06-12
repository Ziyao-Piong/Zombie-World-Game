package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Represents an item that cannot be picked up, but has the option to be
 * dropped.
 * 
 * @author ziyaopiong
 *
 */
public class ImmovableItem extends Item {

	public boolean canDrop;

	/**
	 * Constructor
	 * @param name			the name of this item
	 * @param displayChar	the display character of this item
	 * @param canDrop		a boolean indicates if this item can be dropped or not
	 */
	public ImmovableItem(String name, char displayChar, boolean canDrop) {
		super(name, displayChar, false);
		this.canDrop = canDrop;
	}

	/**
	 * Add an action to the item's list of allowable actions
	 * @param action	an action to be added the item's list of allowable actions
	 */
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

	/**
	 * If this item can be dropped, return a DropItemAction.
	 */
	@Override
	public DropItemAction getDropAction() {
		if (canDrop) {
			return new DropItemAction(this);
		}
		return null;
	}
}
