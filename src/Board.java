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
  
  void placeStars() {
    System.out.printf("Placing stars: %d\n", size/3);
    for (int i=0; i < size/3; i++) {
      createAndPlaceStarRandomly();
    }
  }
  
  void placePlanets() {
    System.out.printf("Placing planets: %d\n", size/2);
    for (int i=0; i < size / 2; i++) {
      createAndPlacePlanetRandomly();
    }
  }
  
  void createAndPlacePlanetRandomly() {
    int rx, ry;
    Random r = new Random();
    do {    
      rx = r.nextInt(size);
      ry = r.nextInt(size);
    } while (!isFree(rx, ry));
    System.out.printf("Placing planet at %d %d\n", rx, ry);
    space[rx][ry] = new Planet();
  }
  
  void createAndPlaceStarRandomly() {
    int rx, ry;
    Random r = new Random();
    do {    
      rx = r.nextInt(size);
      ry = r.nextInt(size);
    } while (!isFree(rx, ry));
    System.out.printf("Placing star at %d %d\n", rx, ry);
    space[rx][ry] = new Star();
  }
  
  boolean isFree(int x, int y) {
    return (space[x][y] == null);
  }
  
  void printBoard() {
    for (int i=0; i<size; i++) {
      for (int j=0; j<size; j++) {
        if (isFree(i, j)) {
          System.out.print(" . ");
        } else {
          System.out.print(space[i][j].tinyString());
        }      
      } 
      System.out.println("");
    }
  }
}
