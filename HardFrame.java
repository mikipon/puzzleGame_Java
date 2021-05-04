import javax.swing.*;
import javax.swing.border.LineBorder;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.applet.Applet;

public class HardFrame extends Applet{

    //画面
    private static JFrame efm;
    private static JLabel lb;
    private static Image getImg;
    private static Image newImg;

    //パズル（タイムアタックだから何個もパズル画像を表示しないといけない？）
    //パズルのボタン
    private static JButton btLT = new JButton();//左上
    private static JButton btLC = new JButton();//左真ん中
    private static JButton btLU = new JButton();//左下
    private static JButton btRT = new JButton();//右上
    private static JButton btRC = new JButton();//右下
    private static JButton btRU = new JButton();//右下
    private static JButton btUL = new JButton();//下の左
    private static JButton btUC = new JButton();//下の真ん中
    private static JButton btUR = new JButton();//下の右

    //パズルを置く場所のボタン
    private static JButton setBt_1L = new JButton();//内の左上
    private static JButton setBt_1R = new JButton();//内の左下
    private static JButton setBt_2C = new JButton();//内の右上
    private static JButton setBt_3L = new JButton();//内の右下
    private static JButton setBt_3R = new JButton();//内の右下

    //パンダ
    private static ImageIcon pPan_1L = new ImageIcon("NormalImg\\pPan1.jpg");
    private static ImageIcon pPan_1C = new ImageIcon("NormalImg\\pPan2.jpg");
    private static ImageIcon pPan_1R = new ImageIcon("NormalImg\\pPan3.jpg");
    private static ImageIcon pPan_2L = new ImageIcon("NormalImg\\pPan4.jpg");
    private static ImageIcon pPan_2C = new ImageIcon("NormalImg\\pPan5.jpg");
    private static ImageIcon pPan_2R = new ImageIcon("NormalImg\\pPan6.jpg");
    private static ImageIcon pPan_3L = new ImageIcon("NormalImg\\pPan7.jpg");
    private static ImageIcon pPan_3C = new ImageIcon("NormalImg\\pPan8.jpg");
    private static ImageIcon pPan_3R = new ImageIcon("NormalImg\\pPan9.jpg");

    //ゴリラ
    private static ImageIcon pGor_1L = new ImageIcon("NormalImg\\pGor1.jpg");
    private static ImageIcon pGor_1C = new ImageIcon("NormalImg\\pGor2.jpg");
    private static ImageIcon pGor_1R = new ImageIcon("NormalImg\\pGor3.jpg");
    private static ImageIcon pGor_2L = new ImageIcon("NormalImg\\pGor4.jpg");
    private static ImageIcon pGor_2C = new ImageIcon("NormalImg\\pGor5.jpg");
    private static ImageIcon pGor_2R = new ImageIcon("NormalImg\\pGor6.jpg");
    private static ImageIcon pGor_3L = new ImageIcon("NormalImg\\pGor7.jpg");
    private static ImageIcon pGor_3C = new ImageIcon("NormalImg\\pGor8.jpg");
    private static ImageIcon pGor_3R = new ImageIcon("NormalImg\\pGor9.jpg");

    //Iconの配列
    private static ImageIcon[][] imgIcon = new ImageIcon[2][9];

    //ランダム用
    private static ArrayList<Integer> listY = new ArrayList<Integer>();

    //全部パズルをあてはめたら
    private static int score = 0;//何個解いた？
    private static JLabel Result = new JLabel();
    private static boolean bool = true;

    //時間
    private static Timer timer;
    private static JLabel timerlb;
    private static int sec = 90;//制限時間

    //色
    private static Color cr1 = new Color(0,0,0,200);
    private static Color backColor = new Color(196,205,207);

    public HardFrame(){

        //サウンド

        //画面の作成メソッドへGo
        CreateGameFrame();
        //画像の設定
        SetImageIcon();

        //Bottonを作成
        //上左
        PuzzleBase(setBt_1L, 188, 150);
        efm.add(setBt_1L);

        //上右
        PuzzleBase(setBt_1R, 388, 150);
        efm.add(setBt_1R);

        //2段目真ん中
        PuzzleBase(setBt_2C, 288, 250);
        efm.add(setBt_2C);

        //下左
        PuzzleBase(setBt_3L, 188, 350);
        efm.add(setBt_3L);

        //下右
        PuzzleBase(setBt_3R, 388, 350);
        efm.add(setBt_3R);

        //パズルのIconを変える
        ChangePuzzle();

        //ボタンごとのアクションリスナー
        btLT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.print("btLT：");

                //押された時の処理のメソッドに飛ぶ
                btLT_ActionPerformed(evt);

                //間違ったパズルを押したとき
                if(btLT.getIcon() == imgIcon[0][1] || btLT.getIcon() == imgIcon[0][3]
                        || btLT.getIcon() == imgIcon[0][5] || btLT.getIcon() == imgIcon[0][7]
                        || btLT.getIcon() == imgIcon[1][1] || btLT.getIcon() == imgIcon[1][3]
                        || btLT.getIcon() == imgIcon[1][5] || btLT.getIcon() == imgIcon[1][7]){

                    System.out.println("おけないよ");

                    //ブーって音出るよ
                    AudioClip SE = null;
                    try {
                        SE = Applet.newAudioClip(new File("SE.wav").toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    SE.play();

                    if(setBt_1L.getIcon() == btLC.getIcon() || setBt_1R.getIcon() == btLC.getIcon()
                            || setBt_2C.getIcon() == btLC.getIcon() || setBt_3L.getIcon() == btLC.getIcon()
                            || setBt_3R.getIcon() == btLC.getIcon()){

                        btLC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLU.getIcon() || setBt_1R.getIcon() == btLU.getIcon()
                            || setBt_2C.getIcon() == btLU.getIcon() || setBt_3L.getIcon() == btLU.getIcon()
                            || setBt_3R.getIcon() == btLU.getIcon()){

                        btLU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRT.getIcon() || setBt_1R.getIcon() == btRT.getIcon()
                            || setBt_2C.getIcon() == btRT.getIcon() || setBt_3L.getIcon() == btRT.getIcon()
                            || setBt_3R.getIcon() == btRT.getIcon()){

                        btRT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRC.getIcon() || setBt_1R.getIcon() == btRC.getIcon()
                            || setBt_2C.getIcon() == btRC.getIcon() || setBt_3L.getIcon() == btRC.getIcon()
                            || setBt_3R.getIcon() == btRC.getIcon()){

                        btRC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRU.getIcon() || setBt_1R.getIcon() == btRU.getIcon()
                            || setBt_2C.getIcon() == btRU.getIcon() || setBt_3L.getIcon() == btRU.getIcon()
                            || setBt_3R.getIcon() == btRU.getIcon()){

                        btRU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUL.getIcon() || setBt_1R.getIcon() == btUL.getIcon()
                            || setBt_2C.getIcon() == btUL.getIcon() || setBt_3L.getIcon() == btUL.getIcon()
                            || setBt_3R.getIcon() == btUL.getIcon()){

                        btUL.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUC.getIcon() || setBt_1R.getIcon() == btUC.getIcon()
                            || setBt_2C.getIcon() == btUC.getIcon() || setBt_3L.getIcon() == btUC.getIcon()
                            || setBt_3R.getIcon() == btUC.getIcon()){

                        btUC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUR.getIcon() || setBt_1R.getIcon() == btUR.getIcon()
                            || setBt_2C.getIcon() == btUR.getIcon() || setBt_3L.getIcon() == btUR.getIcon()
                            || setBt_3R.getIcon() == btUR.getIcon()){

                        btUR.setEnabled(false);

                    }

                }else {

                    //アクションリスナーのボタン以外無効化
                    btLC.setEnabled(false);
                    btLU.setEnabled(false);
                    btRT.setEnabled(false);
                    btRC.setEnabled(false);
                    btRU.setEnabled(false);
                    btUL.setEnabled(false);
                    btUC.setEnabled(false);
                    btUR.setEnabled(false);

                }

            }

        });

        //ボタンごとのアクションリスナー
        btLC.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.print("btLC：");

                //押された時の処理のメソッドに飛ぶ
                btLC_ActionPerformed(evt);

                //パズルが間違ったとき
                if(btLC.getIcon() == imgIcon[0][1] || btLC.getIcon() == imgIcon[0][3]
                        || btLC.getIcon() == imgIcon[0][5] || btLC.getIcon() == imgIcon[0][7]
                        || btLC.getIcon() == imgIcon[1][1] || btLC.getIcon() == imgIcon[1][3]
                        || btLC.getIcon() == imgIcon[1][5] || btLC.getIcon() == imgIcon[1][7]){

                    System.out.println("おけないよ");

                    //ブーって音出るよ
                    AudioClip SE = null;
                    try {
                        SE = Applet.newAudioClip(new File("SE.wav").toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    SE.play();

                    if(setBt_1L.getIcon() == btLT.getIcon() || setBt_1R.getIcon() == btLT.getIcon()
                            || setBt_2C.getIcon() == btLT.getIcon() || setBt_3L.getIcon() == btLT.getIcon()
                            || setBt_3R.getIcon() == btLT.getIcon()){

                        btLT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLU.getIcon() || setBt_1R.getIcon() == btLU.getIcon()
                            || setBt_2C.getIcon() == btLU.getIcon() || setBt_3L.getIcon() == btLU.getIcon()
                            || setBt_3R.getIcon() == btLU.getIcon()){

                        btLU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRT.getIcon() || setBt_1R.getIcon() == btRT.getIcon()
                            || setBt_2C.getIcon() == btRT.getIcon() || setBt_3L.getIcon() == btRT.getIcon()
                            || setBt_3R.getIcon() == btRT.getIcon()){

                        btRT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRC.getIcon() || setBt_1R.getIcon() == btRC.getIcon()
                            || setBt_2C.getIcon() == btRC.getIcon() || setBt_3L.getIcon() == btRC.getIcon()
                            || setBt_3R.getIcon() == btRC.getIcon()){

                        btRC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRU.getIcon() || setBt_1R.getIcon() == btRU.getIcon()
                            || setBt_2C.getIcon() == btRU.getIcon() || setBt_3L.getIcon() == btRU.getIcon()
                            || setBt_3R.getIcon() == btRU.getIcon()){

                        btRU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUL.getIcon() || setBt_1R.getIcon() == btUL.getIcon()
                            || setBt_2C.getIcon() == btUL.getIcon() || setBt_3L.getIcon() == btUL.getIcon()
                            || setBt_3R.getIcon() == btUL.getIcon()){

                        btUL.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUC.getIcon() || setBt_1R.getIcon() == btUC.getIcon()
                            || setBt_2C.getIcon() == btUC.getIcon() || setBt_3L.getIcon() == btUC.getIcon()
                            || setBt_3R.getIcon() == btUC.getIcon()){

                        btUC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUR.getIcon() || setBt_1R.getIcon() == btUR.getIcon()
                            || setBt_2C.getIcon() == btUR.getIcon() || setBt_3L.getIcon() == btUR.getIcon()
                            || setBt_3R.getIcon() == btUR.getIcon()){

                        btUR.setEnabled(false);

                    }

                }else {

                    //アクションリスナーのボタン以外無効化
                    btLT.setEnabled(false);
                    btLU.setEnabled(false);
                    btRT.setEnabled(false);
                    btRC.setEnabled(false);
                    btRU.setEnabled(false);
                    btUL.setEnabled(false);
                    btUC.setEnabled(false);
                    btUR.setEnabled(false);

                }

            }

        });

        //ボタンごとのアクションリスナー
        btLU.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.print("btLU：");

                //押された時の処理のメソッドに飛ぶ
                btLU_ActionPerformed(evt);

                //パズルが間違ったとき
                if (btLU.getIcon() == imgIcon[0][1] || btLU.getIcon() == imgIcon[0][3]
                        || btLU.getIcon() == imgIcon[0][5] || btLU.getIcon() == imgIcon[0][7]
                        || btLU.getIcon() == imgIcon[1][1] || btLU.getIcon() == imgIcon[1][3]
                        || btLU.getIcon() == imgIcon[1][5] || btLU.getIcon() == imgIcon[1][7]) {

                    System.out.println("おけないよ");

                    //ブーって音出るよ
                    AudioClip SE = null;
                    try {
                        SE = Applet.newAudioClip(new File("SE.wav").toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    SE.play();

                    if(setBt_1L.getIcon() == btLT.getIcon() || setBt_1R.getIcon() == btLT.getIcon()
                            || setBt_2C.getIcon() == btLT.getIcon() || setBt_3L.getIcon() == btLT.getIcon()
                            || setBt_3R.getIcon() == btLT.getIcon()){

                        btLT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLC.getIcon() || setBt_1R.getIcon() == btLC.getIcon()
                            || setBt_2C.getIcon() == btLC.getIcon() || setBt_3L.getIcon() == btLC.getIcon()
                            || setBt_3R.getIcon() == btLC.getIcon()){

                        btLC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRT.getIcon() || setBt_1R.getIcon() == btRT.getIcon()
                            || setBt_2C.getIcon() == btRT.getIcon() || setBt_3L.getIcon() == btRT.getIcon()
                            || setBt_3R.getIcon() == btRT.getIcon()){

                        btRT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRC.getIcon() || setBt_1R.getIcon() == btRC.getIcon()
                            || setBt_2C.getIcon() == btRC.getIcon() || setBt_3L.getIcon() == btRC.getIcon()
                            || setBt_3R.getIcon() == btRC.getIcon()){

                        btRC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRU.getIcon() || setBt_1R.getIcon() == btRU.getIcon()
                            || setBt_2C.getIcon() == btRU.getIcon() || setBt_3L.getIcon() == btRU.getIcon()
                            || setBt_3R.getIcon() == btRU.getIcon()){

                        btRU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUL.getIcon() || setBt_1R.getIcon() == btUL.getIcon()
                            || setBt_2C.getIcon() == btUL.getIcon() || setBt_3L.getIcon() == btUL.getIcon()
                            || setBt_3R.getIcon() == btUL.getIcon()){

                        btUL.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUC.getIcon() || setBt_1R.getIcon() == btUC.getIcon()
                            || setBt_2C.getIcon() == btUC.getIcon() || setBt_3L.getIcon() == btUC.getIcon()
                            || setBt_3R.getIcon() == btUC.getIcon()){

                        btUC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUR.getIcon() || setBt_1R.getIcon() == btUR.getIcon()
                            || setBt_2C.getIcon() == btUR.getIcon() || setBt_3L.getIcon() == btUR.getIcon()
                            || setBt_3R.getIcon() == btUR.getIcon()){

                        btUR.setEnabled(false);

                    }

                }else {

                    //アクションリスナーのボタン以外無効化
                    btLT.setEnabled(false);
                    btLC.setEnabled(false);
                    btRT.setEnabled(false);
                    btRC.setEnabled(false);
                    btRU.setEnabled(false);
                    btUL.setEnabled(false);
                    btUC.setEnabled(false);
                    btUR.setEnabled(false);

                }

            }

        });

        //ボタンごとのアクションリスナー
        btRT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.print("btRT：");

                //押された時の処理のメソッドに飛ぶ
                btRT_ActionPerformed(evt);

                //パズルが間違ったとき
                if(btRT.getIcon() == imgIcon[0][1] || btRT.getIcon() == imgIcon[0][3]
                        || btRT.getIcon() == imgIcon[0][5] || btRT.getIcon() == imgIcon[0][7]
                        || btRT.getIcon() == imgIcon[1][1] || btRT.getIcon() == imgIcon[1][3]
                        || btRT.getIcon() == imgIcon[1][5] || btRT.getIcon() == imgIcon[1][7]){

                    System.out.println("おけないよ");

                    //ブーって音出るよ
                    AudioClip SE = null;
                    try {
                        SE = Applet.newAudioClip(new File("SE.wav").toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    SE.play();

                    if(setBt_1L.getIcon() == btLT.getIcon() || setBt_1R.getIcon() == btLT.getIcon()
                            || setBt_2C.getIcon() == btLT.getIcon() || setBt_3L.getIcon() == btLT.getIcon()
                            || setBt_3R.getIcon() == btLT.getIcon()){

                        btLT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLC.getIcon() || setBt_1R.getIcon() == btLC.getIcon()
                            || setBt_2C.getIcon() == btLC.getIcon() || setBt_3L.getIcon() == btLC.getIcon()
                            || setBt_3R.getIcon() == btLC.getIcon()){

                        btLC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLU.getIcon() || setBt_1R.getIcon() == btLU.getIcon()
                            || setBt_2C.getIcon() == btLU.getIcon() || setBt_3L.getIcon() == btLU.getIcon()
                            || setBt_3R.getIcon() == btLU.getIcon()){

                        btLU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRC.getIcon() || setBt_1R.getIcon() == btRC.getIcon()
                            || setBt_2C.getIcon() == btRC.getIcon() || setBt_3L.getIcon() == btRC.getIcon()
                            || setBt_3R.getIcon() == btRC.getIcon()){

                        btRC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRU.getIcon() || setBt_1R.getIcon() == btRU.getIcon()
                            || setBt_2C.getIcon() == btRU.getIcon() || setBt_3L.getIcon() == btRU.getIcon()
                            || setBt_3R.getIcon() == btRU.getIcon()){

                        btRU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUL.getIcon() || setBt_1R.getIcon() == btUL.getIcon()
                            || setBt_2C.getIcon() == btUL.getIcon() || setBt_3L.getIcon() == btUL.getIcon()
                            || setBt_3R.getIcon() == btUL.getIcon()){

                        btUL.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUC.getIcon() || setBt_1R.getIcon() == btUC.getIcon()
                            || setBt_2C.getIcon() == btUC.getIcon() || setBt_3L.getIcon() == btUC.getIcon()
                            || setBt_3R.getIcon() == btUC.getIcon()){

                        btUC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUR.getIcon() || setBt_1R.getIcon() == btUR.getIcon()
                            || setBt_2C.getIcon() == btUR.getIcon() || setBt_3L.getIcon() == btUR.getIcon()
                            || setBt_3R.getIcon() == btUR.getIcon()){

                        btUR.setEnabled(false);

                    }

                }else {

                    //アクションリスナーのボタン以外無効化
                    btLT.setEnabled(false);
                    btLC.setEnabled(false);
                    btLU.setEnabled(false);
                    btRC.setEnabled(false);
                    btRU.setEnabled(false);
                    btUL.setEnabled(false);
                    btUC.setEnabled(false);
                    btUR.setEnabled(false);

                }

            }

        });

        //ボタンごとのアクションリスナー
        btRC.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.print("btUC：");

                //押された時の処理のメソッドに飛ぶ
                btRC_ActionPerformed(evt);

                //パズルが間違ったとき
                if(btRC.getIcon() == imgIcon[0][1] || btRC.getIcon() == imgIcon[0][3]
                        || btRC.getIcon() == imgIcon[0][5] || btRC.getIcon() == imgIcon[0][7]
                        || btRC.getIcon() == imgIcon[1][1] || btRC.getIcon() == imgIcon[1][3]
                        || btRC.getIcon() == imgIcon[1][5] || btRC.getIcon() == imgIcon[1][7]){

                    System.out.println("おけないよ");

                    //ブーって音出るよ
                    AudioClip SE = null;
                    try {
                        SE = Applet.newAudioClip(new File("SE.wav").toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    SE.play();

                    if(setBt_1L.getIcon() == btLT.getIcon() || setBt_1R.getIcon() == btLT.getIcon()
                            || setBt_2C.getIcon() == btLT.getIcon() || setBt_3L.getIcon() == btLT.getIcon()
                            || setBt_3R.getIcon() == btLT.getIcon()){

                        btLT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLC.getIcon() || setBt_1R.getIcon() == btLC.getIcon()
                            || setBt_2C.getIcon() == btLC.getIcon() || setBt_3L.getIcon() == btLC.getIcon()
                            || setBt_3R.getIcon() == btLC.getIcon()){

                        btLC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLU.getIcon() || setBt_1R.getIcon() == btLU.getIcon()
                            || setBt_2C.getIcon() == btLU.getIcon() || setBt_3L.getIcon() == btLU.getIcon()
                            || setBt_3R.getIcon() == btLU.getIcon()){

                        btLU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRT.getIcon() || setBt_1R.getIcon() == btRT.getIcon()
                            || setBt_2C.getIcon() == btRT.getIcon() || setBt_3L.getIcon() == btRT.getIcon()
                            || setBt_3R.getIcon() == btRT.getIcon()){

                        btRT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRU.getIcon() || setBt_1R.getIcon() == btRU.getIcon()
                            || setBt_2C.getIcon() == btRU.getIcon() || setBt_3L.getIcon() == btRU.getIcon()
                            || setBt_3R.getIcon() == btRU.getIcon()){

                        btRU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUL.getIcon() || setBt_1R.getIcon() == btUL.getIcon()
                            || setBt_2C.getIcon() == btUL.getIcon() || setBt_3L.getIcon() == btUL.getIcon()
                            || setBt_3R.getIcon() == btUL.getIcon()){

                        btUL.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUC.getIcon() || setBt_1R.getIcon() == btUC.getIcon()
                            || setBt_2C.getIcon() == btUC.getIcon() || setBt_3L.getIcon() == btUC.getIcon()
                            || setBt_3R.getIcon() == btUC.getIcon()){

                        btUC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUR.getIcon() || setBt_1R.getIcon() == btUR.getIcon()
                            || setBt_2C.getIcon() == btUR.getIcon() || setBt_3L.getIcon() == btUR.getIcon()
                            || setBt_3R.getIcon() == btUR.getIcon()){

                        btUR.setEnabled(false);

                    }

                }else {

                    //アクションリスナーのボタン以外無効化
                    btLT.setEnabled(false);
                    btLC.setEnabled(false);
                    btLU.setEnabled(false);
                    btRT.setEnabled(false);
                    btRU.setEnabled(false);
                    btUL.setEnabled(false);
                    btUC.setEnabled(false);
                    btUR.setEnabled(false);

                }

            }

        });

        //ボタンごとのアクションリスナー
        btRU.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.print("btRU：");

                //押された時の処理のメソッドに飛ぶ
                btRU_ActionPerformed(evt);

                //パズルが間違ったとき
                if(btRU.getIcon() == imgIcon[0][1] || btRU.getIcon() == imgIcon[0][3]
                        || btRU.getIcon() == imgIcon[0][5] || btRU.getIcon() == imgIcon[0][7]
                        || btRU.getIcon() == imgIcon[1][1] || btRU.getIcon() == imgIcon[1][3]
                        || btRU.getIcon() == imgIcon[1][5] || btRU.getIcon() == imgIcon[1][7]){

                    System.out.println("おけないよ");

                    //ブーって音出るよ
                    AudioClip SE = null;
                    try {
                        SE = Applet.newAudioClip(new File("SE.wav").toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    SE.play();

                    if(setBt_1L.getIcon() == btLT.getIcon() || setBt_1R.getIcon() == btLT.getIcon()
                            || setBt_2C.getIcon() == btLT.getIcon() || setBt_3L.getIcon() == btLT.getIcon()
                            || setBt_3R.getIcon() == btLT.getIcon()){

                        btLT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLC.getIcon() || setBt_1R.getIcon() == btLC.getIcon()
                            || setBt_2C.getIcon() == btLC.getIcon() || setBt_3L.getIcon() == btLC.getIcon()
                            || setBt_3R.getIcon() == btLC.getIcon()){

                        btLC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLU.getIcon() || setBt_1R.getIcon() == btLU.getIcon()
                            || setBt_2C.getIcon() == btLU.getIcon() || setBt_3L.getIcon() == btLU.getIcon()
                            || setBt_3R.getIcon() == btLU.getIcon()){

                        btLU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRT.getIcon() || setBt_1R.getIcon() == btRT.getIcon()
                            || setBt_2C.getIcon() == btRT.getIcon() || setBt_3L.getIcon() == btRT.getIcon()
                            || setBt_3R.getIcon() == btRT.getIcon()){

                        btRT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRC.getIcon() || setBt_1R.getIcon() == btRC.getIcon()
                            || setBt_2C.getIcon() == btRC.getIcon() || setBt_3L.getIcon() == btRC.getIcon()
                            || setBt_3R.getIcon() == btRC.getIcon()){

                        btRC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUL.getIcon() || setBt_1R.getIcon() == btUL.getIcon()
                            || setBt_2C.getIcon() == btUL.getIcon() || setBt_3L.getIcon() == btUL.getIcon()
                            || setBt_3R.getIcon() == btUL.getIcon()){

                        btUL.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUC.getIcon() || setBt_1R.getIcon() == btUC.getIcon()
                            || setBt_2C.getIcon() == btUC.getIcon() || setBt_3L.getIcon() == btUC.getIcon()
                            || setBt_3R.getIcon() == btUC.getIcon()){

                        btUC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUR.getIcon() || setBt_1R.getIcon() == btUR.getIcon()
                            || setBt_2C.getIcon() == btUR.getIcon() || setBt_3L.getIcon() == btUR.getIcon()
                            || setBt_3R.getIcon() == btUR.getIcon()){

                        btUR.setEnabled(false);

                    }

                }else {

                    //アクションリスナーのボタン以外無効化
                    btLT.setEnabled(false);
                    btLC.setEnabled(false);
                    btLU.setEnabled(false);
                    btRT.setEnabled(false);
                    btRC.setEnabled(false);
                    btUL.setEnabled(false);
                    btUC.setEnabled(false);
                    btUR.setEnabled(false);

                }

            }

        });

        //ボタンごとのアクションリスナー
        btUL.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.print("btUL：");

                //押された時の処理のメソッドに飛ぶ
                btUL_ActionPerformed(evt);

                //パズルが間違ったとき
                if(btUL.getIcon() == imgIcon[0][1] || btUL.getIcon() == imgIcon[0][3]
                        || btUL.getIcon() == imgIcon[0][5] || btUL.getIcon() == imgIcon[0][7]
                        || btUL.getIcon() == imgIcon[1][1] || btUL.getIcon() == imgIcon[1][3]
                        || btUL.getIcon() == imgIcon[1][5] || btUL.getIcon() == imgIcon[1][7]){

                    System.out.println("おけないよ");

                    //ブーって音出るよ
                    AudioClip SE = null;
                    try {
                        SE = Applet.newAudioClip(new File("SE.wav").toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    SE.play();

                    if(setBt_1L.getIcon() == btLT.getIcon() || setBt_1R.getIcon() == btLT.getIcon()
                            || setBt_2C.getIcon() == btLT.getIcon() || setBt_3L.getIcon() == btLT.getIcon()
                            || setBt_3R.getIcon() == btLT.getIcon()){

                        btLT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLC.getIcon() || setBt_1R.getIcon() == btLC.getIcon()
                            || setBt_2C.getIcon() == btLC.getIcon() || setBt_3L.getIcon() == btLC.getIcon()
                            || setBt_3R.getIcon() == btLC.getIcon()){

                        btLC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLU.getIcon() || setBt_1R.getIcon() == btLU.getIcon()
                            || setBt_2C.getIcon() == btLU.getIcon() || setBt_3L.getIcon() == btLU.getIcon()
                            || setBt_3R.getIcon() == btLU.getIcon()){

                        btLU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRT.getIcon() || setBt_1R.getIcon() == btRT.getIcon()
                            || setBt_2C.getIcon() == btRT.getIcon() || setBt_3L.getIcon() == btRT.getIcon()
                            || setBt_3R.getIcon() == btRT.getIcon()){

                        btRT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRC.getIcon() || setBt_1R.getIcon() == btRC.getIcon()
                            || setBt_2C.getIcon() == btRC.getIcon() || setBt_3L.getIcon() == btRC.getIcon()
                            || setBt_3R.getIcon() == btRC.getIcon()){

                        btRC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRU.getIcon() || setBt_1R.getIcon() == btRU.getIcon()
                            || setBt_2C.getIcon() == btRU.getIcon() || setBt_3L.getIcon() == btRU.getIcon()
                            || setBt_3R.getIcon() == btRU.getIcon()){

                        btRU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUC.getIcon() || setBt_1R.getIcon() == btUC.getIcon()
                            || setBt_2C.getIcon() == btUC.getIcon() || setBt_3L.getIcon() == btUC.getIcon()
                            || setBt_3R.getIcon() == btUC.getIcon()){

                        btUC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUR.getIcon() || setBt_1R.getIcon() == btUR.getIcon()
                            || setBt_2C.getIcon() == btUR.getIcon() || setBt_3L.getIcon() == btUR.getIcon()
                            || setBt_3R.getIcon() == btUR.getIcon()){

                        btUR.setEnabled(false);

                    }

                }else {

                    //アクションリスナーのボタン以外無効化
                    btLT.setEnabled(false);
                    btLC.setEnabled(false);
                    btLU.setEnabled(false);
                    btRT.setEnabled(false);
                    btRC.setEnabled(false);
                    btRU.setEnabled(false);
                    btUC.setEnabled(false);
                    btUR.setEnabled(false);

                }

            }

        });

        //ボタンごとのアクションリスナー
        btUC.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.print("btUC：");

                //押された時の処理のメソッドに飛ぶ
                btUC_ActionPerformed(evt);

                //パズルが間違ったとき
                if(btUC.getIcon() == imgIcon[0][1] || btUC.getIcon() == imgIcon[0][3]
                        || btUC.getIcon() == imgIcon[0][5] || btUC.getIcon() == imgIcon[0][7]
                        || btUC.getIcon() == imgIcon[1][1] || btUC.getIcon() == imgIcon[1][3]
                        || btUC.getIcon() == imgIcon[1][5] || btUC.getIcon() == imgIcon[1][7]){

                    System.out.println("おけないよ");

                    //ブーって音出るよ
                    AudioClip SE = null;
                    try {
                        SE = Applet.newAudioClip(new File("SE.wav").toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    SE.play();

                    if(setBt_1L.getIcon() == btLT.getIcon() || setBt_1R.getIcon() == btLT.getIcon()
                            || setBt_2C.getIcon() == btLT.getIcon() || setBt_3L.getIcon() == btLT.getIcon()
                            || setBt_3R.getIcon() == btLT.getIcon()){

                        btLT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLC.getIcon() || setBt_1R.getIcon() == btLC.getIcon()
                            || setBt_2C.getIcon() == btLC.getIcon() || setBt_3L.getIcon() == btLC.getIcon()
                            || setBt_3R.getIcon() == btLC.getIcon()){

                        btLC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLU.getIcon() || setBt_1R.getIcon() == btLU.getIcon()
                            || setBt_2C.getIcon() == btLU.getIcon() || setBt_3L.getIcon() == btLU.getIcon()
                            || setBt_3R.getIcon() == btLU.getIcon()){

                        btLU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRT.getIcon() || setBt_1R.getIcon() == btRT.getIcon()
                            || setBt_2C.getIcon() == btRT.getIcon() || setBt_3L.getIcon() == btRT.getIcon()
                            || setBt_3R.getIcon() == btRT.getIcon()){

                        btRT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRC.getIcon() || setBt_1R.getIcon() == btRC.getIcon()
                            || setBt_2C.getIcon() == btRC.getIcon() || setBt_3L.getIcon() == btRC.getIcon()
                            || setBt_3R.getIcon() == btRC.getIcon()){

                        btRC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRU.getIcon() || setBt_1R.getIcon() == btRU.getIcon()
                            || setBt_2C.getIcon() == btRU.getIcon() || setBt_3L.getIcon() == btRU.getIcon()
                            || setBt_3R.getIcon() == btRU.getIcon()){

                        btRU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUL.getIcon() || setBt_1R.getIcon() == btUL.getIcon()
                            || setBt_2C.getIcon() == btUL.getIcon() || setBt_3L.getIcon() == btUL.getIcon()
                            || setBt_3R.getIcon() == btUL.getIcon()){

                        btUL.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUR.getIcon() || setBt_1R.getIcon() == btUR.getIcon()
                            || setBt_2C.getIcon() == btUR.getIcon() || setBt_3L.getIcon() == btUR.getIcon()
                            || setBt_3R.getIcon() == btUR.getIcon()){

                        btUR.setEnabled(false);

                    }

                }else{

                    //アクションリスナーのボタン以外無効化
                    btLT.setEnabled(false);
                    btLC.setEnabled(false);
                    btLU.setEnabled(false);
                    btRT.setEnabled(false);
                    btRC.setEnabled(false);
                    btRU.setEnabled(false);
                    btUL.setEnabled(false);
                    btUR.setEnabled(false);

                }

            }

        });

        //ボタンごとのアクションリスナー
        btUR.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.print("btUR：");

                //押された時の処理のメソッドに飛ぶ
                btUR_ActionPerformed(evt);

                //パズルが間違ったとき
                if(btUR.getIcon() == imgIcon[0][1] || btUR.getIcon() == imgIcon[0][3]
                        || btUR.getIcon() == imgIcon[0][5] || btUR.getIcon() == imgIcon[0][7]
                        || btUR.getIcon() == imgIcon[1][1] || btUR.getIcon() == imgIcon[1][3]
                        || btUR.getIcon() == imgIcon[1][5] || btUR.getIcon() == imgIcon[1][7]){

                    System.out.println("おけないよ");

                    //ブーって音出るよ
                    AudioClip SE = null;
                    try {
                        SE = Applet.newAudioClip(new File("SE.wav").toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    SE.play();

                    if(setBt_1L.getIcon() == btLT.getIcon() || setBt_1R.getIcon() == btLT.getIcon()
                            || setBt_2C.getIcon() == btLT.getIcon() || setBt_3L.getIcon() == btLT.getIcon()
                            || setBt_3R.getIcon() == btLT.getIcon()){

                        btLT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLC.getIcon() || setBt_1R.getIcon() == btLC.getIcon()
                            || setBt_2C.getIcon() == btLC.getIcon() || setBt_3L.getIcon() == btLC.getIcon()
                            || setBt_3R.getIcon() == btLC.getIcon()){

                        btLC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btLU.getIcon() || setBt_1R.getIcon() == btLU.getIcon()
                            || setBt_2C.getIcon() == btLU.getIcon() || setBt_3L.getIcon() == btLU.getIcon()
                            || setBt_3R.getIcon() == btLU.getIcon()){

                        btLU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRT.getIcon() || setBt_1R.getIcon() == btRT.getIcon()
                            || setBt_2C.getIcon() == btRT.getIcon() || setBt_3L.getIcon() == btRT.getIcon()
                            || setBt_3R.getIcon() == btRT.getIcon()){

                        btRT.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRC.getIcon() || setBt_1R.getIcon() == btRC.getIcon()
                            || setBt_2C.getIcon() == btRC.getIcon() || setBt_3L.getIcon() == btRC.getIcon()
                            || setBt_3R.getIcon() == btRC.getIcon()){

                        btRC.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btRU.getIcon() || setBt_1R.getIcon() == btRU.getIcon()
                            || setBt_2C.getIcon() == btRU.getIcon() || setBt_3L.getIcon() == btRU.getIcon()
                            || setBt_3R.getIcon() == btRU.getIcon()){

                        btRU.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUL.getIcon() || setBt_1R.getIcon() == btUL.getIcon()
                            || setBt_2C.getIcon() == btUL.getIcon() || setBt_3L.getIcon() == btUL.getIcon()
                            || setBt_3R.getIcon() == btUL.getIcon()){

                        btUL.setEnabled(false);

                    }

                    if(setBt_1L.getIcon() == btUC.getIcon() || setBt_1R.getIcon() == btUC.getIcon()
                            || setBt_2C.getIcon() == btUC.getIcon() || setBt_3L.getIcon() == btUC.getIcon()
                            || setBt_3R.getIcon() == btUC.getIcon()){

                        btUC.setEnabled(false);

                    }

                }else{

                    //アクションリスナーのボタン以外無効化
                    btLT.setEnabled(false);
                    btLC.setEnabled(false);
                    btLU.setEnabled(false);
                    btRT.setEnabled(false);
                    btRC.setEnabled(false);
                    btRU.setEnabled(false);
                    btUL.setEnabled(false);
                    btUC.setEnabled(false);

                }

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
        System.out.println("listX:  "+listX);

        //もしパンダだったら(０ == pHumたち)
        if(listX.get(0) == 0) {

            //Yをランダムにするメソッドを呼ぶ
            RandomY();

            //外側のパズル配置
            //左上
            btLT.setIcon(imgIcon[0][listY.get(0)]);
            PuzzleBase(btLT, 20, 140);
            System.out.println("btLTに"+btLT.getIcon());
            efm.add(btLT);

            //左真ん中
            btLC.setIcon(imgIcon[0][listY.get(1)]);
            PuzzleBase(btLC, 20, 260);
            System.out.println("btLCに"+btLC.getIcon());
            efm.add(btLC);

            //左下
            btLU.setIcon(imgIcon[0][listY.get(2)]);
            PuzzleBase(btLU, 20, 380);
            System.out.println("btLUに"+btLU.getIcon());
            efm.add(btLU);

            //右上
            btRT.setIcon(imgIcon[0][listY.get(3)]);
            PuzzleBase(btRT, 560, 140);
            System.out.println("btRTに"+btRT.getIcon());
            efm.add(btRT);

            //右真ん中
            btRC.setIcon(imgIcon[0][listY.get(4)]);
            PuzzleBase(btRC, 560, 260);
            System.out.println("btRCに"+btRC.getIcon());
            efm.add(btRC);

            //右下
            btRU.setIcon(imgIcon[0][listY.get(5)]);
            PuzzleBase(btRU, 560, 380);
            System.out.println("btRUに"+btRU.getIcon());
            efm.add(btRU);

            //右上
            btUL.setIcon(imgIcon[0][listY.get(6)]);
            PuzzleBase(btUL, 100,  520);
            System.out.println("btULに"+btUL.getIcon());
            efm.add(btUL);

            //右真ん中
            btUC.setIcon(imgIcon[0][listY.get(7)]);
            PuzzleBase(btUC, 300, 520);
            System.out.println("btUCに"+btUC.getIcon());
            efm.add(btUC);

            //右下
            btUR.setIcon(imgIcon[0][listY.get(8)]);
            PuzzleBase(btUR, 500, 520);
            System.out.println("btURに"+btUR.getIcon());
            efm.add(btUR);

        } else {//ゴリラだったら(1 == pHariたち)

            //Yをランダムにするメソッドを呼ぶ
            RandomY();

            //パズル配置
            //左上
            btLT.setIcon(imgIcon[1][listY.get(0)]);
            PuzzleBase(btLT, 20, 140);
            System.out.println("btLTに"+btLT.getIcon());
            efm.add(btLT);

            //左真ん中
            btLC.setIcon(imgIcon[1][listY.get(1)]);
            PuzzleBase(btLC, 20, 260);
            System.out.println("btLCに"+btLC.getIcon());
            efm.add(btLC);

            //左下
            btLU.setIcon(imgIcon[1][listY.get(2)]);
            PuzzleBase(btLU, 20, 380);
            System.out.println("btLUに"+btLU.getIcon());
            efm.add(btLU);

            //右上
            btRT.setIcon(imgIcon[1][listY.get(3)]);
            PuzzleBase(btRT, 560, 140);
            System.out.println("btRTに"+btRT.getIcon());
            efm.add(btRT);

            //右真ん中
            btRC.setIcon(imgIcon[1][listY.get(4)]);
            PuzzleBase(btRC, 560, 260);
            System.out.println("btRCに"+btRC.getIcon());
            efm.add(btRC);

            //右下
            btRU.setIcon(imgIcon[1][listY.get(5)]);
            PuzzleBase(btRU, 560, 380);
            System.out.println("btRUに"+btRU.getIcon());
            efm.add(btRU);

            //右上
            btUL.setIcon(imgIcon[1][listY.get(6)]);
            PuzzleBase(btUL, 100,  520);
            System.out.println("btULに"+btUL.getIcon());
            efm.add(btUL);

            //右真ん中
            btUC.setIcon(imgIcon[1][listY.get(7)]);
            PuzzleBase(btUC, 300, 520);
            System.out.println("btUCに"+btUC.getIcon());
            efm.add(btUC);

            //右下
            btUR.setIcon(imgIcon[1][listY.get(8)]);
            PuzzleBase(btUR, 500, 520);
            System.out.println("btURに"+btUR.getIcon());
            efm.add(btUR);

        }

    }

    //ゲーム画面
    public static void CreateGameFrame(){

        //Easy画面の表示
        efm = new JFrame("Hard");
        efm.setVisible(true);
        efm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        efm.setSize(700,700);
        efm.setLocation(500,100);
        efm.getContentPane().setBackground(backColor);
        efm.setLayout(null);

        //表示文字
        lb = new JLabel();
        lb.setBounds(187,148,300,300);
        lb.setBorder(new LineBorder(Color.BLACK, 2, true));
        efm.add(lb);

        //スコアの表示
        Result.setBounds(200,440,100,100);
        Result.setFont(new Font("Sans-serif",Font.PLAIN,50));
        efm.add(Result);

        Timer();

    }

    public static void Timer(){
        timerlb = new JLabel();
        timerlb.setBounds(170,0,400,100);
        timerlb.setFont(new Font("Sans-serif",Font.PLAIN,50));
        efm.add(timerlb);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timerlb.setText("TimeLimit " + sec + "sec");
                if (sec <= 0){
                    timer.stop();
                    efm.getContentPane().removeAll();
                    setBt_1L = new JButton();
                    setBt_1R = new JButton();
                    setBt_3L = new JButton();
                    setBt_3R = new JButton();
                    btLC = new JButton();
                    btLT = new JButton();
                    btLU = new JButton();
                    btRC = new JButton();
                    btRT = new JButton();
                    btRU = new JButton();
                    btUR = new JButton();
                    btUC = new JButton();
                    btUL = new JButton();
                    Result = new JLabel();
                    sec = 90;
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
        repn.setBounds(0,0,700,700);
        repn.setBackground(cr1);
        efm.add(repn);
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
                    efm.dispose();
                    new  MainFrame();
                }
            }
        });
    }

    public static ArrayList<Integer> RandomY(){

        //配列のYをランダムにする
        // リストに0から4を代入
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

    //内側のボタンに画像があったらその画像を外側のボタンを無効化
    private static void  noTouchPuzzle(){

        //左上
        if(setBt_1L.getIcon() == btLT.getIcon()){

            btLT.setEnabled(false);

        }else if(setBt_1L.getIcon() == btLC.getIcon()){

            btLC.setEnabled(false);

        }else if(setBt_1L.getIcon() == btLU.getIcon()){

            btLU.setEnabled(false);

        }else if(setBt_1L.getIcon() == btRT.getIcon()){

            btRT.setEnabled(false);

        }else if(setBt_1L.getIcon() == btRC.getIcon()){

            btRC.setEnabled(false);

        }else if(setBt_1L.getIcon() == btRU.getIcon()){

            btRU.setEnabled(false);

        }else if(setBt_1L.getIcon() == btUL.getIcon()){

            btUL.setEnabled(false);

        }else if(setBt_1L.getIcon() == btUC.getIcon()){

            btUC.setEnabled(false);

        }else if(setBt_1L.getIcon() == btUR.getIcon()){

            btUR.setEnabled(false);

        }

        //右上
        if(setBt_1R.getIcon() == btLT.getIcon()){

            btLT.setEnabled(false);

        }else if(setBt_1R.getIcon() == btLC.getIcon()){

            btLC.setEnabled(false);

        }else if(setBt_1R.getIcon() == btLU.getIcon()){

            btLU.setEnabled(false);

        }else if(setBt_1R.getIcon() == btRT.getIcon()){

            btRT.setEnabled(false);

        }else if(setBt_1R.getIcon() == btRC.getIcon()){

            btRC.setEnabled(false);

        }else if(setBt_1R.getIcon() == btRU.getIcon()){

            btRU.setEnabled(false);

        }else if(setBt_1R.getIcon() == btUL.getIcon()){

            btUL.setEnabled(false);

        }else if(setBt_1R.getIcon() == btUC.getIcon()){

            btUC.setEnabled(false);

        }else if(setBt_1R.getIcon() == btUR.getIcon()){

            btUR.setEnabled(false);

        }

        //ど真ん中
        if(setBt_2C.getIcon() == btLT.getIcon()){

            btLT.setEnabled(false);

        }else if(setBt_2C.getIcon() == btLC.getIcon()){

            btLC.setEnabled(false);

        }else if(setBt_2C.getIcon() == btLU.getIcon()){

            btLU.setEnabled(false);

        }else if(setBt_2C.getIcon() == btRT.getIcon()){

            btRT.setEnabled(false);

        }else if(setBt_2C.getIcon() == btRC.getIcon()){

            btRC.setEnabled(false);

        }else if(setBt_2C.getIcon() == btRU.getIcon()){

            btRU.setEnabled(false);

        }else if(setBt_2C.getIcon() == btUL.getIcon()){

            btUL.setEnabled(false);

        }else if(setBt_1R.getIcon() == btUC.getIcon()){

            btUC.setEnabled(false);

        }else if(setBt_1R.getIcon() == btUR.getIcon()){

            btUR.setEnabled(false);

        }

        //下の右
        if(setBt_3L.getIcon() == btLT.getIcon()){

            btLT.setEnabled(false);

        }else if(setBt_3L.getIcon() == btLC.getIcon()){

            btLC.setEnabled(false);

        }else if(setBt_3L.getIcon() == btLU.getIcon()){

            btLU.setEnabled(false);

        }else if(setBt_3L.getIcon() == btRT.getIcon()){

            btRT.setEnabled(false);

        }else if(setBt_3L.getIcon() == btRC.getIcon()){

            btRC.setEnabled(false);

        }else if(setBt_3L.getIcon() == btRU.getIcon()){

            btRU.setEnabled(false);

        }else if(setBt_3L.getIcon() == btUL.getIcon()){

            btUL.setEnabled(false);

        }else if(setBt_3L.getIcon() == btUC.getIcon()){

            btUC.setEnabled(false);

        }else if(setBt_3L.getIcon() == btUR.getIcon()){

            btUR.setEnabled(false);

        }

        //下の右
        if(setBt_3R.getIcon() == btLT.getIcon()){

            btLT.setEnabled(false);

        }else if(setBt_3R.getIcon() == btLC.getIcon()){

            btLC.setEnabled(false);

        }else if(setBt_3R.getIcon() == btLU.getIcon()){

            btLU.setEnabled(false);

        }else if(setBt_3R.getIcon() == btRT.getIcon()){

            btRT.setEnabled(false);

        }else if(setBt_3R.getIcon() == btRC.getIcon()){

            btRC.setEnabled(false);

        }else if(setBt_3R.getIcon() == btRU.getIcon()){

            btRU.setEnabled(false);

        }else if(setBt_3R.getIcon() == btUL.getIcon()){

            btUL.setEnabled(false);

        }else if(setBt_3R.getIcon() == btUC.getIcon()){

            btUC.setEnabled(false);

        }else if(setBt_3R.getIcon() == btUR.getIcon()){

            btUR.setEnabled(false);

        }

    }

    //セットするほうのボタンのアクションリスナーをやめる
    private static void StopActionListener(){

        //setBt_1Lのアクションリスナーを止める
        for(ActionListener al : setBt_1L.getActionListeners()){
            setBt_1L.removeActionListener(al);
        }

        //setBt_1Rのアクションリスナーを止める
        for(ActionListener al : setBt_1R.getActionListeners()){
            setBt_1R.removeActionListener(al);
        }

        //setBt_2Cのアクションリスナーを止める
        for(ActionListener al : setBt_2C.getActionListeners()){
            setBt_2C.removeActionListener(al);
        }

        //setBt_3Lのアクションリスナーを止める
        for(ActionListener al : setBt_3L.getActionListeners()){
            setBt_3L.removeActionListener(al);
        }
        
        //setBt_3Rのアクションリスナーを止める
        for(ActionListener al : setBt_3R.getActionListeners()){
            setBt_3R.removeActionListener(al);
        }

    }

    //セットするほうのボタンのアクションリスナーをはじめる
    private static void StartActionListener(){

        //setBt_1Lのアクションリスナーを始める
        for(ActionListener al : setBt_1L.getActionListeners()){
            setBt_1L.addActionListener(al);
        }

        //setBt_1Rのアクションリスナーを始める
        for(ActionListener al : setBt_1R.getActionListeners()){
            setBt_1R.addActionListener(al);
        }

        //setBt_2Cのアクションリスナーを始める
        for(ActionListener al : setBt_2C.getActionListeners()){
            setBt_2C.addActionListener(al);
        }

        //setBt_3Lのアクションリスナーを始める
        for(ActionListener al : setBt_3L.getActionListeners()){
            setBt_3L.addActionListener(al);
        }

        //setBt_3Rのアクションリスナーを始める
        for(ActionListener al : setBt_3R.getActionListeners()){
            setBt_3R.addActionListener(al);
        }

    }

    //選択用のボタンを有効にする
    private static void TrueButton(){

        btLT.setEnabled(true);
        btLC.setEnabled(true);
        btLT.setEnabled(true);
        btRT.setEnabled(true);
        btRC.setEnabled(true);
        btRU.setEnabled(true);
        btUL.setEnabled(true);
        btUC.setEnabled(true);
        btUR.setEnabled(true);

    }

    //全てのボタンのIconをはずす
    public static void NullAllBtIcon(){

        btLT.setIcon(null);
        btLU.setIcon(null);
        btRT.setIcon(null);
        btRU.setIcon(null);
        btUC.setIcon(null);

        setBt_1L.setIcon(null);
        setBt_1R.setIcon(null);
        setBt_2C.setIcon(null);
        setBt_3L.setIcon(null);
        setBt_3R.setIcon(null);

    }

    //左上を押したとき
    public static void btLT_ActionPerformed(ActionEvent e) {

        //ボタンのアイコンを取得してパンダの画像かを識別

        //btLTにある画像が[0][0]だった時、パンダの左上
        if (btLT.getIcon() == imgIcon[0][0]) {

            System.out.println("btLTでpPan_1Lが押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if (setBt_1L.getIcon() == null) {

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //btLTにある画像が[0][1]だった時、パンダの1行目の真ん中
        }else if (btLT.getIcon() == imgIcon[0][1]) {

                System.out.println("btLTでpPan_1Cを押された");

                //ボタンの表示と非表示の判定
//                noTouchPuzzle();

        //btLTにある画像が[0][2]だった時、パンダの一行目の左
        }else if (btLT.getIcon() == imgIcon[0][2]) {

            System.out.println("btLTでpPan_1Rを押された");

            //もしsetBt_1Rに画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //setBt_1Rのアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Rに画像をセット
                        setBt_1R.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btLTにある画像が[0][3]だった時、パンダの2行目の左
        }else if (btLT.getIcon() == imgIcon[0][3]) {

            System.out.println("btLTでpPan_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btLTにある画像が[0][4]だった時、パンダの1行目の真ん中ドセンター
        }else if (btLT.getIcon() == imgIcon[0][4]) {

            System.out.println("btLTでpPan_2Cを押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btLTにある画像が[0][5]だった時、パンダの2行目の右
        }else if (btLT.getIcon() == imgIcon[0][5]) {

            System.out.println("btLTでpPan_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btLTにある画像が[0][6]だった時、パンダの3行目の左
        }else if (btLT.getIcon() == imgIcon[0][6]) {

            System.out.println("btLTでpPan_3Lを押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //btLTにある画像が[0][7]だった時、パンダの3行目の真ん中
        }else if (btLT.getIcon() == imgIcon[0][7]) {

            System.out.println("btLTでpPan_3Cを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

            //btLTにある画像が[0][8]だった時、パンダの3行目の右
            }else if (btLT.getIcon() == imgIcon[0][8]) {

            System.out.println("btLTでpPan_3Rを押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btLTにある画像が[0][0]だった時、ゴリラの1行目の左上
        if (btLT.getIcon() == imgIcon[1][0]) {

            System.out.println("btLTでpGor_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナー
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btLTにある画像が[1][1]だった時、ゴリラの1行目の真ん中
        }else if (btLT.getIcon() == imgIcon[1][1]) {

            System.out.println("btLTでpGor_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btLTにある画像が[1][2]だった時、ゴリラの1行目の右
        }else if (btLT.getIcon() == imgIcon[1][2]) {

            System.out.println("btLTでpGor_1Rを押された");

            //もしsetBt_1Rに画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //setBt_1Rのアクションリスナー
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btLTにある画像が[1][3]だった時、ゴリラの2行目の左
        }else if (btLT.getIcon() == imgIcon[1][3]) {

            System.out.println("btLTでpGor_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btLTにある画像が[1][4]だった時、ゴリラの2行目の真ん中
        }else if (btLT.getIcon() == imgIcon[1][4]) {

            System.out.println("btLTでpGor_2Cを押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナー
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btLTにある画像が[1][5]だった時、ゴリラの2行目の右
        }else if (btLT.getIcon() == imgIcon[1][5]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btLTにある画像が[1][6]だった時、ゴリラの3行目の左
        }else if (btLT.getIcon() == imgIcon[1][6]) {

            System.out.println("btLTでpGor_3Lを押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナー
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //btLTにある画像が[1][7]だった時、ゴリラの3行目の真ん中
        }else if (btLT.getIcon() == imgIcon[1][7]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btLTにある画像が[1][8]だった時、ゴリラの3行目の右
        }else if (btLT.getIcon() == imgIcon[1][8]) {

            System.out.println("btLTでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナー
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(false);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

    }

    //右の一番上のボタンを押したとき
    public static void btRT_ActionPerformed(ActionEvent e) {

        //btRTにある画像が[0][0]だった時、パンダの1行目の左
        if (btRT.getIcon() == imgIcon[0][0]) {

            System.out.println("btRTでpPan_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナー
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[0][1]だった時、パンダの1行目の真ん中
        }else if (btRT.getIcon() == imgIcon[0][1]) {

            System.out.println("btLTでpPan_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btRTにある画像が[0][2]だった時、パンダの1行目の右
        }else if (btRT.getIcon() == imgIcon[0][2]) {

            System.out.println("btRTでpPan_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナー
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[0][3]だった時、2行目の左
        }else if (btRT.getIcon() == imgIcon[0][3]) {

            System.out.println("btLTでpPan_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btRTにある画像が[0][4]だった時、2行目の真ん中、ドセンター
        }else if (btRT.getIcon() == imgIcon[0][4]) {

            System.out.println("btRTでpPan_2Cを押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナー
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[0][5]だった時、パンダの2行目の右
        }else if (btRT.getIcon() == imgIcon[0][5]) {

            System.out.println("btLTでpPan_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btRTにある画像が[0][6]だった時、パンダの3行目の左
        }else if (btRT.getIcon() == imgIcon[0][6]) {

            System.out.println("btRTでpPan_3Lを押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナー
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //btRTにある画像が[0][7]だった時、パンダの3行目の真ん中
        }else if (btRT.getIcon() == imgIcon[0][7]) {

            System.out.println("btLTでpPan_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btRTにある画像が[0][8]だった時、パンダの3行目の右
        }else if (btRT.getIcon() == imgIcon[0][8]) {

            System.out.println("btRTでpPan_3Rを押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナー
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btRTにある画像が[1][0]だった時、ゴリラの1行目の左
        if (btRT.getIcon() == imgIcon[1][0]) {

            System.out.println("btRTでpGor_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナー
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[1][1]だった時
        }else if (btRT.getIcon() == imgIcon[1][1]) {

            System.out.println("btLTでpGor_1Cを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

        //btRTにある画像が[1][2]だった時、ゴリラの1行目の右
        }else if (btRT.getIcon() == imgIcon[1][2]) {

            System.out.println("btRTでpGor_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナー
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[1][3]だった時、ゴリラの2行目の左
        }else if (btRT.getIcon() == imgIcon[1][3]) {

            System.out.println("btLTでpGor_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btRTにある画像が[1][4]だった時、2行目の真ん中
        }else if (btRT.getIcon() == imgIcon[1][4]) {

            System.out.println("btRTでpGor_2Cを押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[1][5]だった時、ゴリラの2行目の右
        }else if (btRT.getIcon() == imgIcon[1][5]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btRTにある画像が[1][6]だった時、3行目の左
        }else if (btRT.getIcon() == imgIcon[1][6]) {

            System.out.println("btRTでpGor_3Lを押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //btRTにある画像が[1][7]だった時、ゴリラの
        }else if (btRT.getIcon() == imgIcon[1][7]) {

            System.out.println("btLTでpGor_3Cを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

            //btRTにある画像が[1][6]だった時
        }else if (btRT.getIcon() == imgIcon[1][8]) {

            System.out.println("btRTでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(false);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

    }

    //下の真ん中のボタンを押したとき
    public static void btUC_ActionPerformed(ActionEvent e){

        //btUCにある画像が[0][0]だった時、パンダの左上
        if (btUC.getIcon() == imgIcon[0][0]) {

            System.out.println("btUCでpPan_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[0][1]だった時、パンダの1C
        }else if (btUC.getIcon() == imgIcon[0][1]) {

            System.out.println("btUCでpPan_1Cを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

        //btUCにある画像が[0][2]だった時、パンダの1R
        }else if (btUC.getIcon() == imgIcon[0][2]) {

            System.out.println("btUCでpPan_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][3]だった時、ぱんだの2L
        }else if (btUC.getIcon() == imgIcon[0][3]) {

            System.out.println("btUCでpPan_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btUCにある画像が[0][4]だった時、パンダの2C
        }else if (btUC.getIcon() == imgIcon[0][4]) {

            System.out.println("btUCでpPan_2Cを押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btUCにある画像が[0][5]だった時、パンダの2R
        }else if (btUC.getIcon() == imgIcon[0][5]) {

            System.out.println("btUCでpPan_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btUCにある画像が[0][6]だった時
        }else if (btUC.getIcon() == imgIcon[0][6]) {

            System.out.println("btUCでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //btRTにある画像が[01][3]だった時
        }else if (btUC.getIcon() == imgIcon[0][7]) {

            System.out.println("btLTでpPan_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btUCにある画像が[0][8]だった時
        }else if (btUC.getIcon() == imgIcon[0][8]) {

            System.out.println("btUCでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btUCにある画像が[1][0]だった時、ゴリラの１L
        if (btUC.getIcon() == imgIcon[1][0]) {

            System.out.println("btUCでpGor_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナー
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[1][1]だった時、ゴリラ1C
        }else if (btUC.getIcon() == imgIcon[1][1]) {

            System.out.println("btLTでpGor_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btUCにある画像が[1][2]だった時、ゴリラの1R
        }else if (btUC.getIcon() == imgIcon[1][2]) {

            System.out.println("btUCでpGor_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナー
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[1][3]だった時
        }else if (btUC.getIcon() == imgIcon[1][3]) {

            System.out.println("btLTでpGor_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btUCにある画像が[1][4]だった時
        }else if (btUC.getIcon() == imgIcon[1][4]) {

            System.out.println("btUCでpGor_2Cを押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナー
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時、ゴリラの２R
        }else if (btUC.getIcon() == imgIcon[1][5]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

        //btUCにある画像が[1][6]だった時
        }else if (btUC.getIcon() == imgIcon[1][6]) {

            System.out.println("btUCでpGor_3Lを押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //btRTにある画像が[01][7]だった時
        }else if (btUC.getIcon() == imgIcon[1][7]) {

            System.out.println("btLTでpGor_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btUCにある画像が[1][8]だった時、ごりの３R
        }else if (btUC.getIcon() == imgIcon[1][8]) {

            System.out.println("btUCでpGor_3Rを押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(false);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

    }

    //ど真ん中が押された時
    public static void btLU_ActionPerformed(ActionEvent e){

        //btLUにある画像が[0][0]だった時、パンダ１L
        if (btLU.getIcon() == imgIcon[0][0]) {

            System.out.println("btLUでpPan_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[0][1]だった時
        }else if (btLU.getIcon() == imgIcon[0][1]) {

            System.out.println("btLTでpPan_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btLUにある画像が[0][2]だった時
        }else if (btLU.getIcon() == imgIcon[0][2]) {

            System.out.println("btLUでpPan_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナー
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[0][3]だった時
        }else if (btLU.getIcon() == imgIcon[0][3]) {

            System.out.println("btLTでpPan_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btLUにある画像が[0][4]だった時
        }else if (btLU.getIcon() == imgIcon[0][4]) {

            System.out.println("btLUでpPan_2Cを押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btLU.getIcon() == imgIcon[0][5]) {

            System.out.println("btLTでpPan_2Rを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

            //btLUにある画像が[0][6]だった時
        }else if (btLU.getIcon() == imgIcon[0][6]) {

            System.out.println("btLUでpPan_3Lを押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //btRTにある画像が[0][7]だった時
        }else if (btLU.getIcon() == imgIcon[0][7]) {

            System.out.println("btLTでpPan_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

        //btLUにある画像が[0][8]だった時
        }else if (btLU.getIcon() == imgIcon[0][8]) {

            System.out.println("btLUでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btLUにある画像が[1][0]だった時
        if (btLU.getIcon() == imgIcon[1][0]) {

            System.out.println("btLUでpGor_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナー
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[1][1]だった時
        }else if (btLU.getIcon() == imgIcon[1][1]) {

            System.out.println("btLTでpGor_1Cを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

        //btLUにある画像が[1][2]だった時
        }else if (btLU.getIcon() == imgIcon[1][2]) {

            System.out.println("btLUでpGor_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナー
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[1][3]だった時
        }else if (btLU.getIcon() == imgIcon[1][3]) {

            System.out.println("btLTでpGor_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btLUにある画像が[1][4]だった時
        }else if (btLU.getIcon() == imgIcon[1][4]) {

            System.out.println("btLUでpGor_2Cを押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btLU.getIcon() == imgIcon[1][5]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

            //btLUにある画像が[1][6]だった時
        }else if (btLU.getIcon() == imgIcon[1][6]) {

            System.out.println("btLUでpGor_3Lを押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[1][7]だった時
        }else if (btLU.getIcon() == imgIcon[1][7]) {

            System.out.println("btLTでpGor_3Cを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

            //btLUにある画像が[1][8]だった時
        }else if (btLU.getIcon() == imgIcon[1][8]) {

            System.out.println("btLUでpGor_3Rを押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(false);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

    }

    //右の下のボタンを押された時
    public static void btRU_ActionPerformed(ActionEvent e){

        //btRUにある画像が[0][0]だった時
        if (btRU.getIcon() == imgIcon[0][0]) {

            System.out.println("btRUでpPan_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[0][1]だった時
        }else if (btRU.getIcon() == imgIcon[0][1]) {

            System.out.println("btLTでpPan_1Cを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

            //btRUにある画像が[0][2]だった時
        }else if (btRU.getIcon() == imgIcon[0][2]) {

            System.out.println("btRUでpPan_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btRU.getIcon() == imgIcon[0][3]) {

            System.out.println("btLTでpPan_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[0][4]だった時
        }else if (btRU.getIcon() == imgIcon[0][4]) {

            System.out.println("btRUでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btRU.getIcon() == imgIcon[0][5]) {

            System.out.println("btLTでpPan_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[0][6]だった時
        }else if (btRU.getIcon() == imgIcon[0][6]) {

            System.out.println("btRUでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[01][7]だった時
        }else if (btRU.getIcon() == imgIcon[0][7]) {

            System.out.println("btLTでpPan_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[0][8]だった時
        }else if (btRU.getIcon() == imgIcon[0][8]) {

            System.out.println("btRUでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btRUにある画像が[1][0]だった時
        if (btRU.getIcon() == imgIcon[1][0]) {

            System.out.println("btRUでpGor_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[1][1]だった時
        }else if (btRU.getIcon() == imgIcon[1][1]) {

            System.out.println("btLTでpGor_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[1][2]だった時
        }else if (btRU.getIcon() == imgIcon[1][2]) {

            System.out.println("btRUでpGor_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btRU.getIcon() == imgIcon[1][3]) {

            System.out.println("btLTでpGor_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[1][4]だった時
        }else if (btRU.getIcon() == imgIcon[1][4]) {

            System.out.println("btRUでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btRU.getIcon() == imgIcon[1][5]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[1][6]だった時
        }else if (btRU.getIcon() == imgIcon[1][6]) {

            System.out.println("btRUでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[01][5]だった時
        }else if (btRU.getIcon() == imgIcon[1][7]) {

            System.out.println("btLTでpGor_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[1][8]だった時
        }else if (btRU.getIcon() == imgIcon[1][8]) {

            System.out.println("btRUでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(false);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

    }

    //下の左を押したとき
    public static void btUL_ActionPerformed(ActionEvent e){

        //btRUにある画像が[0][0]だった時
        if (btUL.getIcon() == imgIcon[0][0]) {

            System.out.println("btULでpPan_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btUL.getIcon() == imgIcon[0][1]) {

            System.out.println("btLTでpPan_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btULにある画像が[0][2]だった時
        }else if (btUL.getIcon() == imgIcon[0][2]) {

            System.out.println("btULでpPan_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btUL.getIcon() == imgIcon[0][3]) {

            System.out.println("btLTでpPan_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[0][4]だった時
        }else if (btUL.getIcon() == imgIcon[0][4]) {

            System.out.println("btRUでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btUL.getIcon() == imgIcon[0][5]) {

            System.out.println("btLTでpPan_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btULにある画像が[0][6]だった時
        }else if (btUL.getIcon() == imgIcon[0][6]) {

            System.out.println("btULでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[01][5]だった時
        }else if (btUL.getIcon() == imgIcon[0][7]) {

            System.out.println("btLTでpPan_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btULにある画像が[0][8]だった時
        }else if (btUL.getIcon() == imgIcon[0][8]) {

            System.out.println("btULでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btULにある画像が[1][0]だった時
        if (btUL.getIcon() == imgIcon[1][0]) {

            System.out.println("btULでpGor_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[1][1]だった時
        }else if (btUL.getIcon() == imgIcon[1][1]) {

            System.out.println("btLTでpGor_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btULにある画像が[1][2]だった時
        }else if (btUL.getIcon() == imgIcon[1][2]) {

            System.out.println("btULでpGor_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btUL.getIcon() == imgIcon[1][3]) {

            System.out.println("btLTでpGor_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btULにある画像が[1][4]だった時
        }else if (btUL.getIcon() == imgIcon[1][4]) {

            System.out.println("btULでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btUL.getIcon() == imgIcon[1][5]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
            noTouchPuzzle();

            //btULにある画像が[1][6]だった時
        }else if (btUL.getIcon() == imgIcon[1][6]) {

            System.out.println("btULでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[01][5]だった時
        }else if (btUL.getIcon() == imgIcon[1][7]) {

            System.out.println("btLTでpGor_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btULにある画像が[1][6]だった時
        }else if (btUL.getIcon() == imgIcon[1][8]) {

            System.out.println("btULでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(false);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

    }


    //右の左下
    public static void btLC_ActionPerformed(ActionEvent e){

        //btRUにある画像が[0][0]だった時
        if (btLC.getIcon() == imgIcon[0][0]) {

            System.out.println("btLCでpPan_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[0][1]だった時
        }else if (btLC.getIcon() == imgIcon[0][1]) {

            System.out.println("btLTでpPan_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btLCにある画像が[0][2]だった時
        }else if (btLC.getIcon() == imgIcon[0][2]) {

            System.out.println("btLCでpPan_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[0][3]だった時
        }else if (btLC.getIcon() == imgIcon[0][3]) {

            System.out.println("btLTでpPan_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[0][4]だった時
        }else if (btLC.getIcon() == imgIcon[0][4]) {

            System.out.println("btRUでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btLC.getIcon() == imgIcon[0][5]) {

            System.out.println("btLTでpPan_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btLCにある画像が[0][6]だった時
        }else if (btLC.getIcon() == imgIcon[0][6]) {

            System.out.println("btLCでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[0][7]だった時
        }else if (btLC.getIcon() == imgIcon[0][7]) {

            System.out.println("btLTでpPan_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btLCにある画像が[0][8]だった時
        }else if (btLC.getIcon() == imgIcon[0][8]) {

            System.out.println("btLCでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btLCにある画像が[1][0]だった時
        if (btLC.getIcon() == imgIcon[1][0]) {

            System.out.println("btLCでpGor_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btLC.getIcon() == imgIcon[1][1]) {

            System.out.println("btLTでpGor_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btLCにある画像が[1][2]だった時
        }else if (btLC.getIcon() == imgIcon[1][2]) {

            System.out.println("btLCでpGor_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btLC.getIcon() == imgIcon[1][3]) {

            System.out.println("btLTでpGor_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btLCにある画像が[1][4]だった時
        }else if (btLC.getIcon() == imgIcon[1][4]) {

            System.out.println("btLCでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btLC.getIcon() == imgIcon[1][5]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btLCにある画像が[1][6]だった時
        }else if (btLC.getIcon() == imgIcon[1][6]) {

            System.out.println("btLCでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[01][5]だった時
        }else if (btLC.getIcon() == imgIcon[1][7]) {

            System.out.println("btLTでpGor_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btLCにある画像が[1][6]だった時
        }else if (btLC.getIcon() == imgIcon[1][8]) {

            System.out.println("btLCでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

    }

    //右の真ん中を押したとき
    public static void btRC_ActionPerformed(ActionEvent e){

        //btRUにある画像が[0][0]だった時
        if (btRC.getIcon() == imgIcon[0][0]) {

            System.out.println("btRCでpPan_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[0][1]だった時
        }else if (btRC.getIcon() == imgIcon[0][1]) {

            System.out.println("btLTでpPan_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRCにある画像が[0][2]だった時
        }else if (btRC.getIcon() == imgIcon[0][2]) {

            System.out.println("btRCでpPan_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btRC.getIcon() == imgIcon[0][3]) {

            System.out.println("btLTでpPan_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[0][4]だった時
        }else if (btRC.getIcon() == imgIcon[0][4]) {

            System.out.println("btRUでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btRC.getIcon() == imgIcon[0][5]) {

            System.out.println("btLTでpPan_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRCにある画像が[0][6]だった時
        }else if (btRC.getIcon() == imgIcon[0][6]) {

            System.out.println("btRCでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[01][5]だった時
        }else if (btRC.getIcon() == imgIcon[0][7]) {

            System.out.println("btLTでpPan_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRCにある画像が[0][8]だった時
        }else if (btRC.getIcon() == imgIcon[0][8]) {

            System.out.println("btRCでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btRCにある画像が[1][0]だった時
        if (btRC.getIcon() == imgIcon[1][0]) {

            System.out.println("btRCでpGor_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[1][1]だった時
        }else if (btRC.getIcon() == imgIcon[1][1]) {

            System.out.println("btLTでpGor_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRCにある画像が[1][2]だった時
        }else if (btRC.getIcon() == imgIcon[1][2]) {

            System.out.println("btRCでpGor_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btRC.getIcon() == imgIcon[1][3]) {

            System.out.println("btLTでpGor_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRCにある画像が[1][4]だった時
        }else if (btRC.getIcon() == imgIcon[1][4]) {

            System.out.println("btRCでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btRC.getIcon() == imgIcon[1][5]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRCにある画像が[1][6]だった時
        }else if (btRC.getIcon() == imgIcon[1][6]) {

            System.out.println("btRCでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[01][5]だった時
        }else if (btRC.getIcon() == imgIcon[1][7]) {

            System.out.println("btLTでpGor_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRCにある画像が[1][6]だった時
        }else if (btRC.getIcon() == imgIcon[1][8]) {

            System.out.println("btRCでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

    }

    //下の右を押したとき
    public static void btUR_ActionPerformed(ActionEvent e){

        //btRUにある画像が[0][0]だった時
        if (btUR.getIcon() == imgIcon[0][0]) {

            System.out.println("btURでpPan_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[0][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][1]だった時
        }else if (btUR.getIcon() == imgIcon[0][1]) {

            System.out.println("btLTでpPan_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btURにある画像が[0][2]だった時
        }else if (btUR.getIcon() == imgIcon[0][2]) {

            System.out.println("btURでpPan_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[0][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(true);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(false);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btUR.getIcon() == imgIcon[0][3]) {

            System.out.println("btLTでpPan_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btRUにある画像が[0][4]だった時
        }else if (btUR.getIcon() == imgIcon[0][4]) {

            System.out.println("btRUでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[0][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btUR.getIcon() == imgIcon[0][5]) {

            System.out.println("btLTでpPan_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btURにある画像が[0][6]だった時
        }else if (btUR.getIcon() == imgIcon[0][6]) {

            System.out.println("btURでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[0][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[01][5]だった時
        }else if (btUR.getIcon() == imgIcon[0][7]) {

            System.out.println("btLTでpPan_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btURにある画像が[0][8]だった時
        }else if (btUR.getIcon() == imgIcon[0][8]) {

            System.out.println("btURでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[0][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

        //ボタンのアイコンを取得してゴリラの画像かを識別
        //btURにある画像が[1][0]だった時
        if (btUR.getIcon() == imgIcon[1][0]) {

            System.out.println("btURでpGor_1Lを押された");

            //もしsetBt_1Lに画像が入っていなかったら
            if(setBt_1L.getIcon() == null){

                //setBt_1Lのアクションリスナーを呼び出す
                setBt_1L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_1Lに画像をセット
                        setBt_1L.setIcon(imgIcon[1][0]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btUR.getIcon() == imgIcon[1][1]) {

            System.out.println("btLTでpGor_1Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btURにある画像が[1][2]だった時
        }else if (btUR.getIcon() == imgIcon[1][2]) {

            System.out.println("btURでpGor_1Rを押された");

            //もしSetBtR1に画像が入っていなかったら
            if(setBt_1R.getIcon() == null){

                //SetBtR1のアクションリスナーを呼び出す
                setBt_1R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBtR1に画像をセット
                        setBt_1R.setIcon(imgIcon[1][2]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_1Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

        //btRTにある画像が[01][5]だった時
        }else if (btUR.getIcon() == imgIcon[1][3]) {

            System.out.println("btLTでpGor_2Lを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btURにある画像が[1][4]だった時
        }else if (btUR.getIcon() == imgIcon[1][4]) {

            System.out.println("btURでpHum3を押された");

            //もしsetBt_2Cに画像が入っていなかったら
            if(setBt_2C.getIcon() == null){

                //setBt_2Cのアクションリスナーを呼び出す
                setBt_2C.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_2Cに画像をセット
                        setBt_2C.setIcon(imgIcon[1][4]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_2Cが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if(setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

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

            //btRTにある画像が[01][5]だった時
        }else if (btUR.getIcon() == imgIcon[1][5]) {

            System.out.println("btLTでpGor_2Rを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btURにある画像が[1][6]だった時
        }else if (btUR.getIcon() == imgIcon[1][6]) {

            System.out.println("btURでpHum4を押された");

            //もしsetBt_3Lに画像が入っていなかったら
            if(setBt_3L.getIcon() == null) {

                //setBt_3Lのアクションリスナーを呼び出す
                setBt_3L.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Lに画像をセット
                        setBt_3L.setIcon(imgIcon[1][6]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Lが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

            //btRTにある画像が[01][5]だった時
        }else if (btUR.getIcon() == imgIcon[1][7]) {

            System.out.println("btLTでpGor_3Cを押された");

            //ボタンの表示と非表示の判定
//            noTouchPuzzle();

            //btURにある画像が[1][6]だった時
        }else if (btUR.getIcon() == imgIcon[1][8]) {

            System.out.println("btURでpHum4を押された");

            //もしsetBt_3Rに画像が入っていなかったら
            if (setBt_3R.getIcon() == null) {

                //setBt_3Rのアクションリスナーを呼び出す
                setBt_3R.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        //setBt_3Rに画像をセット
                        setBt_3R.setIcon(imgIcon[1][8]);

                        //押したボタン以外は有効
                        btLT.setEnabled(true);
                        btLC.setEnabled(true);
                        btLU.setEnabled(true);
                        btRT.setEnabled(true);
                        btRC.setEnabled(false);
                        btRU.setEnabled(true);
                        btUL.setEnabled(true);
                        btUC.setEnabled(true);
                        btUR.setEnabled(true);

                        //ボタンの表示と非表示の判定
                        noTouchPuzzle();

                        System.out.println("--- setBt_3Rが押された時 ---");
                        System.out.println(setBt_1L.getIcon());
                        System.out.println(setBt_1R.getIcon());
                        System.out.println(setBt_2C.getIcon());
                        System.out.println(setBt_3L.getIcon());
                        System.out.println(setBt_3R.getIcon());

                        //全部そろった（内側のボタン全てに画像があったとき）
                        if (setBt_1L.getIcon() != null && setBt_1R.getIcon() != null && setBt_2C.getIcon() != null
                                && setBt_3L.getIcon() != null && setBt_3R.getIcon() != null) {

                            System.out.println("全部そろった");

                            //アクションリスナーを止めるメソッド
                            StopActionListener();

                            //スコアについて
                            score++;//スコア＋１
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

    }

    //imgIconの配列に画像を入れる
    public static void SetImageIcon(){

        //パンダ
        imgIcon[0][0] = ChangeImageSize(pPan_1L);
        imgIcon[0][1] = ChangeImageSize(pPan_1C);
        imgIcon[0][2] = ChangeImageSize(pPan_1R);
        imgIcon[0][3] = ChangeImageSize(pPan_2L);
        imgIcon[0][4] = ChangeImageSize(pPan_2C);
        imgIcon[0][5] = ChangeImageSize(pPan_2R);
        imgIcon[0][6] = ChangeImageSize(pPan_3L);
        imgIcon[0][7] = ChangeImageSize(pPan_3C);
        imgIcon[0][8] = ChangeImageSize(pPan_3R);
        //ゴリラ
        imgIcon[1][0] = ChangeImageSize(pGor_1L);
        imgIcon[1][1] = ChangeImageSize(pGor_1C);
        imgIcon[1][2] = ChangeImageSize(pGor_1R);
        imgIcon[1][3] = ChangeImageSize(pGor_2L);
        imgIcon[1][4] = ChangeImageSize(pGor_2C);
        imgIcon[1][5] = ChangeImageSize(pGor_2R);
        imgIcon[1][6] = ChangeImageSize(pGor_3L);
        imgIcon[1][7] = ChangeImageSize(pGor_3C);
        imgIcon[1][8] = ChangeImageSize(pGor_3R);

    }

    //画像のサイズを変更
    public static ImageIcon ChangeImageSize(ImageIcon imageIcon){

        getImg = imageIcon.getImage();
        newImg = getImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;

    }

}
