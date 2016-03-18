package edu.brandeis.spacegame;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Space {
  private int size;
  private Map<Coordinates,Entity> entities = new HashMap<>();
  private static Random randGen = new Random();
  private static final Logger logger = Logger.getLogger(Space.class.getName());
  
  Space(int s) {
    size = s; 
  }
  
  public int getCountSectors() {
    return size * size;
  }

  public void placeEntityRandomly(Entity ent) {
    Coordinates c = findRandomFreeSector();
    logger.info("Placing: " + ent.tinyString() + " in sector: " + c);
    entities.put(c, ent);
  }
  
  private Coordinates findRandomFreeSector() {
    int infLoopProtect = 1000000;
    Coordinates c = Coordinates.getRandom(size);
    while (!isFree(c)) {
      c = Coordinates.getRandom(size);
      if (infLoopProtect-- < 0) {
        throw(new IllegalStateException("Possible infinite loop"));
      }
    }
    return c;
  }
  
  public void printBoard() {
//    final String ANSI_CLS = "\u001b[2J";
    final String ANSI_HOME = "\u001b[H";
    System.out.print(ANSI_HOME);
    System.out.flush();
  }

  
  public int getCountPlanets() {
    return getPlanets().size();
  }

  public Planet getRandomPlanet() {
    List<Planet>planets = getPlanets();
    return (Planet) (planets.get(randGen.nextInt(planets.size())));
  }

  private List<Planet> getPlanets() {
    return entities.values().stream().filter(x -> x instanceof Planet).map(x -> (Planet) x).collect(Collectors.toList());
  }

  private boolean isFree(Coordinates coord) {
    return !entities.containsKey(coord);  
  }
}
