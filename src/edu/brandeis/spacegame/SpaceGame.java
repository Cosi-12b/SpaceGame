package edu.brandeis.spacegame;

public class SpaceGame {
  public static void main(String[] args) {
    int n = 15;
    Game board = new Game();
    board.initGame(n);
    board.gameLoop();
    board.endGame();
  }
}