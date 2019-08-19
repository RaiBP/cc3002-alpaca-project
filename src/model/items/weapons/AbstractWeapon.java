package model.items.weapons;

import model.items.AbstractItem;

public class AbstractWeapon extends AbstractItem implements IWeapon {

    private final String weaponType;

    /**
     * Constructor for a default weapon without any special behaviour. This type of item can counter-attack.
     *
     * @param name
     *     the name of the weapon
     * @param power
     *     amount of damage the weapon does
     * @param minRange
     *     the minimum range of the weapon
     * @param maxRange
     *     the maximum range of the weapon
     * @param weaponType
     *     the type of the weapon
     */
    AbstractWeapon(final String name, final int power, final int minRange, final int maxRange, final String weaponType)
    {
        super(name, power, minRange, maxRange);
        this.weaponType = weaponType;
    }

    @Override
    public String getWeaponType() {
        return weaponType;
    }

    @Override
    public String getWeakness() {
        return null;
    }

    @Override
    public String getStrength() {
        return null;
    }
}
