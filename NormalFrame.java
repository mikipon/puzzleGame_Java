import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class NormalFrame{

    //画面
    private static JFrame nfr;
    private static JLabel lb;
    private static Image getImg;
    private static Image newImg;

    //パズル（タイムアタックだから何個もパズル画像を表示しないといけない？）
    //パズルのボタン
    private static JButton btL1 = new JButton();
    private static JButton btL2 = new JButton();
    private static JButton btL3 = new JButton();
    private static JButton btU1 = new JButton();
    private static JButton btU2 = new JButton();
    private static JButton btU3 = new JButton();
    private static JButton btR1 = new JButton();
    private static JButton btR2 = new JButton();
    private static JButton btR3 = new JButton();

    //パズルを置く場所のボタン
    private static JButton setBtT1 = new JButton();
    private static JButton setBtT2 = new JButton();
    private static JButton setBtT3 = new JButton();
    private static JButton setBtC1 = new JButton();
    private static JButton setBtC2 = new JButton();
    private static JButton setBtC3 = new JButton();
    private static JButton setBtU1 = new JButton();
    private static JButton setBtU2 = new JButton();
    private static JButton setBtU3 = new JButton();


    //パンダ
    private static ImageIcon icon1=new ImageIcon("NormalImg\\pPan1.jpg");
    private static ImageIcon icon2=new ImageIcon("NormalImg\\pPan2.jpg");
    private static ImageIcon icon3=new ImageIcon("NormalImg\\pPan3.jpg");
    private static ImageIcon icon4=new ImageIcon("NormalImg\\pPan4.jpg");
    private static ImageIcon icon5=new ImageIcon("NormalImg\\pPan5.jpg");
    private static ImageIcon icon6=new ImageIcon("NormalImg\\pPan6.jpg");
    private static ImageIcon icon7=new ImageIcon("NormalImg\\pPan7.jpg");
    private static ImageIcon icon8=new ImageIcon("NormalImg\\pPan8.jpg");
    private static ImageIcon icon9=new ImageIcon("NormalImg\\pPan9.jpg");

    //ゴリラ
    private static ImageIcon icon21=new ImageIcon("NormalImg\\pGor1.jpg");
    private static ImageIcon icon22=new ImageIcon("NormalImg\\pGor2.jpg");
    private static ImageIcon icon23=new ImageIcon("NormalImg\\pGor3.jpg");
    private static ImageIcon icon24=new ImageIcon("NormalImg\\pGor4.jpg");
    private static ImageIcon icon25=new ImageIcon("NormalImg\\pGor5.jpg");
    private static ImageIcon icon26=new ImageIcon("NormalImg\\pGor6.jpg");
    private static ImageIcon icon27=new ImageIcon("NormalImg\\pGor7.jpg");
    private static ImageIcon icon28=new ImageIcon("NormalImg\\pGor8.jpg");
    private static ImageIcon icon29=new ImageIcon("NormalImg\\pGor9.jpg");

    private static ImageIcon[][] imgIcon = new ImageIcon[2][9];

    //ランダム用
    private static ArrayList<Integer> listY = new ArrayList<Integer>();

    //背景
    private static Color backColor = new Color(196,205,207);
    private static Color cr1 = new Color(0,0,0,200);

    //時間
    private static Timer timer;
    private static JLabel timerlb;
    private static int sec = 60;//制限時間

    //全部パズルをあてはめたら
    private static int check;//全部はめた？
    private static int score = 0;//何個解いた？
    private static JLabel Result = new JLabel();
    private static boolean bool = true;

    public  NormalFrame() {

        //画面の作成メソッドへGo
        CreateGameFrame();
        //画像の設定
        SetImageIcon();


        //Bottonを作成
        //ボード側のボタンのサイズと位置を設定
        //左上
        PuzzleBase(setBtT1, 188, 150);
        nfr.add(setBtT1);

        //上、真ん中
        PuzzleBase(setBtT2, 288, 150);
        nfr.add(setBtT2);

        //右上
        PuzzleBase(setBtT3, 388, 150);
        nfr.add(setBtT3);

        //真ん中、左
        PuzzleBase(setBtC1, 188, 250);
        nfr.add(setBtC1);

        //ド真ん中
        PuzzleBase(setBtC2, 288, 250);
        nfr.add(setBtC2);

        //真ん中、右
        PuzzleBase(setBtC3, 388, 250);
        nfr.add(setBtC3);

        //左下
        PuzzleBase(setBtU1, 188, 350);
        nfr.add(setBtU1);

        //下、真ん中
        PuzzleBase(setBtU2, 288, 350);
        nfr.add(setBtU2);

        //右下
        PuzzleBase(setBtU3, 388, 350);
        nfr.add(setBtU3);

        //パズルのIconを変える
        ChangePuzzle();

        //外の左上のボタンを押したときに呼び出される
        btL1.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btL1：");

                //押された時の処理のメソッドに飛ぶ
                btL1_ActionPerformed(evt);

                //アクションリスナーのボタン以外無効化
                btL2.setEnabled(false);
                btL3.setEnabled(false);
                btR1.setEnabled(false);
                btR2.setEnabled(false);
                btR3.setEnabled(false);
                btU1.setEnabled(false);
                btU2.setEnabled(false);
                btU3.setEnabled(false);
            }

        });


        //外の左下のボタンが押された時に呼び出される
        btL2.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btL2：");

                //押された時の処理のメソッドに飛ぶ
                btL2_ActionPerformed(evt);

                //アクションリスナーのボタン以外無効化
                btL1.setEnabled(false);
                btL3.setEnabled(false);
                btR1.setEnabled(false);
                btR2.setEnabled(false);
                btR3.setEnabled(false);
                btU1.setEnabled(false);
                btU2.setEnabled(false);
                btU3.setEnabled(false);
            }

        });


        //外の左下のボタンが押された時に呼び出される
        btL3.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btL2：");

                //押された時の処理のメソッドに飛ぶ
                btL3_ActionPerformed(evt);

                //アクションリスナーのボタン以外無効化
                btL1.setEnabled(false);
                btL2.setEnabled(false);
                btR1.setEnabled(false);
                btR2.setEnabled(false);
                btR3.setEnabled(false);
                btU1.setEnabled(false);
                btU2.setEnabled(false);
                btU3.setEnabled(false);

            }

        });



        //外側の右上のボタンが押された時に呼び出される
        btR1.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btR1：");

                //押された時の処理のメソッドに飛ぶ
                btR1_ActionPerformed(evt);

                //アクションリスナーのボタン以外無効化
                btL1.setEnabled(false);
                btL2.setEnabled(false);
                btL3.setEnabled(false);
                btR2.setEnabled(false);
                btR3.setEnabled(false);
                btU1.setEnabled(false);
                btU2.setEnabled(false);
                btU3.setEnabled(false);

            }

        });

        //外側の右、真ん中のボタンが押された時に呼び出される
        btR2.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btR2：");

                //押された時の処理のメソッドに飛ぶ
                btR2_ActionPerformed(evt);

                //アクションリスナーのボタン以外無効化
                btL1.setEnabled(false);
                btL2.setEnabled(false);
                btL3.setEnabled(false);
                btR1.setEnabled(false);
                btR3.setEnabled(false);
                btU1.setEnabled(false);
                btU2.setEnabled(false);
                btU3.setEnabled(false);

            }

        });

        //外側の右、真ん中のボタンが押された時に呼び出される
        btR3.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btR3：");

                //押された時の処理のメソッドに飛ぶ
                btR3_ActionPerformed(evt);

                //アクションリスナーのボタン以外無効化
                btL1.setEnabled(false);
                btL2.setEnabled(false);
                btL3.setEnabled(false);
                btR1.setEnabled(false);
                btR2.setEnabled(false);
                btU1.setEnabled(false);
                btU2.setEnabled(false);
                btU3.setEnabled(false);

            }

        });

        //外の左下のボタンが押された時に呼び出される
        btU1.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btU1：");

                //押された時の処理のメソッドに飛ぶ
                btU1_ActionPerformed(evt);

                //アクションリスナーのボタン以外無効化

                btL1.setEnabled(false);
                btL2.setEnabled(false);
                btL3.setEnabled(false);
                btR1.setEnabled(false);
                btR2.setEnabled(false);
                btR3.setEnabled(false);
                btU2.setEnabled(false);
                btU3.setEnabled(false);

            }

        });

        //外の真ん中下のボタンが押された時に呼び出される
        btU2.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btU2：");

                //押された時の処理のメソッドに飛ぶ
                btU2_ActionPerformed(evt);


                //アクションリスナーのボタン以外無効化
                btL1.setEnabled(false);
                btL2.setEnabled(false);
                btL3.setEnabled(false);
                btR1.setEnabled(false);
                btR2.setEnabled(false);
                btR3.setEnabled(false);
                btU1.setEnabled(false);
                btU3.setEnabled(false);

            }

        });

        //外の右下のボタンが押された時に呼び出される
        btU3.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btU3：");

                //押された時の処理のメソッドに飛ぶ
                btU3_ActionPerformed(evt);


                //アクションリスナーのボタン以外無効化
                btL1.setEnabled(false);
                btL2.setEnabled(false);
                btL3.setEnabled(false);
                btR1.setEnabled(false);
                btR2.setEnabled(false);
                btR3.setEnabled(false);
                btU1.setEnabled(false);
                btU2.setEnabled(false);

            }

        });

    }


    //パズルをランダムに変えて、外側のそれぞれのボタンにIconに配置する
    public  static  void ChangePuzzle(){
        //リストの中身をクリア
        listY.clear();

        //配列のXをランダムにする
        ArrayList<Integer> listX = new ArrayList<Integer>();

        // リストに0から4を代入
        for(int i=0; i<2; i++) listX.add(i);

        //Listの中身をシャッフル
        Collections.shuffle(listX);

        //もしパンダだったら(０ == pHumたち)
        if(listX.get(0) == 0) {

            //Yをランダムにするメソッドを呼ぶ
            RandomY();

            //外側のパズル配置
            //右上
            btL1.setIcon(imgIcon[0][listY.get(0)]);
            PuzzleBase(btL1,20,140);
            nfr.add(btL1);

            //上、真ん中
            btL2.setIcon(imgIcon[0][listY.get(1)]);
            PuzzleBase(btL2,20,260);
            nfr.add(btL2);

            //左上
            btL3.setIcon(imgIcon[0][listY.get(2)]);
            PuzzleBase(btL3,20,380);
            nfr.add(btL3);

            //真ん中、左
            btR1.setIcon(imgIcon[0][listY.get(3)]);
            PuzzleBase(btR1,560,140);
            nfr.add(btR1);

            //ど真ん中
            btR2.setIcon(imgIcon[0][listY.get(4)]);
            PuzzleBase(btR2,560,260);
            nfr.add(btR2);

            //真ん中、右
            btR3.setIcon(imgIcon[0][listY.get(5)]);
            PuzzleBase(btR3,560,380);
            nfr.add(btR3);

            //左下
            btU1.setIcon(imgIcon[0][listY.get(6)]);
            PuzzleBase(btU1,100,520);
            nfr.add(btU1);

            //下、真ん中
            btU2.setIcon(imgIcon[0][listY.get(7)]);
            PuzzleBase(btU2,300,520);
            nfr.add(btU2);

            //右下
            btU3.setIcon(imgIcon[0][listY.get(8)]);
            PuzzleBase(btU3,500,520);
            nfr.add(btU3);
        }else{//ゴリラだったら(1 == pGorたち)

            //Yをランダムにするメソッドを呼ぶ
            RandomY();

            //右上
            btL1.setIcon(imgIcon[listX.get(0)][listY.get(0)]);
            PuzzleBase(btL1,20,140);
            nfr.add(btL1);

            //上、真ん中
            btL2.setIcon(imgIcon[listX.get(0)][listY.get(1)]);
            PuzzleBase(btL2,20,260);
            nfr.add(btL2);

            //左上
            btL3.setIcon(imgIcon[listX.get(0)][listY.get(2)]);
            PuzzleBase(btL3,20,380);
            nfr.add(btL3);

            //真ん中、左
            btR1.setIcon(imgIcon[listX.get(0)][listY.get(3)]);
            PuzzleBase(btR1,560,140);
            nfr.add(btR1);

            //ど真ん中
            btR2.setIcon(imgIcon[1][listY.get(4)]);
            PuzzleBase(btR2,560,260);
            nfr.add(btR2);

            //真ん中、右
            btR3.setIcon(imgIcon[listX.get(0)][listY.get(5)]);
            PuzzleBase(btR3,560,380);
            nfr.add(btR3);

            //左下
            btU1.setIcon(imgIcon[listX.get(0)][listY.get(6)]);
            PuzzleBase(btU1,100,520);
            nfr.add(btU1);

            //下、真ん中
            btU2.setIcon(imgIcon[listX.get(0)][listY.get(7)]);
            PuzzleBase(btU2,300,520);
            nfr.add(btU2);

            //右下
            btU3.setIcon(imgIcon[listX.get(0)][listY.get(8)]);
            PuzzleBase(btU3,500,520);
            nfr.add(btU3);

        }


    }

    public static void CreateGameFrame(){

        //Normal画面の表示
        //フレーム
        nfr = new JFrame("Normal");
        nfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nfr.setSize(700,700);
        nfr.setLocation(500,100);
        nfr.getContentPane().setBackground(backColor);
        nfr.setLayout(null);
        nfr.setVisible(true);



        //表示文字
        lb = new JLabel();
        lb.setBounds(186,147,305,305);
        lb.setBorder(new LineBorder(
                Color.BLACK
                , 2, true));
        nfr.add(lb);

        //スコアの表示
        Result.setBounds(200,450,100,100);
        Result.setFont(new Font("Sans-serif",Font.PLAIN,50));
        nfr.add(Result);

        Timer();
    }

    //時間
    public static void Timer(){
        timerlb = new JLabel();
        timerlb.setBounds(170,0,400,100);
        timerlb.setFont(new Font("Sans-serif",Font.PLAIN,50));
        nfr.add(timerlb);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timerlb.setText("TimeLimit " + sec + "sec");
                if (sec <= 0){
                    timer.stop();

                    nfr.getContentPane().removeAll();

                    setBtC1 = new JButton();
                    setBtC2 = new JButton();
                    setBtC3 = new JButton();
                    setBtT1 = new JButton();
                    setBtT2 = new JButton();
                    setBtT3 = new JButton();
                    setBtU1 = new JButton();
                    setBtU2 = new JButton();
                    setBtU3 = new JButton();

                    btL1 = new JButton();
                    btL2 = new JButton();
                    btL3 = new JButton();
                    btR1 = new JButton();
                    btR2 = new JButton();
                    btR3 = new JButton();
                    btU1 = new JButton();
                    btU2 = new JButton();
                    btU3 = new JButton();
                    Result = new JLabel();
                    sec = 60;

                    Result();
                    score = 0;


                }else{
                    sec--;
                }
            }
        });
        timer.start();
    }

    //結果画面
    public static void Result(){
        //全部消す
        nfr.getContentPane().removeAll();
        nfr.repaint();

        //パネル貼る
        JPanel repn = new JPanel();
        repn.setBounds(0,0,700,700);
        repn.setBackground(cr1);
        nfr.add(repn);
        repn.setLayout(null);

        JLabel relb = new JLabel("Result");
        relb.setBounds(255,0,350,100);
        relb.setFont(new Font("Sans-serif",Font.PLAIN,50));
        relb.setForeground(Color.white);
        repn.add(relb);

        //結果
        JLabel sco = new JLabel();
        sco.setText(String.valueOf(score));
        sco.setBounds(300,200,350,100);
        sco.setFont(new Font("Sans-serif",Font.PLAIN,100));
        sco.setForeground(Color.white);
        repn.add(sco);

        //メイン戻るボタン
        JButton topbt = new JButton("Back to Title");
        topbt.setBounds(190,400,280,60);
        topbt.setFont(new Font("Sans-serif",Font.PLAIN,30));
        repn.add(topbt);
        topbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("Back to Title")) {
                    nfr.dispose();
                    new  MainFrame();
                }
            }
        });
    }
    public static ArrayList<Integer> RandomY(){

        //配列のYをランダムにする
        // リストに0から9を代入
        for(int i=0; i<9; i++){

            listY.add(i);

        }

        //Listの中身をシャッフル
        Collections.shuffle(listY);
        return listY;

    }

    //ボタンとパズルの位置を決定
    public static void PuzzleBase(JButton baseBt, int x, int y){

        int width = 100;
        int height = 100;
        baseBt.setBounds(x, y, width, height);

    }


    //内側のボタンに画像があったらその画像があった外側のボタンを無効化
    private static void  noTouchPuzzle(){

        //左上
        if(setBtT1.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtT1.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);


        }else if(setBtT1.getIcon() == btL3.getIcon()){

            btL3.setEnabled(false);

        }else if(setBtT1.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtT1.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }else if(setBtT1.getIcon() == btR3.getIcon()){

            btR3.setEnabled(false);

        }else if(setBtT1.getIcon() == btU1.getIcon()){

            btU1.setEnabled(false);

        }else if(setBtT1.getIcon() == btU2.getIcon()){

            btU2.setEnabled(false);

        }else if(setBtT1.getIcon() == btU3.getIcon()){

            btU3.setEnabled(false);

        }


        //上、真ん中
        if(setBtT2.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtT2.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);


        }else if(setBtT2.getIcon() == btL3.getIcon()){

            btL3.setEnabled(false);

        }else if(setBtT2.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtT2.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }else if(setBtT2.getIcon() == btR3.getIcon()){

            btR3.setEnabled(false);

        }else if(setBtT2.getIcon() == btU1.getIcon()){

            btU1.setEnabled(false);

        }else if(setBtT2.getIcon() == btU2.getIcon()){

            btU2.setEnabled(false);

        }else if(setBtT2.getIcon() == btU3.getIcon()){

            btU3.setEnabled(false);

        }


        //右上
        if(setBtT3.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtT3.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);


        }else if(setBtT3.getIcon() == btL3.getIcon()){

            btL3.setEnabled(false);

        }else if(setBtT3.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtT3.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }else if(setBtT3.getIcon() == btR3.getIcon()){

            btR3.setEnabled(false);

        }else if(setBtT3.getIcon() == btU1.getIcon()){

            btU1.setEnabled(false);

        }else if(setBtT3.getIcon() == btU2.getIcon()){

            btU2.setEnabled(false);

        }else if(setBtT3.getIcon() == btU3.getIcon()){

            btU3.setEnabled(false);

        }

        //真ん中,左
        if(setBtC1.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtC1.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);


        }else if(setBtC1.getIcon() == btL3.getIcon()){

            btL3.setEnabled(false);

        }else if(setBtC1.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtC1.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }else if(setBtC1.getIcon() == btR3.getIcon()){

            btR3.setEnabled(false);

        }else if(setBtC1.getIcon() == btU1.getIcon()){

            btU1.setEnabled(false);

        }else if(setBtC1.getIcon() == btU2.getIcon()){

            btU2.setEnabled(false);

        }else if(setBtC1.getIcon() == btU3.getIcon()){

            btU3.setEnabled(false);

        }

        //ド真ん中
        if(setBtC2.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtC2.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);


        }else if(setBtC2.getIcon() == btL3.getIcon()){

            btL3.setEnabled(false);

        }else if(setBtC2.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtC2.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }else if(setBtC2.getIcon() == btR3.getIcon()){

            btR3.setEnabled(false);

        }else if(setBtC2.getIcon() == btU1.getIcon()){

            btU1.setEnabled(false);

        }else if(setBtC2.getIcon() == btU2.getIcon()){

            btU2.setEnabled(false);

        }else if(setBtC2.getIcon() == btU3.getIcon()){

            btU3.setEnabled(false);

        }

        //真ん中,右
        if(setBtC3.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtC3.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);


        }else if(setBtC3.getIcon() == btL3.getIcon()){

            btL3.setEnabled(false);

        }else if(setBtC3.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtC3.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }else if(setBtC3.getIcon() == btR3.getIcon()){

            btR3.setEnabled(false);

        }else if(setBtC3.getIcon() == btU1.getIcon()){

            btU1.setEnabled(false);

        }else if(setBtC3.getIcon() == btU2.getIcon()){

            btU2.setEnabled(false);

        }else if(setBtC3.getIcon() == btU3.getIcon()){

            btU3.setEnabled(false);

        }

        //左下
        if(setBtU1.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtU1.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);


        }else if(setBtU1.getIcon() == btL3.getIcon()){

            btL3.setEnabled(false);

        }else if(setBtU1.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtU1.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }else if(setBtU1.getIcon() == btR3.getIcon()){

            btR3.setEnabled(false);

        }else if(setBtU1.getIcon() == btU1.getIcon()){

            btU1.setEnabled(false);

        }else if(setBtU1.getIcon() == btU2.getIcon()){

            btU2.setEnabled(false);

        }else if(setBtU1.getIcon() == btU3.getIcon()){

            btU3.setEnabled(false);

        }

        //下、真ん中
        if(setBtU2.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtU2.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);


        }else if(setBtU2.getIcon() == btL3.getIcon()){

            btL3.setEnabled(false);

        }else if(setBtU2.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtU2.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }else if(setBtU2.getIcon() == btR3.getIcon()){

            btR3.setEnabled(false);

        }else if(setBtU2.getIcon() == btU1.getIcon()){

            btU1.setEnabled(false);

        }else if(setBtU2.getIcon() == btU2.getIcon()){

            btU2.setEnabled(false);

        }else if(setBtU2.getIcon() == btU3.getIcon()){

            btU3.setEnabled(false);

        }

        //右下
        if(setBtU3.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtU3.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);


        }else if(setBtU3.getIcon() == btL3.getIcon()){

            btL3.setEnabled(false);

        }else if(setBtU3.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtU3.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }else if(setBtU3.getIcon() == btR3.getIcon()){

            btR3.setEnabled(false);

        }else if(setBtU3.getIcon() == btU1.getIcon()){

            btU1.setEnabled(false);

        }else if(setBtU3.getIcon() == btU2.getIcon()){

            btU2.setEnabled(false);

        }else if(setBtU3.getIcon() == btU3.getIcon()){

            btU3.setEnabled(false);

        }


    }

    //セットするほうのボタンのアクションリスナーをやめる
    private static void StopActionListener(){

        //setBtT1のアクションリスナーを止める
        for(ActionListener al : setBtT1.getActionListeners()){
            setBtT1.removeActionListener(al);
        }

        //setBtT2のアクションリスナーを止める
        for(ActionListener al : setBtT2.getActionListeners()){
            setBtT2.removeActionListener(al);
        }

        //setBtT3のアクションリスナーを止める
        for(ActionListener al : setBtT3.getActionListeners()){
            setBtT3.removeActionListener(al);
        }

        //setBtC1のアクションリスナーを止める
        for(ActionListener al : setBtC1.getActionListeners()){
            setBtC1.removeActionListener(al);
        }

        //setBtC2のアクションリスナーを止める
        for(ActionListener al : setBtC2.getActionListeners()){
            setBtC2.removeActionListener(al);
        }

        //setBtC3のアクションリスナーを止める
        for(ActionListener al : setBtC3.getActionListeners()){
            setBtC3.removeActionListener(al);
        }

        //setBtU1のアクションリスナーを止める
        for(ActionListener al : setBtU1.getActionListeners()){
            setBtU1.removeActionListener(al);
        }

        //setBtU2のアクションリスナーを止める
        for(ActionListener al : setBtU2.getActionListeners()){
            setBtU2.removeActionListener(al);
        }

        //setBtU3のアクションリスナーを止める
        for(ActionListener al : setBtU3.getActionListeners()){
            setBtU3.removeActionListener(al);
        }
    }

    //セットするほうのボタンのアクションリスナーをはじめる
    private static void StartActionListener(){

        //setBtT1のアクションリスナーを始める
        for(ActionListener al : setBtT1.getActionListeners()){
            setBtT1.addActionListener(al);
        }

        //setBtT2のアクションリスナーを始める
        for(ActionListener al : setBtT2.getActionListeners()){
            setBtT2.addActionListener(al);
        }

        //setBtT3のアクションリスナーを始める
        for(ActionListener al : setBtT3.getActionListeners()){
            setBtT3.addActionListener(al);
        }

        //setBtC1のアクションリスナーを始める
        for(ActionListener al : setBtC1.getActionListeners()){
            setBtC1.addActionListener(al);
        }

        //setBtC2のアクションリスナーを始める
        for(ActionListener al : setBtC2.getActionListeners()){
            setBtC2.addActionListener(al);
        }

        //setBtC3のアクションリスナーを始める
        for(ActionListener al : setBtC3.getActionListeners()){
            setBtC3.addActionListener(al);
        }

        //setBtU1のアクションリスナーを始める
        for(ActionListener al : setBtU1.getActionListeners()){
            setBtU1.addActionListener(al);
        }

        //setBtU2のアクションリスナーを始める
        for(ActionListener al : setBtU2.getActionListeners()){
            setBtU2.addActionListener(al);
        }

        //setBtU3のアクションリスナーを始める
        for(ActionListener al : setBtU3.getActionListeners()){
            setBtU3.addActionListener(al);
        }

    }

    //外側のボタンを有効にする
    private static void TrueButton(){

        btL1.setEnabled(true);
        btL2.setEnabled(true);
        btL3.setEnabled(true);
        btR1.setEnabled(true);
        btR2.setEnabled(true);
        btR3.setEnabled(true);
        btU1.setEnabled(true);
        btU2.setEnabled(true);
        btU3.setEnabled(true);

    }

    //全てのボタンのIconを無くす
    public static void NullAllBtIcon(){

        btL1.setIcon(null);
        btL2.setIcon(null);
        btL3.setIcon(null);
        btR1.setIcon(null);
        btR2.setIcon(null);
        btR3 .setIcon(null);
        btU1.setIcon(null);
        btU2.setIcon(null);
        btU3 .setIcon(null);

        setBtT1.setIcon(null);
        setBtT2.setIcon(null);
        setBtT3.setIcon(null);
        setBtC1.setIcon(null);
        setBtC2.setIcon(null);
        setBtC3.setIcon(null);
        setBtU1.setIcon(null);
        setBtU2.setIcon(null);
        setBtU3.setIcon(null);

    }

    //左の1番目を押したとき
    public static void btL1_ActionPerformed(ActionEvent e) {
        //btL1にある画像が[0][0]（パンダの左上）だった時
        if (btL1.getIcon() == imgIcon[0][0]) {

            System.out.println("btL1でpPan1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[0][1]（パンダの上、真ん中）だった時
        }else if (btL1.getIcon() == imgIcon[0][1]) {

            System.out.println("btL1でpPan2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[0][1]);

                        ///押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[0][2]（パンダの右上）だった時
        }else if (btL1.getIcon() == imgIcon[0][2]) {

            System.out.println("btL1でpPan3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[0][3]（パンダの真ん中、左）だった時
        }else if (btL1.getIcon() == imgIcon[0][3]) {

            System.out.println("btL1でpPan4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[0][4]（パンダのド真ん中）だった時
        }else if (btL1.getIcon() == imgIcon[0][4]) {

            System.out.println("btL1でpPan5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[0][5]（パンダのド真ん中、右）だった時
        }else if (btL1.getIcon() == imgIcon[0][5]) {

            System.out.println("btL1でpPan6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[0][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[0][6]（パンダのド左下）だった時
        }else if (btL1.getIcon() == imgIcon[0][6]) {

            System.out.println("btL1でpPan7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[0][7]（パンダの下、真ん中）だった時
        }else if (btL1.getIcon() == imgIcon[0][7]) {

            System.out.println("btL1でpPan8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[0][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[0][8]（パンダのド右下）だった時
        }else if (btL1.getIcon() == imgIcon[0][8]) {

            System.out.println("btL1でpPan9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btL2が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btL1にある画像が[1][0]（ゴリラの左上）だった時
        if (btL1.getIcon() == imgIcon[1][0]) {

            System.out.println("btL1でpGor1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[1][1]（ゴリラの上、真ん中）だった時
        }else if (btL1.getIcon() == imgIcon[1][1]) {

            System.out.println("btL1でpGor2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[1][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[1][2]（ゴリラの右上）だった時
        }else if (btL1.getIcon() == imgIcon[1][2]) {

            System.out.println("btL1でpGor3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[1][3]（ゴリラの真ん中、左）だった時
        }else if (btL1.getIcon() == imgIcon[1][3]) {

            System.out.println("btL1でpGor4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[1][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[1][4]（ゴリラのド真ん中）だった時
        }else if (btL1.getIcon() == imgIcon[1][4]) {

            System.out.println("btL1でpGor5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[1][5]（ゴリラのド真ん中、右）だった時
        }else if (btL1.getIcon() == imgIcon[1][5]) {

            System.out.println("btL1でpGor6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[1][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[1][6]（ゴリラのド左下）だった時
        }else if (btL1.getIcon() == imgIcon[1][6]) {

            System.out.println("btL1でpGor7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[1][7]（ゴリラの下、真ん中）だった時
        }else if (btL1.getIcon() == imgIcon[1][7]) {

            System.out.println("btL1でpGor8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[1][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL1にある画像が[1][8]（ゴリラのド右下）だった時
        }else if (btL1.getIcon() == imgIcon[1][8]) {

            System.out.println("btL1でpGor9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btL1が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

    }


    //左の2番目を押したとき
    public static void btL2_ActionPerformed(ActionEvent e) {
        //btL2にある画像が[0][0]（パンダの左上）だった時
        if (btL2.getIcon() == imgIcon[0][0]) {

            System.out.println("btL2でpPan1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[0][1]（パンダの上、真ん中）だった時
        }else if (btL2.getIcon() == imgIcon[0][1]) {

            System.out.println("btL2でpPan2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[0][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[0][2]（パンダの右上）だった時
        }else if (btL2.getIcon() == imgIcon[0][2]) {

            System.out.println("btL2でpPan3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[0][3]（パンダの真ん中、左）だった時
        }else if (btL2.getIcon() == imgIcon[0][3]) {

            System.out.println("btL2でpPan4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[0][4]（パンダのド真ん中）だった時
        }else if (btL2.getIcon() == imgIcon[0][4]) {

            System.out.println("btL2でpPan5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[0][5]（パンダのド真ん中、右）だった時
        }else if (btL2.getIcon() == imgIcon[0][5]) {

            System.out.println("btL2でpPan6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[0][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[0][6]（パンダのド左下）だった時
        }else if (btL2.getIcon() == imgIcon[0][6]) {

            System.out.println("btL2でpPan7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[0][7]（パンダの下、真ん中）だった時
        }else if (btL2.getIcon() == imgIcon[0][7]) {

            System.out.println("btL2でpPan8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[0][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[0][8]（パンダのド右下）だった時
        }else if (btL2.getIcon() == imgIcon[0][8]) {

            System.out.println("btL2でpPan9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btL2が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btL2にある画像が[1][0]（ゴリラの左上）だった時
        if (btL2.getIcon() == imgIcon[1][0]) {

            System.out.println("btL2でpGor1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[1][1]（ゴリラの上、真ん中）だった時
        }else if (btL2.getIcon() == imgIcon[1][1]) {

            System.out.println("btL2でpGor2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[1][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[1][2]（ゴリラの右上）だった時
        }else if (btL2.getIcon() == imgIcon[1][2]) {

            System.out.println("btL2でpGor3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[1][3]（ゴリラの真ん中、左）だった時
        }else if (btL2.getIcon() == imgIcon[1][3]) {

            System.out.println("btL2でpGor4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[1][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[1][4]（ゴリラのド真ん中）だった時
        }else if (btL2.getIcon() == imgIcon[1][4]) {

            System.out.println("btL2でpGor5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[1][5]（ゴリラのド真ん中、右）だった時
        }else if (btL2.getIcon() == imgIcon[1][5]) {

            System.out.println("btL2でpGor6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[1][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[1][6]（ゴリラのド左下）だった時
        }else if (btL2.getIcon() == imgIcon[1][6]) {

            System.out.println("btL2でpGor7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[1][7]（ゴリラの下、真ん中）だった時
        }else if (btL2.getIcon() == imgIcon[1][7]) {

            System.out.println("btL2でpGor8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[1][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[1][8]（ゴリラのド右下）だった時
        }else if (btL2.getIcon() == imgIcon[1][8]) {

            System.out.println("btL2でpGor9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btL2が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

    }

    //左の3番目を押したとき
    public static void btL3_ActionPerformed(ActionEvent e) {
        //btL3にある画像が[0][0]（パンダの左上）だった時
        if (btL3.getIcon() == imgIcon[0][0]) {

            System.out.println("btL3でpPan1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[0][1]（パンダの上、真ん中）だった時
        }else if (btL3.getIcon() == imgIcon[0][1]) {

            System.out.println("btL3でpPan2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[0][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[0][2]（パンダの右上）だった時
        }else if (btL3.getIcon() == imgIcon[0][2]) {

            System.out.println("btL3でpPan3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL2にある画像が[0][3]（パンダの真ん中、左）だった時
        }else if (btL3.getIcon() == imgIcon[0][3]) {

            System.out.println("btL3でpPan4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[0][4]（パンダのド真ん中）だった時
        }else if (btL3.getIcon() == imgIcon[0][4]) {

            System.out.println("btL3でpPan5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[0][5]（パンダのド真ん中、右）だった時
        }else if (btL3.getIcon() == imgIcon[0][5]) {

            System.out.println("btL3でpPan6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[0][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[0][6]（パンダのド左下）だった時
        }else if (btL3.getIcon() == imgIcon[0][6]) {

            System.out.println("btL3でpPan7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[0][7]（パンダの下、真ん中）だった時
        }else if (btL3.getIcon() == imgIcon[0][7]) {

            System.out.println("btL3でpPan8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[0][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[0][8]（パンダのド右下）だった時
        }else if (btL3.getIcon() == imgIcon[0][8]) {

            System.out.println("btL3でpPan9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btL3が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btL3にある画像が[1][0]（ゴリラの左上）だった時
        if (btL3.getIcon() == imgIcon[1][0]) {

            System.out.println("btL3でpGor1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[1][1]（ゴリラの上、真ん中）だった時
        }else if (btL3.getIcon() == imgIcon[1][1]) {

            System.out.println("btL3でpGor2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[1][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[1][2]（ゴリラの右上）だった時
        }else if (btL3.getIcon() == imgIcon[1][2]) {

            System.out.println("btL3でpGor3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[1][3]（ゴリラの真ん中、左）だった時
        }else if (btL3.getIcon() == imgIcon[1][3]) {

            System.out.println("btL3でpGor4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[1][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[1][4]（ゴリラのド真ん中）だった時
        }else if (btL3.getIcon() == imgIcon[1][4]) {

            System.out.println("btL3でpGor5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[1][5]（ゴリラのド真ん中、右）だった時
        }else if (btL3.getIcon() == imgIcon[1][5]) {

            System.out.println("btL3でpGor6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[1][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[1][6]（ゴリラのド左下）だった時
        }else if (btL3.getIcon() == imgIcon[1][6]) {

            System.out.println("btL3でpGor7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[1][7]（ゴリラの下、真ん中）だった時
        }else if (btL3.getIcon() == imgIcon[1][7]) {

            System.out.println("btL3でpGor8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[1][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btL3にある画像が[1][8]（ゴリラのド右下）だった時
        }else if (btL3.getIcon() == imgIcon[1][8]) {

            System.out.println("btL3でpGor9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btL3が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

    }


    //右の1番目を押したとき
    public static void btR1_ActionPerformed(ActionEvent e) {
        //btR1にある画像が[0][0]（パンダの左上）だった時
        if (btR1.getIcon() == imgIcon[0][0]) {

            System.out.println("btR1でpPan1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[0][1]（パンダの上、真ん中）だった時
        }else if (btR1.getIcon() == imgIcon[0][1]) {

            System.out.println("btR1でpPan2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[0][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[0][2]（パンダの右上）だった時
        }else if (btR1.getIcon() == imgIcon[0][2]) {

            System.out.println("btR1でpPan3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[0][3]（パンダの真ん中、左）だった時
        }else if (btR1.getIcon() == imgIcon[0][3]) {

            System.out.println("btR1でpPan4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[0][4]（パンダのド真ん中）だった時
        }else if (btR1.getIcon() == imgIcon[0][4]) {

            System.out.println("btR1でpPan5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[0][5]（パンダのド真ん中、右）だった時
        }else if (btR1.getIcon() == imgIcon[0][5]) {

            System.out.println("btR1でpPan6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[0][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[0][6]（パンダのド左下）だった時
        }else if (btR1.getIcon() == imgIcon[0][6]) {

            System.out.println("btR1でpPan7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[0][7]（パンダの下、真ん中）だった時
        }else if (btR1.getIcon() == imgIcon[0][7]) {

            System.out.println("btR1でpPan8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[0][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[0][8]（パンダのド右下）だった時
        }else if (btR1.getIcon() == imgIcon[0][8]) {

            System.out.println("btR1でpPan9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btR1が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btR1にある画像が[1][0]（ゴリラの左上）だった時
        if (btR1.getIcon() == imgIcon[1][0]) {

            System.out.println("btR1でpGor1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[1][1]（ゴリラの上、真ん中）だった時
        }else if (btR1.getIcon() == imgIcon[1][1]) {

            System.out.println("btR1でpGor2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[1][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[1][2]（ゴリラの右上）だった時
        }else if (btR1.getIcon() == imgIcon[1][2]) {

            System.out.println("btR1でpGor3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[1][3]（ゴリラの真ん中、左）だった時
        }else if (btR1.getIcon() == imgIcon[1][3]) {

            System.out.println("btR1でpGor4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[1][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[1][4]（ゴリラのド真ん中）だった時
        }else if (btR1.getIcon() == imgIcon[1][4]) {

            System.out.println("btR1でpGor5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[1][5]（ゴリラのド真ん中、右）だった時
        }else if (btR1.getIcon() == imgIcon[1][5]) {

            System.out.println("btR1でpGor6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[1][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[1][6]（ゴリラのド左下）だった時
        }else if (btR1.getIcon() == imgIcon[1][6]) {

            System.out.println("btR1でpGor7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[1][7]（ゴリラの下、真ん中）だった時
        }else if (btR1.getIcon() == imgIcon[1][7]) {

            System.out.println("btR1でpGor8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[1][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR1にある画像が[1][8]（ゴリラのド右下）だった時
        }else if (btR1.getIcon() == imgIcon[1][8]) {

            System.out.println("btR1でpGor9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btR1が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

    }

    //右の2番目を押したとき
    public static void btR2_ActionPerformed(ActionEvent e) {
        //btR2にある画像が[0][0]（パンダの左上）だった時
        if (btR2.getIcon() == imgIcon[0][0]) {

            System.out.println("btR2でpPan1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[0][1]（パンダの上、真ん中）だった時
        }else if (btR2.getIcon() == imgIcon[0][1]) {

            System.out.println("btR2でpPan2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[0][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[0][2]（パンダの右上）だった時
        }else if (btR2.getIcon() == imgIcon[0][2]) {

            System.out.println("btR2でpPan3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[0][3]（パンダの真ん中、左）だった時
        }else if (btR2.getIcon() == imgIcon[0][3]) {

            System.out.println("btR2でpPan4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[0][4]（パンダのド真ん中）だった時
        }else if (btR2.getIcon() == imgIcon[0][4]) {

            System.out.println("btR2でpPan5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[0][5]（パンダのド真ん中、右）だった時
        }else if (btR2.getIcon() == imgIcon[0][5]) {

            System.out.println("btR2でpPan6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[0][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[0][6]（パンダのド左下）だった時
        }else if (btR2.getIcon() == imgIcon[0][6]) {

            System.out.println("btR2でpPan7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[0][7]（パンダの下、真ん中）だった時
        }else if (btR2.getIcon() == imgIcon[0][7]) {

            System.out.println("btR2でpPan8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[0][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[0][8]（パンダのド右下）だった時
        }else if (btR2.getIcon() == imgIcon[0][8]) {

            System.out.println("btR2でpPan9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btR2が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btR2にある画像が[1][0]（ゴリラの左上）だった時
        if (btR2.getIcon() == imgIcon[1][0]) {

            System.out.println("btR2でpGor1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[1][1]（ゴリラの上、真ん中）だった時
        }else if (btR2.getIcon() == imgIcon[1][1]) {

            System.out.println("btR2でpGor2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[1][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[1][2]（ゴリラの右上）だった時
        }else if (btR2.getIcon() == imgIcon[1][2]) {

            System.out.println("btR2でpGor3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //bt2にある画像が[1][3]（ゴリラの真ん中、左）だった時
        }else if (btR2.getIcon() == imgIcon[1][3]) {

            System.out.println("btR2でpGor4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[1][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[1][4]（ゴリラのド真ん中）だった時
        }else if (btR2.getIcon() == imgIcon[1][4]) {

            System.out.println("btR2でpGor5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[1][5]（ゴリラのド真ん中、右）だった時
        }else if (btR2.getIcon() == imgIcon[1][5]) {

            System.out.println("btR2でpGor6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[1][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[1][6]（ゴリラのド左下）だった時
        }else if (btR2.getIcon() == imgIcon[1][6]) {

            System.out.println("btR2でpGor7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[1][7]（ゴリラの下、真ん中）だった時
        }else if (btR2.getIcon() == imgIcon[1][7]) {

            System.out.println("btR2でpGor8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[1][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR2にある画像が[1][8]（ゴリラのド右下）だった時
        }else if (btR2.getIcon() == imgIcon[1][8]) {

            System.out.println("btR2でpGor9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btR2が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

    }

    //右の3番目を押したとき
    public static void btR3_ActionPerformed(ActionEvent e) {
        //btR3にある画像が[0][0]（パンダの左上）だった時
        if (btR3.getIcon() == imgIcon[0][0]) {

            System.out.println("btR3でpPan1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[0][1]（パンダの上、真ん中）だった時
        }else if (btR3.getIcon() == imgIcon[0][1]) {

            System.out.println("btR3でpPan2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[0][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[0][2]（パンダの右上）だった時
        }else if (btR3.getIcon() == imgIcon[0][2]) {

            System.out.println("btR3でpPan3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[0][3]（パンダの真ん中、左）だった時
        }else if (btR3.getIcon() == imgIcon[0][3]) {

            System.out.println("btR3でpPan4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[0][4]（パンダのド真ん中）だった時
        }else if (btR3.getIcon() == imgIcon[0][4]) {

            System.out.println("btR3でpPan5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[0][5]（パンダのド真ん中、右）だった時
        }else if (btR3.getIcon() == imgIcon[0][5]) {

            System.out.println("btR3でpPan6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[0][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[0][6]（パンダのド左下）だった時
        }else if (btR3.getIcon() == imgIcon[0][6]) {

            System.out.println("btR3でpPan7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[0][7]（パンダの下、真ん中）だった時
        }else if (btR3.getIcon() == imgIcon[0][7]) {

            System.out.println("btR3でpPan8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[0][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[0][8]（パンダのド右下）だった時
        }else if (btR3.getIcon() == imgIcon[0][8]) {

            System.out.println("btR3でpPan9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btR3が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btR3にある画像が[1][0]（ゴリラの左上）だった時
        if (btR3.getIcon() == imgIcon[1][0]) {

            System.out.println("btR3でpGor1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[1][1]（ゴリラの上、真ん中）だった時
        }else if (btR3.getIcon() == imgIcon[1][1]) {

            System.out.println("btR3でpGor2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[1][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[1][2]（ゴリラの右上）だった時
        }else if (btR3.getIcon() == imgIcon[1][2]) {

            System.out.println("btR3でpGor3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //bt3にある画像が[1][3]（ゴリラの真ん中、左）だった時
        }else if (btR3.getIcon() == imgIcon[1][3]) {

            System.out.println("btR3でpGor4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[1][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[1][4]（ゴリラのド真ん中）だった時
        }else if (btR3.getIcon() == imgIcon[1][4]) {

            System.out.println("btR3でpGor5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[1][5]（ゴリラのド真ん中、右）だった時
        }else if (btR3.getIcon() == imgIcon[1][5]) {

            System.out.println("btR3でpGor6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[1][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[1][6]（ゴリラのド左下）だった時
        }else if (btR3.getIcon() == imgIcon[1][6]) {

            System.out.println("btR3でpGor7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[1][7]（ゴリラの下、真ん中）だった時
        }else if (btR3.getIcon() == imgIcon[1][7]) {

            System.out.println("btR3でpGor8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[1][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btR3にある画像が[1][8]（ゴリラのド右下）だった時
        }else if (btR3.getIcon() == imgIcon[1][8]) {

            System.out.println("btR3でpGor9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(false);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btR3が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

    }

    //下の1番目を押したとき
    public static void btU1_ActionPerformed(ActionEvent e) {
        //btU1にある画像が[0][0]（パンダの左上）だった時
        if (btU1.getIcon() == imgIcon[0][0]) {

            System.out.println("btU1でpPan1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[0][1]（パンダの上、真ん中）だった時
        }else if (btU1.getIcon() == imgIcon[0][1]) {

            System.out.println("btU1でpPan2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[0][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[0][2]（パンダの右上）だった時
        }else if (btU1.getIcon() == imgIcon[0][2]) {

            System.out.println("btU1でpPan3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[0][3]（パンダの真ん中、左）だった時
        }else if (btU1.getIcon() == imgIcon[0][3]) {

            System.out.println("btU1でpPan4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[0][4]（パンダのド真ん中）だった時
        }else if (btU1.getIcon() == imgIcon[0][4]) {

            System.out.println("btU1でpPan5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[0][5]（パンダのド真ん中、右）だった時
        }else if (btU1.getIcon() == imgIcon[0][5]) {

            System.out.println("btU1でpPan6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[0][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[0][6]（パンダのド左下）だった時
        }else if (btU1.getIcon() == imgIcon[0][6]) {

            System.out.println("btU1でpPan7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[0][7]（パンダの下、真ん中）だった時
        }else if (btU1.getIcon() == imgIcon[0][7]) {

            System.out.println("btU1でpPan8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[0][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[0][8]（パンダのド右下）だった時
        }else if (btU1.getIcon() == imgIcon[0][8]) {

            System.out.println("btU1でpPan9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btU1が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btU1にある画像が[1][0]（ゴリラの左上）だった時
        if (btU1.getIcon() == imgIcon[1][0]) {

            System.out.println("btU1でpGor1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[1][1]（ゴリラの上、真ん中）だった時
        }else if (btU1.getIcon() == imgIcon[1][1]) {

            System.out.println("btU1でpGor2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[1][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[1][2]（ゴリラの右上）だった時
        }else if (btU1.getIcon() == imgIcon[1][2]) {

            System.out.println("btU1でpGor3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[1][3]（ゴリラの真ん中、左）だった時
        }else if (btU1.getIcon() == imgIcon[1][3]) {

            System.out.println("btU1でpGor4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[1][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[1][4]（ゴリラのド真ん中）だった時
        }else if (btU1.getIcon() == imgIcon[1][4]) {

            System.out.println("btU1でpGor5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[1][5]（ゴリラのド真ん中、右）だった時
        }else if (btU1.getIcon() == imgIcon[1][5]) {

            System.out.println("btU1でpGor6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[1][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[1][6]（ゴリラのド左下）だった時
        }else if (btU1.getIcon() == imgIcon[1][6]) {

            System.out.println("btU1でpGor7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[1][7]（ゴリラの下、真ん中）だった時
        }else if (btU1.getIcon() == imgIcon[1][7]) {

            System.out.println("btU1でpGor8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[1][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU1にある画像が[1][8]（ゴリラのド右下）だった時
        }else if (btU1.getIcon() == imgIcon[1][8]) {

            System.out.println("btU1でpGor9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(false);
                        btU2.setEnabled(true);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btU1が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

    }


    //下の2番目を押したとき
    public static void btU2_ActionPerformed(ActionEvent e) {
        //btU2にある画像が[0][0]（パンダの左上）だった時
        if (btU2.getIcon() == imgIcon[0][0]) {

            System.out.println("btU2でpPan1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[0][1]（パンダの上、真ん中）だった時
        }else if (btU2.getIcon() == imgIcon[0][1]) {

            System.out.println("btU2でpPan2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[0][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[0][2]（パンダの右上）だった時
        }else if (btU2.getIcon() == imgIcon[0][2]) {

            System.out.println("btU2でpPan3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[0][3]（パンダの真ん中、左）だった時
        }else if (btU2.getIcon() == imgIcon[0][3]) {

            System.out.println("btU2でpPan4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[0][4]（パンダのド真ん中）だった時
        }else if (btU2.getIcon() == imgIcon[0][4]) {

            System.out.println("btU2でpPan5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[0][5]（パンダのド真ん中、右）だった時
        }else if (btU2.getIcon() == imgIcon[0][5]) {

            System.out.println("btU2でpPan6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[0][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[0][6]（パンダのド左下）だった時
        }else if (btU2.getIcon() == imgIcon[0][6]) {

            System.out.println("btU2でpPan7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[0][7]（パンダの下、真ん中）だった時
        }else if (btU2.getIcon() == imgIcon[0][7]) {

            System.out.println("btU2でpPan8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[0][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[0][8]（パンダのド右下）だった時
        }else if (btU2.getIcon() == imgIcon[0][8]) {

            System.out.println("btU2でpPan9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btU2が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btU2にある画像が[1][0]（ゴリラの左上）だった時
        if (btU2.getIcon() == imgIcon[1][0]) {

            System.out.println("btU2でpGor1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[1][1]（ゴリラの上、真ん中）だった時
        }else if (btU2.getIcon() == imgIcon[1][1]) {

            System.out.println("btU2でpGor2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[1][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[1][2]（ゴリラの右上）だった時
        }else if (btU2.getIcon() == imgIcon[1][2]) {

            System.out.println("btU2でpGor3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[1][3]（ゴリラの真ん中、左）だった時
        }else if (btU2.getIcon() == imgIcon[1][3]) {

            System.out.println("btU2でpGor4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[1][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[1][4]（ゴリラのド真ん中）だった時
        }else if (btU2.getIcon() == imgIcon[1][4]) {

            System.out.println("btU2でpGor5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[1][5]（ゴリラのド真ん中、右）だった時
        }else if (btU2.getIcon() == imgIcon[1][5]) {

            System.out.println("btU2でpGor6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[1][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[1][6]（ゴリラのド左下）だった時
        }else if (btU2.getIcon() == imgIcon[1][6]) {

            System.out.println("btU2でpGor7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[1][7]（ゴリラの下、真ん中）だった時
        }else if (btU2.getIcon() == imgIcon[1][7]) {

            System.out.println("btU2でpGor8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[1][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU2にある画像が[1][8]（ゴリラのド右下）だった時
        }else if (btU2.getIcon() == imgIcon[1][8]) {

            System.out.println("btU2でpGor9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(false);
                        btU3.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btU2が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

    }


    //下の3番目を押したとき
    public static void btU3_ActionPerformed(ActionEvent e) {
        //btU3にある画像が[0][0]（パンダの左上）だった時
        if (btU3.getIcon() == imgIcon[0][0]) {

            System.out.println("btU3でpPan1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[0][1]（パンダの上、真ん中）だった時
        }else if (btU3.getIcon() == imgIcon[0][1]) {

            System.out.println("btU3でpPan2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[0][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[0][2]（パンダの右上）だった時
        }else if (btU3.getIcon() == imgIcon[0][2]) {

            System.out.println("btU3でpPan3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[0][3]（パンダの真ん中、左）だった時
        }else if (btU3.getIcon() == imgIcon[0][3]) {

            System.out.println("btU3でpPan4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[0][4]（パンダのド真ん中）だった時
        }else if (btU3.getIcon() == imgIcon[0][4]) {

            System.out.println("btU3でpPan5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[0][5]（パンダのド真ん中、右）だった時
        }else if (btU3.getIcon() == imgIcon[0][5]) {

            System.out.println("btU3でpPan6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[0][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[0][6]（パンダのド左下）だった時
        }else if (btU3.getIcon() == imgIcon[0][6]) {

            System.out.println("btU3でpPan7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[0][7]（パンダの下、真ん中）だった時
        }else if (btU3.getIcon() == imgIcon[0][7]) {

            System.out.println("btU3でpPan8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[0][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[0][8]（パンダのド右下）だった時
        }else if (btU3.getIcon() == imgIcon[0][8]) {

            System.out.println("btU3でpPan9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btU3が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btU3にある画像が[1][0]（ゴリラの左上）だった時
        if (btU3.getIcon() == imgIcon[1][0]) {

            System.out.println("btU3でpGor1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtT1.getIcon() == null){

                //SetBtT1のアクションリスナーを呼び出す
                setBtT1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtT1に画像をセット
                        setBtT1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);


                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[1][1]（ゴリラの上、真ん中）だった時
        }else if (btU3.getIcon() == imgIcon[1][1]) {

            System.out.println("btU3でpGor2を押された");

            //もしSetBtT2に画像が入っていなかったら
            if(setBtT2.getIcon() == null){

                //SetBtT2のアクションリスナーを呼び出す
                setBtT2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtT2に画像をセット
                        setBtT2.setIcon(imgIcon[1][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[1][2]（ゴリラの右上）だった時
        }else if (btU3.getIcon() == imgIcon[1][2]) {

            System.out.println("btU3でpGor3を押された");

            //もしSetBtT3に画像が入っていなかったら
            if(setBtT3.getIcon() == null){

                //SetBtT3のアクションリスナーを呼び出す
                setBtT3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtT3に画像をセット
                        setBtT3.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtT3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[1][3]（ゴリラの真ん中、左）だった時
        }else if (btU3.getIcon() == imgIcon[1][3]) {

            System.out.println("btU3でpGor4を押された");

            //もしsetBtC1に画像が入っていなかったら
            if(setBtC1.getIcon() == null){

                //setBtC1のアクションリスナーを呼び出す
                setBtC1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC1に画像をセット
                        setBtC1.setIcon(imgIcon[1][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[1][4]（ゴリラのド真ん中）だった時
        }else if (btU3.getIcon() == imgIcon[1][4]) {

            System.out.println("btU3でpGor5を押された");

            //もしsetBtC2に画像が入っていなかったら
            if(setBtC2.getIcon() == null){

                //setBtC2のアクションリスナーを呼び出す
                setBtC2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC2に画像をセット
                        setBtC2.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[1][5]（ゴリラのド真ん中、右）だった時
        }else if (btU3.getIcon() == imgIcon[1][5]) {

            System.out.println("btU3でpGor6を押された");

            //もしsetBtC3に画像が入っていなかったら
            if(setBtC3.getIcon() == null){

                //setBtC3のアクションリスナーを呼び出す
                setBtC3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtC3に画像をセット
                        setBtC3.setIcon(imgIcon[1][5]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[1][6]（ゴリラのド左下）だった時
        }else if (btU3.getIcon() == imgIcon[1][6]) {

            System.out.println("btU3でpGor7を押された");

            //もしsetBtU1に画像が入っていなかったら
            if(setBtU1.getIcon() == null){

                //setBtU1のアクションリスナーを呼び出す
                setBtU1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU1に画像をセット
                        setBtU1.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU1が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[1][7]（ゴリラの下、真ん中）だった時
        }else if (btU3.getIcon() == imgIcon[1][7]) {

            System.out.println("btU3でpGor8を押された");

            //もしsetBtU2に画像が入っていなかったら
            if(setBtU2.getIcon() == null){

                //setBtU2のアクションリスナーを呼び出す
                setBtU2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU2に画像をセット
                        setBtU2.setIcon(imgIcon[1][7]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtU2が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }

            //btU3にある画像が[1][8]（ゴリラのド右下）だった時
        }else if (btU3.getIcon() == imgIcon[1][8]) {

            System.out.println("btU3でpGor9を押された");

            //もしsetBtU3に画像が入っていなかったら
            if(setBtU3.getIcon() == null){

                //setBtU3のアクションリスナーを呼び出す
                setBtU3.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtU3に画像をセット
                        setBtU3.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btL3.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);
                        btR3.setEnabled(true);
                        btU1.setEnabled(true);
                        btU2.setEnabled(true);
                        btU3.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtC3が押された時 ---");
                        System.out.println(setBtT1.getIcon());
                        System.out.println(setBtT2.getIcon());
                        System.out.println(setBtT3.getIcon());
                        System.out.println(setBtC1.getIcon());
                        System.out.println(setBtC2.getIcon());
                        System.out.println(setBtC3.getIcon());
                        System.out.println(setBtU1.getIcon());
                        System.out.println(setBtU2.getIcon());
                        System.out.println(setBtU3.getIcon());

                        //全部そろった
                        if(setBtT1.getIcon() != null && setBtT2.getIcon() != null
                                && setBtT3.getIcon() != null
                                && setBtC1.getIcon() != null && setBtC2.getIcon() != null
                                && setBtC3.getIcon() != null
                                && setBtU1.getIcon() != null && setBtU2.getIcon() != null
                                && setBtU3.getIcon() != null) {

                            System.out.println("全部そろった");

                            //ボタンのアクションリスナーを止める
                            StopActionListener();

                            //スコアについて
                            score ++;//スコア＋１
                            Result.setText(String.valueOf(score));//テキスト表示
                            System.out.println(score);

                            //ボタンの有効化
                            TrueButton();

                            //全てのボタンのIconを消す
                            NullAllBtIcon();

                            //パズルを変える
                            ChangePuzzle();

                        }

                    }

                });

            }
        }

        System.out.println("--- btU3が押された時 ---");
        System.out.println(setBtT1.getIcon());
        System.out.println(setBtT2.getIcon());
        System.out.println(setBtT3.getIcon());
        System.out.println(setBtC1.getIcon());
        System.out.println(setBtC2.getIcon());
        System.out.println(setBtC3.getIcon());
        System.out.println(setBtU1.getIcon());
        System.out.println(setBtU2.getIcon());
        System.out.println(setBtU3.getIcon());

    }


    //imgIconの配列に画像を入れる
    public static void SetImageIcon(){

        //パンダ
        imgIcon[0][0] = ChangeImageSize(icon1);
        imgIcon[0][1] = ChangeImageSize(icon2);
        imgIcon[0][2] = ChangeImageSize(icon3);
        imgIcon[0][3] = ChangeImageSize(icon4);
        imgIcon[0][4] = ChangeImageSize(icon5);
        imgIcon[0][5] = ChangeImageSize(icon6);
        imgIcon[0][6] = ChangeImageSize(icon7);
        imgIcon[0][7] = ChangeImageSize(icon8);
        imgIcon[0][8] = ChangeImageSize(icon9);

        //ゴリラ
        imgIcon[1][0] = ChangeImageSize(icon21);
        imgIcon[1][1] = ChangeImageSize(icon22);
        imgIcon[1][2] = ChangeImageSize(icon23);
        imgIcon[1][3] = ChangeImageSize(icon24);
        imgIcon[1][4] = ChangeImageSize(icon25);
        imgIcon[1][5] = ChangeImageSize(icon26);
        imgIcon[1][6] = ChangeImageSize(icon27);
        imgIcon[1][7] = ChangeImageSize(icon28);
        imgIcon[1][8] = ChangeImageSize(icon29);

    }

    //画像のサイズを変更
    public static ImageIcon ChangeImageSize(ImageIcon imageIcon){

        getImg = imageIcon.getImage();
        newImg = getImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;

    }
}