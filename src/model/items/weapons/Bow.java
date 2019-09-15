package model.items.weapons;

import model.units.*;

/**
 * @author Ignacio Slater Muñoz
 * @since
 */
public class Bow extends AbstractWeapon {

  /**
   * Creates a new bow.
   * <p>
   * Bows are weapons that can't attack adjacent units, so it's minimum range must me greater than
   * one.
   *
   * @param name
   *     the name of the bow
   * @param power
   *     the damage power of the bow
   * @param minRange
   *     the minimum range of the bow
   * @param maxRange
   *     the maximum range of the bow
   */
  public Bow(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
    this.minRange = Math.max(minRange, 2);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  @Override
  protected int attack(IUnit targetUnit) {
    return targetUnit.receiveBowAttack(this);
  }

  @Override
  protected int receiveAxeAttack(Axe axe) { return axe.getPower(); }

  @Override
  protected int receiveSpearAttack(Spear spear) { return spear.getPower(); }

  @Override
  protected int receiveSwordAttack(Sword sword) { return sword.getPower(); }

  @Override
  public void equipOnSwordMaster(SwordMaster swordMaster) { }

  @Override
  public void equipOnArcher(Archer archer) { archer.setEquippedItem(this); }

  @Override
  public void equipOnCleric(Cleric cleric) { }

  @Override
  public void equipOnFighter(Fighter fighter) { }

  @Override
  public void equipOnHero(Hero hero) { }

}
