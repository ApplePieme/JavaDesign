package javaDesign_02;

public class Ball implements Runnable {
    private double x;
    private double y;
    private int r;
    private int moveX = 20;    //小球初速度
    private int moveY = moveX;
    private int a = 10;   //小球加速度
    private BallFrame ballFrame;    //面板对象

    public Ball(double x, double y, int r, BallFrame ballFrame) {   //构造方法，获取坐标x,y小球半径r和面板
        this.x = x;
        this.y = y;
        this.r = r;
        this.ballFrame = ballFrame;
    }


    public void run() {
        drawBall();
    }

    public void drawBall() {     //每个线程的画小球的方法
        while (true) {
            ballFrame.repaint();
            if (x + r > ballFrame.getWidth() || x - r < 0) {    //碰到边缘后速度方向变为原来的反方向
                moveX = -moveX;
            }
            if (y + r > ballFrame.getHeight() || y - r < 0) {
                moveY = -moveY;
            }
            x += moveX;   //更新移动后的坐标
            y += moveY;
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
