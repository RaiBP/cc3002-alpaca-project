package model.factory.unit;

import model.units.IUnit;
import model.units.Sorcerer;

public class SorcererFactory extends AbstractUnitFactory{

    @Override
    public IUnit getDefaultUnit() {
        return new Sorcerer();
    }
}
