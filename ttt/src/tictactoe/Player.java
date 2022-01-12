package tictactoe;

/**
 * This class represents the two sides of TicTacToe
 */
public enum Player {
    X{
        public String toString(){
            return "X";
        }
    },
    O{
        public String toString(){
            return "O";
        }
    }
}
