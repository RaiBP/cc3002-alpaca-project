package model.items.tools;

import model.items.AbstractItem;

public class AbstractTool extends AbstractItem {
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
}