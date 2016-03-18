package edu.brandeis.spacegame;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Game {
  private int size;
  private Space space;
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
    placeSpaceShips();
    placePeople();
    logger.info(String.format("Game is initialized"));
  }

  public void gameLoop() {
    clock = 0;
    while (keepRunning()) {
      gamePulse();
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
    space = new Space(size);
  }
  
  void endGame() {
    logger.info(String.format("Game is ended"));
  }
  
  private void placeStars() {
    int nStars = (int) (space.getCountSectors() * Constants.PROB_STAR_IN_SECTOR);
    for (int i = 0; i < nStars; i++) {
      Star aStar = new Star();
      space.placeEntityRandomly(aStar);
      sims.add(aStar);
    }
  }
  
  private void placePlanets() {
    int totalSectors = space.getCountSectors();
    int nPlanets = (int) (totalSectors * Constants.PROB_PLANET_IN_SECTOR);
    for (int i = 0; i < nPlanets ; i++) {
      Planet planet = new Planet();
      space.placeEntityRandomly(planet);      
      sims.add(planet);
    }
  }
  
  private void placeSpaceShips() {
    int totalPlanets = space.getCountPlanets();
    int nSpaceShips = (int) (totalPlanets * Constants.PROB_SHIP_ON_PLANET);
    for (int i = 0; i < nSpaceShips ; i++) {
      SpaceShip ship = new SpaceShip();
      Planet p = space.getRandomPlanet();
      p.addSpaceShip(ship);
      sims.add(ship);
    }
  }
  
  private void placePeople() {
    int totalPlanets = space.getCountPlanets();
    int nPeople = (int) (totalPlanets * Constants.PROB_PERSON_ON_PLANET);
    for (int i = 0; i < nPeople; i++) {
      Person pers = new Person();
      sims.add(pers);
      Planet planit = space.getRandomPlanet();
      planit.addPerson(pers);
    }
  }
  
  private void sysConfig() {
    System.setProperty("java.util.logging.SimpleFormatter.format", 
//      "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$-6s %2$s %5$s%6$s%n");
      "[%1$tH:%1$tM:%1$tS] %5$s%6$s%n");

  }

}
