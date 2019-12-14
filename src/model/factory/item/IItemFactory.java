package model.factory.item;

import model.items.IEquipableItem;

/**
 * This interface represents all item factories.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public interface IItemFactory {

    /**
     * @return default item for corresponding item type
     */
    IEquipableItem getDefaultItem();
}
