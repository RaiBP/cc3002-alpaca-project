package model.factory.item;

import model.items.IEquipableItem;
import model.items.weapons.Bow;

public class BowFactory extends AbstractItemFactory {

    @Override
    public IEquipableItem getDefaultItem() {
        return new Bow();
    }
}
