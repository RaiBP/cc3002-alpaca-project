package model.units;

import model.factory.item.AxeFactory;
import model.factory.item.IItemFactory;
import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author  Raimundo Becerra Parra
 * @since 1.0
 */
public class Fighter extends AbstractUnit {
  private static IItemFactory axeFactory = new AxeFactory();

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
   * Creates default Fighter unit:
   *  - 75 HP
   *  - Max. 2 cells per move
   *  - Default Axe in item list
   */
  public Fighter() {
    this(75, 2, null, axeFactory.getDefaultItem());
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
      List<IEquipableItem> itemsCopy = List.copyOf(getItems());
      if (addItemToInventory(item)) {
        item.equipOnFighter(this);
        if (getEquippedItem() == null) {
          setItemList(itemsCopy);
        }
      }
    }
    else {
      item.equipOnFighter(this);
    }
  }
}
