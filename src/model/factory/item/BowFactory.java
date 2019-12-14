package model.factory.item;

import model.items.IEquipableItem;
import model.items.weapons.Bow;

/**
 * Factory class for creating Bow type weapons.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class BowFactory extends AbstractItemFactory {

    /**
     * @return default Bow type weapon
     */
    @Override
    public IEquipableItem getDefaultItem() {
        return new Bow();
    }
}
