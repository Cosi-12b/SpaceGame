package edu.brandeis.spacegame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Planet extends Entity implements Simulable {
  boolean home;
  private static final Logger logger = Logger.getLogger(Planet.class.getName());
  List<SpaceShip> ships = new ArrayList<SpaceShip>();
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

  @Override
  public void simulationStep(int time) {
    // TODO Auto-generated method stub
    
  }
}
