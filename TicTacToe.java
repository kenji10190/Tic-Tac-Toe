import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel text_filed = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    public TicTacToe(){
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        text_filed.setBackground(new Color(25,25,25));
        text_filed.setForeground(new Color(25,255,0));
        text_filed.setFont(new Font("HGP創英角ﾎﾟｯﾌﾟ体", Font.PLAIN, 75));
        text_filed.setHorizontalAlignment(JLabel.CENTER);
        text_filed.setText("○×ゲーム");
        text_filed.setOpaque(true);
        
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));

        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("HGP創英角ﾎﾟｯﾌﾟ体", Font.PLAIN, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(text_filed);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e){

        for (int i = 0; i < 9; i++){
            if (e.getSource() == buttons[i]){
                if (player1_turn){
                    if (buttons[i].getText() == ""){
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("×");
                        player1_turn = false;
                        text_filed.setText("○のターンです!");
                        check();
                    }
                } else {
                    if (buttons[i].getText() == ""){
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("○");
                        player1_turn = true;
                        text_filed.setText("×のターンです!");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn(){
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0){
            player1_turn = true;
            text_filed.setText("×のターンです!");
        } else {
            player1_turn = false;
            text_filed.setText("○のターンです!");
        }
    }

    public void check(){

    }

    public void xWin(int a, int b, int c){

    }

    public void oWin(int a, int b, int c){

    }

}