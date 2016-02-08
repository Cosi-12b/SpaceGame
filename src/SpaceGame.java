public class SpaceGame {
  public static void main(String[] args) {
    int n = 15;
    Board board = new Board();
    board.initN(n);
    board.initGame();
    board.printBoard();
    board.endGame();
  }
}