package model.factory.item;

import model.items.IEquipableItem;
import model.items.magic.Anima;

/**
 * Factory class for creating Anima type spells.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class AnimaFactory extends AbstractItemFactory {

    /**
     * @return default Anima type spell
     */
    @Override
    public IEquipableItem getDefaultItem() {
        return new Anima();
    }
}
