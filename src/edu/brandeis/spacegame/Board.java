package edu.brandeis.spacegame;

import java.util.Random;

public class Board {
  private int size;
  private Star[][] stars;
//  private Planets[] planets;
  
  void initN(int n) {
    size = n;
    createBoard();
    System.out.printf("Board initialized as %d x %d\n", n, n);
  }
  
  void initGame() {
    System.out.println("Game is initialzied");
//    placeStars();
//    placePlanets();
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
    System.out.printf("Creating and placing: %d stars%n", size/3);
    for (int i=0; i < size/3; i++) {
      creatAndPlaceStarRandomly();
    }
  }
  
//    
  private void creatAndPlaceStarRandomly() {
    int rx, ry;
    Random r = new Random();
    do {    
      rx = r.nextInt(size);
      ry = r.nextInt(size);
    } while (!isFree(rx, ry));
    stars[rx][ry] = new Star();
  }
 
  private boolean isFree(int x, int y) {
    return (stars[x][y] == null);
  }

  
//  private void creatAndPlacePlanetRandomly() {
//    int rx, ry;
//    Random r = new Random();
//    do {    
//      rx = r.nextInt(size);
//      ry = r.nextInt(size);
//    } while (!isFree(rx, ry));
//    planets[rx][ry] = new Planet();
//  }
  
// private void placePlanets() {
//  System.out.printf("Creating and placing: %d planets%n", size/2);
//  for (int i=0; i < size / 2; i++) {
//    creatAndPlacePlanetRandomly();
//  }
//}
//
//
  
  void printBoard() {
    for (int i=0; i<size; i++) {
      for (int j=0; j<size; j++) {
        if (isFree(i, j)) {
          System.out.print("  .  ");
        } else {
          System.out.print(stars[i][j].toString());
        }      
      } 
      System.out.println("");
    }
    System.out.println("Notation Planet: P:#nstars:#npeople");
  }
}
