package tictactoe;

import java.awt.event.ActionListener;

public interface TicTacToeView {

    void setText(String s);
    void setListener(ActionListener a);
    void setCell(String side,int r, int c);
}
