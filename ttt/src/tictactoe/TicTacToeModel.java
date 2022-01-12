package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class implements TicTacToe and represents a
 * TicTacToe model
 */
public class TicTacToeModel implements TicTacToe {

  private Player[][] board;
  private Player side;

  /**
   * Construct a TicTacToeModel with no arguments
   */
  public TicTacToeModel(){

    board=new Player[3][3];
    side=Player.X;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
      row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
          .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
    /**********
    List<String> rows = new ArrayList<>();
    for(Player[] row : getBoard()) {
      List<String> rowStrings = new ArrayList<>();
      for(Player p : row) {
        if(p == null) {
          rowStrings.add(" ");
        } else {
          rowStrings.add(p.toString());
        }
      }
      rows.add(" " + String.join(" | ", rowStrings));
    }
    return String.join("\n-----------\n", rows);
    ************/
  }

  @Override
  public void move(int r, int c) {
    if(isGameOver()) throw new IllegalStateException("Game over");
    if(r<0||c<0||r>2||c>2||getMarkAt(r,c)!=null) throw new IllegalArgumentException("Illegal position");

    board[r][c]=side;
    if(side==Player.X){side=Player.O;}
    else {side=Player.X;}
  }

  @Override
  public Player getTurn() {
    if(isGameOver()==true) return null;
    return side;
  }

  /**
   * To find out whether all cells are occupied
   * @return true if all cells of the board are not null
   */
  private boolean isFull(){
    int i,j;
    Player[][] o=this.getBoard();
    for(i=0; i<3; i++){
      for(j=0; j<3; j++){
        if(o[i][j]==null) return false;
      }
    }
    return true;
  }
  @Override
  public boolean isGameOver() {
    if(getWinner()!=null) return true;
    if(isFull()) return true;
    return false;
  }

  @Override
  public Player getWinner() {
    Player lastSide;
    if(side==Player.X){lastSide=Player.O;}
    else {lastSide=Player.X;}
    int i;
    Player[][] o=this.getBoard();
    for(i=0; i<3; i++){
      if((o[i][0]==o[i][1])&(o[i][1]==o[i][2])&(o[i][2]==lastSide)) return lastSide;
      if((o[0][i]==o[1][i])&(o[1][i]==o[2][i])&(o[2][i]==lastSide)) return lastSide;
    }
    if(((o[0][0]==o[1][1])&(o[1][1]==o[2][2])&(o[1][1]==lastSide))||((o[0][2]==o[1][1])&(o[1][1]==o[2][0])&(o[1][1]==lastSide))) return lastSide;
    return null;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] newBoard=new Player[3][3];
    int i,j;
    for(i=0; i<3; i++){
      for(j=0; j<3; j++){
        newBoard[i][j]=board[i][j];
      }
    }
    return newBoard;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if(r<0||c<0||r>2||c>2) throw new IllegalArgumentException("Illegal position");
    return board[r][c];
  }
}
