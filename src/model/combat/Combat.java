package model.combat;

import model.items.IEquipableItem;
import model.items.magic.ISpell;
import model.items.weapons.IWeapon;
import model.units.IUnit;

/**
 * This class represents the Combat between two units.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public class Combat {
    private IUnit attacker;
    private IUnit victim;

    /**
     * Creates a new Combat class instance.
     *
     * @param attacker
     *     the unit which will attack first
     * @param victim
     *     the unit which will receive the first attack. It may or may not counter-attack
    */
    public Combat(IUnit attacker, IUnit victim) {
        this.attacker = attacker;
        this.victim = victim;
    }

    /**
     * Performs a series of attacks until either the attacker or the victim perish (meaning their HP's reach 0).
     */
    public void doCombat() {
        while (victim.isAlive() && attacker.isAlive()) {
            attack();
        }
    }

    /**
     * Performs an attack, but only if the following conditions are met:
     * <ul>
     * <li>The attacker and the victim are alive, meaning their HP are greater than 0. </li>
     * <li>The attacker's equipped item is either a weapon or a spell. </li>
     * <li>The victim is on the attacker's spell or weapon range. </li>
     * </ul>
     * A successful attack will reduce the victim's HP and will result in a counter-attack by the victim, which will be
     * tested for the same three conditions above. If the counter-attack is successful, the original attacker will
     * perform another attack, subject to the same three conditions, and so on. This chain of attacks and
     * counter-attacks will only end if one of the three conditions fails somewhere in the chain.
     */
    private void attack() {
        IEquipableItem weapon = attacker.getEquippedItem();

        boolean hitPointCondition = victim.isAlive() && attacker.isAlive();
        boolean rangeCondition = weapon.getMinRange() <= victim.getLocation().distanceTo(attacker.getLocation()) &&
                victim.getLocation().distanceTo(attacker.getLocation()) <= weapon.getMaxRange();

        if (hitPointCondition && rangeCondition) {

            boolean weaponCondition = weapon instanceof IWeapon;
            boolean spellCondition = weapon instanceof ISpell;

            int damage;

            if (weaponCondition) {
                // get attacker's weapon power
                damage = getDamageWeapon();
            }

            else if (spellCondition) {
                // get attacker's spell power
                damage = getDamageSpell();
            }

            else {
                return;
            }

            // reduce victim's HP according to weapon's or spell's power
            victim.setHitPoints(victim.getCurrentHitPoints() - damage);

            // victim now counter attacks, roles are reversed
            Combat counterAttack = new Combat(victim, attacker);
            counterAttack.attack();
        }
    }

    /**
     * Calculates the damage done to victim unit, considering that the attacker's equipped item is a weapon
     *
     * @return damage done to victim unit
     */
    private int getDamageWeapon() {
        IWeapon attackerWeapon = (IWeapon) attacker.getEquippedItem();
        IEquipableItem victimWeapon = victim.getEquippedItem();

        if (victimWeapon instanceof IWeapon) {
            String victimWeaponType = ((IWeapon) victimWeapon).getWeaponType();

            // attacker's weapon is strong against victim's weapon
            if (victimWeaponType.equals(attackerWeapon.getStrength())) {
                return (int) (((IEquipableItem) attackerWeapon).getPower() * 1.5);
            }

            // attacker's weapon is weak against victim's weapon
            if (victimWeaponType.equals(attackerWeapon.getWeakness())) {
                return ((IEquipableItem) attackerWeapon).getPower() - 20;
            }
        }

        if (victimWeapon instanceof ISpell) {
            return (int) (((IEquipableItem) attackerWeapon).getPower() * 1.5);
        }

        return ((IEquipableItem) attackerWeapon).getPower();
    }

    /**
     * Calculates the damage done to victim unit, considering that the attacker's equipped item is a spell
     *
     * @return damage done to victim unit
     */
    private int getDamageSpell() {
        ISpell attackerSpell = (ISpell) attacker.getEquippedItem();
        IEquipableItem victimWeapon = victim.getEquippedItem();

        // victim's equipped item is a spell
        if (victimWeapon instanceof ISpell) {
            String victimSpellType = ((ISpell) victimWeapon).getSpellType();

            // attacker's spell is strong against victim's spell
            if (victimSpellType.equals(attackerSpell.getStrength())) {
                return (int) (((IEquipableItem) attackerSpell).getPower() * 1.5);
            }

            // attacker's spell is weak against victim's spell
            if (victimSpellType.equals(attackerSpell.getWeakness())) {
                return ((IEquipableItem) attackerSpell).getPower() - 20;
            }
        }

        // victim's equipped item is a weapon
        if (victimWeapon instanceof IWeapon) {
            return (int) (((IEquipableItem) attackerSpell).getPower() * 1.5);
        }

        return ((IEquipableItem) attackerSpell).getPower();
    }

    /**
     * @return attacker unit for the current Combat instance
     */
    public IUnit getAttacker() {
        return attacker;
    }

    /**
     * @return victim unit for the current Combat instance
     */
    public IUnit getVictim() {
        return victim;
    }

    /**
     * Sets attacker unit for the current Combat instance
     */
    public void setAttacker(IUnit attacker) {
        this.attacker = attacker;
    }

    /**
     * Sets victim unit for the current Combat instance
     */
    public void setVictim(IUnit victim) {
        this.victim = victim;
    }
}
