package model.items.tools;

import model.units.*;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units but cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends AbstractTool {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipOnSwordMaster(SwordMaster swordMaster) { }

  @Override
  public void equipOnArcher(Archer archer) { }

  @Override
  public void equipOnCleric(Cleric cleric) { cleric.setEquippedItem(this); }

  @Override
  public void equipOnFighter(Fighter fighter) { }

  @Override
  public void equipOnHero(Hero hero) { }

  @Override
  public void equipOnSorcerer(Sorcerer sorcerer) { }

  @Override
  public void useOn(IUnit targetUnit) {
    double distance = getOwner().getLocation().distanceTo(targetUnit.getLocation());

    if (targetUnit.isAlive() && !(isOutOfRange(distance))) {
      int healedHP = targetUnit.getCurrentHitPoints() + getPower();
      targetUnit.setHitPoints(Math.min(healedHP, targetUnit.getMaxHitPoints()));
    }
  }
}
