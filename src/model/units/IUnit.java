package model.units;

import java.util.List;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

  /**
   * Sets the currently equipped item of this unit.
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
   * @return whether unit is alive or not
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
   * Sets a new location for this unit,
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
   */
  void moveTo(Location targetLocation);

  /**
   * Combats other unit.
   * <p>
   * Units in combat attack each other until one or the other is dead.
   */
  void combatUnit(IUnit victim);
}
