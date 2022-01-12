package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class SwingTicTacToeController implements TicTacToeController,ActionListener {
    private TicTacToe m;
    private TicTacToeView v;
    private int tmp,tmp2;
    private Player side;
    String app= "";

    public SwingTicTacToeController(TicTacToeView view,TicTacToe model){
        m=model;
        v=view;
    }

    public void play(){
        v.setListener(this);
    }
    @Override
    public void playGame(TicTacToe m) {
        if(m==null) throw new IllegalArgumentException("no model");
        int row, col;
        side=m.getTurn();
        row=tmp;
        col=tmp2;
        try{
            m.move(row,col);
            if(m.isGameOver()&m.getWinner()==null) app=(String.format(m.toString())+"\nGame is over! Tie game.\n");
            else if(m.isGameOver()) app=(String.format(m.toString())+"\nGame is over! "+m.getWinner()+" wins.\n");
            else app=(String.format(m.toString())+"\nEnter a move for "+m.getTurn()+":\n");
        }
        catch (IllegalStateException e){
            app=(String.format("Game Over"));
        }
        catch (IllegalArgumentException a) {
            app=(String.format("Not a valid move: "+(tmp+1)+", "+(tmp2+1)+"\n"));
            tmp = -1;
            tmp2 = -1;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("00")){
            tmp=0;
            tmp2=0;
        }
        if(e.getActionCommand().equals("01")){
            tmp=0;
            tmp2=1;
        }
        if(e.getActionCommand().equals("02")){
            tmp=0;
            tmp2=2;
        }
        if(e.getActionCommand().equals("10")){
            tmp=1;
            tmp2=0;
        }
        if(e.getActionCommand().equals("11")){
            tmp=1;
            tmp2=1;
        }
        if(e.getActionCommand().equals("12")){
            tmp=1;
            tmp2=2;
        }
        if(e.getActionCommand().equals("20")){
            tmp=2;
            tmp2=0;

        }
        if(e.getActionCommand().equals("21")){
            tmp=2;
            tmp2=1;

        }
        if(e.getActionCommand().equals("22")){
            tmp=2;
            tmp2=2;

        }

        this.playGame(m);
        if(side!=null)v.setCell(side.toString(),tmp,tmp2);
        v.setText(app.toString());
    }
}
