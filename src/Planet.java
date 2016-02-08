import java.util.ArrayList;
import java.util.List;

public class Planet extends Entity {
  boolean home;
  List<SpaceShip> ships = new ArrayList<SpaceShip>();
  
  Planet() {
  }
  
  public String tinyString() {
    return (String.format(" o[%d] ", ships.size()));
  }

  public void addSpaceShip(SpaceShip ship) {
    ships.add(ship);
  }
}
