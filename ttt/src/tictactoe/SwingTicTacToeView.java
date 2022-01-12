package tictactoe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTicTacToeView extends JFrame implements TicTacToeView {


    private JPanel mainPanel;
    private JButton button00;
    private JButton button01;
    private JButton button02;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    private JLabel JLabel;
    private ImageIcon px=new ImageIcon("x.png");
    private ImageIcon po=new ImageIcon("o.png");
    private ImageIcon psp=new ImageIcon("sp.png");

    public SwingTicTacToeView(String t){
        super(t);
        setSize(5500, 5300);
        setLocation(200, 200);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        button00.setActionCommand("00");
        button00.setIcon(psp);
        button01.setActionCommand("01");
        button01.setIcon(psp);
        button02.setActionCommand("02");
        button02.setIcon(psp);
        button10.setActionCommand("10");
        button10.setIcon(psp);
        button11.setActionCommand("11");
        button11.setIcon(psp);
        button12.setActionCommand("12");
        button12.setIcon(psp);
        button20.setActionCommand("20");
        button20.setIcon(psp);
        button21.setActionCommand("21");
        button21.setIcon(psp);
        button22.setActionCommand("22");
        button22.setIcon(psp);

    }
    public void setListener(ActionListener a){
        button00.addActionListener(a);
        button01.addActionListener(a);
        button02.addActionListener(a);
        button10.addActionListener(a);
        button11.addActionListener(a);
        button12.addActionListener(a);
        button20.addActionListener(a);
        button21.addActionListener(a);
        button22.addActionListener(a);
    }
    public void setCell(String side,int r,int c){
        if(r==0&c==0) {
            if(side.equals("X")){
                button00.setIcon(px);
            }else{button00.setIcon(po);}
        }
        if(r==0&c==1) {
            if(side.equals("X")){
                button01.setIcon(px);
            }else{button01.setIcon(po);}
        }
        if(r==0&c==2) {
            if(side.equals("X")){
                button02.setIcon(px);
            }else{button02.setIcon(po);}
        }
        if(r==1&c==0) {
            if(side.equals("X")){
                button10.setIcon(px);
            }else{button10.setIcon(po);}
        }
        if(r==1&c==1) {
            if(side.equals("X")){
                button11.setIcon(px);
            }else{button11.setIcon(po);}
        }
        if(r==1&c==2) {
            if(side.equals("X")){
                button12.setIcon(px);
            }else{button12.setIcon(po);}
        }
        if(r==2&c==0) {
            if(side.equals("X")){
                button20.setIcon(px);
            }else{button20.setIcon(po);}
        }
        if(r==2&c==1) {
            if(side.equals("X")){
                button21.setIcon(px);
            }else{button21.setIcon(po);}
        }
        if(r==2&c==2) {
            if(side.equals("X")){
                button22.setIcon(px);
            }else{button22.setIcon(po);}
        }
    }
    public void setText(String s){
        JLabel.setText(s);
    }

}
