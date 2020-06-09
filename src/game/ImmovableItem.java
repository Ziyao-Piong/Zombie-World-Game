package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

public class ImmovableItem extends Item {
	
	public boolean canDrop;
	
	public ImmovableItem(String name, char displayChar, boolean canDrop) {
		super(name, displayChar, false);
		this.canDrop = canDrop;
	}
	
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}
		
	@Override
	public DropItemAction getDropAction() {
		if (canDrop) {
			return new DropItemAction(this);
		}
		return null;
	}
}
