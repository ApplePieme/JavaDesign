package javaDesign_02;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BallListener extends MouseAdapter {
    private double x;
    private double y;
    private int r = 20;
    private BallFrame ballFrame;
    private ArrayList list;

    public BallListener(BallFrame ballFrame, ArrayList<Ball> list) {
        this.ballFrame = ballFrame;   //获取面板对象
        this.list = list;     //获取list
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        super.mouseClicked(e);
        x = 500 * Math.random();    //每次点击鼠标，位置随机
        y = 500 * Math.random();
        move(x, y);      //每点一次鼠标，调用一次move方法
    }

    public void move(double x, double y) {
        Ball ball = new Ball(x, y, r, ballFrame);    //创建小球对象，传入坐标x,y小球半径r和面板对象
        list.add(ball);     //将小球对象添加到list中
        Thread thread = new Thread(ball);     //创建线程对象
        thread.start();     //启动线程
    }
}
