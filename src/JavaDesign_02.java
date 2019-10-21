import javax.swing.*;
import java.awt.*;

public class JavaDesign_02 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        makeFrame m = new makeFrame();
        m.make();
    }

}

class makeFrame {
    public void make() {
        JFrame app = new JFrame("小球运动程序");
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
        app.pack();
    }
}