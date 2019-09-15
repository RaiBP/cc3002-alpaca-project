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
 * This interface represents the <i>items</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the items are defined here.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

  /**
   * @param distance
   *     distance from item's owner to target unit
   * @return true if <b>distance</b> parameter is smaller than <b>minRange</b> or greater than <b>maxRange</b>
   */
  boolean isOutOfRange(double distance);

  /**
   * Equips item to Sword Master type unit
   * @param swordMaster
   *     Sword Master type unit
   */
  void equipOnSwordMaster(SwordMaster swordMaster);

  /**
   * Equips item to Archer type unit
   * @param archer
   *     Archer type unit
   */
  void equipOnArcher(Archer archer);

  /**
   * Equips item to Cleric type unit
   * @param cleric
   *     Cleric type unit
   */
  void equipOnCleric(Cleric cleric);

  /**
   * Equips item to Fighter type unit
   * @param fighter
   *     Fighter type unit
   */
  void equipOnFighter(Fighter fighter);

  /**
   * Equips item to Hero type unit
   * @param hero
   *     Hero type unit
   */
  void equipOnHero(Hero hero);

  /**
   * Equips item to Sorcerer type unit
   * @param sorcerer
   *     Sorcerer type unit
   */
  void equipOnSorcerer(Sorcerer sorcerer);

  /**
   * @param targetUnit
   *     Target of item's attack
   * @return total damage done to target unit. -1 if attack can't be performed
   */
  int getDamageFromAttack(IUnit targetUnit);

  /**
   * @param bow
   *     Bow item from which the attack will be received
   * @return total damage done to the item's owner due to <b>bow</b>'s attack
   */
  int getDamageFromBowAttack(Bow bow);

  /**
   * @param dark
   *     Dark spell item from which the attack will be received
   * @return total damage done to the item's owner due to <b>dark</b> spell's attack
   */
  int getDamageFromDarkSpellAttack(Dark dark);

  /**
   * @param light
   *     Light spell item from which the attack will be received
   * @return total damage done to the item's owner due to <b>light</b> spell's attack
   */
  int getDamageFromLightSpellAttack(Light light);

  /**
   * @param anima
   *     Anima spell item from which the attack will be received
   * @return total damage done to the item's owner due to <b>anima</b> spell's attack
   */
  int getDamageFromAnimaSpellAttack(Anima anima);

  /**
   * @param axe
   *     Axe item from which the attack will be received
   * @return total damage done to the item's owner due to <b>axe</b>'s attack
   */
  int getDamageFromAxeAttack(Axe axe);

  /**
   * @param spear
   *     Spear item from which the attack will be received
   * @return total damage done to the item's owner due to <b>spear</b>'s attack
   */
  int getDamageFromSpearAttack(Spear spear);

  /**
   * @param sword
   *     Sword item from which the attack will be received
   * @return total damage done to the item's owner due to <b>sword</b>'s attack
   */
  int getDamageFromSwordAttack(Sword sword);

  /**
   * Set the item's ownership to <b>unit</b>.
   * @param unit
   *     Unit whom the item's ownership will be transferred to
   */
  void setOwner(IUnit unit);

  /**
   * Sets item's power
   * @param power
   *     Power to be set on item
   */
  void setPower(int power);

  /**
   * Generic method for using item on a target unit. If item is a weapon, a combat will take place between the item's
   * owner and the target unit. If item is a tool, a tool-specific action will be performed on the target unit.
   * @param targetUnit
   *     Target unit on which the item's action will be performed
   */
  void useOn(IUnit targetUnit);
}
