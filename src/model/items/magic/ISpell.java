package model.items.magic;

/**
 * This interface represents the <i>magic spells</i> that the Sorcerer units of the game can use.
 * <p>
 * The signature for all the common methods of the magic spells are defined here. Every spell has a
 * base damage and is strong or weak against other type of spell.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public interface ISpell {

    /**
     * @return weapon type of current spell
     */
    String getSpellType();

    /**
     * @return spell type to which current spell is weak to
     */
    String getWeakness();

    /**
     * @return spell type to which current spell is strong to
     */
    String getStrength();

}