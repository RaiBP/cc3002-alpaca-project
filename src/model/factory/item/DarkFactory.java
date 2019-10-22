package model.factory.item;

import model.items.IEquipableItem;
import model.items.magic.Dark;

public class DarkFactory extends AbstractItemFactory {

    @Override
    public IEquipableItem getDefaultItem() {
        return new Dark();
    }
}
