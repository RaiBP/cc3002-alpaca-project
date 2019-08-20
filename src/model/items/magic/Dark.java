package model.items.magic;

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
        super(name, power, minRange, maxRange, "Dark");
    }

    @Override
    public String getWeakness() {
        return "Light";
    }

    @Override
    public String getStrength() {
        return "Anima";
    }

}