package model;

import model.factory.unit.*;
import model.items.IEquipableItem;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Sword;
import model.map.Field;
import model.map.Location;
import model.units.Alpaca;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TacticianTest {
    Tactician tactician;
    private static IUnitFactory archerFactory = new ArcherFactory();
    private static IUnitFactory fighterFactory = new FighterFactory();
    private static IUnitFactory heroFactory = new HeroFactory();
    private static IUnitFactory sorcererFactory = new SorcererFactory();
    private static IUnitFactory swordMasterFactory = new SwordMasterFactory();
    private static IUnitFactory clericFactory = new ClericFactory();
    private static IUnitFactory alpacaFactory = new AlpacaFactory();

    @BeforeEach
    void setUp() {
        tactician = new Tactician("Player 1");
    }

    @Test
    void getAndSetName() {
        assertEquals("Player 1", tactician.getName());

        tactician.setName("xyzzy");

        assertEquals("xyzzy", tactician.getName());
    }

    @Test void getAndSetSelectedUnit() {
        tactician.addCleric();
        tactician.setSelectedUnit(fighterFactory.getDefaultUnit());
        assertNull(tactician.getSelectedUnit());
        tactician.setSelectedUnit(clericFactory.getDefaultUnit());
        assertNull(tactician.getSelectedUnit()); // different instance of Cleric, can't be selected

        IUnit cleric = tactician.getUnitByIndex(0);
        tactician.setSelectedUnit(cleric);
        assertEquals(cleric, tactician.getSelectedUnit());

        tactician.addSorcerer();
        IUnit sorcerer = tactician.getUnitByIndex(1);
        tactician.setSelectedUnit(sorcerer);

        assertNotEquals(cleric, tactician.getSelectedUnit());
        assertEquals(sorcerer, tactician.getSelectedUnit());

        tactician.setSelectedUnit(cleric);
        assertNotEquals(sorcerer, tactician.getSelectedUnit());
        assertEquals(cleric, tactician.getSelectedUnit());

        tactician.setSelectedUnit(null);
        assertNull(tactician.getSelectedUnit());
    }

    @Test
    void selectedUnitGetters() {
        // Current HP and Max HP
        tactician.addSwordMaster();
        tactician.selectUnitByIndex(0);
        assertEquals(75, tactician.getSelectedUnitCurrentHP());
        assertEquals(75, tactician.getSelectedUnitMaxHP());

        tactician.getSelectedUnit().setHitPoints(1);
        assertEquals(1, tactician.getSelectedUnitCurrentHP());
        assertEquals(75, tactician.getSelectedUnitMaxHP());

        tactician.getSelectedUnit().setHitPoints(100);
        assertEquals(75, tactician.getSelectedUnitCurrentHP());
        assertEquals(75, tactician.getSelectedUnitMaxHP());

        // Inventory
        IEquipableItem sword = tactician.getSelectedUnit().getItems().get(0);
        IEquipableItem bow = new Bow();
        tactician.addItemToSelectedUnitInventory(bow);

        List<IEquipableItem> items = new ArrayList<>(Arrays.asList(sword, bow));
        assertArrayEquals(items.toArray(), tactician.getSelectedUnitInventory().toArray());

        tactician.addFighter();
        IEquipableItem axe = tactician.getUnitByIndex(1).getItems().get(0);
        List<IEquipableItem> items2 = new ArrayList<>(Collections.singletonList(axe));
        assertFalse(Arrays.equals(items2.toArray(), tactician.getSelectedUnitInventory().toArray()));

        tactician.selectUnitByIndex(1);
        assertArrayEquals(items2.toArray(), tactician.getSelectedUnitInventory().toArray());

        tactician.addItemToSelectedUnitInventory(bow);
        assertArrayEquals(items2.toArray(), tactician.getSelectedUnitInventory().toArray());

        bow.setOwner(null);
        tactician.addItemToSelectedUnitInventory(bow);
        List<IEquipableItem> items3 = new ArrayList<>(Arrays.asList(axe, bow));
        assertArrayEquals(items3.toArray(), tactician.getSelectedUnitInventory().toArray());

        // Equipped item
        assertNull(tactician.getSelectedUnitEquippedItem());
        tactician.equipItemToSelectedUnit(new Axe());
        assertEquals(axe, tactician.getSelectedUnitEquippedItem());
        tactician.equipItemToSelectedUnit(null);
        assertNull(tactician.getSelectedUnitEquippedItem());
        tactician.equipItemToSelectedUnit(axe);
        assertEquals(axe, tactician.getSelectedUnitEquippedItem());

        tactician.selectUnitByIndex(0);
        assertNull(tactician.getSelectedUnitEquippedItem());
        tactician.equipItemToSelectedUnit(axe);
        assertNull(tactician.getSelectedUnitEquippedItem());
        tactician.equipItemToSelectedUnit(sword);
        assertEquals(sword, tactician.getSelectedUnitEquippedItem());
    }

    @Test
    void getAndSetSelectedUnitLocation() {
        Field field = new Field();
        field.addCells(true, new Location(0, 0), new Location(0, 1),
                new Location(0, 2), new Location(1, 0), new Location(1, 1),
                new Location(1, 2), new Location(2, 0), new Location(2, 1),
                new Location(2, 2));
        tactician.setField(field);

        tactician.addAlpaca();
        tactician.selectUnitByIndex(0);
        assertNull(tactician.getSelectedUnitLocation());
        tactician.setSelectedUnitLocation(new Location(2, 3));
        assertNull(tactician.getSelectedUnitLocation());
        tactician.setSelectedUnitLocation(new Location(0, 0));
        assertEquals(new Location(0, 0), tactician.getSelectedUnitLocation());

        tactician.addAlpaca();
        tactician.selectUnitByIndex(1);
        assertNull(tactician.getSelectedUnitLocation());
        tactician.setSelectedUnitLocation(new Location(0, 0)); // trying to place unit in occupied cell
        assertNull(tactician.getSelectedUnitLocation());
        tactician.setSelectedUnitLocation(new Location(0, 1));
        assertEquals(new Location(0, 1), tactician.getSelectedUnitLocation());

        tactician.addFighter();
        tactician.selectUnitByIndex(2);
        assertNull(tactician.getSelectedUnitLocation());
        tactician.setSelectedUnitLocation(new Location(0, 0));
        assertNull(tactician.getSelectedUnitLocation());
        tactician.setSelectedUnitLocation(new Location(0, 1));
        assertNull(tactician.getSelectedUnitLocation());
        tactician.setSelectedUnitLocation(new Location(0, 2));
        assertEquals(new Location(0, 2), tactician.getSelectedUnitLocation());
    }

    @Test
    void selectUnitIn() {
        Field field = new Field();
        field.addCells(true, new Location(0, 0), new Location(0, 1),
                new Location(0, 2), new Location(1, 0), new Location(1, 1),
                new Location(1, 2), new Location(2, 0), new Location(2, 1),
                new Location(2, 2));
        tactician.setField(field);

        tactician.addAlpaca();
        tactician.selectUnitByIndex(0);
        assertEquals(new Alpaca(), tactician.getSelectedUnit());

        tactician.selectUnitIn(tactician.getField().getCell(0,0));
        assertNull(tactician.getSelectedUnit());

        tactician.addSorcerer();
        tactician.selectUnitByIndex(1);
        tactician.setSelectedUnitLocation(new Location(0, 0));

        tactician.selectUnitByIndex(0);
        tactician.setSelectedUnitLocation(new Location(0, 1));
        assertEquals(new Alpaca(), tactician.getSelectedUnit());

        tactician.selectUnitIn(new Location(0, 0));
        assertEquals(new Sorcerer(), tactician.getSelectedUnit());

        tactician.selectUnitIn(new Location(0, 1));
        assertEquals(new Alpaca(), tactician.getSelectedUnit());

        tactician.selectUnitIn(new Location(0, 3));
        assertNull(tactician.getSelectedUnit());

        tactician.selectUnitIn(tactician.getField().getCell(0,0));
        assertEquals(new Sorcerer(), tactician.getSelectedUnit());

        tactician.selectUnitIn(tactician.getField().getCell(0,1));
        assertEquals(new Alpaca(), tactician.getSelectedUnit());

        tactician.selectUnitIn(tactician.getField().getCell(0,3));
        assertNull(tactician.getSelectedUnit());
    }

    @Test
    void getAndSelectItem() {
        tactician.addFighter();
        tactician.selectUnitByIndex(0);
        IEquipableItem axe = tactician.getSelectedUnitInventory().get(0);
        IEquipableItem bow = new Bow();
        tactician.addItemToSelectedUnitInventory(bow);

        tactician.selectItem(axe);
        assertNotEquals(bow, tactician.getSelectedItem());
        assertEquals(axe, tactician.getSelectedItem());

        tactician.selectItem(bow);
        assertNotEquals(axe, tactician.getSelectedItem());
        assertEquals(bow, tactician.getSelectedItem());

        tactician.selectItem(new Sword());
        assertNull(tactician.getSelectedItem());
    }

    @Test
    void unitDeathObserver() {
        tactician.addFighter();
        tactician.selectUnitByIndex(0);
        IUnit selectedUnit = tactician.getSelectedUnit();
        selectedUnit.setHitPoints(0);
        assertNotEquals(selectedUnit, tactician.getSelectedUnit());
        assertNull(tactician.getSelectedUnit());
        assertEquals(0, tactician.getUnits().size());
    }

    @Test
    void heroDeathObserver() {
        tactician.addFighter();
        tactician.addArcher();
        tactician.addSorcerer();
        tactician.addHero();
        assertEquals(4, tactician.getUnits().size());

        tactician.selectUnitByIndex(0);
        IUnit selectedFighter = tactician.getSelectedUnit();
        selectedFighter.setHitPoints(0);
        assertEquals(3, tactician.getUnits().size());

        tactician.selectUnitByIndex(2);
        IUnit selectedHero = tactician.getSelectedUnit();
        selectedHero.setHitPoints(0);
        assertTrue(tactician.getUnits().isEmpty());
    }

    @Test
    void selectUnitByIndex() {
        tactician.addFighter();
        tactician.addArcher();
        tactician.addSorcerer();
        tactician.addHero();

        tactician.selectUnitByIndex(0);
        assertEquals(fighterFactory.getDefaultUnit(), tactician.getSelectedUnit());

        tactician.selectUnitByIndex(1);
        assertEquals(archerFactory.getDefaultUnit(), tactician.getSelectedUnit());

        tactician.selectUnitByIndex(2);
        assertEquals(sorcererFactory.getDefaultUnit(), tactician.getSelectedUnit());

        tactician.selectUnitByIndex(3);
        assertEquals(heroFactory.getDefaultUnit(), tactician.getSelectedUnit());

        tactician.selectUnitByIndex(42);
        assertNull(tactician.getSelectedUnit());
    }

}