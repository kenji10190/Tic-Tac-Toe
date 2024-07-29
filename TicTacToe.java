import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel retry_panel = new JPanel();
    JLabel text_field = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton retry_button = new JButton();
    boolean player1_turn;

    public TicTacToe(){
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 850);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        text_field.setBackground(new Color(33,47,61));
        text_field.setForeground(new Color(25,255,0));
        text_field.setFont(new Font("HGP創英角ﾎﾟｯﾌﾟ体", Font.PLAIN, 75));
        text_field.setHorizontalAlignment(JLabel.CENTER);
        text_field.setOpaque(true);
        
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(23,32,42));

        retry_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        retry_panel.setBackground(new Color(23, 32, 42));

        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            buttons[i].setBackground(new Color(23,32,42));
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("HGP創英角ﾎﾟｯﾌﾟ体", Font.PLAIN, 120));
            // Tabボタンでボタンにフォーカスされるようにするかの設定。
            buttons[i].setFocusable(false);
            // 各ボタンにアクションリスナーを設定する。対象はtictactoeインスタンス
            buttons[i].addActionListener(this);
            buttons[i].setEnabled(false);
        }

        retry_button.setText("もう一回");
        retry_button.setFont(new Font("HGP創英角ﾎﾟｯﾌﾟ体", Font.PLAIN, 50));
        retry_button.setBackground(Color.WHITE);
        retry_button.addActionListener(e -> resetGame());
        retry_panel.add(retry_button);

        title_panel.add(text_field);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel, BorderLayout.CENTER);
        frame.add(retry_panel, BorderLayout.SOUTH);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        for (JButton button: buttons){
            if (e.getSource() == button){
                if (player1_turn){
                    if (button.getText().equals("")){
                        button.setForeground(new Color(255, 0, 0));
                        button.setText("×");
                        player1_turn = false;
                        text_field.setText("○のターンです!");
                        check();
                    } 
                } else {
                    if (button.getText().equals("")){
                        button.setForeground(new Color(0, 0, 255));
                        button.setText("○");
                        player1_turn = true;
                        text_field.setText("×のターンです!");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn(){
        
        text_field.setText("○×ゲーム");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (JButton button: buttons){
            button.setEnabled(true);
        }

        if (random.nextInt(2) == 0){
            player1_turn = true;
            text_field.setText("×のターンです!");
        } else {
            player1_turn = false;
            text_field.setText("○のターンです!");
        }
    }

    public void check(){

        int[][] winningPosition = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
        };

        for (int[] pos: winningPosition){
            if ((buttons[pos[0]].getText() == "×") &&
                (buttons[pos[1]].getText() == "×") &&
                (buttons[pos[2]].getText() == "×")
            ){
                highlightTheLine(pos[0], pos[1], pos[2]);
                text_field.setText("×の勝ちです!!");
            } else if (
                (buttons[pos[0]].getText() == "○") &&
                (buttons[pos[1]].getText() == "○") &&
                (buttons[pos[2]].getText() == "○")
            ){
                highlightTheLine(pos[0], pos[1], pos[2]);
                text_field.setText("○の勝ちです!!");
            }

        }
    }

    public void highlightTheLine(int a, int b, int c){

        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (JButton button: buttons){
            button.setEnabled(false);
        }
    }

    public void resetGame(){
        for (JButton button: buttons){
            button.setText("");
            button.setBackground(new Color(23,32,42));
            button.setEnabled(true);
        }
        
        firstTurn();
    }
}