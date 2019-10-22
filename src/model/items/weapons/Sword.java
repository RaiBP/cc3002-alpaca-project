package model.items.weapons;

import model.units.*;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends AbstractWeapon {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /**
   * Creates Default Sword item:
   *  - 10 Power Units
   *  - Minimum range of 1 cell
   *  - Maximum range of 2 cells
   */
  public Sword() {
    this("Default Sword", 10, 1, 2);
  }

  @Override
  protected int attack(IUnit targetUnit) {
    return targetUnit.receiveSwordAttack(this);
  }

  @Override
  protected int receiveAxeAttack(Axe axe) {
    return Math.max(0, axe.getPower() - 20);
  }

  @Override
  protected int receiveSpearAttack(Spear spear) {
    return (int) (1.5 * spear.getPower());
  }

  @Override
  protected int receiveSwordAttack(Sword sword) {
    return sword.getPower();
  }

  @Override
  public void equipOnSwordMaster(SwordMaster swordMaster) { swordMaster.setEquippedItem(this); }

  @Override
  public void equipOnArcher(Archer archer) { }

  @Override
  public void equipOnCleric(Cleric cleric) { }

  @Override
  public void equipOnFighter(Fighter fighter) { }

  @Override
  public void equipOnHero(Hero hero) { }

}
