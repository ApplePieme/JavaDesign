package javaDesign_02;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallFrame extends JPanel {
    private ArrayList<Ball> list = new ArrayList<Ball>();   //用来存放Ball类型的线程对象
    private JTextField[] t = {new JTextField("小球半径", 8), new JTextField(15), new JTextField("小球初速度", 8),
            new JTextField(15), new JTextField("小球加速度", 8), new JTextField(15)};//用数组存放创建的文本框

    public void showUI() {
        JFrame frame = new JFrame("小球运动程序");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        Container container = frame.getContentPane();     //创建一个容器
        container.setLayout(new BorderLayout());      //容器设置为边界布局
        JPanel panel = new JPanel();      //再创建一个面板，用来放文本框
        panel.setLayout(new GridLayout(3, 6));      //面板的布局设置为网格布局方便放置文本框
        t[0].setEditable(false);    //把带字的文本框设置为不可编辑
        t[2].setEditable(false);
        t[4].setEditable(false);
        container.add(panel, BorderLayout.NORTH);    //把用来放文本框的面板放在容器的北部
        container.add(this, BorderLayout.CENTER);    //把该类的对象面板放在容器的中部
        for (int i = 0; i < t.length; i++) {    //把文本框添加到面板中
            panel.add(t[i]);
        }
        BallListener listener = new BallListener(this, list, t);  //创建监听类的对象，把面板与list传过去
        this.addMouseListener(listener);    //为面板添加鼠标事件监听器
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < list.size(); i++) {     //小球个数为list的长度
            Ball ball = (Ball) list.get(i);    //获取小球对象
            double x = ball.getX();   //获取坐标x,y和小球的半径r
            double y = ball.getY();
            int r = ball.getR();
            g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);  //画实心圆
        }
    }

    public static void main(String[] args) {
        BallFrame ballFrame = new BallFrame();
        ballFrame.showUI();
    }

}
