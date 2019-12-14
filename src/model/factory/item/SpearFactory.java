package model.factory.item;

import model.items.IEquipableItem;
import model.items.weapons.Spear;

/**
 * Factory class for creating Spear type weapons.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class SpearFactory extends AbstractItemFactory {

    /**
     * @return default Spear type weapon
     */
    @Override
    public IEquipableItem getDefaultItem() {
        return new Spear();
    }
}
