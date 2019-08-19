package model.items.weapons;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IWeapon {

    /**
     * @return weapon type of current weapon
     */
    String getWeaponType();

    /**
     * @return weapon type to which current weapon is weak to
     */
    String getWeakness();

    /**
     * @return weapon type to which current weapon is strong to
     */
    String getStrength();

}
