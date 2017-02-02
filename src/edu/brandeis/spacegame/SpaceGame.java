package edu.brandeis.spacegame;

// Overall game class. Begin by keeping it really small to maximize encapsulation
public class SpaceGame {
  public static void main(String[] args) {
    Board board = new Board();
    board.initN(15);
    board.initGame();
    board.printBoard();
    board.endGame();
  }
}