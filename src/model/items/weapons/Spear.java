package model.items.weapons;

import model.units.*;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractWeapon {

  /**
   * Creates a new Spear item
   *
   * @param name
   *     the name of the Spear
   * @param power
   *     the damage of the Spear
   * @param minRange
   *     the minimum range of the Spear
   * @param maxRange
   *     the maximum range of the Spear
   */
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /**
   * Creates Default Spear item:
   *  - 15 Power Units
   *  - Minimum range of 1 cell
   *  - Maximum range of 3 cells
   */
  public Spear() {
    this("Default Spear", 15, 1, 3);
  }

  @Override
  protected int attack(IUnit targetUnit) {
    return targetUnit.receiveSpearAttack(this);
  }

  @Override
  protected int receiveAxeAttack(Axe axe) { return (int) (1.5 * axe.getPower()); }

  @Override
  protected int receiveSpearAttack(Spear spear) { return spear.getPower(); }

  @Override
  protected int receiveSwordAttack(Sword sword) { return Math.max(0, sword.getPower() - 20); }

  @Override
  public void equipOnSwordMaster(SwordMaster swordMaster) { }

  @Override
  public void equipOnArcher(Archer archer) { }

  @Override
  public void equipOnCleric(Cleric cleric) { }

  @Override
  public void equipOnFighter(Fighter fighter) { }

  @Override
  public void equipOnHero(Hero hero) { hero.setEquippedItem(this); }

}
