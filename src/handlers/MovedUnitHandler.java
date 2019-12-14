package handlers;

import controller.GameController;
import model.map.Location;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for the event of moving a unit. This handler will unassign the former cell of the moved unit at the game
 * controller level.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class MovedUnitHandler implements PropertyChangeListener {
    GameController gameController;

    /**
     * Creates a handler object
     * @param gameController        game controller whom will receive the moved unit information
     */
    public MovedUnitHandler(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Handles the <b>event</b> of a moved unit, telling the game controller to unassign the former cell in which the
     * unit resided.
     * @param evt      fired event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        gameController.unassignCell((Location) evt.getOldValue());
    }
}
