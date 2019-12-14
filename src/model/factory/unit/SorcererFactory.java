package model.factory.unit;

import model.units.IUnit;
import model.units.Sorcerer;

/**
 * Factory class for creating Sorcerer type units.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class SorcererFactory extends AbstractUnitFactory{

    /**
     * @return default Sorcerer type units
     */
    @Override
    public IUnit getDefaultUnit() {
        return new Sorcerer();
    }
}
