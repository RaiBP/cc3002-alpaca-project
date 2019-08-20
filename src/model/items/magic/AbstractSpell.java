package model.items.magic;

import model.items.AbstractItem;

public class AbstractSpell extends AbstractItem implements ISpell {

    private final String spellType;

    /**
     * Constructor for a default spell without any special behaviour.
     *
     * @param name
     *     the name of the spell
     * @param power
     *     amount of damage the spell does
     * @param minRange
     *     the minimum range of the spell
     * @param maxRange
     *     the maximum range of the spell
     * @param spellType
     *     the type of the spell
     */
    AbstractSpell(final String name, final int power, final int minRange, final int maxRange, final String spellType)
    {
        super(name, power, minRange, maxRange);
        this.spellType = spellType;
    }

    @Override
    public String getSpellType() { return spellType; }

    @Override
    public String getWeakness() {
        return null;
    }

    @Override
    public String getStrength() {
        return null;
    }
}
