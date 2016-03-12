package edu.brandeis.spacegame;

import java.util.logging.Logger;

import com.github.javafaker.Faker;

public class Person implements Simulable {
  private String name;
  private static final Logger logger = Logger.getLogger(Person.class.getName());

  
  Person() {
    Faker faker = new Faker();
    this.setName(faker.name().firstName());
    logger.info(String.format("Creating person: %s", name));
  }

  public void simulationStep(int time) {
    // TODO Auto-generated method stub
    
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
