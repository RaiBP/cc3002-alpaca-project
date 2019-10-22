package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController implements PropertyChangeListener {
    private List<Tactician> tacticians = new ArrayList<>();
    private Map<Location, Tactician> locationTacticianMap = new HashMap<>();
    private Field field = new Field();
    private Tactician turnOwner;
    private long seed;
    private int[] turnOrder;
    private int turnNumber;
    private int roundNumber;
    private int maxRounds;

    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayers the number of players for this game
     * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
     */
    public GameController(int numberOfPlayers, int mapSize) {
        this(numberOfPlayers, mapSize, new Random().nextLong());
    }

    public GameController(int numberOfPlayers, int mapSize, long seed) {
        setSeed(seed);
        field.setSeed(seed);
        generateMap(mapSize);
        generateTacticians(numberOfPlayers);
        initTurnOrder();
        asignCells();
        initUnits();
    }

    public List<Location> getTacticianCellsByIndex(int index) {
        Tactician tactician = tacticians.get(index);
        return getTacticianCells(tactician);
    }

    public List<Location> getTacticianCells(Tactician tactician) {
        List<Location> cells = new ArrayList<>();
        locationTacticianMap.forEach((k, val) -> {
            if (val.equals(tactician)) {
                cells.add(k);
            }
        });
        return cells;
    }

    public List<Location> getTurnOwnerCells() {
        return getTacticianCells(turnOwner);
    }

    private void asignCells() {
        int assignedCells = 0;
        int cellsPerPlayer = field.getDimensions() / numberOfPlayers();
        int assignableCells = cellsPerPlayer * numberOfPlayers();
        while (assignedCells < assignableCells) {
            int playerNumber = assignedCells / cellsPerPlayer;
            Location randomCell = field.getRandomCell();
            Tactician tactician = locationTacticianMap.get(randomCell);
            if (tactician == null) {
                locationTacticianMap.put(randomCell, tacticians.get(playerNumber));
                assignedCells++;
            }
        }
    }


    private void initUnits() {
        while (!maxTurnsReached()) {
            getTurnOwner().positionUnits();
            endTurn();
        }
    }

    // TODO: poner este metodo en Field
    private void generateMap(int mapSize) {
        List<Location> cells = new ArrayList<>();
        for (int row = 0; row < mapSize; row++) {
            for (int column = 0; column < mapSize; column++) {
                cells.add(new Location(row, column));
            }
        }
        field.addCells(false, cells.toArray(new Location[cells.size()]));
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    private void generateTacticians(int numberOfPlayers) {
        for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++) {
            tacticians.add(new Tactician("Player " + playerNumber));
            tacticians.get(playerNumber).setField(field);
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
        if (maxTurnsReached()) {
            startNewRound();

            if (maxRoundsReached()) {
                endGame();
            }
        }
        else {
            setNewTurnOwner();
        }
        turnNumber++;
    }

    private boolean maxTurnsReached() {
        return turnNumber >= numberOfPlayers();
    }

    private boolean maxRoundsReached() {
        return maxRounds > 0 && (roundNumber - 1 > maxRounds);
    }

    private void startNewRound() {
        initTurnOrder();
        setTurnNumber(0);
        roundNumber++;
    }

    private void endGame() {
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
                return;
            }
        }
    }

    private void removeTacticianFromTurnOrder(Tactician tactician) {
        int tacticianIndex = tacticians.indexOf(tactician);
        int[] newTurnArray = new int[numberOfPlayers() - 1];

        int newIndex = 0;
        for (int oldIndex = 0; oldIndex < numberOfPlayers(); oldIndex++) {
            if (turnOrder[oldIndex] != tacticianIndex) {
                newTurnArray[newIndex] = turnOrder[oldIndex];
                if (turnOrder[oldIndex] > tacticianIndex) {
                    newTurnArray[newIndex]--;
                }
                newIndex++;
            }
        }

        turnOrder = newTurnArray;
    }

    /**
     * Starts the game.
     *
     * @param maxTurns the maximum number of turns the game can last
     */
    public void initGame(final int maxTurns) {
        setMaxRounds(maxTurns);
        setRoundNumber(1);
    }

    private void initTurnOrder() {
        initTurnOrderArray();
        setTurnNumber(0);
        setNewTurnOwner();
    }

    private void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    private void setNewTurnOwner() {
        turnOwner = tacticians.get(turnOrder[turnNumber]);
    }

    private void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    private void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    // TODO: cambiar array por ArrayList y usar método shuffle (no necesito testearlo)
    private void initTurnOrderArray() {
        Random random = new Random(seed);
        int size = numberOfPlayers();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = i;
        }

        for (int i = 0; i < array.length; i++) {
            int randomPosition = random.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        if (turnRepetition(array[0])) {
            int temp = array[0];
            array[0] = array[1];
            array[1] = temp;
        }

        turnOrder = array;
    }

    private boolean turnRepetition(int nextTurn) {
        if (turnOrder == null) {
            return false;
        }
        int lastTurn = turnOrder[turnOrder.length - 1];
        return lastTurn == nextTurn;
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
        return null;
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
        IEquipableItem item = getItems().get(index);
        turnOwner.selectItem(item);
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

    public void addItemToSelectedUnitInventory(IEquipableItem item) {
        getSelectedUnit().addItemToInventory(item);
    }

    public int numberOfPlayers() {
        return tacticians.size();
    }

    public Tactician getNextTurnOwner() {
        if (turnNumber + 1 < numberOfPlayers()) {
            return getTacticians().get(turnOrder[turnNumber + 1]);
        }
        else {
            return null;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

    }
}
