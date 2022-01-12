package tictactoe;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements TicTacToeController and represents a console controller of TicTacToe
 *
 */
public class TicTacToeConsoleController implements TicTacToeController {
    private Readable read;
    private Appendable app;

    /**
     * Construct a TicTacToeConsoleController and initialize it with input r and output a
     * @param r the input of the controller
     * @param a the output of the controller
     */
    public TicTacToeConsoleController(Readable r, Appendable a){
        if(r==null||a==null) throw new IllegalArgumentException("Invalid input and output");
        read=r;
        app=a;
    }

    @Override
    public void play(){

    }
    @Override
    public void playGame(TicTacToe m) {
        if(m==null) throw new IllegalArgumentException("no model");
        try{
            app.append(String.format(m.toString()+"\nEnter a move for "+m.getTurn()+":\n"));
            int row, col;
            Scanner scanner=new Scanner(read);
            while (scanner.hasNext()){
                String tmp=scanner.next();
                if(tmp.equals("q")||tmp.equals("Q")) {
                    app.append("Game quit! Ending game state:\n"+m.toString()+"\n");
                    return;
                }
                if(!tmp.matches("\\d")) app.append(String.format("Not a valid number: "+tmp+"\n"));
                else{
                    if(scanner.hasNext()){
                        String tmp2= scanner.next();
                        if(tmp2.equals("q") || tmp2.equals("Q")) {
                            app.append("Game quit! Ending game state:\n"+m.toString()+"\n");
                            return;
                        }
                        else{
                            if(!tmp2.matches("\\d")) app.append(String.format("Not a valid number: "+tmp2+"\n"));
                            else{
                                row=Integer.parseInt(tmp)-1;
                                col=Integer.parseInt(tmp2)-1;
                                try{
                                    m.move(row,col);
                                    if(m.isGameOver()&m.getWinner()==null) app.append(String.format(m.toString())+"\nGame is over! Tie game.\n");
                                    else if(m.isGameOver()) app.append(String.format(m.toString())+"\nGame is over! "+m.getWinner()+" wins.\n");
                                    else app.append(String.format(m.toString())+"\nEnter a move for "+m.getTurn()+":\n");
                                }
                                catch (IllegalArgumentException a){app.append(String.format("Not a valid move: "+tmp+", "+tmp2+"\n"));}
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e){
            throw new IllegalStateException("append exception");
        }
    }
}
