package edu.brandeis.spacegame;

public class SpaceGame {
  public static void main(String[] args) {
    Game board = new Game();
    board.initGame(Constants.SIZE);
    board.gameLoop();
    board.endGame();
  }
}