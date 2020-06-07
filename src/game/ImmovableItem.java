package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

public class ImmovableItem extends Item {
	
	public ImmovableItem(String name, char displayChar) {
		super(name, displayChar, false);
	}
	
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}
		
	@Override
	public DropItemAction getDropAction() {
		return new DropItemAction(this);
	} 
	

}
