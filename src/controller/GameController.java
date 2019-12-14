package controller;

import java.beans.PropertyChangeListener;
import java.util.*;

import handlers.MovedUnitHandler;
import handlers.RetiringTacticianHandler;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class GameController {
    private List<Tactician> tacticians = new ArrayList<>();
    private List<String> winners = new ArrayList<>();
    private Map<Location, Tactician> assignedCells = new HashMap<>();
    private Field field = new Field();
    private Tactician turnOwner;
    private long seed;
    private List<String> turnOrder;
    private int turnNumber;
    private int roundNumber;
    private int maxRounds;
    private int numberOfPlayers;
    private boolean firstGame = true;
    private boolean currentGameFinished = false;
    private boolean currentGameStarted = false;

    private PropertyChangeListener retiringTacticianHandler = new RetiringTacticianHandler(this),
            movedUnitHandler = new MovedUnitHandler(this);
    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayers the number of players for this game
     * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
     */
    public GameController(int numberOfPlayers, int mapSize) {
        this(numberOfPlayers, mapSize, new Random().nextLong());
    }

    /**
     * Creates the controller for a new game, with specific seed.
     *
     * @param numberOfPlayers the number of players for this game
     * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
     * @param seed            the seed to be used in random experiments
     */
    public GameController(int numberOfPlayers, int mapSize, long seed) {
        this.numberOfPlayers = numberOfPlayers;

        setSeed(seed);
        field.setSeed(seed);
        generateMap(mapSize);

        generateTacticians(numberOfPlayers);
        initTurnOrder();
        assignCells();
        initUnits();
        turnOwner = getTacticianByName(getTurnOrder().get(0));
        turnNumber = 0;
        roundNumber = 0;
    }

    private void subscribeToRetiringTacticianNotification(Tactician tactician) {
        tactician.addRetiringTacticianListener(retiringTacticianHandler);
    }

    private void unsubscribeToRetiringTacticianNotification(Tactician tactician) {
        tactician.removeRetiringTacticianListener(retiringTacticianHandler);
    }

    private void subscribeToMovedUnitNotification(Tactician tactician) {
        tactician.addMovedUnitListener(movedUnitHandler);
    }

    private void unsubscribeToMovedUnitNotification(Tactician tactician) {
        tactician.removeMovedUnitListener(movedUnitHandler);
    }

    private void subscribeToAllNotifications(Tactician tactician) {
        subscribeToMovedUnitNotification(tactician);
        subscribeToRetiringTacticianNotification(tactician);
    }

    private void unsubscribeToAllNotifications(Tactician tactician) {
        unsubscribeToMovedUnitNotification(tactician);
        unsubscribeToRetiringTacticianNotification(tactician);
    }

    /**
     * Getter for cells in the GameMap that have units corresponding to the specified <b>tactician</b>.
     *
     * @param tactician         the tactician from whom to extract the cells
     * @return                  list of cells that have units corresponding to specified <b>tactician</b>
     */
    public List<Location> getTacticianCells(Tactician tactician) {
        List<Location> cells = new ArrayList<>();
        assignedCells.forEach((k, val) -> {
            if (val.equals(tactician)) {
                cells.add(k);
            }
        });
        return cells;
    }

    /**
     * Getter for cells in the GameMap that have units corresponding to the <b>TurnOwner</b>.
     *
     * @return                  list of cells that have units corresponding to the <b>TurnOwner</b>
     */
    public List<Location> getTurnOwnerCells() {
        return getTacticianCells(turnOwner);
    }

    private void assignCells() {
        // to be implemented in 3.0
        // placeholder for current version: each Tactician will get ONE random cell each

        for (Tactician tactician : tacticians) {
            Location randomCell = field.getRandomCell();

            while (assignedCells.get(randomCell) != null) {
                randomCell = field.getRandomCell();
            }

            assignedCells.put(randomCell, tactician);
        }
    }

    private void initUnits() {
        for (int j = 0; j < getNumberOfPlayers(); j++) {
            getTurnOwner().positionUnits(getTurnOwnerCells());
            endTurn();
        }
    }

    /**
     * Sets the location of the <b>tactician</b>'s unit corresponding to the index <b>unitIndex</b> to <b>cell</b>.
     */
    public void setUnitPositionByIndexIn(Tactician tactician, int unitIndex, Location cell) {
        IUnit unit = tactician.getUnitByIndex(unitIndex);
        if (cell != null) {
            IUnit previousUnitInCell = cell.getUnit();
            if (previousUnitInCell != null) {
                previousUnitInCell.setLocation(null);
            }
            cell.setUnit(unit);
            assignedCells.put(cell, tactician);
        }
        unit.setLocation(cell);
    }

    /**
     * Removes <b>unit</b> from the <b>tactician</b>.
     */
    public void removeUnitFromTactician(Tactician tactician, IUnit unit) {
        assignedCells.remove(unit.getLocation());
        tactician.removeUnit(unit);
        for (Location cell : getTurnOwnerCells()) {
            if (cell.getUnit() == unit) {
                cell.setUnit(null);
            }
        }
    }

    private void generateMap(int mapSize) {
        List<Location> cells = new ArrayList<>();
        for (int row = 0; row < mapSize; row++) {
            for (int column = 0; column < mapSize; column++) {
                cells.add(new Location(row, column));
            }
        }
        field.addCells(false, cells.toArray(new Location[cells.size()]));
    }

    /**
     * Sets the seed to be used in random experiments.
     */
    public void setSeed(long seed) {
        this.seed = seed;
    }

    private void generateTacticians(int numberOfPlayers) {
        tacticians = new ArrayList<>();
        for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++) {
            Tactician tactician = new Tactician("Player " + playerNumber);
            tactician.setField(field);
            tacticians.add(tactician);
        }
    }

    /**
     * @return the list of all the tacticians participating in the game.
     */
    public List<Tactician> getTacticians() {
        return tacticians;
    }

    /**
     * @return the map of the current game
     */
    public Field getGameMap() {
        return field;
    }

    /**
     * @return the tactician that's currently playing
     */
    public Tactician getTurnOwner() {
        return turnOwner;
    }

    /**
     * @return the number of rounds since the start of the game.
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * @return the maximum number of rounds a match can last
     */
    public int getMaxRounds() {
        return maxRounds;
    }

    /**
     * Finishes the current player's turn.
     */
    public void endTurn() {
        if (currentGameFinished) {
            return;
        }

        if (getTurnOwner().getHeroStatus()) {
            removeTactician(getTurnOwner().getName());
        }

        if (maxTurnsReached()) {
            if (currentGameStarted){
                startNewRound();

                if (maxRoundsReached()) {
                    endGame();
                }
            }
        }
        else {
            setNewTurnOwner();
            turnNumber++;
        }
        getTurnOwner().setSelectedUnit(null);
        getTurnOwner().resetMovedUnits();

    }

    private boolean maxTurnsReached() {
        return turnNumber >= getNumberOfPlayers() - 1;
    }

    private boolean maxRoundsReached() {
        return maxRounds > 0 && (roundNumber >= maxRounds + 1);
    }

    private void startNewRound() {
        initTurnOrder();
        setTurnNumber(0);
        roundNumber++;
    }

    /**
     * Finishes a game started by the initGame method. Winners will be selected in this method.
     */
    public void endGame() {
        int currentMax = 0;
        List<String> currentWinners = new ArrayList<>();
        for (Tactician tactician : tacticians) {
            int numberOfUnits = tactician.getNumberOfUnits();
            if (numberOfUnits >= currentMax) {
                currentMax = numberOfUnits;
                currentWinners.add(tactician.getName());
            }
        }
        winners = currentWinners;
        currentGameFinished = true;
        currentGameStarted = false;
    }

    /**
     * Getter for boolean indicating whether the game started by initGame has finished.
     */
    public boolean isCurrentGameFinished() {
        return currentGameFinished;
    }

    /**
     * Removes a tactician and all of it's units from the game.
     *
     * @param tactician the player to be removed
     */
    public void removeTactician(String tactician) {
        for (Tactician otherTactician : tacticians) {
            if (otherTactician.getName().equals(tactician)) {
                if (turnOrder != null) {
                    removeTacticianFromTurnOrder(otherTactician);
                }
                tacticians.remove(otherTactician);
                otherTactician.removeAllCurrentUnits();
                unsubscribeToAllNotifications(otherTactician);
                if (getNumberOfPlayers() == 1) {
                    endGame();
                }
                return;
            }
        }
    }

    private void removeTacticianFromTurnOrder(Tactician tactician) {
        turnOrder.remove(tactician.getName());
    }

    /**
     * Starts the game.
     *
     * @param maxTurns the maximum number of turns the game can last
     */
    public void initGame(final int maxTurns) {
        if (getNumberOfPlayers() == 1) {
            endGame();
        }
        else {
            currentGameStarted = true;
            if (!firstGame) {
                currentGameFinished = false;
                generateTacticians(numberOfPlayers);
                initTurnOrder();
                clearField();
                assignCells();
                initUnits();
            }
            turnOwner = getTacticianByName(turnOrder.get(0));
            turnOwner.setIsTurnOwner(true);
            tacticians.forEach(Tactician::resetMovedUnits);
            tacticians.forEach(this::subscribeToAllNotifications);
            currentGameFinished = false;
            currentGameStarted = true;
            firstGame = false;
            setMaxRounds(maxTurns);
            setRoundNumber(1);
            setTurnNumber(0);
            winners = new ArrayList<>();
        }
    }

    private void clearField() {
        List<Location> cells = new ArrayList<>(assignedCells.keySet());
        for (Location cell : cells) {
            cell.setUnit(null);
        }
        assignedCells = new HashMap<>();
    }

    /**
     * Getter for cells in the GameMap that haven't got units associated to them.
     *
     * @return                  list of cells that don't have units associated to them
     */
    public List<Location> getUnassignedCells() {
        int size = field.getSize();
        List<Location> freeCells = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Location cell = field.getCell(row, col);
                if (!assignedCells.containsKey(cell)) {
                    freeCells.add(cell);
                }
            }
        }
        return freeCells;
    }

    private void initTurnOrder() {
        initTurnOrderArray();
        setTurnNumber(-1);
        setNewTurnOwner();
        turnNumber++;
    }

    private void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    private void setNewTurnOwner() {
        if (turnOwner != null) {
            turnOwner.setIsTurnOwner(false);
        }
        turnOwner = getTacticianByName(turnOrder.get(turnNumber + 1));
        turnOwner.setIsTurnOwner(true);
    }

    private void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    private void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    private void initTurnOrderArray() {
        List<String> array = new ArrayList<>();

        getTacticians().forEach(tactician -> array.add(tactician.getName()));

        Collections.shuffle(array, new Random(seed));

        if (turnRepetition(array.get(0)) && array.size() > 1) {
            Collections.swap(array, 0, 1);
        }

        turnOrder = array;
    }

    private boolean turnRepetition(String nextTurn) {
        if (turnOrder == null) {
            return false;
        }
        String lastTurn = turnOrder.get(turnOrder.size() -1);
        return lastTurn.equals(nextTurn);
    }

    /**
     * Starts a game without a limit of turns.
     */
    public void initEndlessGame() {
        initGame(-1);
    }

    /**
     * @return the winner of this game, if the match ends in a draw returns a list of all the winners
     */
    public List<String> getWinners() {
        return winners;
    }

    /**
     * @return the current player's selected unit
     */
    public IUnit getSelectedUnit() {
        return turnOwner.getSelectedUnit();
    }

    /**
     * Selects a unit in the game map
     *
     * @param x horizontal position of the unit
     * @param y vertical position of the unit
     */
    public void selectUnitIn(int x, int y) {
        turnOwner.selectUnitIn(field.getCell(x, y));
    }

    /**
     * @return the inventory of the currently selected unit.
     */
    public List<IEquipableItem> getItems() {
        return turnOwner.getSelectedUnitInventory();
    }

    /**
     * Equips an item from the inventory to the currently selected unit.
     *
     * @param index the location of the item in the inventory.
     */
    public void equipItem(int index) {
        if (getItems().size() <= index) {
            return;
        }
        IEquipableItem item = getItems().get(index);
        turnOwner.equipItemToSelectedUnit(item);
    }

    /**
     * Uses the equipped item on a target
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    public void useItemOn(int x, int y) {
        IUnit targetUnit = field.getCell(x, y).getUnit();
        if (targetUnit != null) {
            turnOwner.useEquippedItemOn(targetUnit);
        }
    }

    /**
     * Selects an item from the selected unit's inventory.
     *
     * @param index the location of the item in the inventory.
     */
    public void selectItem(int index) {
        if (getItems().size() <= index) {
            turnOwner.selectItem(null);
        }
        else {
            IEquipableItem item = getItems().get(index);
            turnOwner.selectItem(item);
        }
    }

    /**
     * Gives the selected item to a target unit.
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    public void giveItemTo(int x, int y) {
        IUnit receivingUnit = field.getCell(x, y).getUnit();
        if (receivingUnit != null) {
            turnOwner.giveItem(receivingUnit, turnOwner.getSelectedItem());
        }
    }

    /**
     * Adds an <b>item</b> to the selected unit's inventory.
     *
     * @param item    item to be added to the selected unit's inventory
     */
    public void addItemToSelectedUnitInventory(IEquipableItem item) {
        getSelectedUnit().addItemToInventory(item);
    }

    /**
     * @return  current number of players (tacticians) in the game
     */
    public int getNumberOfPlayers() {
        return tacticians.size();
    }

    /**
     * Adds a <b>Default Fighter</b> to the <b>Turn Owner's</b> list of units.
     */
    public void addFighterToTurnOwner() {
        this.getTurnOwner().addFighter();
    }

    /**
     * Adds a <b>Default Archer</b> to the <b>Turn Owner's</b> list of units.
     */
    public void addArcherToTurnOwner() {
        this.getTurnOwner().addArcher();
    }

    /**
     * Adds a <b>Default Fighter</b> to the <b>Turn Owner's</b> list of units.
     */
    public void addClericToTurnOwner() {
        this.getTurnOwner().addCleric();
    }

    /**
     * Adds a <b>Default Sorcerer</b> to the <b>Turn Owner's</b> list of units.
     */
    public void addSorcererToTurnOwner() {
        this.getTurnOwner().addSorcerer();
    }

    /**
     * Adds a <b>Default Sword Master</b> to the <b>Turn Owner's</b> list of units.
     */
    public void addSwordMasterToTurnOwner() {
        this.getTurnOwner().addSwordMaster();
    }

    /**
     * Adds a <b>Default Alpaca</b> to the <b>Turn Owner's</b> list of units.
     */
    public void addAlpacaToTurnOwner() {
        this.getTurnOwner().addAlpaca();
    }

    /**
     * Adds a <b>Default Hero</b> to the <b>Turn Owner's</b> list of units.
     */
    public void addHeroToTurnOwner() {
        this.getTurnOwner().addHero();
    }

    /**
     * Selects unit from the <b>Turn Owner</b>'s list of units in the position <b>unitIndex</b>.
     */
    public void selectTurnOwnerUnitByIndex(int unitIndex) {
        this.getTurnOwner().selectUnitByIndex(unitIndex);
    }

    /**
     * @return tactician named <b>tacticianName</b>
     */
    public Tactician getTacticianByName(String tacticianName) {
        for (Tactician tactician : tacticians) {
            if (tactician.getName().equals(tacticianName)) {
                return tactician;
            }
        }
        return null;
    }

    /**
     * Adds a <b>Default Fighter</b> to the <b>tactician's</b> list of units.
     */
    public void addFighterToTactician(Tactician tactician) {
        tactician.addFighter();
    }

    /**
     * Adds a <b>Default Hero</b> to the <b>tactician's</b> list of units.
     */
    public void addHeroToTactician(Tactician tactician) {
        tactician.addHero();
    }

    /**
     * Adds a <b>Default Sword Master</b> to the <b>tactician's</b> list of units.
     */
    public void addSwordMasterToTactician(Tactician tactician) {
        tactician.addSwordMaster();
    }

    /**
     * Adds a <b>Default Cleric</b> to the <b>tactician's</b> list of units.
     */
    public void addClericToTactician(Tactician tactician) {
        tactician.addCleric();
    }

    /**
     * Adds a <b>Default Alpaca</b> to the <b>tactician's</b> list of units.
     */
    public void addAlpacaToTactician(Tactician tactician) {
        tactician.addAlpaca();
    }

    /**
     * Adds a <b>Default Archer</b> to the <b>tactician's</b> list of units.
     */
    public void addArcherToTactician(Tactician tactician) {
        tactician.addArcher();
    }

    /**
     * Adds a <b>Default Sorcerer</b> to the <b>tactician's</b> list of units.
     */
    public void addSorcererToTactician(Tactician tactician) {
        tactician.addSorcerer();
    }


    /**
     * Getter for the player order. Only the names of the tacticians are stored. The corresponding list indexes
     * represent the player order,from 0 (the first to play) to the number of players - 1 (the last to play).
     * @return list that contains the player order for the current round.
     */
    public List<String> getTurnOrder() {
        return turnOrder;
    }

    /**
     * Deletes the reference to the specified <b>cell</b> in the assigned cells HashMap.
     */
    public void unassignCell(Location cell) {
        assignedCells.remove(cell);
    }


}
