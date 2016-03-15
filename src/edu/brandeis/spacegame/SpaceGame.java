package edu.brandeis.spacegame;

import java.io.IOException;

public class SpaceGame {
  public static void main(String[] args) throws IOException {
    int n = 15;
    Board board = new Board();
    board.initGame(n);
    board.gameLoop();
    board.endGame();
  }
}