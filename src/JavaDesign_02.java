import javax.swing.*;
import java.awt.*;

public class JavaDesign_02{
    /*    private double x=0;
        private double y=0;
        private int r=0;
        private double degree = Math.PI / 3;
        private double speed=10;

        public JavaDesign_02(){
            JFrame app=new JFrame("小球运动程序");
            app.setVisible(true);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setSize(520, 120);
            Container c = app.getContentPane();
            c.setLayout(new GridLayout(4, 4));
            JTextField[] t = { new JTextField("小球半径", 8), new JTextField(15), new JTextField("小球初速度", 8),
                    new JTextField(15), new JTextField("小球加速度", 8), new JTextField(15) };
            t[0].setEditable(false);
            t[2].setEditable(false);
            t[4].setEditable(false);
            for (int i = 0; i < 6; i++) {
                c.add(t[i]);
            }
            c.add(new JavaDesign_02());
            app.pack();
        }
        public void paint(Graphics g){
            g.drawOval((int)x,(int)y,r,r);
            x = x + speed * Math.cos(degree);
            y = y + speed * Math.sin(degree);
            if (y > 800-r || y < r)
                degree = -degree;
            if (x > 800+r || x < r)
                degree = Math.PI - degree;
            speed=speed-0.05;
        }

        public void run(){
            while (true) {
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame f = new JFrame();
        Circle circle = new Circle();
        circle.setSize(100,100);
        f.setVisible(true);
        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        Container c = f.getContentPane();
        c.setLayout(new GridLayout(7, 6));
        JTextField[] t = { new JTextField("小球半径", 8), new JTextField(15), new JTextField("小球初速度", 8),
                new JTextField(15), new JTextField("小球加速度", 8), new JTextField(15) };
        t[0].setEditable(false);
        t[2].setEditable(false);
        t[4].setEditable(false);
        for (int i = 0; i < 6; i++) {
            c.add(t[i]);
        }
        c.add(circle);
        Thread th = new Thread(circle);
        th.start();
    }
}

class Circle extends JPanel implements Runnable {

    private double x = 0;
    private double y = 0;
    private int r = 10;
    double degree = Math.PI / 3;
    double speed=1;
    boolean right=true;

    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval((int)x,(int) y, r, r);
        //x += 10;
        //r += 1;
        /*x = x + speed * Math.cos(degree);
        y = y + speed * Math.sin(degree);
        if (y > 10000-r || y < r)
            degree = -degree;
        if (x > 10000+r || x < r)
            degree = Math.PI - degree;*/

        if(right)
            x+=10;
        else
            x-=10;
        if(x>800-2*r)
            right=false;
        if(x<r)
            right=true;
    }
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}