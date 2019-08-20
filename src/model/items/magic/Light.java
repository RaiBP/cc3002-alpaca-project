package model.items.magic;

/**
 * This class represents an Light spell.
 * <p>
 * Light spells are strong against Dark spells but weak against Anima spells.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public class Light extends AbstractSpell {

    /**
     * Creates a new Light spell item
     *
     * @param name
     *     the name of the Light spell
     * @param power
     *     the damage of the Light spell
     * @param minRange
     *     the minimum range of the Light spell
     * @param maxRange
     *     the maximum range of the Light spell
     */
    public Light(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange, "Light");
    }

    @Override
    public String getWeakness() {
        return "Anima";
    }

    @Override
    public String getStrength() {
        return "Dark";
    }

}