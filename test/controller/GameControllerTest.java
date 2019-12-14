package controller;

import java.util.*;
import java.util.stream.IntStream;
import model.Tactician;
import model.items.IEquipableItem;
import model.items.magic.Dark;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.map.Field;
import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {
  private IUnit defaultFighter = new Fighter();
  private IUnit defaultArcher = new Archer();
  private IUnit defaultHero = new Hero();
  private IUnit defaultCleric = new Cleric();
  private IUnit defaultSwordMaster = new SwordMaster();
  private IUnit defaultSorcerer = new Sorcerer();
  private IUnit defaultAlpaca = new Alpaca();
  private IEquipableItem defaultAxe = new Axe();
  private IEquipableItem defaultBow = new Bow();
  private GameController controller;
  private List<String> testTacticians;

  @BeforeEach
  void setUp() {
    // Se define la semilla como un número aleatorio para generar variedad en los tests
    long randomSeed = new Random().nextLong();
    controller = new GameController(4, 7, randomSeed);
    testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
  }

  @Test
  void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + i, tacticians.get(i).getName());
    }
  }

  @Test
  void getGameMap() {
    Field gameMap = controller.getGameMap();
    assertEquals(7, gameMap.getSize());
    assertTrue(controller.getGameMap().isConnected());
  }

  @Test
  void getTurnOwner() {
    String firstPlayer = controller.getTurnOrder().get(0);
    String secondPlayer = controller.getTurnOrder().get(1);
    String thirdPlayer = controller.getTurnOrder().get(2);
    String fourthPlayer = controller.getTurnOrder().get(3);

    assertEquals(firstPlayer, controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(secondPlayer, controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(thirdPlayer, controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(fourthPlayer, controller.getTurnOwner().getName());
    controller.endTurn();

    controller.initEndlessGame();

    assertEquals(firstPlayer, controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(secondPlayer, controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(thirdPlayer, controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(fourthPlayer, controller.getTurnOwner().getName());
    controller.endTurn();
  }

  @Test
  void getRoundNumber() {
    controller.initGame(10);
    for (int i = 1; i < 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }

  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
      controller.initGame(nextInt);
      assertEquals(nextInt, controller.getMaxRounds());
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  @Test
  void endTurn() {
    Tactician firstPlayer = controller.getTurnOwner();
    // Nuevamente, para determinar el orden de los jugadores se debe usar una semilla
    Tactician secondPlayer = controller.getTacticianByName(controller.getTurnOrder().get(1)); // Tactician siguiente
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
  }

  @Test
  void removeTactician() {
    assertEquals(4, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 0");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 0", tactician.getName()));
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  @Test
  void getWinners() {
    controller.initGame(2);
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
        .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertEquals(0, controller.getWinners().size());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertEquals(0, controller.getWinners().size());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  @Test
  void killHeroVictory() {
    GameController controller = new GameController(2, 2);
    Tactician player0 = controller.getTacticianByName("Player 0");
    Tactician player1 = controller.getTacticianByName("Player 1");

    List<Location> freeCells = controller.getUnassignedCells();

    controller.addHeroToTactician(player0);
    controller.addHeroToTactician(player0); // Player 0 has two Heros
    controller.setUnitPositionByIndexIn(player0, 1, freeCells.get(0));

    controller.initEndlessGame();

    IUnit hero = player0.getUnitByIndex(1);

    int numberOfHits = 1;
    while (!controller.isCurrentGameFinished()) {
      Tactician turnOwner = controller.getTurnOwner();
      if (turnOwner == player1) {
        turnOwner.selectUnitByIndex(0);
        turnOwner.equipItemToSelectedUnit(turnOwner.getSelectedUnitInventory().get(0));
        turnOwner.doCombat(hero);
        assertEquals(Math.max(0, 100 - 10 * numberOfHits), hero.getCurrentHitPoints());
        assertEquals(75, turnOwner.getSelectedUnit().getCurrentHitPoints());
        numberOfHits++;
      }
      controller.endTurn();
    }

    assertEquals(1, controller.getWinners().size());
    assertEquals("Player 1", controller.getWinners().get(0));
  }

  @Test
  void killAllUnitsVictory() {
    GameController controller = new GameController(2, 2);
    Tactician player0 = controller.getTacticianByName("Player 0");
    Tactician player1 = controller.getTacticianByName("Player 1");

    controller.removeUnitFromTactician(player0, player0.getUnitByIndex(0));

    List<Location> freeCells = controller.getUnassignedCells();
    controller.addSorcererToTactician(player0);
    controller.setUnitPositionByIndexIn(player0, 0, freeCells.get(0));

    controller.addAlpacaToTactician(player1);
    controller.addAlpacaToTactician(player1);
    controller.setUnitPositionByIndexIn(player1, 1, freeCells.get(1));
    controller.setUnitPositionByIndexIn(player1, 2, freeCells.get(2));

    player1.selectUnitByIndex(0);
    Location player1FighterLocation = player1.getSelectedUnitLocation();

    player0.selectUnitByIndex(0);
    controller.addItemToSelectedUnitInventory(new Dark("OP Dark Spell", 30, 1, 9));

    controller.initEndlessGame();

    while (!controller.isCurrentGameFinished()) {
      Tactician turnOwner = controller.getTurnOwner();
      turnOwner.selectUnitByIndex(0);
      if (turnOwner == player0) {
        turnOwner.equipItemToSelectedUnit(turnOwner.getSelectedUnitInventory().get(0));

        controller.useItemOn(freeCells.get(1).getRow(), freeCells.get(1).getColumn());
        controller.useItemOn(freeCells.get(2).getRow(), freeCells.get(2).getColumn());
        controller.useItemOn(player1FighterLocation.getRow(), player1FighterLocation.getColumn());
      }
      controller.endTurn();
    }

    assertEquals(1, controller.getWinners().size());
    assertEquals("Player 0", controller.getWinners().get(0));
  }

  @Test
  void getSelectedUnit() {
    controller.addArcherToTurnOwner();

    controller.selectTurnOwnerUnitByIndex(0);

    IUnit selectedUnit = controller.getSelectedUnit();

    // todas los Tacticians tienen un Fighter de placeholder en la primera posición
    assertEquals(defaultFighter, selectedUnit);
    assertNotEquals(defaultArcher, selectedUnit);

    controller.selectTurnOwnerUnitByIndex(1);
    IUnit newSelectedUnit = controller.getSelectedUnit();

    assertNotEquals(defaultFighter, newSelectedUnit);
    assertEquals(defaultArcher, newSelectedUnit);
  }

  @Test
  void selectUnitIn() {
    Tactician currentPlayer = controller.getTurnOwner();

    List<Location> freeCells = controller.getUnassignedCells();

    currentPlayer.selectUnitByIndex(0);

    Location fighterLocation = controller.getSelectedUnit().getLocation();

    assertEquals(defaultFighter, controller.getSelectedUnit());
    assertNotEquals(defaultSwordMaster, controller.getSelectedUnit());

    currentPlayer.addSwordMaster();
    currentPlayer.selectUnitByIndex(1);

    Location swordMasterLocation = freeCells.get(0);
    currentPlayer.setSelectedUnitLocation(swordMasterLocation);

    assertEquals(defaultSwordMaster, controller.getSelectedUnit());
    assertNotEquals(defaultFighter, controller.getSelectedUnit());

    int row = fighterLocation.getRow();
    int column = fighterLocation.getColumn();
    int otherRow = swordMasterLocation.getRow();
    int otherColumn = swordMasterLocation.getColumn();

    controller.selectUnitIn(row, column);
    assertEquals(defaultFighter, controller.getSelectedUnit());
    assertNotEquals(defaultSwordMaster, controller.getSelectedUnit());

    controller.selectUnitIn(otherRow, otherColumn);
    assertEquals(defaultSwordMaster, controller.getSelectedUnit());
    assertNotEquals(defaultFighter, controller.getSelectedUnit());
  }

  @Test
  void getItems() {
    Tactician currentPlayer = controller.getTurnOwner();
    currentPlayer.addFighter();
    currentPlayer.selectUnitByIndex(0);

    List<IEquipableItem> items = new ArrayList<>();
    items.add(defaultAxe);

    assertEquals(items, controller.getItems());

    items.add(defaultBow);

    assertNotEquals(items, controller.getItems());

    controller.addItemToSelectedUnitInventory(defaultBow);

    assertEquals(items, controller.getItems());
  }

  @Test
  void equipItem() {
    controller.selectTurnOwnerUnitByIndex(0);
    controller.addItemToSelectedUnitInventory(defaultBow);
    controller.equipItem(0);

    assertEquals(defaultAxe, controller.getSelectedUnit().getEquippedItem());
    assertNotEquals(defaultBow, controller.getSelectedUnit().getEquippedItem());

    controller.equipItem(1);

    // Fighter can't equip Bows, so equipped item stays the same as before
    assertEquals(defaultAxe, controller.getSelectedUnit().getEquippedItem());
    assertNotEquals(defaultBow, controller.getSelectedUnit().getEquippedItem());

    IEquipableItem testAxe = new Axe("Powerful Axe", 100, 1, 2);
    controller.addItemToSelectedUnitInventory(testAxe);
    controller.equipItem(2);

    assertEquals(testAxe, controller.getSelectedUnit().getEquippedItem());
    assertNotEquals(defaultAxe, controller.getSelectedUnit().getEquippedItem());

    controller.equipItem(42);
    assertEquals(testAxe, controller.getSelectedUnit().getEquippedItem());
  }

  @Test
  void useItemOn() {
    GameController controller = new GameController(2, 2);
    controller.selectTurnOwnerUnitByIndex(0);
    controller.equipItem(0);
    IUnit targetUnit = controller.getTacticianByName(controller.getTurnOrder().get(1)).getUnitByIndex(0);
    controller.useItemOn(targetUnit.getLocation().getRow(), targetUnit.getLocation().getColumn());
    assertEquals(65, targetUnit.getCurrentHitPoints());

    controller.addClericToTactician(controller.getTurnOwner());
    controller.setUnitPositionByIndexIn(controller.getTurnOwner(), 1, controller.getUnassignedCells().get(0));

    controller.selectTurnOwnerUnitByIndex(1);
    controller.equipItem(0);
    controller.useItemOn(targetUnit.getLocation().getRow(), targetUnit.getLocation().getColumn());
    // Clerics can heal Units from other Tacticians
    assertEquals(75, targetUnit.getCurrentHitPoints());
  }

  @Test
  void selectItem() {
    controller.selectTurnOwnerUnitByIndex(0);
    controller.addItemToSelectedUnitInventory(defaultBow);
    controller.selectItem(0);

    assertEquals(defaultAxe, controller.getTurnOwner().getSelectedItem());
    assertNotEquals(defaultBow, controller.getTurnOwner().getSelectedItem());

    controller.selectItem(1);

    // Fighter can't equip Bow, but can select it
    assertEquals(defaultBow, controller.getTurnOwner().getSelectedItem());
    assertNotEquals(defaultAxe, controller.getTurnOwner().getSelectedItem());

    IEquipableItem testAxe = new Axe("Powerful Axe", 100, 1, 2);
    controller.addItemToSelectedUnitInventory(testAxe);
    controller.selectItem(2);

    assertEquals(testAxe, controller.getTurnOwner().getSelectedItem());
    assertNotEquals(defaultAxe, controller.getTurnOwner().getSelectedItem());

    controller.selectItem(42);
    assertNull(controller.getTurnOwner().getSelectedItem());
  }

  @Test
  void giveItemTo() {
    GameController controller = new GameController(2, 2);
    controller.selectTurnOwnerUnitByIndex(0);
    controller.selectItem(0);

    Tactician player0 = controller.getTurnOwner();
    Tactician player1 = controller.getTacticianByName(controller.getTurnOrder().get(1));

    IUnit targetUnit = player1.getUnitByIndex(0);
    controller.giveItemTo(targetUnit.getLocation().getRow(), targetUnit.getLocation().getColumn());

    assertEquals(2, targetUnit.getItems().size());
    assertTrue(controller.getSelectedUnit().getItems().isEmpty());

    player0.addItemToSelectedUnitInventory(defaultBow);
    assertEquals(1, controller.getSelectedUnit().getItems().size());
    controller.selectItem(0);
    controller.giveItemTo(targetUnit.getLocation().getRow(), targetUnit.getLocation().getColumn());

    assertEquals(3, targetUnit.getItems().size());
    assertTrue(controller.getSelectedUnit().getItems().isEmpty());

    controller.endTurn();
    controller.selectTurnOwnerUnitByIndex(0);
    controller.selectItem(0);

    IUnit newTargetUnit = player0.getUnitByIndex(0);

    controller.giveItemTo(newTargetUnit.getLocation().getRow(), newTargetUnit.getLocation().getColumn());

    assertEquals(2, targetUnit.getItems().size());
    assertEquals(1, newTargetUnit.getItems().size());
  }

  @Test
  void addUnits() {
    controller.addArcherToTurnOwner();
    controller.addClericToTurnOwner();
    controller.addHeroToTurnOwner();
    controller.addAlpacaToTurnOwner();
    controller.addFighterToTurnOwner();
    controller.addSorcererToTurnOwner();
    controller.addSwordMasterToTurnOwner();

    List<IUnit> unitList = new ArrayList<>(Arrays.asList(
            defaultFighter, defaultArcher, defaultCleric, defaultHero, defaultAlpaca, defaultFighter,
            defaultSorcerer, defaultSwordMaster
    ));

    assertArrayEquals(unitList.toArray(), controller.getTurnOwner().getUnits().toArray());

    Tactician tactician = controller.getTacticianByName(controller.getTurnOrder().get(1));

    controller.addArcherToTactician(tactician);
    controller.addClericToTactician(tactician);
    controller.addHeroToTactician(tactician);
    controller.addAlpacaToTactician(tactician);
    controller.addFighterToTactician(tactician);
    controller.addSorcererToTactician(tactician);
    controller.addSwordMasterToTactician(tactician);

    assertArrayEquals(unitList.toArray(), tactician.getUnits().toArray());
  }

  @Test
  void moveUnits() {
    GameController controller = new GameController(2, 2);

    controller.initEndlessGame();

    Tactician player0 = controller.getTurnOwner();
    player0.selectUnitByIndex(0);
    Tactician player1 = controller.getTacticianByName(controller.getTurnOrder().get(1));
    player1.selectUnitByIndex(0);

    Location player0UnitLocation = player0.getSelectedUnitLocation();
    Location player1UnitLocation = player1.getSelectedUnitLocation();

    player0.moveSelectedUnitToLocation(player1UnitLocation);

    // Unit couldn't be moved, because there was already a Unit there
    assertEquals(player0UnitLocation, player0.getSelectedUnitLocation());

    List<Location> freeCells = controller.getUnassignedCells();

    player0.moveSelectedUnitToLocation(freeCells.get(0));

    assertEquals(freeCells.get(0), player0.getSelectedUnitLocation());
    assertTrue(controller.getUnassignedCells().contains(player0UnitLocation));

    player0.moveSelectedUnitToLocation(freeCells.get(1));

    // Unit couldn't be moved, because it was already moved in this turn
    assertEquals(freeCells.get(0), player0.getSelectedUnitLocation());

    controller.addHeroToTactician(player0);
    player0.selectUnitByIndex(1);
    controller.setUnitPositionByIndexIn(player0, 1, freeCells.get(1));

    player0.moveSelectedUnitToLocation(player0UnitLocation);

    // Unit could be moved, because it hasn't been moved before
    assertEquals(player0UnitLocation, player0.getSelectedUnitLocation());

    controller.endTurn();
    controller.endTurn();

    player0.selectUnitByIndex(0);
    player0.moveSelectedUnitToLocation(freeCells.get(1));

    // Unit could be moved, because it wasn't moved on this turn
    assertEquals(freeCells.get(1), player0.getSelectedUnitLocation());
  }

  @Test

  void killHeroInMidTurn() {
    GameController controller = new GameController(2, 2);

    controller.addHeroToTurnOwner();

    controller.setUnitPositionByIndexIn(controller.getTurnOwner(), 1, controller.getUnassignedCells().get(0));

    controller.initEndlessGame();

    Tactician tactician = controller.getTurnOwner();

    tactician.selectUnitByIndex(1);
    IUnit hero = controller.getSelectedUnit();
    hero.setHitPoints(0);

    // TurnOwner's Hero died. He can still play the entirety of his turn.

    assertEquals(tactician, controller.getTurnOwner());
    assertFalse(controller.isCurrentGameFinished());
    assertNull(tactician.getSelectedUnit());
    assertFalse(tactician.getUnits().contains(hero));
    assertEquals(1, tactician.getUnits().size());

    Tactician tactician2 = controller.getTacticianByName(controller.getTurnOrder().get(1));
    tactician2.selectUnitByIndex(0);

    tactician.selectUnitByIndex(0);
    controller.equipItem(0);
    controller.useItemOn(tactician2.getSelectedUnitLocation().getRow(), tactician2.getSelectedUnitLocation().getColumn());

    // The TurnOwner whose Hero got killed can still attack other Tacticians in his turn
    assertEquals(65, tactician2.getSelectedUnit().getCurrentHitPoints());

    controller.endTurn();

    assertTrue(controller.isCurrentGameFinished());
    assertEquals(1, controller.getWinners().size());
    assertEquals(tactician2.getName(), controller.getWinners().get(0));
  }

  @Test
  void singlePlayerGame() {
    GameController controller = new GameController(1, 2);

    controller.initGame(2);

    assertEquals(1, controller.getWinners().size());
    assertEquals(controller.getTacticians().get(0).getName(), controller.getWinners().get(0));

  }

}