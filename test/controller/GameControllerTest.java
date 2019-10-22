package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import model.Tactician;
import model.items.IEquipableItem;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.map.Field;
import model.map.Location;
import model.units.Archer;
import model.units.Fighter;
import model.units.IUnit;
import model.units.SwordMaster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {
  private IUnit defaultFighter = new Fighter();
  private IUnit defaultArcher = new Archer();
  private IEquipableItem defaultAxe = new Axe();
  private IEquipableItem defaultBow = new Bow();
  private IUnit defaultSwordMaster = new SwordMaster();
  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;

  @BeforeEach
  void setUp() {
    // Se define la semilla como un número aleatorio para generar variedad en los tests
    randomSeed = new Random().nextLong();
    controller = new GameController(4, 7);
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
    assertEquals(7, gameMap.getSize()); // getSize deben definirlo
    assertTrue(controller.getGameMap().isConnected());
    Random testRandom = new Random(randomSeed);
    // Para testear funcionalidades que dependen de valores aleatorios se hacen 2 cosas:
    //  - Comprobar las invariantes de las estructuras que se crean (en este caso que el mapa tenga
    //    las dimensiones definidas y que sea conexo.
    //  - Setear una semilla para el generador de números aleatorios. Hacer esto hace que la
    //    secuencia de números generada sea siempre la misma, así pueden predecir los
    //    resultados que van a obtener.
    //    Hay 2 formas de hacer esto en Java, le pueden pasar el seed al constructor de Random, o
    //    usar el método setSeed de Random.
    //  ESTO ÚLTIMO NO ESTÁ IMPLEMENTADO EN EL MAPA, ASÍ QUE DEBEN AGREGARLO (!)
  }

  @Test
  void getTurnOwner() {
    controller.getTurnOwner();

    //  En este caso deben hacer lo mismo que para el mapa
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
      System.out.println(nextInt);
      assertEquals(nextInt, controller.getMaxRounds());
      System.out.println(nextInt);
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  @Test
  void endTurn() {
    Tactician firstPlayer = controller.getTurnOwner();
    // Nuevamente, para determinar el orden de los jugadores se debe usar una semilla
    Tactician secondPlayer = controller.getNextTurnOwner(); // Tactician siguiente
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
    assertNull(controller.getWinners());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertNull(controller.getWinners());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  // Desde aquí en adelante, los tests deben definirlos completamente ustedes
  @Test
  void getSelectedUnit() {
    Tactician currentPlayer = controller.getTurnOwner();
    currentPlayer.addFighter();
    currentPlayer.addArcher();

    currentPlayer.selectUnitByIndex(0);
    IUnit selectedUnit = controller.getSelectedUnit();

    assertEquals(defaultFighter, selectedUnit);
    assertNotEquals(defaultArcher, selectedUnit);

    currentPlayer.selectUnitByIndex(1);
    IUnit newSelectedUnit = controller.getSelectedUnit();

    assertNotEquals(defaultFighter, newSelectedUnit);
    assertEquals(defaultArcher, newSelectedUnit);
  }

  @Test
  void selectUnitIn() {
    Tactician currentPlayer = controller.getTurnOwner();

    List<Location> cells = controller.getTurnOwnerCells();
    Location testLocation = cells.get(0);
    Location otherTestLocation = cells.get(1);

    currentPlayer.addFighter();
    currentPlayer.selectUnitByIndex(0);
    currentPlayer.setSelectedUnitLocation(testLocation);

    assertEquals(defaultFighter, controller.getSelectedUnit());
    assertNotEquals(defaultSwordMaster, controller.getSelectedUnit());

    currentPlayer.addSwordMaster();
    currentPlayer.selectUnitByIndex(1);
    currentPlayer.setSelectedUnitLocation(otherTestLocation);

    assertEquals(defaultSwordMaster, controller.getSelectedUnit());
    assertNotEquals(defaultFighter, controller.getSelectedUnit());

    int row = testLocation.getRow();
    int column = testLocation.getColumn();
    int otherRow = otherTestLocation.getRow();
    int otherColumn = otherTestLocation.getColumn();

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
    Tactician currentPlayer = controller.getTurnOwner();
    currentPlayer.addFighter();
    currentPlayer.selectUnitByIndex(0);
    controller.addItemToSelectedUnitInventory(defaultBow);
    controller.equipItem(0);

    assertEquals(defaultAxe, controller.getSelectedUnit().getEquippedItem());
    assertNotEquals(defaultBow, controller.getSelectedUnit().getEquippedItem());

    controller.equipItem(1);

    assertEquals(defaultBow, controller.getSelectedUnit().getEquippedItem());
    assertNotEquals(defaultAxe, controller.getSelectedUnit().getEquippedItem());
  }

  @Test
  void useItemOn() {
  }

  @Test
  void selectItem() {
  }

  @Test
  void giveItemTo() {
  }
}