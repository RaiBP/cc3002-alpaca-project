package model.factory.unit;

import model.units.IUnit;

/**
 * This interface represents all unit factories.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public interface IUnitFactory {

    /**
     * @return default unit for corresponding unit type
     */
    IUnit getDefaultUnit();
}
