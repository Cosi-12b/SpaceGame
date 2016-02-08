import java.util.Random;

public class Board {
  private int size;
  private Entity[][] space;
  
  void initN(int n) {
    size = n;
    createBoard();
    System.out.printf("Board initialized as %d x %d\n", n, n);
  }
  
  void initGame() {
    System.out.println("Game is initialzied");
    placeStars();
    placePlanets();
    placeSpaceShips();
  }
  
  void beginGame() {
    System.out.println("Game has been started");    
  }
  
  void createBoard() {
    space = new Entity[size][size];
  }
  
  void endGame() {
    System.out.println("Game has been ended");
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
      creatAndPlaceEntityRandomly(planet);
    }
  }
  
  private void placeSpaceShips() {
    Random r = new Random();
    for (Entity[] eRow: space) {
      for (Entity ent: eRow) {
        if (!(ent instanceof Planet)) continue;
        // A planet gets a spaceship with probability 0.3
        if (r.nextFloat() >= 0.3) continue;
        Planet plan = (Planet) ent;
        SpaceShip ship = new SpaceShip();
        plan.addSpaceShip(ship);
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
  
  void printBoard() {
    for (int i=0; i<size; i++) {
      for (int j=0; j<size; j++) {
        if (isFree(i, j)) {
          System.out.print("  .   ");
        } else {
          System.out.print(space[i][j].tinyString());
        }      
      } 
      System.out.println("");
    }
  }
}
