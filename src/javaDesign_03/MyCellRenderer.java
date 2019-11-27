package javaDesign_03;

import javax.swing.*;
import java.awt.*;

public class MyCellRenderer extends JLabel implements ListCellRenderer {
    Icon[] icons;

    public MyCellRenderer(Icon[] icons) {
        this.icons = icons;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String str = value.toString();
        setText(str);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));        //加入宽度为5的空白边框
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setIcon(icons[index]);
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
}
