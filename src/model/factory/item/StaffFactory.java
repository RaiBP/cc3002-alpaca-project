package model.factory.item;

import model.items.IEquipableItem;
import model.items.tools.Staff;

public class StaffFactory extends AbstractItemFactory {

    @Override
    public IEquipableItem getDefaultItem() {
        return new Staff()
                ;
    }
}
