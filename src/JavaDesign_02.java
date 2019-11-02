import javax.swing.*;
import java.awt.*;

public class JavaDesign_02 {

    public void launchFrame(Circle c) {
        JFrame frame = new JFrame("小球发射程序");
        frame.setSize(520, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 6));
        JTextField[] t = {new JTextField("小球半径", 8), new JTextField(15), new JTextField("小球初速度", 8),
                new JTextField(15), new JTextField("小球加速度", 8), new JTextField(15)};
        t[0].setEditable(false);
        t[2].setEditable(false);
        t[4].setEditable(false);
        container.add(panel, BorderLayout.NORTH);
        container.add(c, BorderLayout.CENTER);
        for (int i = 0; i < 6; i++) {
            panel.add(t[i]);
        }
        frame.setVisible(true);
        Thread thread = new Thread(c);
        thread.start();

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JavaDesign_02 javaDesign_02 = new JavaDesign_02();
        Circle circle = new Circle();
        javaDesign_02.launchFrame(circle);
    }
}

class Circle extends JPanel implements Runnable {

    private double x = 0;
    private double y = 0;
    private int r = 10;
    double degree = Math.PI / 3;
    double speed = 1;
    boolean right = true;

    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval((int) x, (int) y, r, r);
        //x += 10;
        //r += 1;
        /*x = x + speed * Math.cos(degree);
        y = y + speed * Math.sin(degree);
        if (y > 10000-r || y < r)
            degree = -degree;
        if (x > 10000+r || x < r)
            degree = Math.PI - degree;*/

        if (right)
            x += 10;
        else
            x -= 10;
        if (x > 800 - 2 * r)
            right = false;
        if (x < r)
            right = true;

        if (right)
            y += 10;
        else
            y -= 10;
        if (y > 800 - 2 * r)
            right = false;
        if (y < r)
            right = true;
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