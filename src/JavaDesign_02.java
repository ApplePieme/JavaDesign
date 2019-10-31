import javax.swing.*;
import java.awt.*;

public class JavaDesign_02 {
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

//    public void launchFrame(){
//        JFrame f = new JFrame("小球运动程序");
//        f.setVisible(true);
//        f.setSize(800, 800);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setLocationRelativeTo(null);
//        Container container = f.getContentPane();
//    }

    public void launchFrame(Circle c) {
        JFrame frame = new JFrame("小球运动程序");
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        Container container = frame.getContentPane();
        GridBagLayout gridBagLayout = new GridBagLayout();
        container.setLayout(gridBagLayout);
        int[] gridx = {0, 0, 0, 1, 1, 1};
        int[] gridy = {0, 1, 2, 0, 1, 2};
        int[] gridwidth = {1, 1, 1, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER};
        int[] gridheight = {1, 1, 1, 1, 1, 1};
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JTextField[] textFields = {new JTextField("小球半径", 8), new JTextField("小球初速度", 8), new JTextField("小球加速度", 8),
                new JTextField(10), new JTextField(10), new JTextField(10)};
        textFields[0].setEditable(false);
        textFields[1].setEditable(false);
        textFields[2].setEditable(false);
        for (int i = 0; i < gridx.length; i++) {
            gridBagConstraints.gridx = gridx[i];
            gridBagConstraints.gridy = gridy[i];
            gridBagConstraints.gridwidth = gridwidth[i];
            gridBagConstraints.gridheight = gridheight[i];
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagLayout.setConstraints(textFields[i], gridBagConstraints);
            container.add(textFields[i]);
        }
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagLayout.setConstraints(c, gridBagConstraints);
        container.add(c);
        Thread thread = new Thread(c);
        thread.start();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        /*JFrame f = new JFrame();
        Circle circle = new Circle();
        circle.setSize(100, 100);
        f.setVisible(true);
        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        Container c = f.getContentPane();
        c.setLayout(new GridLayout(7, 6));
        JTextField[] t = {new JTextField("小球半径", 8), new JTextField(15), new JTextField("小球初速度", 8),
                new JTextField(15), new JTextField("小球加速度", 8), new JTextField(15)};
        t[0].setEditable(false);
        t[2].setEditable(false);
        t[4].setEditable(false);
        for (int i = 0; i < 6; i++) {
            c.add(t[i]);
        }
        c.add(circle);
        Thread th = new Thread(circle);
        th.start();*/
        /*JFrame f = new JFrame("小球运动程序");
        f.setVisible(true);
        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        Container container = f.getContentPane();
        container.setLayout(new GridLayout(2, 2));
        JPanel[] panels = new JPanel[2];
        panels[0] = new JPanel();
        panels[0].setLayout(new GridLayout(3, 6));
        panels[1] = new JPanel();
        container.add(panels[0]);
        container.add(panels[1]);
        JTextField[] t = {new JTextField("小球半径", 8), new JTextField(15), new JTextField("小球初速度", 8),
                new JTextField(15), new JTextField("小球加速度", 8), new JTextField(15)};
        t[0].setEditable(false);
        t[2].setEditable(false);
        t[4].setEditable(false);
        for (int i = 0; i < 6; i++) {
            panels[0].add(t[i]);
        }
        Circle circle = new Circle();
        panels[1].add(circle);
        Thread thread = new Thread(circle);
        thread.start();*/
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
        g.drawOval((int) x, (int) y, r, r);
        //x += 10;
        //r += 1;
        /*x = x + speed * Math.cos(degree);
        y = y + speed * Math.sin(degree);
        if (y > 10000-r || y < r)
            degree = -degree;
        if (x > 10000+r || x < r)
            degree = Math.PI - degree;*/

        /*if (right)
            x += 10;
        else
            x -= 10;
        if (x > 200 - 2 * r)
            right = false;
        if (x < r)
            right = true;*/

        if (right)
            y += 10;
        else
            y -= 10;
        if (y > 200 - 2 * r)
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