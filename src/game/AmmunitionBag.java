package game;

public class AmmunitionBag extends PortableItem {
    private int shotgunAmmo = 0;
    private int sniperAmmo = 0;

    public AmmunitionBag() {
        super("AmmunitionBag", 'B');
    }

    public void addShotgunAmmo(int shotgunAmmo) {
        this.shotgunAmmo += shotgunAmmo;
    }

    public void addSniperAmmo(int sniperAmmo) {
        this.sniperAmmo += sniperAmmo;
    }

    public int getShotgunAmmo() {
        return shotgunAmmo;
    }

    public int getSniperAmmo() {
        return sniperAmmo;
    }


}