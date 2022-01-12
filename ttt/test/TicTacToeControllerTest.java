import static org.junit.Assert.assertEquals;

import tictactoe.*;

import java.io.StringReader;
import java.util.Arrays;
import org.junit.Test;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and appendable.
 */
public class TicTacToeControllerTest {

  // ADDITIONAL TEST CASES TO IMPLEMENT:
  // Play game to completion, where there is a winner
  // Input where the q comes instead of an integer for the row
  // Input where the q comes instead of an integer for the column
  // Input where non-integer garbage comes instead of an integer for the row
  // Input where non-integer garbage comes instead of an integer for the column
  // Input where the move is integers, but outside the bounds of the board
  // Input where the move is integers, but invalid because the cell is occupied
  // Multiple invalid moves in a row of various kinds
  // Input including valid moves interspersed with invalid moves, game is played to completion
  // What happens when the input ends "abruptly" -- no more input, but not quit, and game not over
  // THIS IS NOT AN EXHAUSTIVE LIST

  /**
   * Test X wins
   */
  @Test
  public void testXWin() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 1 1 2 3 1 1 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(36, lines.length);
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }

  /**
   * Test O wins with invalid number
   */
  @Test
  public void testOWin() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 one 1 1 2 1 1 2 2 2 3 3 2 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(43, lines.length);
    assertEquals("Not a valid number: one", lines[6]);
    assertEquals("Game is over! O wins.", lines[lines.length - 1]);
  }

  /**
   * Test the input with non-integer
   */
  @Test
  public void testNonInt() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("one 1 1 2 2.8 3 ?");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(15, lines.length);
    assertEquals("Not a valid number: one", lines[lines.length - 9]);
    assertEquals("Not a valid number: 2.8", lines[lines.length - 2]);
    assertEquals("Not a valid number: ?", lines[lines.length - 1]);
  }

  /**
   * Test the invalid move of in same cell
   */
  @Test
  public void testSameCell() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 1");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(13, lines.length);
    assertEquals("Not a valid move: 1, 1", lines[lines.length - 1]);
  }

  /**
   * Test game quit
   */
  @Test
  public void testQuit() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("qone 1 1 q 1");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(19, lines.length);
    assertEquals("Not a valid number: qone", lines[lines.length - 13]);
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  ", lastMsg);
  }

  /**
   * Test moves out of board
   */
  @Test
  public void outBoard() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("4 4 2 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(13, lines.length);
    assertEquals("Not a valid move: 4, 4", lines[lines.length - 7]);
  }

  /**
   * Test valid moves combined with invalid moves
   */
  @Test
  public void testValidAndInvalid() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("one 1 1 1 1 1 4 0 1 1.1 1");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for X:\n" +
            "Not a valid number: one\n" +
            " X |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for O:\n" +
            "Not a valid move: 1, 1\n" +
            "Not a valid move: 1, 4\n" +
            "Not a valid move: 0, 1\n" +
            "Not a valid number: 1.1\n", gameLog.toString());
  }

  /**
   * Test a single valid move
   */
  @Test
  public void testSingleValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n", gameLog.toString());
  }

  /**
   * Multiple invalid moves in a row of various kinds
   */
  @Test
  public void testBogusInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("!#$ 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals(13, lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  /**
   * Test draw
   */
  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    // note the entire sequence of user inputs for the entire game is in this one string:
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(60, lines.length);
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

}
