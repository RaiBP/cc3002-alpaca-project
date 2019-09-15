package model.items.weapons;

import model.items.AbstractItem;
import model.items.magic.Anima;
import model.items.magic.Dark;
import model.items.magic.Light;
import model.units.IUnit;
import model.units.Sorcerer;

/**
 * Abstract class that defines some common information and behaviour between all weapons i.e. items that can attack
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public abstract class AbstractWeapon extends AbstractItem {

    /**
     * Constructor for a default weapon without any special behaviour. This type of item can attack and counter-attack
     *
     * @param name
     *     the name of the weapon
     * @param power
     *     amount of damage the weapon does
     * @param minRange
     *     the minimum range of the weapon
     * @param maxRange
     *     the maximum range of the weapon
     */
    AbstractWeapon(final String name, final int power, final int minRange, final int maxRange)
    {
        super(name, power, minRange, maxRange);
    }

    @Override
    protected int receiveBowAttack(Bow bow) {
        return bow.getPower();
    }

    @Override
    protected int receiveDarkSpellAttack(Dark dark) { return (int) (1.5 * dark.getPower()); }

    @Override
    protected int receiveLightSpellAttack(Light light) { return (int) (1.5 * light.getPower()); }

    @Override
    protected int receiveAnimaSpellAttack(Anima anima) { return (int) (1.5 * anima.getPower()); }

    /**
     * {@inheritDoc}
     * <p>
     * Sorcerers can't equip weapons, only spell books
     */
    @Override
    public void equipOnSorcerer(Sorcerer sorcerer) { }

    @Override
    public void useOn(IUnit targetUnit) {
        getOwner().doCombat(targetUnit);
    }

}
