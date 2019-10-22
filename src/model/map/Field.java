package model.map;

import java.util.*;

/**
 * This class represents the map where the units are located and the game is played.
 * <p>
 * The field is an undirected graph composed of <i>Location</i> nodes where the weight of every edge
 * of the graph is 1.
 * Since all cells of the map should be reachable, the graph must be connected.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Field {

  private Map<String, Location> map = new HashMap<>();
  private Random random = new Random();
  private StringBuilder builder = new StringBuilder();

  /**
   * Add cells to the map.
   *
   * @param connectAll
   *     a flag that indicates if all the cells should be connected to it's neighbours
   * @param cells
   *     the locations that are going to be added to the map
   */
  public void addCells(final boolean connectAll, final Location... cells) {
    for (Location cell : cells) {
      addCell(cell);
      Location[] adjacentCells = getAdjacentCells(cell);
      for (Location adjacentCell : adjacentCells) {
        if (connectAll || random.nextDouble() > 1.0 / 3 || cell.getNeighbours().size() < 1) {
          addConnection(cell, adjacentCell);
        }
      }
    }
  }

  /**
   * Adds a cell to the map
   *
   * @param cell
   *     the location to be added
   */
  private void addCell(final Location cell) {
    map.put(cell.toString(), cell);
  }

  /**
   * Gets the possible adjacent cells to a given cell
   *
   * @param cell
   *     the location of the current cell
   * @return an array of the adjacent cells
   */
  private Location[] getAdjacentCells(final Location cell) {
    int row = cell.getRow(),
        col = cell.getColumn();
    return new Location[]{getCell(row - 1, col), getCell(row + 1, col), getCell(row, col - 1),
        getCell(row, col + 1)};
  }

  /**
   * Creates a connection between 2 cells
   */
  private void addConnection(Location cell1, Location cell2) {
    cell1.addNeighbour(cell2);
  }

  /**
   * @param row
   *     the row of the cell
   * @param col
   *     the column of the cell
   * @return the Location that represents the cell at (row, col)
   */
  public Location getCell(final int row, final int col) {
    String id = generateID(row, col);
    return map.getOrDefault(id, new InvalidLocation());
  }

  /**
   * Checks whether <b>location</b> is a valid Location or not. For it to be valid, both coordinates must be different
   * from -1.
   * @param location
   *     the location to be checked
   * @return
   *      boolean. True if both coordinates are different from -1 (i.e. <b>location</b> is valid). False if not
   */
  public boolean isValidLocation(Location location) {
    Location locationResponse = getCell(location.getRow(), location.getColumn());
    return (locationResponse.getRow() != -1) && (locationResponse.getColumn() != -1);
  }

  /**
   * Creates a map key from a row and a column
   *
   * @param row
   *     the row of the cell
   * @param col
   *     the column of the cell
   * @return a string of the form (row, col)
   */
  private String generateID(final int row, final int col) {
    builder.setLength(0);
    builder.append("(").append(row).append(", ").append(col).append(")");
    return builder.toString();
  }

  public Map<String, Location> getMap() {
    return Map.copyOf(map);
  }

  /**
   * Checks if the map is connected using BFS.
   *
   * @return true if the map is connected, false otherwise.
   */
  public boolean isConnected() {
    Set<Location> visitedNodes = new HashSet<>();
    Queue<Location> toVisit = new LinkedList<>();
    toVisit.add(map.entrySet().iterator().next().getValue());
    while (!toVisit.isEmpty()) {
      if (visitedNodes.size() == map.size()) {
        return true;
      }
      Location currentNode = toVisit.poll();
      for (Location neighbour :
          currentNode.getNeighbours()) {
        if (!visitedNodes.contains(neighbour)) {
          visitedNodes.add(neighbour);
          toVisit.add(neighbour);
        }
      }
    }
    return false;
  }

  /**
   * Removes a connection from two locations of the field
   */
  public void removeConnection(final Location cell1, final Location cell2) {
    if (cell1.getNeighbours().size() > 1 && cell2.getNeighbours().size() > 1) {
      cell1.removeNeighbour(cell2);
    }
  }

  /**
   * Checks if two cells of the map are connected
   */
  public boolean checkConnection(final Location cell1, final Location cell2) {
    return cell1.isNeighbour(cell2);
  }

  public int getSize() {
    return (int) Math.sqrt(getDimensions());
  }

  public int getDimensions() {
    return map.size();
  }

  public void print() {
    int size = getSize();
    StringBuilder grid = new StringBuilder(size * (size + 1));

    for (int row = 0; row < size; row++) {
      for (int column = 0; column < size; column++) {
        Location value = map.get(generateID(row, column));
        if (value == null) {
          grid.append("0");
        }
        else {
          if (value.getNeighbours().size() != 0) {
            grid.append("1");
          } else {
            grid.append("0");
          }
        }
      }
      grid.append('\n');
    }
    System.out.print(grid.toString());
  }

  public void setSeed(long seed) {
    random =  new Random(seed);
  }

  public void setMap(Map<String, Location> mapCopy) {
    map = mapCopy;
  }

  public Location getRandomCell() {
    int randomRow = random.nextInt(getSize());
    int randomColumn = random.nextInt(getSize());
    return getCell(randomRow, randomColumn);
  }
}
