package edu.brandeis.spacegame;

import java.util.logging.Logger;

public class Star extends Entity implements Simulable {
  private static final Logger logger = Logger.getLogger(Star.class.getName());

	public String tinyString() {
		return ("  star  ");
	}

	public void simulationStep(int time) {
	  logger.fine(String.format("Simstep %d%n", time));
	}

}
