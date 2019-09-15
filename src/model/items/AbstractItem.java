package model.items;

import model.items.magic.Anima;
import model.items.magic.Dark;
import model.items.magic.Light;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private int power;
  protected int maxRange;
  protected int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

    @Override
  public void equipTo(final IUnit unit) {
    unit.setEquippedItem(this);
    owner = unit;
  }

  @Override
  public IUnit getOwner() {
    return owner;
  }

  @Override
  public void setOwner(IUnit unit) {
    owner = unit;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public void setPower(int power) {
    this.power = power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  @Override
  public boolean isOutOfRange(double distance) {
    return distance > maxRange || distance < minRange;
  }

  protected abstract int attack(IUnit targetUnit);

  @Override
  public abstract void equipOnSwordMaster(SwordMaster swordMaster);

  @Override
  public abstract void equipOnArcher(Archer archer);

  @Override
  public abstract void equipOnCleric(Cleric cleric);

  @Override
  public abstract void equipOnFighter(Fighter fighter);

  @Override
  public abstract void equipOnHero(Hero hero);

  @Override
  public abstract void equipOnSorcerer(Sorcerer sorcerer);

  protected abstract int receiveAxeAttack(Axe axe);

  protected abstract int receiveBowAttack(Bow bow);

  protected abstract int receiveSpearAttack(Spear spear);

  protected abstract int receiveSwordAttack(Sword sword);

  protected abstract int receiveDarkSpellAttack(Dark dark);

  protected abstract int receiveLightSpellAttack(Light light);

  protected abstract int receiveAnimaSpellAttack(Anima anima);

  @Override
  public int getDamageFromAttack(IUnit targetUnit) { return attack(targetUnit);}

  @Override
  public int getDamageFromBowAttack(Bow bow) { return receiveBowAttack(bow); }

  @Override
  public int getDamageFromDarkSpellAttack(Dark dark) { return receiveDarkSpellAttack(dark); }

  @Override
  public int getDamageFromLightSpellAttack(Light light) { return receiveLightSpellAttack(light); }

  @Override
  public int getDamageFromAnimaSpellAttack(Anima anima) { return receiveAnimaSpellAttack(anima); }

  @Override
  public int getDamageFromAxeAttack(Axe axe) { return receiveAxeAttack(axe); }

  @Override
  public int getDamageFromSpearAttack(Spear spear) { return receiveSpearAttack(spear); }

  @Override
  public int getDamageFromSwordAttack(Sword sword) { return receiveSwordAttack(sword); }


  /**
   * Two objects can be equal regardless of their ownership
   */
  @Override
  public boolean equals(Object object){
    if (object instanceof AbstractItem) {
      AbstractItem item = (AbstractItem) object;
      return item.name.equals(name) && item.power == power && item.minRange == minRange &&
              item.maxRange == maxRange;
    }
    return false;
  }

}
