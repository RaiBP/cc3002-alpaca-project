package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an <i>Alpaca</i> type unit.
 * <p>
 * This are a special kind of unit that can carry an unlimited amount of items but can't use any of
 * them.
 *
 * @author  Raimundo Becerra Parra
 * @since 1.0
 */
public class Alpaca extends AbstractUnit {

  /**
   * Creates a new Alpaca.
   *
   * @param hitPoints
   *     the amount of damage this unit can receive
   * @param movement
   *     number of cells the unit can move
   * @param location
   *     current position of the unit
   * @param items
   *     the items carried by this unit
   */
  public Alpaca(final int hitPoints, final int movement, final Location location,
      final IEquipableItem... items) {
    super(hitPoints, movement, location, Integer.MAX_VALUE, items);
  }

  /**
  * Creates default Alpaca unit:
  *  - 100 HP
  *  - Max. 2 cells per move
  *  - No default item in item list
  */
  public Alpaca() {
      this(100, 2, null);
  }

    /**
   * {@inheritDoc}
   * <p>
   * The <i>Alpaca</i> cannot equip any item.
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    // Method body intentionally left empty
  }

  /**
   * {@inheritDoc}
   * <p>
   * The Alpaca has no restrictions on the number of items that can be added
   */
  @Override
  public boolean addItemToInventory(IEquipableItem item) {
    if (item != null && item.getOwner() == null) {
      List<IEquipableItem> list = new ArrayList<>(getItems());
      list.add(item);
      setItemList(list);
      item.setOwner(this);
      return true;
    }
    return false;
  }
}
