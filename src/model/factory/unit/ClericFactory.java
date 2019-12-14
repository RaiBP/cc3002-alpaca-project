package model.factory.unit;

import model.units.Cleric;
import model.units.IUnit;

/**
 * Factory class for creating Cleric type units.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class ClericFactory extends AbstractUnitFactory {

    /**
     * @return default Cleric type units
     */
    @Override
    public IUnit getDefaultUnit() {
        return new Cleric();
    }
}
