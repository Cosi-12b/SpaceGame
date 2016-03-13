package edu.brandeis.spacegame;

import java.util.HashMap;
import java.util.Map;

public class Space {
  private int size;
  private Map<Coordinates,Entity> entities = new HashMap<>();
  
  Space(int s) {
    size = s; 
  }
  
  public int getCountSectors() {
    return size * size;
  }

  public void placeEntityRandomly(Entity ent) {
    Coordinates c = findRandomFreeSector();
    entities.put(c, ent);
  }
  
  private Coordinates findRandomFreeSector() {
    int infLoopProtect = 1000000;
    Coordinates c = Coordinates.getRandom(size);
    while (!isFree(c)) {
      c = Coordinates.getRandom(size);
      if (infLoopProtect-- < 0) {
        throw(new IllegalStateException("Possible infinite loop"));
      }
    }
    return c;
  }
  
  private boolean isFree(Coordinates coord) {
    return entities.containsKey(coord);  
  }
}
