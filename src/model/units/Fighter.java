package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author  Raimundo Becerra Parra
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  /**
   * Creates a new Fighter unit.
   *
   * @param hitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param location
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Fighter(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    if (!(getItems().contains(item))) {
      if (addItemToInventory(item)) {
        item.equipOnFighter(this);
      }
    }
    else {
      item.equipOnFighter(this);
    }
  }
}
