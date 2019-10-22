package model.factory.item;

import model.items.IEquipableItem;
import model.items.magic.Light;

public class LightFactory extends AbstractItemFactory {

    @Override
    public IEquipableItem getDefaultItem() {
        return new Light();
    }
}
