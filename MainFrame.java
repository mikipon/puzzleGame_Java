import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame implements ActionListener {
    private static JFrame fr;
    private static JLabel bg,lb;
    private static JButton bt1,bt2,bt3;
    private static Font fn = new Font("Sans-serif",Font.PLAIN,32);

    //最初の画面作成
    public MainFrame() {

        fr = new JFrame("TimeAttackPuzzleGame");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(600,500);
        fr.setLocation(500,100);
        fr.setLayout(null);

        //背景
        ImageIcon bgicon = new ImageIcon("pazuru.png");
        bg=new JLabel();
        bg.setIcon(bgicon);
        bg.setBounds(-25,-50,600,600);
        fr.add(bg);

        //題名
        lb = new JLabel("Puzzle Puzzle");
        lb.setFont(new Font("Sans-serif",Font.PLAIN,50));
        lb.setBounds(160,80,400,50);
        bg.add(lb);

        //最初の画面の写真集
        ImageIcon icon = new ImageIcon("ame.png");
        ImageIcon icon2 = new ImageIcon("onigiri.png");
        ImageIcon icon3 = new ImageIcon("fire.png");

        //Easyのボタン
        bt1 = new JButton("Easy");
        bt1.setIcon(icon);
        bt1.setBounds(175,150,250,60);
        bt1.setFont(fn);
        bt1.addActionListener(this);
        bg.add(bt1);

        //Normalのボタン
        bt2 = new JButton("Normal");
        bt2.setIcon(icon2);
        bt2.setBounds(175,250,250,60);
        bt2.setFont(fn);
        bt2.addActionListener(this);
        bg.add(bt2);

        //Hardのボタン
        bt3 = new JButton("Hard");
        bt3.setIcon(icon3);
        bt3.setBounds(175,350,250,60);
        bt3.setFont(fn);
        bt3.addActionListener(this);
        bg.add(bt3);

        //表示
        fr.setVisible(true);

    }

    //ボタンが押された時の処理
    @Override
    public void actionPerformed(ActionEvent e){
        JButton b=(JButton) e.getSource();
        if(b==bt1){
            fr.setVisible(false);
            new StartGameFrame(1,"スタートしますか？");

        }else if(b==bt2){
            fr.setVisible(false);
            new StartGameFrame(2,"スタートしますか？");

        }else{
            fr.setVisible(false);
            new StartGameFrame(3,"スタートしますか？");
        }
    }

    public static void main(String[] args) {
        try {
            new MainFrame();
        } catch (Exception e) {

        }
    }
}