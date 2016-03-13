package edu.brandeis.spacegame;

import java.util.Random;

public class Coordinates {
  private int x;
  private int y;
  private static Random randGen = new Random();

  
  public Coordinates(int anX, int aY) {
    x = anX;
    y = aY;
  }
  
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Coordinates)) return false;
    Coordinates c = (Coordinates) o;
    return (c.x == x && c.y == y);
  }
  
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }
  
  public static Coordinates getRandom(int size) {
    return new Coordinates(randGen.nextInt(size), randGen.nextInt(size));
  }
  
  

}
