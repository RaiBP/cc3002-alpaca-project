package model.items.magic;

import model.units.*;

/**
 * This class represents an Dark spell.
 * <p>
 * Dark spells are strong against Anima spells but weak against Light spells.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public class Dark extends AbstractSpell {

    /**
     * Creates a new Dark spell item
     *
     * @param name
     *     the name of the Dark spell
     * @param power
     *     the damage of the Dark spell
     * @param minRange
     *     the minimum range of the Dark spell
     * @param maxRange
     *     the maximum range of the Dark spell
     */
    public Dark(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    protected int attack(IUnit targetUnit) { return targetUnit.receiveDarkSpellAttack(this);
    }

    @Override
    protected int receiveDarkSpellAttack(Dark dark) { return dark.getPower(); }

    @Override
    protected int receiveLightSpellAttack(Light light) { return (int) (1.5 * light.getPower()); }

    @Override
    protected int receiveAnimaSpellAttack(Anima anima) { return Math.max(0, anima.getPower() - 20); }

}