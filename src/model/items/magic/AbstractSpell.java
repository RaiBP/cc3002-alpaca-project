package model.items.magic;

import model.items.AbstractItem;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between all spells i.e. items that can attack but
 * only a Sorcerer can equip
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public abstract class AbstractSpell extends AbstractItem {

    /**
     * Constructor for a default spell without any special behaviour.
     *
     * @param name     the name of the spell
     * @param power    amount of damage the spell does
     * @param minRange the minimum range of the spell
     * @param maxRange the maximum range of the spell
     */
    AbstractSpell(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    protected int receiveAxeAttack(Axe axe) {
        return (int) (1.5 * axe.getPower());
    }

    @Override
    protected int receiveBowAttack(Bow bow) {
        return (int) (1.5 * bow.getPower());
    }

    @Override
    protected int receiveSpearAttack(Spear spear) {
        return (int) (1.5 * spear.getPower());
    }

    @Override
    protected int receiveSwordAttack(Sword sword) {
        return (int) (1.5 * sword.getPower());
    }

    @Override
    public void equipOnSwordMaster(SwordMaster swordMaster) {
        // SwordMaster unit can't equip spell books
    }

    @Override
    public void equipOnArcher(Archer archer) {
        // Archer unit can't equip spell books
    }

    @Override
    public void equipOnCleric(Cleric cleric) {
        // Cleric unit can't equip spell books
    }

    @Override
    public void equipOnFighter(Fighter fighter) {
        // Fighter unit can't equip spell books
    }

    @Override
    public void equipOnHero(Hero hero) {
        // Hero unit can't equip spell books
    }

    @Override
    public void equipOnSorcerer(Sorcerer sorcerer) {
        // Sorcerer unit can equip all spell books
        sorcerer.setEquippedItem(this);
    }

    public void useOn(IUnit targetUnit) {
        getOwner().doCombat(targetUnit);
    }
}
