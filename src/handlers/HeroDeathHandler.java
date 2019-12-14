package handlers;

import model.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for the event of a hero dying. This handler will remove all the units from the affected tactician if its not
 * currently its turn. If the affected tactician is the current Turn Owner, only the hero status will be changed and the
 * units will be removed when its turn is finished.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class HeroDeathHandler implements PropertyChangeListener {
    Tactician tactician;

    /**
     * Creates a handler object
     * @param tactician        tactician whom will receive the dying hero information
     */
    public HeroDeathHandler(Tactician tactician) {
        this.tactician = tactician;
    }

    /**
     * Handles the <b>event</b> of a hero dying, telling the tactician to change it's <b>hero status</b> and to remove
     * all its units (if it's not its turn).
     *
     * @param evt      fired event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        tactician.removeUnit((IUnit) evt.getSource());
        if (!(tactician.isTurnOwner())) {
            tactician.removeAllCurrentUnits();
        }
        tactician.setHeroStatus(true);
    }
}
