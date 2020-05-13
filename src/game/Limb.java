package game;

import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

public abstract class Limb extends WeaponItem{
	
	public Limb(String name, char displayChar) {
		super(name, displayChar, 20, "hits");
	}
	
	public abstract Weapon craftWeapon();

}
