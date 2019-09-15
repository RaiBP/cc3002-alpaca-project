package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents an <i>Sorcerer</i> type unit.
 * <p>
 * This kind of unit <b>can only use magic spells</b>.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public class Sorcerer extends AbstractUnit {

    /**
     * Creates a new Sorcerer unit.
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
    public Sorcerer(final int hitPoints, final int movement, final Location position, final IEquipableItem... items) {
        super(hitPoints, movement, position, 3, items);
    }

    /**
     * Sets the currently equipped item of this unit.
     * <p>
     * The <i>Sorcerer</i> can <b>only equip Spells</b>.
     *
     * @param item
     *     the item to equip
     */
    @Override
    public void equipItem(IEquipableItem item) {
        if (!(getItems().contains(item))) {
            List<IEquipableItem> itemsCopy = List.copyOf(getItems());
            if (addItemToInventory(item)) {
                item.equipOnSorcerer(this);
                if (getEquippedItem() == null) {
                    setItemList(itemsCopy);
                }
            }
        }
        else {
            item.equipOnSorcerer(this);
        }
    }

}
