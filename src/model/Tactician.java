package model;

import model.factory.unit.*;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

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


    public Tactician(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUnits(List<IUnit> units) {
        this.units = units;
    }

    public void addUnit(IUnit unit){
        units.add(unit);
    }

    public void positionUnits() {
        // to be implemented in 3.0
    }

    public void setField(Field field) {
        this.field = field;
    }

    // TODO: implementar test que intente seleccionar una unidad que no tiene el tactician
    public void setSelectedUnit(IUnit unit) {
        if (units.contains(unit)) {
            selectedUnit = unit;
        }
    }

    public int getSelectedUnitCurrentHP() {
        return selectedUnit.getCurrentHitPoints();
    }

    public int getSelectedUnitMaxHP() {
        return selectedUnit.getMaxHitPoints();
    }

    public List<IEquipableItem> getSelectedUnitInventory() {
        return selectedUnit.getItems();
    }

    public IEquipableItem getSelectedUnitEquippedItem() {
        return selectedUnit.getEquippedItem();
    }

    public void setSelectedUnitLocation(Location cell) {
        if (field.isValidLocation(cell) && cell.getUnit() == null) {
            selectedUnit.setLocation(cell);
            cell.setUnit(selectedUnit);
        }
    }

    public boolean unitHasBeenMoved(IUnit unit) {
        return movedUnits.contains(unit);
    }

    public void addMovedUnit(IUnit unit) {
        movedUnits.add(unit);
    }

    // TODO: test what happens if an unit is moved twice
    public void moveSelectedUnitToLocation(Location cell) {
        if (field.isValidLocation(cell) && !(unitHasBeenMoved(selectedUnit))) {
            selectedUnit.moveTo(cell);
            addMovedUnit(selectedUnit);
        }
    }

    public void addArcher() {
        units.add(archerFactory.getDefaultUnit());
    }

    public void addSwordMaster() {
        units.add(swordMasterFactory.getDefaultUnit());
    }

    public void addFighter() {
        units.add(fighterFactory.getDefaultUnit());
    }

    public void addHero() {
        units.add(heroFactory.getDefaultUnit());
    }

    public void addCleric() {
        units.add(clericFactory.getDefaultUnit());
    }

    public void addSorcerer() {
        units.add(sorcererFactory.getDefaultUnit());
    }

    public void addAlpaca() {
        units.add(alpacaFactory.getDefaultUnit());
    }

    public void addItemToSelectedUnitInventory(IEquipableItem item) {
        selectedUnit.addItemToInventory(item);
    }

    public void equipItemToSelectedUnit(IEquipableItem item) {
        selectedUnit.equipItem(item);
    }

    public void useEquippedItemOn(IUnit targetUnit) {
        selectedUnit.useEquippedItemOn(targetUnit);
    }

    public void giveItem(IUnit receivingUnit, IEquipableItem item) {
        selectedUnit.giveItem(receivingUnit, item);
    }

    public void doCombat(IUnit targetUnit) {
        selectedUnit.doCombat(targetUnit);
    }

    public void selectUnitIn(Location cell) {
        IUnit unitInCell = cell.getUnit();
        if (units.contains(unitInCell)) {
            setSelectedUnit(unitInCell);
        }
    }

    public void selectUnitByIndex(int unitIndex) {
        setSelectedUnit(units.get(unitIndex));
    }

    public IUnit getSelectedUnit() {
        return selectedUnit;
    }

    public void selectItem(IEquipableItem item) {
        if (selectedUnit.getItems().contains(item)) {
            setSelectedItem(item);
        }
    }

    public IEquipableItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(IEquipableItem item) {
        selectedItem = item;
    }

    // TODO: crear metodo para unidades derrotadas (eliminarlas de la celda)
    // TODO: observer para cuando todas las unidades son derrotadas
    // TODO: observer para cuando muere un heroe
    // TODO: identificar el tactician en cada unidad (no pueden haber dos tacticians con la misma unidad)
    // TODO: añadir combate
    // TODO: testear todo
    // TODO: observer para que el tactician le diga al controller cuando termino su turno
}
