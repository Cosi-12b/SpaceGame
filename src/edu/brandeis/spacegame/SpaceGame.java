package edu.brandeis.spacegame;

import java.io.IOException;

public class SpaceGame {
  public static void main(String[] args) throws IOException {
    int n = 15;
    Game board = new Game();
    board.initGame(n);
    board.gameLoop();
    board.endGame();
  }
}