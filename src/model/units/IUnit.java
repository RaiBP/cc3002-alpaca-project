package model.units;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import model.items.IEquipableItem;
import model.items.magic.Anima;
import model.items.magic.Dark;
import model.items.magic.Light;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public interface IUnit {

  /**
   * Sets the currently equipped item of this unit
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);


  /**
   * @return maximum hit points of the unit
   */
  int getMaxHitPoints();

  /**
   * @return current hit points of the unit
   */
  int getCurrentHitPoints();

  /**
   * @return true if unit is alive, false if not
   */
  boolean isAlive();

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @param hitPoints
   *     set hitPoints for this unit
   */
  void setHitPoints(int hitPoints);

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit
   * @param location
   *      new unit location. Must be a valid location.
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   * @return true if unit was moved successfully, false if not
   */
  boolean moveTo(Location targetLocation);

  /**
   * @param axe
   *     Axe item from which the attack will be received
   * @return total damage done to the unit due to <b>axe</b>'s attack
   */
  int receiveAxeAttack(Axe axe);

  /**
   * @param bow
   *     Bow item from which the attack will be received
   * @return total damage done to the unit due to <b>bow</b>'s attack
   */
  int receiveBowAttack(Bow bow);

  /**
   * @param spear
   *     Spear item from which the attack will be received
   * @return total damage done to the unit due to <b>spear</b>'s attack
   */
  int receiveSpearAttack(Spear spear);

  /**
   * @param sword
   *     Sword item from which the attack will be received
   * @return total damage done to the unit due to <b>sword</b>'s attack
   */
  int receiveSwordAttack(Sword sword);

  /**
   * @param anima
   *     Anima spell item from which the attack will be received
   * @return total damage done to the unit due to <b>anima</b> spell's attack
   */
  int receiveAnimaSpellAttack(Anima anima);

  /**
   * @param dark
   *     Dark spell item from which the attack will be received
   * @return total damage done to the unit due to <b>dark</b> spell's attack
   */
  int receiveDarkSpellAttack(Dark dark);

  /**
   * @param light
   *     Light spell item from which the attack will be received
   * @return total damage done to the unit due to <b>light</b> spell's attack
   */
  int receiveLightSpellAttack(Light light);

  /**
   * @param targetUnit
   *     Target of unit's attack
   * @return total damage done to target unit. -1 if attack can't be performed
   */
  int getDamage(IUnit targetUnit);

  /**
   * Generic method for using equipped item on a target unit. If item is a weapon, a combat will take place between the
   * unit and the target unit. If item is a tool, a tool-specific action will be performed on the target unit.
   * @param targetUnit
   *     Target unit on which the equipped item's action will be performed
   */
  void useEquippedItemOn(IUnit targetUnit);

  /**
   * Adds item to the unit's inventory list. The unit's inventory must not be full for this operation to be successful
   *
   * @param item
   *     item to be added to inventory
   * @return true if add operation is successful, false in the contrary case
   */
  boolean addItemToInventory(IEquipableItem item);

  /**
   * Gives <b>item</b> to <b>receivingUnit</b>. The following conditions must be achieved for this operation to be
   * successful:
   *      - <b>item</b> must be in the unit's inventory
   *      - the <b>receivingUnit</b>'s inventory must not be full
   *      - <b>item</b> must not be null
   * If operation is successful, the <b>item</b> will be removed from the unit's inventory and added to the
   * <b>receivingUnit</b>'s inventory.
   * @param receivingUnit
   *      Unit that will receive the specified item
   * @param item
   *      Item to be given
   */
  void giveItem(IUnit receivingUnit, IEquipableItem item);

  /**
   * Sets <b>list</b> as the unit's new inventory list
   * @param list      list of items
   */
  void setItemList(List<IEquipableItem> list);

  /**
   * Performs combat between the unit and <b>targetUnit</b>
   * @param targetUnit    unit whom to perform combat to
   */
  void doCombat(IUnit targetUnit);

  /**
   * Adds listener to unit death notification
   * @param listener    notification listener
   */
  void addUnitDeathListener(PropertyChangeListener listener);

  /**
   * Removes listener from unit death notification
   * @param listener    notification listener
   */
  void removeUnitDeathListener(PropertyChangeListener listener);

  /**
   * Adds listener to Hero death notification
   * @param listener    notification listener
   */
  void addHeroDeathListener(PropertyChangeListener listener);

  /**
   * Remove listener from Hero death notification
   * @param listener    notification listener
   */
  void removeHeroDeathListener(PropertyChangeListener listener);

  /**
   * @return unit death notification object
   */
  PropertyChangeSupport getUnitDeathNotification();

  /**
   * @return hero death notification object
   */
  PropertyChangeSupport getHeroDeathNotification();
}
