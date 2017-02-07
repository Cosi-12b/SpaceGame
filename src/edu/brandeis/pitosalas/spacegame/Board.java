package edu.brandeis.pitosalas.spacegame;
import java.util.Random;

public class Board {
  private int size;
  private Star[][] stars;
  
  void initN(int n) {
    size = n;
    createBoard();
    placeStars();
    System.out.printf("Board initialized as %d x %d\n", n, n);
  }
  
  void initGame() {
    System.out.println("Game is initialzied");
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
  
  private void placeStars() {
    System.out.printf("Creating and placing: %d stars\n", size/3);
    for (int i=0; i < size/3; i++) {
      creatAndPlaceStarRandomly();
    }
  }

  private void creatAndPlaceStarRandomly() {
    int rx, ry;
    Random r = new Random();
    do {    
      rx = r.nextInt(size);
      ry = r.nextInt(size);
    } while (!isFree(rx, ry));
    placeStar(rx, ry);
  }
  
  void placeStar(int rx, int ry) {
    stars[rx][ry] = new Star();
  }
  
  private boolean isFree(int x, int y) {
    return (stars[x][y] == null);
  }
  
  void printBoard() {
    for (int i=0; i<size; i++) {
      for (int j=0; j<size; j++) {
        if (isFree(i, j)) {
          System.out.print("   .   ");
        } else {
          System.out.print(stars[i][j]);
        }      
      } 
      System.out.println("");
    }
    System.out.println("Notation Planet: P:#nstars:#npeople");
  }
}
