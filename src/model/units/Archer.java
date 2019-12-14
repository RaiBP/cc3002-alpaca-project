package model.units;

import model.factory.item.BowFactory;
import model.factory.item.IItemFactory;
import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents an <i>Archer</i> type unit.
 * <p>
 * This kind of unit <b>can only use bows</b>.
 *
 * @author  Raimundo Becerra Parra
 * @since 1.0
 */
public class Archer extends AbstractUnit {
  private static IItemFactory bowFactory = new BowFactory();

  /**
   * Creates a new Archer unit.
   *
   * @param hitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param position
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Archer(final int hitPoints, final int movement, final Location position, final IEquipableItem... items) {
    super(hitPoints, movement, position, 3, items);
  }

  /**
   * Creates default Archer unit:
   *  - 50 HP
   *  - Max. 2 cells per move
   *  - Default Bow in item list
   */
  public Archer() {
    this(50, 2, null, bowFactory.getDefaultItem());
  }

  /**
   * Sets the currently equipped item of this unit.
   * <p>
   * The <i>Archer</i> can <b>only equip Bows</b>.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(IEquipableItem item) {
    if (item == null) {
      equippedItem = null;
      return;
    }
    if (!(getItems().contains(item))) {
      List<IEquipableItem> itemsCopy = List.copyOf(getItems());
      if (addItemToInventory(item)) {
        item.equipOnArcher(this);
        if (getEquippedItem() == null) {
          setItemList(itemsCopy);
        }
      }
    }
    else {
      item.equipOnArcher(this);
    }
  }
}
