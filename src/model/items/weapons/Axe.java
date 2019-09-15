package model.items.weapons;

import model.units.*;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak against swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends AbstractWeapon {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the Axe
   * @param minRange
   *     the minimum range of the Axe
   * @param maxRange
   *     the maximum range of the Axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  protected int attack(IUnit targetUnit) {
    return targetUnit.receiveAxeAttack(this);
  }

  @Override
  protected int receiveAxeAttack(Axe axe) {
    return axe.getPower();
  }

  @Override
  protected int receiveSpearAttack(Spear spear) {
    return Math.max(0, spear.getPower() - 20);
  }

  @Override
  protected int receiveSwordAttack(Sword sword) {
    return (int) (1.5 * sword.getPower());
  }

  @Override
  public void equipOnSwordMaster(SwordMaster swordMaster) { }

  @Override
  public void equipOnArcher(Archer archer) { }

  @Override
  public void equipOnCleric(Cleric cleric) { }

  @Override
  public void equipOnFighter(Fighter fighter) { fighter.setEquippedItem(this); }

  @Override
  public void equipOnHero(Hero hero) { }

}
