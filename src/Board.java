import java.util.Random;

public class Board {
  private int size;
  private Star[][] stars;
  
  void initN(int n) {
    size = n;
    createBoard();
    System.out.printf("Board initialized as %d x %d\n", n, n);
  }
  
  void initGame() {
    System.out.println("Game is initialzied");
    placeStars();
  }
  
  void beginGame() {
    System.out.println("Game has been started");    
  }
  
  void createBoard() {
    stars = new Star[size][size];
  }
  
  void endGame() {
    System.out.println("Game has been ended");
  }
  
  void placeStars() {
    for (int i=0; i < size/3; i++) {
      createAndPlaceStarRandomly();
    }
  }
  
  void createAndPlaceStarRandomly() {
    int rx, ry;
    Random r = new Random();
    do {    
      rx = r.nextInt(size);
      ry = r.nextInt(size);
    } while (stars[rx][ry] != null);
    System.out.printf("Placing star at %d %d\n", rx, ry);
    stars[rx][ry] = new Star();
  }
  
  boolean isFree(int x, int y) {
    return (stars[x][y] == null);
  }
  
  void printBoard() {
    for (int i=0; i<size; i++) {
      for (int j=0; j<size; j++) {
        if (stars[i][j]==null) {
          System.out.print(" . ");
        } else {
          System.out.print(" * ");
        }      
      } 
      System.out.println("");
    }
  }
}
