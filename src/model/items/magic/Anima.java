package model.items.magic;

import model.units.*;

/**
 * This class represents an Anima spell.
 * <p>
 * Anima spells are strong against Light spells but weak against Dark spells.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public class Anima extends AbstractSpell {

    /**
     * Creates a new Anima spell item
     *
     * @param name
     *     the name of the Anima spell
     * @param power
     *     the damage of the Anima spell
     * @param minRange
     *     the minimum range of the Anima spell
     * @param maxRange
     *     the maximum range of the Anima spell
     */
    public Anima(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    protected int attack(IUnit targetUnit) { return targetUnit.receiveAnimaSpellAttack(this);
    }

    @Override
    protected int receiveDarkSpellAttack(Dark dark) { return (int) (1.5 * dark.getPower()); }

    @Override
    protected int receiveLightSpellAttack(Light light) { return Math.max(0, light.getPower() - 20); }

    @Override
    protected int receiveAnimaSpellAttack(Anima anima) { return anima.getPower(); }

}