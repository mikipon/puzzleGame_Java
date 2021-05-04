import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameFrame implements ActionListener{
    private JFrame sfr;
    private JButton button;
    private  JLabel label;
    private int level;

    public StartGameFrame(int i,String s){
        this.level=i;
        this.sfr=new JFrame("開始準備画面");
        this.sfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.sfr.setSize(500,400);
        this.sfr.setLocation(700,200);
        this.sfr.setLayout(null);

        this.label=new JLabel();
        this.label.setText(s);
        this.label.setFont(new Font("Sans-serif",Font.PLAIN,30));
        this.label.setBounds(110,130,400,50);
        this.sfr.add(this.label);


        this.button=new JButton("開始");
        this.button.setFont(new Font("Sans-serif",Font.PLAIN,30));
        this.button.setBounds(120,220,250,60);
        this.button.addActionListener(this);
        this.sfr.add(this.button);

        this.sfr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton b=(JButton) e.getSource();
        if(b==this.button){
            sfr.setVisible(false);
            ChangeFrame(this.level);
        }
    }

    public void ChangeFrame(int i){
        this.level=i;
        if(this.level==1){
            new EasyFrame();
        }else if(this.level==2){
            new NormalFrame();
        }else{
            new HardFrame();
        }
    }
}