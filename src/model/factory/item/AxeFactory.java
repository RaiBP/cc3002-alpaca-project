package model.factory.item;

import model.items.IEquipableItem;
import model.items.weapons.Axe;

/**
 * Factory class for creating Axe type weapons.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class AxeFactory extends AbstractItemFactory {

    /**
     * @return default Axe type weapon
     */
    @Override
    public IEquipableItem getDefaultItem() {
        return new Axe();
    }
}
