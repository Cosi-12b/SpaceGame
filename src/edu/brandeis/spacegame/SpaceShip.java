package edu.brandeis.spacegame;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.github.javafaker.Faker;

public class SpaceShip implements Simulable {
  private static final Logger logger = Logger.getLogger(SpaceShip.class.getName());
  private String name;
  private int capacity = Constants.SPACESHIP_CAPACITY;
  private List<Person> persons = new LinkedList<Person>();
  private Planet planetShipIsOn; // or null
  
  
  SpaceShip() {
    Faker faker = new Faker();
    this.setName(faker.name().firstName());
    logger.info(String.format("Creating Ship: \"%s\"", name));
  }
  
  public boolean loadPerson(Person p) {
    if (persons.size() >= capacity) {
      return false;
    } else {
      persons.add(p);
    }
    return true;
  }
  
  public boolean unload(Person personToUnload) {
    int index = persons.indexOf(personToUnload);
    if (index == -1) return false;
    persons.remove(index);
    return true;
  }

  public void simulationStep(int time) {
    if (persons.size() < capacity) {
      if (planetShipIsOn == null || planetShipIsOn.persons.isEmpty()) return;
      Person p = planetShipIsOn.unloadNextPerson();
      logger.info(String.format("Person \"%s\" enters Spaceship \"%s\" (on planet \"%s\")", p.getName(), getName(), planetShipIsOn.getName()));
      loadPerson(p);
    } else if (persons.size() == capacity  && planetShipIsOn != null) {
        launch();
      } else if (planetShipIsOn == null) {
        travelInSpace();
      }
    }
  
  private void travelInSpace() {
    logger.info(String.format("Spaceship \"%s\" traveling but getting nowhere", getName()));
  }

  private void launch() {
    logger.info(String.format("Launching Spaceship \"%s\" from planet \"%s\")", getName(), planetShipIsOn.getName()));
    planetShipIsOn = null;
    
  }

  public Planet getPlanetShipIsOn() {
    return planetShipIsOn;
  }

  public void setPlanetShipIsOn(Planet planetShipIsOn) {
    this.planetShipIsOn = planetShipIsOn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
