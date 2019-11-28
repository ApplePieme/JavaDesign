package javaDesign_03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MainForm extends JFrame implements ActionListener {
    public static MainForm _instance;
    public Stack<String> stack, stackReturn;
    public JList<String> list;
    public String curURL = "";
    public Map<String, String> maps = new HashMap<String, String>();
    JPanel panel, showPanel, sortPanel, functPanel;
    JButton preBtn, latBtn, goBtn;
    JTextField SearchText, GuideText;
    JLabel searchTxt, searchType;
    JCheckBox fileCheck, dirCheck;
    JScrollPane scrollShow;
    JPopupMenu jPopupMenu1 = null;
    JPopupMenu jPopupMenu2 = null;
    JPopupMenu jPopupMenu3 = null;
    JMenuItem[] jMenuItems1 = new JMenuItem[2];
    JMenuItem jMenuItems2;
    JMenuItem delete;
    public DefaultListModel defaultListModel;
    public Icon[] allIcons = new Icon[999999];
    public int iconCounter = 0;
    Boolean isSearching = false;
    String preURL = "";
    String latURL = "";

    public MainForm() {
        this._instance = this;
        this.setTitle("文件管理器");
        this.setBounds(500, 500, 1010, 650);
        this.getContentPane().setLayout(null);
        Init();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        setVisible(true);
    }

    public void Init() {
        panel = new JPanel();
        sortPanel = new JPanel();
        showPanel = new JPanel();
        functPanel = new JPanel();

        panel.setBounds(5, 5, 1000, 50);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

        //顶部搜索栏
        sortPanel.setSize(200, 80);
        sortPanel.setLayout(new FlowLayout());

        searchTxt = new JLabel("搜索");
        searchTxt.setBounds(5, 5, 50, 30);
        SearchText = new JTextField(15);
        SearchText.setBounds(50, 5, 120, 30);
        SearchText.addActionListener(this);
        searchType = new JLabel("搜索类型");
        fileCheck = new JCheckBox("文件");
        fileCheck.setSelected(true);
        dirCheck = new JCheckBox("目录");
        dirCheck.setSelected(true);
        fileCheck.addActionListener(this);
        dirCheck.addActionListener(this);
        panel.add(sortPanel);
        panel.add(searchTxt);
        panel.add(SearchText);
        panel.add(searchType);
        panel.add(fileCheck);
        panel.add(dirCheck);

        //中上导航栏
        functPanel.setBounds(5, 50, 990, 45);
        functPanel.setLayout(null);
        preBtn = new JButton("<");
        preBtn.setFont(new Font("Serif", Font.PLAIN, 20));
        preBtn.setBounds(5, 5, 85, 25);
        preBtn.addActionListener(this);
        latBtn = new JButton(">");
        latBtn.setFont(new Font("Serif", Font.PLAIN, 20));
        latBtn.setBounds(85, 5, 85, 25);
        latBtn.addActionListener(this);
        GuideText = new JTextField();
        GuideText.setBounds(180, 5, 740, 25);
        GuideText.addActionListener(this);
        goBtn = new JButton("GO");
        goBtn.setFont(new Font("Serif", Font.PLAIN, 15));
        goBtn.setBounds(925, 5, 65, 25);
        goBtn.addActionListener(this);
        functPanel.add(preBtn);
        functPanel.add(latBtn);
        functPanel.add(GuideText);
        functPanel.add(goBtn);
        this.add(functPanel);

        //中部文件列表
        stack = new Stack<String>();
        stackReturn = new Stack<String>();
        showPanel.setBounds(0, 90, 1010, 600);
        showPanel.setLayout(null);
        list = new JList<String>();
        jPopupMenu1 = new JPopupMenu();       //文件和文件夹的属性菜单
        jPopupMenu2 = new JPopupMenu();       //磁盘的属性菜单
        jPopupMenu3 = new JPopupMenu();
        jMenuItems1[0] = new JMenuItem("删除");
        jMenuItems1[1] = new JMenuItem("重命名");
        for (int i = 0; i < 2; ++i) {
            jMenuItems1[i].addActionListener(this);
            jPopupMenu1.add(jMenuItems1[i]);
        }
        jMenuItems2 = new JMenuItem("打开");
        jMenuItems2.addActionListener(this);
        jPopupMenu2.add(jMenuItems2);
        delete = new JMenuItem("删除");
        delete.addActionListener(this);
        jPopupMenu3.add(delete);
        list.add(jPopupMenu3);
        list.add(jPopupMenu2);
        list.add(jPopupMenu1);

        Home_List();
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (list.getSelectedIndex() != -1) {
                    if (event.getClickCount() == 1) {

                    } else if (event.getClickCount() == 2) {
                        System.out.println(list.getSelectedValue());
                        twoClick(list.getSelectedValue());
                    }
                    if (event.getButton() == 3) {
                        if (curURL != "") {
                            if (list.getSelectedValuesList().size() == 1) {
                                jPopupMenu1.show(list, event.getX(), event.getY());
                            } else if (list.getSelectedValuesList().size() > 1) {
                                jPopupMenu3.show(list, event.getX(), event.getY());
                            }
                        } else if (curURL == "" && list.getSelectedValuesList().size() == 1) {
                            jPopupMenu2.show(list, event.getX(), event.getY());
                        }
                    }
                }
            }
        });

        scrollShow = new JScrollPane(list);
        showPanel.add(scrollShow);
        scrollShow.setSize(990, 520);
        scrollShow.setLocation(5, 5);
        this.add(showPanel);
        this.add(panel);
    }

    public void twoClick(String choice) {
        if (!isSearching) {
            choice += "\\";
            File file = new File(curURL + choice);
            if (file.isDirectory()) {
                curURL += choice;
                stack.push(curURL);
                GoThere();
            } else {
                OpenIt(file);
            }
        } else {
            File file = new File(maps.get(choice));
            OpenIt(file);
        }
    }

    public void Home_List() {
        List<String> Disks = MemoryInfo.getDisk();
        defaultListModel = new DefaultListModel();
        for (int i = 0; i < Disks.size(); ++i) {
            defaultListModel.addElement(Disks.get(i));
        }
        Icon[] icons = GetFileIcon.getSmallIcon("HOME");
        list.setModel(defaultListModel);
        list.setCellRenderer(new MyCellRenderer(icons));
        GuideText.setText("");
        curURL = "";
        stack.push(curURL);
    }

    public void OpenIt(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoThere() {      //跳转函数
        GuideText.setText(curURL);
        if (curURL != "") {         //curURL非空，就跳入目标目录
            defaultListModel.clear();
            String[] getString = GetFileNames.getFileName(curURL);
            for (int i = 0; i < getString.length; ++i) {
                defaultListModel.addElement(getString[i]);
            }
            Icon[] icons = GetFileIcon.getSmallIcon(curURL);
            list.setModel(defaultListModel);
            list.setCellRenderer(new MyCellRenderer(icons));
        } else {      //curURL为空时，跳回根目录
            Home_List();
        }
    }

    public void GetAllResults(String path) {        //搜索函数
        if (path != "") {
            String[] getString = GetFileNames.getFileName(path);
            for (int i = 0; i < getString.length; ++i) {
                File file = new File(path + getString[i] + "\\");
                if (file.isDirectory()) {     //遍历子文件夹
                    GetAllResults(path + getString[i] + "\\");
                } else {
                    String prefix = getString[i].substring(getString[i].lastIndexOf('.') + 1);
                    System.out.println(getString[i]);
                    maps.put(getString[i], path + getString[i]);
                    defaultListModel.addElement(getString[i]);
                    allIcons[iconCounter++] = GetFileIcon.getSingleSmallIcon(path + getString[i]);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == preBtn) {
            latURL = curURL;
            if (!stack.isEmpty()) {
                stack.pop();
                stackReturn.push(curURL);
                if (!stack.isEmpty()) {
                    curURL = stack.peek();
                } else {
                    curURL = "";
                }
                GoThere();
            }
            if (isSearching) {
                isSearching = false;
            }
        } else if (e.getSource() == latBtn) {
            if (!stackReturn.isEmpty()) {
                curURL = stackReturn.peek();
                stackReturn.pop();
                stack.push(curURL);
                GoThere();
            }
            if (isSearching) {
                isSearching = false;
            }
        } else if (e.getSource() == jMenuItems2) {
            if (!isSearching) {
                String url = curURL + list.getSelectedValue();
                if (curURL != "") {
                    url += "\\";
                }
                File file = new File(url);
                if (file.isDirectory()) {
                    twoClick(url);
                } else {
                    OpenIt(file);
                }
            } else {
                File file = new File(maps.get(list.getSelectedValue()));
                OpenIt(file);
            }
        } else if (e.getSource() == jMenuItems1[0]) {
            File file = new File(curURL + "/" + list.getSelectedValue());
            int n;
            if (file.isFile()) {
                n = JOptionPane.showConfirmDialog(null, "确定要删除文件" + file.getName() + "么？", "文件删除", JOptionPane.YES_NO_OPTION);
            } else {
                n = JOptionPane.showConfirmDialog(null, "确定要删除" + file.getName() + "及其目录下的文件么？", "文件夹删除", JOptionPane.YES_NO_OPTION);
            }
            if (n == 0) {
                FileDelete.delete(curURL + list.getSelectedValue() + "\\");
                GoThere();
            }
        } else if (e.getSource() == delete) {
            List<String> selectedStr = list.getSelectedValuesList();
            File file;
            int num = selectedStr.size();
            int n = JOptionPane.showConfirmDialog(null, "确认要删除" + selectedStr.get(0) + "等" + num + "项么？", "文件删除", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                if (isSearching) {
                    for (int i = 0; i < selectedStr.size(); ++i) {
                        file = new File(maps.get(selectedStr.get(i)));
                        FileDelete.delete(file.getAbsolutePath());
                    }
                } else {
                    for (int i = 0; i < selectedStr.size(); ++i) {
                        FileDelete.delete(curURL + selectedStr.get(i) + "\\");
                    }
                    GoThere();
                }
            }
        } else if (e.getSource() == jMenuItems1[1]) {
            String before = list.getSelectedValue();
            File file = new File(curURL + before + "\\");
            String after = "";
            if (file.isDirectory()) {
                after = (String) JOptionPane.showInputDialog(null, "请输入新文件夹名：\n", "重命名", JOptionPane.PLAIN_MESSAGE, null, null, list.getSelectedValue());
            } else {
                after = (String) JOptionPane.showInputDialog(null, "请输入新文件名：\n", "重命名", JOptionPane.PLAIN_MESSAGE, null, null, list.getSelectedValue());
            }
            if (before != after && after != null) {
                new File(curURL + before + "\\").renameTo(new File(curURL + after + "\\"));
                GoThere();
            } else {
                GoThere();
            }
        } else if (e.getSource() == goBtn || e.getSource() == GuideText) {
            String url = GuideText.getText();
            if (url.length() > 0) {
                File file = new File(url);
                if (file.exists()) {
                    stack.push(curURL);
                    curURL = url;
                    GoThere();
                } else {
                    JOptionPane.showConfirmDialog(null, "没有找到该目录！", "确认对话框", JOptionPane.YES_OPTION);
                }
            } else {
                Home_List();
            }
        } else if (e.getSource() == SearchText) {
            boolean flagDir = false;
            boolean flagFile = false;
            if (fileCheck.isSelected()) {
                flagFile = true;
            }
            if (dirCheck.isSelected()) {
                flagDir = true;
            }
            if (!(flagFile || flagDir)) {
                JOptionPane.showMessageDialog(null, "请至少选择一个搜索类别！", "确认对话框", JOptionPane.YES_OPTION);
            } else {
                isSearching = true;
                maps.clear();
                isSearching = true;
                defaultListModel.clear();
                iconCounter = 0;
                allIcons = new Icon[999999];
                FileSearch.bfsSearchFile(curURL, SearchText.getText(), flagDir, flagFile);
                list.setModel(defaultListModel);
                list.setCellRenderer(new MyCellRenderer(allIcons));
            }
        }
    }

    public static void main(String[] args) {
        MainForm mainForm = new MainForm();
    }
}
