package edu.brandeis.spacegame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.github.javafaker.Faker;

public class Planet extends Entity implements Simulable {
  boolean home;
  private String name;
  private static final Logger logger = Logger.getLogger(Planet.class.getName());
  List<SpaceShip> ships = new ArrayList<SpaceShip>();
  List<Person> persons = new ArrayList<Person>();
  
  Planet() {
    Faker faker = new Faker();
    this.setName(faker.name().firstName() + "anus");
    logger.info(String.format("Creating Planet: \"%s\"", name));
  }
  
  private void setName(String theName) {
    name = theName;
  }

  public String tinyString() {
    return (String.format(" %s(%d,%d) ", name, ships.size(), persons.size()));
  }

  public void addSpaceShip(SpaceShip ship) {
    ships.add(ship);
    ship.setPlanetShipIsOn(this);
  }
  
  public void addPerson(Person p) {
    logger.info("Person " + p.getName() + " placed on " + name);
    persons.add(p);
  }
  
  public boolean unloadPerson(Person toUnload) {
    int index = persons.indexOf(toUnload);
    if (index == -1) return false;
    persons.remove(index);
    return true;
  }
  
  public void simulationStep(int time) {
    // TODO Auto-generated method stub
    
  }

  public Person unloadNextPerson() {
    Person p = persons.get(0);
    persons.remove(0);
    return p;
  }

  public String getName() {
    return name;
  }

}
