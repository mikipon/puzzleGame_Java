import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class EasyFrame {

    //画面
    private static JFrame efm;
    private static JLabel lb;
    private static Image getImg;
    private static Image newImg;

    //パズル（タイムアタックだから何個もパズル画像を表示しないといけない？）
    //パズルのボタン
    private static JButton btL1 = new JButton();//外の左上
    private static JButton btL2 = new JButton();//外の左下
    private static JButton btR1 = new JButton();//外の右上
    private static JButton btR2 = new JButton();//外の右下

    //パズルを置く場所のボタン
    private static JButton setBtL1 = new JButton();//内の左上
    private static JButton setBtL2 = new JButton();//内の左下
    private static JButton setBtR1 = new JButton();//内の右上
    private static JButton setBtR2 = new JButton();//内の右下

    //ハンバーガー
    private static ImageIcon pHum1 = new ImageIcon("puzzleImg\\pHam_1.jpg");//写真の左上
    private static ImageIcon pHum2 = new ImageIcon("puzzleImg\\pHam_2.jpg");//写真の右上
    private static ImageIcon pHum3 = new ImageIcon("puzzleImg\\pHam_3.jpg");//写真の左下
    private static ImageIcon pHum4 = new ImageIcon("puzzleImg\\pHam_4.jpg");//写真の右下

    //ハリネズミ
    private static ImageIcon pHari1 = new ImageIcon("puzzleImg\\pHari_1.jpg");//写真の左上
    private static ImageIcon pHari2 = new ImageIcon("puzzleImg\\pHari_2.jpg");//写真の右上
    private static ImageIcon pHari3 = new ImageIcon("puzzleImg\\pHari_3.jpg");//写真の左下
    private static ImageIcon pHari4 = new ImageIcon("puzzleImg\\pHari_4.jpg");//写真の右下

    //Iconの配列
    private static ImageIcon[][] imgIcon = new ImageIcon[2][4];

    //ランダム用
    private static ArrayList<Integer> listY = new ArrayList<Integer>();

    //全部パズルをあてはめたら
    private static int check;//全部はめた？
    private static int score = 0;//何個解いた？
    private static JLabel Result = new JLabel();
    private static boolean bool = true;

    //時間
    private static Timer timer;
    private static JLabel timerlb;
    private static int sec = 30;//制限時間

    //色
    private static Color cr1 = new Color(0,0,0,200);
    private static Color backColor = new Color(196,205,207);

    public EasyFrame() {

        //画面の作成メソッドへGo
        CreateGameFrame();
        //画像の設定
        SetImageIcon();

        //Bottonを作成
        //左上
        PuzzleBase(setBtL1, 188, 227);
        efm.add(setBtL1);

        //左下
        PuzzleBase(setBtR1, 288, 227);
        efm.add(setBtR1);

        //右上
        PuzzleBase(setBtL2, 188, 327);
        efm.add(setBtL2);

        //左下
        PuzzleBase(setBtR2, 288, 327);
        efm.add(setBtR2);

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
                btR1.setEnabled(false);
                btR2.setEnabled(false);
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
                btR1.setEnabled(false);
                btR2.setEnabled(false);
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
                btR2.setEnabled(false);

            }

        });

        //外側の右下のボタンが押された時に呼び出される
        btR2.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.out.print("btR2：");

                //押された時の処理のメソッドに飛ぶ
                btR2_ActionPerformed(evt);

                //アクションリスナーのボタン以外無効化
                btL1.setEnabled(false);
                btL2.setEnabled(false);
                btR1.setEnabled(false);

            }

        });

    }

    //パズルをランダムに変えて、外側のそれぞれのボタンにIconに配置する
    public static void ChangePuzzle(){

        //リストの中身をクリア
        listY.clear();

        //配列のXをランダムにする
        ArrayList<Integer> listX = new ArrayList<Integer>();

        // リストに0から4を代入
        for(int i=0; i<2; i++) listX.add(i);

        //Listの中身をシャッフル
        Collections.shuffle(listX);

        //もしハンバーガだったら(０ == pHumたち)
        if(listX.get(0) == 0) {

            //Yをランダムにするメソッドを呼ぶ
            RandomY();

            //外側のパズル配置
            //左上
            btL1.setIcon(imgIcon[0][listY.get(0)]);
            PuzzleBase(btL1, 50, 120);
            efm.add(btL1);

            //左下
            btL2.setIcon(imgIcon[0][listY.get(1)]);
            PuzzleBase(btL2, 50, 420);
            efm.add(btL2);

            //右上
            btR1.setIcon(imgIcon[0][listY.get(2)]);
            PuzzleBase(btR1, 420, 120);
            efm.add(btR1);

            //右下
            btR2.setIcon(imgIcon[0][listY.get(3)]);
            PuzzleBase(btR2, 420, 420);
            efm.add(btR2);

        } else {//ハリネズミだったら(1 == pHariたち)

            //Yをランダムにするメソッドを呼ぶ
            RandomY();

            //パズル配置
            //左上
            btL1.setIcon(imgIcon[1][listY.get(0)]);
            PuzzleBase(btL1, 50, 120);
            efm.add(btL1);

            //左下
            btL2.setIcon(imgIcon[1][listY.get(1)]);
            PuzzleBase(btL2, 50, 420);
            efm.add(btL2);

            //右上
            btR1.setIcon(imgIcon[1][listY.get(2)]);
            PuzzleBase(btR1, 420, 120);
            efm.add(btR1);

            //右下
            btR2.setIcon(imgIcon[1][listY.get(3)]);
            PuzzleBase(btR2, 420, 420);
            efm.add(btR2);

        }

    }

    //ゲーム画面
    public static void CreateGameFrame(){

        //Easy画面の表示
        efm = new JFrame("Easy");
        efm.setVisible(true);
        efm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        efm.setSize(600,700);
        efm.setLocation(500,100);
        efm.getContentPane().setBackground(backColor);
        efm.setLayout(null);

        //表示文字
        lb = new JLabel();
        lb.setBounds(187,227,200,200);
        lb.setBorder(new LineBorder(Color.BLACK, 2, true));
        efm.add(lb);

        //スコアの表示
        Result.setBounds(200,400,100,100);
        Result.setFont(new Font("Sans-serif",Font.PLAIN,50));
        efm.add(Result);

        Timer();
    }

    //時間
    public static void Timer(){
        timerlb = new JLabel();
        timerlb.setBounds(110,0,400,100);
        timerlb.setFont(new Font("Sans-serif",Font.PLAIN,50));
        efm.add(timerlb);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timerlb.setText("TimeLimit " + sec + "sec");
                if (sec <= 0){
                    timer.stop();

                    efm.getContentPane().removeAll();
                    setBtL1 = new JButton();
                    setBtL2 = new JButton();
                    setBtR1 = new JButton();
                    setBtR2 = new JButton();

                    btL1 = new JButton();
                    btL2 = new JButton();
                    btR1 = new JButton();
                    btR2 = new JButton();
                    Result = new JLabel();
                    sec = 30;

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
        efm.getContentPane().removeAll();
        efm.repaint();

        //パネル貼る
        JPanel repn = new JPanel();
        repn.setBounds(0,0,600,700);
        repn.setBackground(cr1);
        efm.add(repn);
        repn.setLayout(null);

        JLabel relb = new JLabel("Result");
        relb.setBounds(215,0,350,100);
        relb.setFont(new Font("Sans-serif",Font.PLAIN,50));
        relb.setForeground(Color.white);
        repn.add(relb);

        //結果
        JLabel sco = new JLabel();
        sco.setText(String.valueOf(score));
        sco.setBounds(245,200,350,100);
        sco.setFont(new Font("Sans-serif",Font.PLAIN,100));
        sco.setForeground(Color.white);
        repn.add(sco);

        //メイン戻るボタン
        JButton topbt = new JButton("Back to Title");
        topbt.setBounds(140,400,280,60);
        topbt.setFont(new Font("Sans-serif",Font.PLAIN,30));
        repn.add(topbt);
        topbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("Back to Title")) {
                    efm.dispose();
                    new  MainFrame();
                }
            }
        });
    }

    public static ArrayList<Integer> RandomY(){

        //配列のYをランダムにする
        // リストに0から4を代入
        for(int i=0; i<4; i++){

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

        //真ん中の左上
        if(setBtL1.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtL1.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);

        }else if(setBtL1.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtL1.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }

        //真ん中の左下
        if(setBtL2.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtL2.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);

        }else if(setBtL2.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtL2.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }

        //真ん中の右上
        if(setBtR1.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtR1.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);

        }else if(setBtR1.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtR1.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }

        //真ん中の右下
        if(setBtR2.getIcon() == btL1.getIcon()){

            btL1.setEnabled(false);

        }else if(setBtR2.getIcon() == btL2.getIcon()){

            btL2.setEnabled(false);

        }else if(setBtR2.getIcon() == btR1.getIcon()){

            btR1.setEnabled(false);

        }else if(setBtR2.getIcon() == btR2.getIcon()){

            btR2.setEnabled(false);

        }

    }

    //セットするほうのボタンのアクションリスナーをやめる
    private static void StopActionListener(){

        //setBtL1のアクションリスナーを止める
        for(ActionListener al : setBtL1.getActionListeners()){
            setBtL1.removeActionListener(al);
        }

        //setBtL2のアクションリスナーを止める
        for(ActionListener al : setBtL2.getActionListeners()){
            setBtL2.removeActionListener(al);
        }

        //setBtR1のアクションリスナーを止める
        for(ActionListener al : setBtR1.getActionListeners()){
            setBtR1.removeActionListener(al);
        }

        //setBtR2のアクションリスナーを止める
        for(ActionListener al : setBtR2.getActionListeners()){
            setBtR2.removeActionListener(al);
        }

    }

    //セットするほうのボタンのアクションリスナーをはじめる
    private static void StartActionListener(){

        //setBtL1のアクションリスナーを始める
        for(ActionListener al : setBtL1.getActionListeners()){
            setBtL1.addActionListener(al);
        }

        //setBtL2のアクションリスナーを始める
        for(ActionListener al : setBtL2.getActionListeners()){
            setBtL2.addActionListener(al);
        }

        //setBtR1のアクションリスナーを始める
        for(ActionListener al : setBtR1.getActionListeners()){
            setBtR1.addActionListener(al);
        }

        //setBtR2のアクションリスナーを始める
        for(ActionListener al : setBtR2.getActionListeners()){
            setBtR2.addActionListener(al);
        }

    }

    //外側のボタンを有効にする
    private static void TrueButton(){

        btL1.setEnabled(true);
        btL2.setEnabled(true);
        btR1.setEnabled(true);
        btR2.setEnabled(true);

    }

    //全てのボタンのIconを無くす
    public static void NullAllBtIcon(){

        btL1.setIcon(null);
        btL2.setIcon(null);
        btR1.setIcon(null);
        btR2.setIcon(null);

        setBtL1.setIcon(null);
        setBtL2.setIcon(null);
        setBtR1.setIcon(null);
        setBtR2.setIcon(null);

    }

    //左の1番目を押したとき
    public static void btL1_ActionPerformed(ActionEvent e) {

        //ボタンのアイコンを取得してハンバーガーの画像かを識別

        //btL1にある画像が[0][0]（ハンバーガの左上）だった時
        if (btL1.getIcon() == imgIcon[0][0]) {

            System.out.println("btL1でpHum1を押された");

            //もしSetBtL1に画像が入っていなかったら
            if(setBtL1.getIcon() == null){

                //SetBtL1のアクションリスナーを呼び出す
                setBtL1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtL1に画像をセット
                        setBtL1.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

            //btL1にある画像が[0][1]（ハンバーガの右上）だった時
        }else if (btL1.getIcon() == imgIcon[0][1]) {

            System.out.println("btL1でpHum2を押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBtR1.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBtR1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        ////setBtR1に画像をセット
                        setBtR1.setIcon(imgIcon[0][1]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

            //btL1にある画像が[0][2]（ハンバーガの左下）だった時
        }else if (btL1.getIcon() == imgIcon[0][2]) {

            System.out.println("btL1でpHum3を押された");

            //もしSetBtL2に画像が入っていなかったら
            if(setBtL2.getIcon() == null){

                //SetBtL2のアクションリスナーを呼び出す
                setBtL2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //SetBtL2に画像をセット
                        setBtL2.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

            //btL1にある画像が[0][3]（ハンバーガの右下）だった時
        }else if (btL1.getIcon() == imgIcon[0][3]) {

            System.out.println("btL1でpHum4を押された");

            //もしsetBtR2に画像が入っていなかったら
            if(setBtR2.getIcon() == null){

                //setBtR2のアクションリスナーを呼び出す
                setBtR2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR2に画像をセット
                        setBtR2.setIcon(imgIcon[0][3]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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
        System.out.println(setBtL1.getIcon());
        System.out.println(setBtL2.getIcon());
        System.out.println(setBtR1.getIcon());
        System.out.println(setBtR2.getIcon());

        //ボタンのアイコンを取得してハリネズミの画像かを識別
        if (btL1.getIcon() == imgIcon[1][0]) {

            System.out.println("btL1でpHum1を押された");

            //真ん中のボタンに画像がなかった時
            if(setBtL1.getIcon() == null){

                //真ん中のボタンのアクションリスナー
                setBtL1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //真ん中のボタンに画像をセットする
                        setBtL1.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btL1.getIcon() == imgIcon[1][1]) {

            System.out.println("btL1でpHum2を押された");

            if(setBtR1.getIcon() == null){

                setBtR1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR1.setIcon(imgIcon[1][1]);
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btL1.getIcon() == imgIcon[1][2]) {

            System.out.println("btL1でpHum3を押された");

            if(setBtL2.getIcon() == null){

                setBtL2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL2.setIcon(imgIcon[1][2]);
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());


                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btL1.getIcon() == imgIcon[1][3]) {

            System.out.println("btL1でpHum4を押された");

            if(setBtR2.getIcon() == null){

                setBtR2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR2.setIcon(imgIcon[1][3]);
                        btL1.setEnabled(false);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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
        System.out.println(setBtL1.getIcon());
        System.out.println(setBtL2.getIcon());
        System.out.println(setBtR1.getIcon());
        System.out.println(setBtR2.getIcon());

    }

    //左の2番目を押したとき
    public static void btL2_ActionPerformed(ActionEvent e) {

        //ボタンのアイコンを取得してハンバーガーの画像かを識別
        if(btL2.getIcon() == imgIcon[0][0]){

            System.out.println("BtL2でpHum1を押された");

            if(setBtL1.getIcon() == null){

                setBtL1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL1.setIcon(imgIcon[0][0]);
                        btL2.setEnabled(false);
                        btL1.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btL2.getIcon() == imgIcon[0][1]) {

            System.out.println("BtL2でpHum2を押された");

            if(setBtR1.getIcon() == null){

                setBtR1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR1.setIcon(imgIcon[0][1]);
                        btL2.setEnabled(false);
                        btL1.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btL2.getIcon() == imgIcon[0][2]) {

            System.out.println("BtL2でpHum3を押された");

            if(setBtL2.getIcon() == null){

                setBtL2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL2.setIcon(imgIcon[0][2]);
                        btL2.setEnabled(false);
                        btL1.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btL2.getIcon() == imgIcon[0][3]) {

            System.out.println("BtL2でpHum4を押された");

            if(setBtR2.getIcon() == null){

                setBtR2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR2.setIcon(imgIcon[0][3]);
                        btL2.setEnabled(false);
                        btL1.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

            System.out.println("--- btL2が押された時 ---");
            System.out.println(setBtL1.getIcon());
            System.out.println(setBtL2.getIcon());
            System.out.println(setBtR1.getIcon());
            System.out.println(setBtR2.getIcon());

        }

        //ボタンのアイコンを取得してハリネズミの画像かを識別
        if(btL2.getIcon() == imgIcon[1][0]){

            System.out.println("BtL2でpHum1を押された");

            if(setBtL1.getIcon() == null){

                setBtL1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL1.setIcon(imgIcon[1][0]);
                        btL2.setEnabled(false);
                        btL1.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btL2.getIcon() == imgIcon[1][1]) {

            System.out.println("BtL2でpHum2を押された");

            if(setBtR1.getIcon() == null){

                setBtR1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR1.setIcon(imgIcon[1][1]);
                        btL2.setEnabled(false);
                        btL1.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btL2.getIcon() == imgIcon[1][2]) {

            System.out.println("BtL2でpHum3を押された");

            if(setBtL2.getIcon() == null){

                setBtL2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL2.setIcon(imgIcon[1][2]);
                        btL2.setEnabled(false);
                        btL1.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btL2.getIcon() == imgIcon[1][3]) {

            System.out.println("BtL2でpHum4を押された");

            if(setBtR2.getIcon() == null){

                setBtR2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR2.setIcon(imgIcon[1][3]);
                        btL2.setEnabled(false);
                        btL1.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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
        System.out.println(setBtL1.getIcon());
        System.out.println(setBtL2.getIcon());
        System.out.println(setBtR1.getIcon());
        System.out.println(setBtR2.getIcon());

    }

    //右の1番目を押したとき
    public static void btR1_ActionPerformed(ActionEvent e) {

        //ボタンのアイコンを取得してハンバーガーの画像かを識別
        if(btR1.getIcon() == imgIcon[0][0]){

            System.out.println("btR1でpHum1を押された");

            if(setBtL1.getIcon() == null){

                setBtL1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL1.setIcon(imgIcon[0][0]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR1.getIcon() == imgIcon[0][1]) {

            System.out.println("btR1でpHum2を押された");

            if(setBtR1.getIcon() == null){

                setBtR1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR1.setIcon(imgIcon[0][1]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR1.getIcon() == imgIcon[0][2]) {

            System.out.println("btR1でpHum3を押された");

            if(setBtL2.getIcon() == null){

                setBtL2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL2.setIcon(imgIcon[0][2]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR1.getIcon() == imgIcon[0][3]) {

            System.out.println("btR1でpHum4を押された");

            if(setBtR2.getIcon() == null){

                setBtR2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR2.setIcon(imgIcon[0][3]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

            System.out.println("--- btL2が押された時 ---");
            System.out.println(setBtL1.getIcon());
            System.out.println(setBtL2.getIcon());
            System.out.println(setBtR1.getIcon());
            System.out.println(setBtR2.getIcon());

        }

        //ボタンのアイコンを取得してハリネズミの画像かを識別
        if(btR1.getIcon() == imgIcon[1][0]){

            System.out.println("btR1でpHum1を押された");

            if(setBtL1.getIcon() == null){

                setBtL1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL1.setIcon(imgIcon[1][0]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR1.getIcon() == imgIcon[1][1]) {

            System.out.println("btR1でpHum2を押された");

            if(setBtR1.getIcon() == null){

                setBtR1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR1.setIcon(imgIcon[1][1]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR1.getIcon() == imgIcon[1][2]) {

            System.out.println("btR1でpHum3を押された");

            if(setBtL2.getIcon() == null){

                setBtL2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL2.setIcon(imgIcon[1][2]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR1.getIcon() == imgIcon[1][3]) {

            System.out.println("btR1でpHum4を押された");

            if(setBtR2.getIcon() == null){

                setBtR2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR2.setIcon(imgIcon[1][3]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(false);
                        btR2.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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
        System.out.println(setBtL1.getIcon());
        System.out.println(setBtL2.getIcon());
        System.out.println(setBtR1.getIcon());
        System.out.println(setBtR2.getIcon());

    }

    //右の2番目を押したとき
    public static void btR2_ActionPerformed(ActionEvent e) {

        //ボタンのアイコンを取得してハンバーガーの画像かを識別
        if(btR2.getIcon() == imgIcon[0][0]){

            System.out.println("btR2でpHum1を押された");

            if(setBtL1.getIcon() == null){

                setBtL1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL1.setIcon(imgIcon[0][0]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR2.getIcon() == imgIcon[0][1]) {

            System.out.println("btR2でpHum2を押された");

            if(setBtR1.getIcon() == null){

                setBtR1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR1.setIcon(imgIcon[0][1]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR2.getIcon() == imgIcon[0][2]) {

            System.out.println("btR2でpHum3を押された");

            if(setBtL2.getIcon() == null){

                setBtL2.addActionListener(new ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL2.setIcon(imgIcon[0][2]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR2.getIcon() == imgIcon[0][3]) {

            System.out.println("btR2でpHum4を押された");

            if(setBtR2.getIcon() == null){

                setBtR2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR2.setIcon(imgIcon[0][3]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        //ボタンのアイコンを取得してハリネズミの画像かを識別
        if(btR2.getIcon() == imgIcon[1][0]){

            System.out.println("btR2でpHum1を押された");

            if(setBtL1.getIcon() == null){

                setBtL1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL1.setIcon(imgIcon[1][0]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR2.getIcon() == imgIcon[1][1]) {

            System.out.println("btR2でpHum2を押された");

            if(setBtR1.getIcon() == null){

                setBtR1.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR1.setIcon(imgIcon[1][1]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR1が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR2.getIcon() == imgIcon[1][2]) {

            System.out.println("btR2でpHum3を押された");

            if(setBtL2.getIcon() == null){

                setBtL2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtL2.setIcon(imgIcon[1][2]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtL2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

        }else if (btR2.getIcon() == imgIcon[1][3]) {

            System.out.println("btR2でpHum4を押された");

            if(setBtR2.getIcon() == null){

                setBtR2.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        setBtR2.setIcon(imgIcon[1][3]);
                        btL1.setEnabled(true);
                        btL2.setEnabled(true);
                        btR1.setEnabled(true);
                        btR2.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBtR2が押された時 ---");
                        System.out.println(setBtL1.getIcon());
                        System.out.println(setBtL2.getIcon());
                        System.out.println(setBtR1.getIcon());
                        System.out.println(setBtR2.getIcon());

                        //全部そろった
                        if(setBtL1.getIcon() != null && setBtL2.getIcon() != null
                                && setBtR1.getIcon() != null && setBtR2.getIcon() != null) {

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

                            TrueButton();
                            NullAllBtIcon();
                            ChangePuzzle();

                        }

                    }

                });

            }

        }

        System.out.println("--- btR2が押された時 ---");
        System.out.println(setBtL1.getIcon());
        System.out.println(setBtL2.getIcon());
        System.out.println(setBtR1.getIcon());
        System.out.println(setBtR2.getIcon());

    }

    //imgIconの配列に画像を入れる
    public static void SetImageIcon(){

        //ハンバーガ
        imgIcon[0][0] = ChangeImageSize(pHum1);
        imgIcon[0][1] = ChangeImageSize(pHum2);
        imgIcon[0][2] = ChangeImageSize(pHum3);
        imgIcon[0][3] = ChangeImageSize(pHum4);
        //ハリネズミ
        imgIcon[1][0] = ChangeImageSize(pHari1);
        imgIcon[1][1] = ChangeImageSize(pHari2);
        imgIcon[1][2] = ChangeImageSize(pHari3);
        imgIcon[1][3] = ChangeImageSize(pHari4);

    }

    //画像のサイズを変更
    public static ImageIcon ChangeImageSize(ImageIcon imageIcon){

        getImg = imageIcon.getImage();
        newImg = getImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;

    }

}