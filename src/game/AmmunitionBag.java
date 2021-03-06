package game;

/**
 * An immovable item that collects ammo for the player,
 *
 * @author Yi kin Heng
 */
public class AmmunitionBag extends ImmovableItem {

    private int shotgunAmmo = 0;
    private int sniperAmmo = 0;

    /**
     * Constructor, the player cannot drop this item.
     */
    public AmmunitionBag() {
        super("AmmunitionBag", 'B', false);
    }

    /***
     * increases the instance variable of int shotgunAmmo
     * @param shotgunAmmo the number of shotgunAmmo to increase
     */
    public void addShotgunAmmo(int shotgunAmmo) {
        this.shotgunAmmo += shotgunAmmo;
    }

    /***
     * increases the instance variable of int sniperAmmo
     * @param sniperAmmo the number of sniperAmmo to increase
     */
    public void addSniperAmmo(int sniperAmmo) {
        this.sniperAmmo += sniperAmmo;
    }

    /***
     * method that decreases the instance variable of int shotgunAmmo by 1 ammo whenever it is used.
     */
    public void useShotgunAmmo() {
        this.shotgunAmmo -= 1;
    }

    /***
     * method that decreases the instance variable of int sniperAmmo by 1 ammo whenever it is used.
     */
    public void useSniperAmmo() {
        this.sniperAmmo -= 1;
    }

    /***
     * Getter for the instance variable of int shotgunAmmo
     * @return shotgunAmmo the number of shotgunAmmo
     */
    public int getShotgunAmmo() {
        return shotgunAmmo;
    }

    /***
     * Getter for the instance variable of int sniperAmmo
     * @return ammoCount the number of sniperAmmo
     */
    public int getSniperAmmo() {
        return sniperAmmo;
    }
    
    /**
     * Check if there's any shotgun ammo left
     * @return true if there's shotgun ammo left
     */
    public boolean hasShotgunAmmo() {
    	if (shotgunAmmo > 0) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Check if there's any sniper ammo left
     * @return	true if there's sniper ammo left 
     */
    public boolean hasSniperAmmo() {
    	if (sniperAmmo > 0) {
    		return true;
    	}
    	return false;
    }


}