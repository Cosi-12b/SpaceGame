package edu.brandeis.spacegame;

public class Planet {
  boolean home;
  String name;
  Planet(String name) {
    this.name = name;
  }
  
  public String tinyString() {
    return ("  planet  ");
  }
}
