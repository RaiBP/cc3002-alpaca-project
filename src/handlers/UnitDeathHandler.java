package handlers;

import model.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for the event of a unit dying (except for a hero). This handler will remove the dying unit from the affected
 * tactician's list of units.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class UnitDeathHandler implements PropertyChangeListener {
    Tactician tactician;

    /**
     * Creates a handler object
     * @param tactician        tactician whom will receive the dying unit information
     */
    public UnitDeathHandler(Tactician tactician) {
        this.tactician = tactician;
    }

    /**
     * Handles the <b>event</b> of a unit dying, telling the tactician to remove said unit from its list of units.
     *
     * @param evt      fired event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        tactician.removeUnit((IUnit) evt.getSource());
    }
}
