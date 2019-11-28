package javaDesign_03;

import javax.swing.*;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class FileSearch {
    public static void bfsSearchFile(String path, String regex, boolean isDisplyDir, boolean isDisplyFile) {
        boolean isFind = false;
        regex = "(.*)" + regex + "(.*)";
        if (!(isDisplyDir || isDisplyFile)) {
            throw new IllegalArgumentException("isDisplyDir和isDisplyFile至少有一个要为true");
        }
        Queue<File> queue = new LinkedList<>();
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                queue.offer(file);
            } else {
                if (file.getName().matches(regex) && isDisplyFile) {
                    isFind = true;
                    MainForm._instance.maps.put(file.getName(), file.getAbsolutePath());
                    MainForm._instance.defaultListModel.addElement(file.getName());
                    MainForm._instance.allIcons[MainForm._instance.iconCounter++] = GetFileIcon.getSingleSmallIcon(file.getAbsolutePath());
                }
            }
        }
        while (!queue.isEmpty()) {
            File fileTemp = queue.poll();
            if (isDisplyDir) {
                if (fileTemp.getName().matches(regex)) {
                    isFind = true;
                    MainForm._instance.maps.put(fileTemp.getName(), fileTemp.getAbsolutePath());
                    MainForm._instance.defaultListModel.addElement(fileTemp.getName());
                    MainForm._instance.allIcons[MainForm._instance.iconCounter++] = GetFileIcon.getSingleSmallIcon(fileTemp.getAbsolutePath());
                }
            }
            File[] fileListTemp = fileTemp.listFiles();
            if (fileListTemp == null)
                continue;
            for (File file : fileListTemp) {
                if (file.isDirectory()) {
                    queue.offer(file);
                } else {
                    if (file.getName().matches(regex) && isDisplyFile) {
                        isFind = true;
                        MainForm._instance.maps.put(file.getName(), file.getAbsolutePath());
                        MainForm._instance.defaultListModel.addElement(file.getName());
                        MainForm._instance.allIcons[MainForm._instance.iconCounter++] = GetFileIcon.getSingleSmallIcon(file.getAbsolutePath());
                    }
                }
            }
        }
        if (!isFind) {
            JOptionPane.showMessageDialog(null, "未找到相关结果", "确认对话框", JOptionPane.YES_OPTION);
        }
    }
}
