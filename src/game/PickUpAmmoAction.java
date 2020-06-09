package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class PickUpAmmoAction extends Action {
    private Ammo ammo;
    private AmmunitionBag ammunitionBag;

    public PickUpAmmoAction(Ammo ammo, AmmunitionBag ammunitionBag) {
        this.ammo = ammo;
        this.ammunitionBag = ammunitionBag;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (ammo.getClass() == ShotgunAmmo.class) {
            ammunitionBag.addShotgunAmmo(ammo.getAmmoCount());
            map.locationOf(actor).removeItem(ammo);


        } else if (ammo.getClass() == SniperAmmo.class) {
            ammunitionBag.addSniperAmmo(ammo.getAmmoCount());
            map.locationOf(actor).removeItem(ammo);

        }
        return actor + " collected " + ammo.getAmmoCount() + " " + ammo.getClass().getSimpleName() + ". ShotgunAmmo: " + ammunitionBag.getShotgunAmmo() + " SniperAmmo: " + ammunitionBag.getSniperAmmo();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " collects " + ammo.getAmmoCount() + " " + ammo.getClass().getSimpleName();
    }
}
