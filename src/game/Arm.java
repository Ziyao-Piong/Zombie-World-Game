package game;

import edu.monash.fit2099.engine.WeaponItem;

public class Arm extends Limb{
	
	private WeaponItem upgradedWeapon = new ZombieClub();

	public Arm() {
		super("arm", 'A');
		allowableActions.add(new CraftWeaponAction(this));
	}
	
	@Override
	public WeaponItem craftWeapon() {
		return upgradedWeapon;
	}

}
