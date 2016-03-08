import java.util.ArrayList;
import java.util.List;

public class Planet extends Entity {
  boolean home;
  ArrayList<SpaceShip> ships = new ArrayList<SpaceShip>();
  List<Person> people = new ArrayList<Person>();
  
  Planet() {
  }
  
  public String tinyString() {
    return (String.format(" P:%d:%d ", ships.size(), people.size()));
  }

  public void addSpaceShip(SpaceShip ship) {
    ships.add(ship);
  }
  
  public void addPerson(Person p) {
    people.add(p);
  }
}
