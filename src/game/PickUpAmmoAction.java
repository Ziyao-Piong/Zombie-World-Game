package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An action that adds the ammo on the ground to the player's AmmunitionBag then
 * remove the ammo from the map.
 *
 * @author Yi Kin Heng
 */
public class PickUpAmmoAction extends Action {
    private Ammo ammo;
    private AmmunitionBag ammunitionBag;

    /**
     * Constructor
     *
     * @param ammo          the ammo item
     * @param ammunitionBag the ammunitionBag of the player
     */
    public PickUpAmmoAction(Ammo ammo, AmmunitionBag ammunitionBag) {
        this.ammo = ammo;
        this.ammunitionBag = ammunitionBag;
    }

    /**
     * Overrides Super class's execute method and picks up the ammo item and removing it from the map
     *
     * @param actor the actor picking up the ammo item
     * @param map   the map containing the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (ammo.getClass() == ShotgunAmmo.class) {
            ammunitionBag.addShotgunAmmo(ammo.getAmmoCount());
            map.locationOf(actor).removeItem(ammo);


        } else if (ammo.getClass() == SniperAmmo.class) {
            ammunitionBag.addSniperAmmo(ammo.getAmmoCount());
            map.locationOf(actor).removeItem(ammo);

        }
        return actor + " collected " + ammo.getAmmoCount() + " " + ammo.getClass().getSimpleName() +
        		". ShotgunAmmo: " + ammunitionBag.getShotgunAmmo() + " SniperAmmo: " + ammunitionBag.getSniperAmmo();
    }

    /**
     * Overrides Super class's menuDescription method and returns the string to be displayed to the player
     *
     * @param actor the actor picking up the ammo item
     * @return String to be displayed to the player.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " collects " + ammo.getAmmoCount() + " " + ammo.getClass().getSimpleName();
    }
}
