package model.combat;

import model.items.IEquipableItem;
import model.items.weapons.IWeapon;
import model.units.IUnit;
import model.items.weapons.AbstractWeapon;

public class Combat {
    private IUnit attacker;
    private IUnit victim;

    public Combat(IUnit attacker, IUnit victim) {
        this.attacker = attacker;
        this.victim = victim;
    }

    public void doCombat() {
        while (victim.isAlive() && attacker.isAlive()) {
            attack();
        }
    }

    private void attack() {
        IEquipableItem weapon = attacker.getEquippedItem();

        boolean hitPointCondition = victim.isAlive() && attacker.isAlive();
        boolean weaponCondition = weapon instanceof IWeapon;
        boolean rangeCondition = weapon.getMinRange() <= victim.getLocation().distanceTo(attacker.getLocation()) &&
                victim.getLocation().distanceTo(attacker.getLocation()) <= weapon.getMaxRange();

        if (hitPointCondition && weaponCondition && rangeCondition) {

            // get attacker's weapon power
            int damage = getDamage();

            // reduce victim's HP according to weapon's power
            victim.setHitPoints(victim.getCurrentHitPoints() - damage);

            // victim now counter attacks
            Combat counterAttack = new Combat(victim, attacker);
            counterAttack.attack();

        }
    }

    private int getDamage() {
        IWeapon attackerWeapon = (IWeapon) attacker.getEquippedItem();
        IEquipableItem victimWeapon = victim.getEquippedItem();

        if (victimWeapon instanceof IWeapon) {
            String victimWeaponType = ((IWeapon) victimWeapon).getWeaponType();

            if (victimWeaponType.equals(attackerWeapon.getStrength())) {
                return (int) (((IEquipableItem) attackerWeapon).getPower() * 1.5);
            }

            if (victimWeaponType.equals(attackerWeapon.getWeakness())) {
                return ((IEquipableItem) attackerWeapon).getPower() - 20;
            }
        }

        return ((IEquipableItem) attackerWeapon).getPower();

    }

    public IUnit getAttacker() {
        return attacker;
    }

    public IUnit getVictim() {
        return victim;
    }

    public void setAttacker(IUnit attacker) {
        this.attacker = attacker;
    }

    public void setVictim(IUnit victim) {
        this.victim = victim;
    }
}
