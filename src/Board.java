import java.util.Random;

public class Board {
  private int size;
  private Star[][] stars;
  private Planet[][] planets;
  
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
    stars = new Star[size][size];
    planets = new Planet[size][size];
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
    planets[rx][ry] = new Planet();
  }
  
  void createAndPlaceStarRandomly() {
    int rx, ry;
    Random r = new Random();
    do {    
      rx = r.nextInt(size);
      ry = r.nextInt(size);
    } while (!isFree(rx, ry));
    System.out.printf("Placing star at %d %d\n", rx, ry);
    stars[rx][ry] = new Star();
  }
  
  boolean isFree(int x, int y) {
    return ((stars[x][y] == null) && (planets[x][y] == null));
  }
  
  void printBoard() {
    for (int i=0; i<size; i++) {
      for (int j=0; j<size; j++) {
        if (isFree(i, j)) {
          System.out.print(" . ");
        } else if (planets[i][j] != null) {
          System.out.print(" o ");
        } else if (stars[i][j] != null) {
          System.out.print(" * ");
        }      
      } 
      System.out.println("");
    }
  }
}
