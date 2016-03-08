package edu.brandeis.spacegame;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Logger;

public class Board {
  private int size;
  private Entity[][] space;
  private LinkedList<Simulable> sims;
  private static final Logger logger = Logger.getLogger(Board.class.getName());
  private int clock;
  boolean keepRunning;

  
  void initGame(int n) {
    size = n;
    createBoard();
    logger.info(String.format("Board initialized as %d x %d\n", n, n));
    sims = new LinkedList<Simulable>();
    placeStars();
    placePlanets();
    placeSpaceShipsAndPeople();
    logger.info(String.format("Game is initialized"));
  }

  public void gameLoop() {
    clock = 0;
    keepRunning = true;
    while (keepRunning) {
      gamePulse(clock);
      printBoard(clock);
      clock++;
    }
  }

  private void gamePulse(int curTime) {
    for (Simulable s: sims) {
      s.simulationStep(curTime);
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
    System.out.printf("Creating and placing: %d stars\n", size/3);
    for (int i=0; i < size/3; i++) {
      Star aStar = new Star();
      creatAndPlaceEntityRandomly(aStar);
    }
  }
  
  private void placePlanets() {
    System.out.printf("Creating and placing: %d planets\n", size/2);
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

          // A planet gets a spaceship based on probability
          if (r.nextFloat() <= 0.5) { 
            SpaceShip ship = new SpaceShip();
            sims.add(ship);
            plan.addSpaceShip(ship);
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

}
