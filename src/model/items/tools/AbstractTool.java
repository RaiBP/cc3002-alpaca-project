package model.items.tools;

import model.items.AbstractItem;
import model.items.magic.Anima;
import model.items.magic.Dark;
import model.items.magic.Light;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between all tools i.e. items that cannot attack
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public abstract class AbstractTool extends AbstractItem {
    /**
     * Constructor for a default tool without any special behaviour. This type of item cannot counter-attack.
     *
     * @param name
     *     the name of the tool
     * @param power
     *     amount of healing the tool does
     * @param minRange
     *     the minimum range of the tool
     * @param maxRange
     *     the maximum range of the tool
     */
    AbstractTool(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    protected int receiveAxeAttack(Axe axe) {
        return axe.getPower();
    }

    @Override
    protected int receiveBowAttack(Bow bow) {
        return bow.getPower();
    }

    @Override
    protected int receiveSpearAttack(Spear spear) {
        return spear.getPower();
    }

    @Override
    protected int receiveSwordAttack(Sword sword) {
        return sword.getPower();
    }

    @Override
    protected int receiveDarkSpellAttack(Dark dark) {
        return dark.getPower();
    }

    @Override
    protected int receiveLightSpellAttack(Light light) {
        return light.getPower();
    }

    @Override
    protected int receiveAnimaSpellAttack(Anima anima) {
        return anima.getPower();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <i>Tools</i> can't attack, they always return -1
     */
    @Override
    protected int attack(IUnit targetUnit) {
        return -1;
    }

}