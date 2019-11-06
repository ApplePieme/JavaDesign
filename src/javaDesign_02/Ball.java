package javaDesign_02;

public class Ball implements Runnable {
    private double x;
    private double y;
    private int r;
    private int moveX;    //小球初速度
    private int moveY;
    private int a;   //小球加速度
    private BallFrame ballFrame;    //面板对象
    private boolean flagX = true;   //创建两个boolean类型来分别控制小球x轴和y轴速度的方向
    private boolean flagY = true;

    public Ball(double x, double y, int r, int moveX, int a, BallFrame ballFrame) {   //构造方法，获取坐标x,y小球半径r和面板
        this.x = x;
        this.y = y;
        this.r = r;
        this.moveX = moveX;
        this.moveY = moveX;
        this.a = a;
        this.ballFrame = ballFrame;
    }


    public void run() {
        drawBall();
    }

    public void drawBall() {     //每个线程的画小球的方法
        while (true) {
            ballFrame.repaint();
            if (flagX) {    //若flagX为true则小球在x轴正向移动
                x += moveX;
                if (moveX > 0)      //速度不能为负
                    moveX += a;
                else
                    moveX = 0;
            } else {    //反之则小球在x轴负向移动
                x -= moveX;
                if (moveX > 0)
                    moveX += a;
                else
                    moveX = 0;
            }
            if (x + r > ballFrame.getWidth()) {     //若小球碰到窗口右边界，则flagX变为false
                flagX = false;
            }
            if (x - r < 0) {        //若小球碰到窗口左边界，则flagX变为true
                flagX = true;
            }
            if (flagY) {    //若flagY为true则小球在y轴正向移动
                y += moveY;
                if (moveY > 0)
                    moveY += a;
                else
                    moveY = 0;
            } else {    //反之则小球在y轴负向移动
                y -= moveY;
                if (moveY > 0)
                    moveY += a;
                else
                    moveY = 0;
            }
            if (y + r > ballFrame.getWidth()) {     //若小球碰到窗口下边界，则flagY变为false
                flagY = false;
            }
            if (y - r < 0) {        //若小球碰到窗口上边界，则flagY变为true
                flagY = true;
            }
            try {
                Thread.sleep(60);   //让线程休眠一段时间，让小球运动较为平稳
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }
}
