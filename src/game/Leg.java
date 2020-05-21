package game;

import edu.monash.fit2099.engine.WeaponItem;

public class Leg extends Limb{
	
	private WeaponItem upgradedWeapon = new ZombieMace();
	
	public Leg() {
		super("leg", 'L');
		allowableActions.add(new CraftWeaponAction(this));
	}
	
	@Override
	public WeaponItem craftWeapon() {
		return upgradedWeapon;
	}

}
