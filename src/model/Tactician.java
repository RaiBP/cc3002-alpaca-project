package model;

import handlers.HeroDeathHandler;
import handlers.UnitDeathHandler;
import model.factory.unit.*;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * The game's player.
 * The tactician provides the interface between the controller and the in-game units.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class Tactician {
    private static IUnitFactory archerFactory = new ArcherFactory();
    private static IUnitFactory fighterFactory = new FighterFactory();
    private static IUnitFactory heroFactory = new HeroFactory();
    private static IUnitFactory sorcererFactory = new SorcererFactory();
    private static IUnitFactory swordMasterFactory = new SwordMasterFactory();
    private static IUnitFactory clericFactory = new ClericFactory();
    private static IUnitFactory alpacaFactory = new AlpacaFactory();

    private String name;
    private List<IUnit> units = new ArrayList<>();
    private List<IUnit> movedUnits = new ArrayList<>();
    private Field field;
    private IUnit selectedUnit;
    private IEquipableItem selectedItem;
    private boolean heroStatus = false;
    private boolean isTurnOwner = false;

    private PropertyChangeListener unitDeathHandler = new UnitDeathHandler(this),
            heroDeathHandler = new HeroDeathHandler(this);

    private PropertyChangeSupport
            noUnitsNotification = new PropertyChangeSupport(this),
            movedUnitNotification = new PropertyChangeSupport(this);

    /**
     * Creates one tactician.
     *
     * @param name the tactician's name
     */
    public Tactician(String name) {
        this.name = name;
    }

    /**
     * @return tactician's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the tactician's name.
     */
    public void setName(String s) {
        name = s;
    }

    private void subscribeToUnitDeathNotification(IUnit unit) {
        unit.addUnitDeathListener(unitDeathHandler);
    }

    private void unsubscribeToUnitDeathNotification(IUnit unit) {
        unit.removeUnitDeathListener(unitDeathHandler);
    }

    private void subscribeToHeroDeathNotification(IUnit unit) {
        unit.addHeroDeathListener(heroDeathHandler);
    }

    private void unsubscribeToHeroDeathNotification(IUnit unit) {
        unit.removeHeroDeathListener(heroDeathHandler);
    }

    private void unsubscribeToEverything(IUnit unit) {
        unsubscribeToUnitDeathNotification(unit);
        unsubscribeToHeroDeathNotification(unit);
    }

    /**
     * Removes unit from the tactician's unit list.
     */
    public void removeUnit(IUnit unit) {
        units.remove(unit);
        movedUnits.remove(unit);
        unsubscribeToEverything(unit);
        if (getSelectedUnit() == unit) {
            setSelectedUnit(null);
        }
        if (unit.getLocation() != null) {
            unit.getLocation().setUnit(null);
        }
        unit.setLocation(null);
        if (units.size() == 0) {
            fireNoUnitsEvent();
        }
    }

    /**
     * Removes several units from the tactician's unit list.
     */
    public void removeUnits(List<IUnit> units) {
        units.forEach(unit -> {
            if (unit.getLocation() != null) {
                unit.getLocation().setUnit(null);
                unit.setLocation(null);
                unsubscribeToEverything(unit);
                movedUnits.remove(unit);
            }
        });

        List<IUnit> newUnits = new ArrayList<>(getUnits());
        newUnits.removeAll(units);
        if (!(newUnits.contains(getSelectedUnit()))) {
            setSelectedUnit(null);
        }
        this.units = newUnits;
        if (newUnits.size() == 0) {
            fireNoUnitsEvent();
        }
    }

    private void fireNoUnitsEvent() {
        noUnitsNotification.firePropertyChange(new PropertyChangeEvent(this, "No Units remaining",
                null, null));
    }

    private void fireMovedUnitEvent(IUnit unit, Location oldLocation, Location newLocation) {
        movedUnitNotification.firePropertyChange(new PropertyChangeEvent(unit,
                "Unit has been moved", oldLocation, newLocation));
    }

    /**
     * Removes all units from the tactician's unit list.
     */
    public void removeAllCurrentUnits() {
        removeUnits(units);
    }

    private void addUnit(IUnit unit) {
        units.add(unit);
    }

    /**
     * Selects and positions units in the GameMap.
     * <b>IMPORTANT:</b> In version 2.0 this is only a <b>PLACEHOLDER</b>.
     */
    public void positionUnits(List<Location> assignedCells) {
        // to be implemented in 3.0
        // placeholder for current version: A fighter will be placed in the randomly assigned cell
        addFighter();
        assignedCells.get(0).setUnit(getUnitByIndex(0));
        getUnitByIndex(0).setLocation(assignedCells.get(0));
    }

    /**
     * @return number of units in tactician's unit list
     */
    public int getNumberOfUnits() {
        return units.size();
    }

    /**
     * Setter for the GameMap.
     */
    public void setField(Field field) {
        this.field = field;
    }

    /**
     * @return the GameMap
     */
    public Field getField() {
        return field;
    }

    /**
     * Setter for the unit to be selected.
     */
    public void setSelectedUnit(IUnit unit) {
        for (IUnit u : units) {
            if (u == unit) {
                selectedUnit = unit;
                return;
            }
        }
        selectedUnit = null;
    }

    /**
     * @return selected unit's current hit points
     */
    public int getSelectedUnitCurrentHP() {
        return selectedUnit.getCurrentHitPoints();
    }

    /**
     * @return selected unit's maximum hit points
     */
    public int getSelectedUnitMaxHP() {
        return selectedUnit.getMaxHitPoints();
    }

    /**
     * @return selected unit's inventory of items
     */
    public List<IEquipableItem> getSelectedUnitInventory() {
        return selectedUnit.getItems();
    }

    /**
     * @return selected unit's equipped item
     */
    public IEquipableItem getSelectedUnitEquippedItem() {
        return selectedUnit.getEquippedItem();
    }

    /**
     * Setter for the selected unit's location
     */
    public void setSelectedUnitLocation(Location cell) {
        Location fieldCell = field.getCell(cell.getRow(), cell.getColumn());
        if (field.isValidLocation(cell) && fieldCell.getUnit() == null) {
            selectedUnit.setLocation(cell);
            fieldCell.setUnit(selectedUnit);
        }
    }

    /**
     * @return selected unit's location
     */
    public Location getSelectedUnitLocation() {
        return selectedUnit.getLocation();
    }

    /**
     * @return whether or not the unit has been moved in the current turn
     */
    public boolean unitHasBeenMoved(IUnit unit) {
        return movedUnits.contains(unit);
    }

    /**
     * Adds <b>unit</b> to the list of moved units in the current turn.
     */
    public void addMovedUnit(IUnit unit) {
        movedUnits.add(unit);
    }

    /**
     * Moves the selected unit to the specified location <b>cell</b>.
     */
    public void moveSelectedUnitToLocation(Location cell) {
        if (field.isValidLocation(cell) && !(unitHasBeenMoved(selectedUnit))) {
            Location currentLocation = selectedUnit.getLocation();

            if (selectedUnit.moveTo(cell)) {
                addMovedUnit(selectedUnit);
                currentLocation.setUnit(null);
                fireMovedUnitEvent(selectedUnit, currentLocation, cell);
            }
        }
    }

    /**
     * Adds <b>Default Archer</b> to the tactician's list of units.
     */
    public void addArcher() {
        IUnit newArcher = archerFactory.getDefaultUnit();
        addUnit(newArcher);
        subscribeToUnitDeathNotification(newArcher);
    }

    /**
     * Adds <b>Default Sword Master</b> to the tactician's list of units.
     */
    public void addSwordMaster() {
        IUnit newSwordMaster = swordMasterFactory.getDefaultUnit();
        addUnit(newSwordMaster);
        subscribeToUnitDeathNotification(newSwordMaster);
    }

    /**
     * Adds <b>Default Fighter</b> to the tactician's list of units.
     */
    public void addFighter() {
        IUnit newFighter = fighterFactory.getDefaultUnit();
        addUnit(newFighter);
        subscribeToUnitDeathNotification(newFighter);
    }

    /**
     * Adds <b>Default Hero</b> to the tactician's list of units.
     */
    public void addHero() {
        IUnit newHero = heroFactory.getDefaultUnit();
        addUnit(newHero);
        subscribeToHeroDeathNotification(newHero);
    }

    /**
     * Adds <b>Default Cleric</b> to the tactician's list of units.
     */
    public void addCleric() {
        IUnit newCleric = clericFactory.getDefaultUnit();
        addUnit(newCleric);
        subscribeToUnitDeathNotification(newCleric);
    }

    /**
     * Adds <b>Default Sorcerer</b> to the tactician's list of units.
     */
    public void addSorcerer() {
        IUnit newSorcerer = sorcererFactory.getDefaultUnit();
        addUnit(newSorcerer);
        subscribeToUnitDeathNotification(newSorcerer);
    }

    /**
     * Adds <b>Default Alpaca</b> to the tactician's list of units.
     */
    public void addAlpaca() {
        IUnit newAlpaca = alpacaFactory.getDefaultUnit();
        addUnit(newAlpaca);
        subscribeToUnitDeathNotification(newAlpaca);
    }

    /**
     * Adds specified <b>item</b> to the selected unit.
     */
    public void addItemToSelectedUnitInventory(IEquipableItem item) {
        selectedUnit.addItemToInventory(item);
    }

    /**
     * Equips specified <b>item</b> to the selected unit.
     */
    public void equipItemToSelectedUnit(IEquipableItem item) {
        selectedUnit.equipItem(item);
    }

    /**
     * Makes the selected unit use its equipped item on a specified <b>targetUnit</b>.
     */
    public void useEquippedItemOn(IUnit targetUnit) {
        selectedUnit.useEquippedItemOn(targetUnit);
    }

    /**
     * Gives the specified <b>item</b> from the selected unit inventory to a <b>receivingUnit</b>.
     */
    public void giveItem(IUnit receivingUnit, IEquipableItem item) {
        selectedUnit.giveItem(receivingUnit, item);
    }

    /**
     * Makes the selected unit enter combat with a specified <b>targetUnit</b>.
     */
    public void doCombat(IUnit targetUnit) {
        selectedUnit.doCombat(targetUnit);
    }

    /**
     * Selects unit in specified <b>cell</b>.
     */
    public void selectUnitIn(Location cell) {
        IUnit unitInCell = field.getCell(cell.getRow(), cell.getColumn()).getUnit();
        if (units.contains(unitInCell)) {
            setSelectedUnit(unitInCell);
        }
        else {
            setSelectedUnit(null);
        }
    }

    /**
     * Selects unit with position <b>unitIndex</b> in the tactician's unit list.
     */
    public void selectUnitByIndex(int unitIndex) {
        if (getUnits().size() <= unitIndex) {
            setSelectedUnit(null);
        }
        else {
            setSelectedUnit(getUnitByIndex(unitIndex));
        }
    }

    /**
     * @return selected unit
     */
    public IUnit getSelectedUnit() {
        return selectedUnit;
    }

    /**
     * @return unit with position <b>unitIndex</b> in the tactician's unit list
     */
    public IUnit getUnitByIndex(int unitIndex) {
        return units.get(unitIndex);
    }

    /**
     * Selects specified <b>item</b> from the selected unit's item list.
     */
    public void selectItem(IEquipableItem item) {
        if (item == null) {
            setSelectedItem(null);
        }
        else {
            if (selectedUnit.getItems().contains(item)) {
                setSelectedItem(item);
            }
            else {
                setSelectedItem(null);
            }
        }
    }

    /**
     * @return selected item
     */
    public IEquipableItem getSelectedItem() {
        return selectedItem;
    }

    private void setSelectedItem(IEquipableItem item) {
        selectedItem = item;
    }

    /**
     * @return tactician's unit list
     */
    public List<IUnit> getUnits() {
        return units;
    }

    /**
     * Adds <b>listener</b> to the notification of no remaining units in the tactician.
     */
    public void addRetiringTacticianListener(PropertyChangeListener listener) {
        this.noUnitsNotification.addPropertyChangeListener(listener);
    }

    /**
     * Removes <b>listener</b> from the notification of no remaining units in the tactician.
     */
    public void removeRetiringTacticianListener(PropertyChangeListener listener) {
        this.noUnitsNotification.removePropertyChangeListener(listener);
    }

    /**
     * Adds <b>listener</b> to the notification of a moved unit
     */
    public void addMovedUnitListener(PropertyChangeListener listener) {
        this.movedUnitNotification.addPropertyChangeListener(listener);
    }

    /**
     * Remove <b>listener</b> to the notification of a moved unit
     */
    public void removeMovedUnitListener(PropertyChangeListener listener) {
        this.movedUnitNotification.removePropertyChangeListener(listener);
    }

    /**
     * Empties the <b>movedUnits</b> list.
     */
    public void resetMovedUnits() {
        movedUnits = new ArrayList<>();
    }

    /**
     * Sets the <b>status</b> of the tactician's Hero(es). If <b>status</b> is false, then at least one Hero has died.
     */
    public void setHeroStatus(boolean status) {
        heroStatus = status;
    }

    /**
     * @return the <b>status</b> of the tactician's Hero(es). If <b>status</b> is false, then at least one Hero has died.
     */
    public boolean getHeroStatus() {
        return heroStatus;
    }

    /**
     * Sets whether the tactician is currently the <b>Turn Owner</b>
     */
    public void setIsTurnOwner(boolean status) {
        isTurnOwner = status;
    }

    /**
     * @return whether or not the tactician is currently the <b>Turn Owner</b>
     */
    public boolean isTurnOwner() {
        return isTurnOwner;
    }
}
