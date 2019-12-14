package model.factory.item;

import model.items.IEquipableItem;
import model.items.tools.Staff;

/**
 * Factory class for creating Staff type tools.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class StaffFactory extends AbstractItemFactory {

    /**
     * @return default Staff type tool
     */
    @Override
    public IEquipableItem getDefaultItem() {
        return new Staff()
                ;
    }
}
