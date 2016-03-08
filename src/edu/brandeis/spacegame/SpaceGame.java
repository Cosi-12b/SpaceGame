package edu.brandeis.spacegame;
public class SpaceGame {
  public static void main(String[] args) {
    int n = 15;
    Board board = new Board();
    board.initGame(n);
    board.gameLoop();
    board.endGame();
  }
}