package model.factory.unit;

import model.units.IUnit;
import model.units.SwordMaster;

public class SwordMasterFactory extends AbstractUnitFactory {

    @Override
    public IUnit getDefaultUnit() {
        return new SwordMaster();
    }
}
