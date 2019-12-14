package model.factory.unit;

import model.units.Archer;
import model.units.IUnit;

/**
 * Factory class for creating Archer type units.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class ArcherFactory extends AbstractUnitFactory {

    /**
     * @return default Archer type units
     */
    @Override
    public IUnit getDefaultUnit() {
        return new Archer();
    }
}
