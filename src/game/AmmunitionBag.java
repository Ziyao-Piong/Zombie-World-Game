package game;

public class AmmunitionBag extends ImmovableItem {
	
    private int shotgunAmmo = 0;
    private int sniperAmmo = 0;

    public AmmunitionBag() {
        super("AmmunitionBag", 'B', false);
    }

    public void addShotgunAmmo(int shotgunAmmo) {
        this.shotgunAmmo += shotgunAmmo;
    }

    public void addSniperAmmo(int sniperAmmo) {
        this.sniperAmmo += sniperAmmo;
    }
    
    public void useShotgunAmmo() {
    	this.shotgunAmmo -= 1;
    }
    
    public void useSniperAmmo() {
    	this.sniperAmmo -= 1;
    }

    public int getShotgunAmmo() {
        return shotgunAmmo;
    }

    public int getSniperAmmo() {
        return sniperAmmo;
    }


}