package handlers;

import controller.GameController;
import model.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for the event of a tactician losing all its units. This handler will remove said tactician from the game via
 * the game controller.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class RetiringTacticianHandler implements PropertyChangeListener {
    GameController gameController;

    /**
     * Creates a handler object
     * @param gameController        game controller whom will receive the retiring tactician information
     */
    public RetiringTacticianHandler(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Handles the <b>event</b> of a tactician losing all its units, telling the game controller to remove said
     * tactician from the game.
     * @param evt      fired event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        gameController.removeTactician(((Tactician) evt.getSource()).getName());
    }
}
