package edu.brandeis.spacegame;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Game {
  private int size;
  private Entity[][] space;
  private LinkedList<Simulable> sims;
  private static final Logger logger = Logger.getLogger(Game.class.getName());
  private int clock;
  boolean keepRunning;

  
  void initGame(int n) {
    sysConfig();
    size = n;
    createBoard();
    logger.info(String.format("Board initialized as %d x %d", n, n));
    sims = new LinkedList<Simulable>();
    placeStars();
    placePlanets();
    placeSpaceShipsAndPeople();
    logger.info(String.format("Game is initialized"));
  }

  public void gameLoop() {
    clock = 0;
    while (keepRunning()) {
      gamePulse();
//      printBoard(clock);
      clock++;
    }
  }
  
  private boolean keepRunning() {
    return (clock < 25);
  }

  private void gamePulse() {
    logger.info("");
    logger.info(String.format("*** Clock: %d", clock));
    for (Simulable s: sims) {
      s.simulationStep(clock);
    }
    
  }

  void beginGame() {
    logger.info(String.format("Game is started"));
  }
  
  void createBoard() {
    space = new Entity[size][size];
  }
  
  void endGame() {
    logger.info(String.format("Game is ended"));
  }
  
  private void placeStars() {
    logger.info(String.format("Creating and placing: %d stars", size/3));
    for (int i=0; i < size/3; i++) {
      Star aStar = new Star();
      creatAndPlaceEntityRandomly(aStar);
    }
  }
  
  private void placePlanets() {
    logger.info(String.format("Creating and placing: %d planets", size/2));
    for (int i=0; i < size / 2; i++) {
      Planet planet = new Planet();
      sims.add(planet);
      creatAndPlaceEntityRandomly(planet);
    }
  }
  
  private void placeSpaceShipsAndPeople() {
    Random r = new Random();
    for (Entity[] eRow: space) {
      for (Entity ent: eRow) {
        if (ent instanceof Planet) {
          Planet plan = (Planet) ent;

          // A planet gets a spaceship and two people based on probability
          if (r.nextFloat() <= Constants.PROB_SHIP_ON_PLANET) { 
            SpaceShip ship = new SpaceShip();
            ship.setPlanetShipIsOn(plan);
            sims.add(ship);
            plan.addSpaceShip(ship);
            Person pers = new Person();
            plan.addPerson(pers);
            sims.add(pers);
            pers = new Person();
            plan.addPerson(pers);
            sims.add(pers);
          }
          // A planet gets a person based on probability
          if (r.nextFloat() <= 0.5) { 
            Person pers = new Person();
            plan.addPerson(pers);
            sims.add(pers);
          }
        }
      }
    }
  }
  
  private void creatAndPlaceEntityRandomly(Entity ent) {
    int rx, ry;
    Random r = new Random();
    do {    
      rx = r.nextInt(size);
      ry = r.nextInt(size);
    } while (!isFree(rx, ry));
    space[rx][ry] = ent;
  }
  
  private boolean isFree(int x, int y) {
    return (space[x][y] == null);
  }
  
  void printBoard(int now) {
    final String ANSI_CLS = "\u001b[2J";
    final String ANSI_HOME = "\u001b[H";
    System.out.print(ANSI_HOME);
    System.out.flush();

    logger.info(String.format("\fTime is: %d%n", now));
    for (int i=0; i<size; i++) {
      for (int j=0; j<size; j++) {
        if (isFree(i, j)) {
          System.out.print("   .   ");
        } else {
          System.out.print(space[i][j].tinyString());
        }      
      } 
      System.out.println("");
    }
    System.out.println("Notation Planet: P:#nstars:#npeople");
  }
  
  private void sysConfig() {
    System.setProperty("java.util.logging.SimpleFormatter.format", 
//      "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$-6s %2$s %5$s%6$s%n");
      "[%1$tH:%1$tM:%1$tS] %5$s%6$s%n");

  }

}
