package model.factory.unit;

import model.units.Alpaca;
import model.units.IUnit;

public class AlpacaFactory extends AbstractUnitFactory {

    @Override
    public IUnit getDefaultUnit() {
        return new Alpaca();
    }
}
