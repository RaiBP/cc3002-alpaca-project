package model.factory.unit;

import model.units.IUnit;
import model.units.SwordMaster;

/**
 * Factory class for creating Sword Master type units.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class SwordMasterFactory extends AbstractUnitFactory {

    /**
     * @return default Sword Master type units
     */
    @Override
    public IUnit getDefaultUnit() {
        return new SwordMaster();
    }
}
