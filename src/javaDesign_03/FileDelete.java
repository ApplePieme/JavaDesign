package javaDesign_03;

import javax.swing.*;
import java.io.File;

public class FileDelete {
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "删除文件失败：" + file.getName() + "不存在！", "提示", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                JOptionPane.showMessageDialog(null, "删除单个文件" + file.getName() + "成功！", "提示", JOptionPane.WARNING_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "删除单个文件" + file.getName() + "失败！", "提示", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "删除单个文件失败：" + file.getName() + "不存在！", "提示", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public static boolean deleteDirectory(String dir) {
        if (!dir.endsWith(File.separator)) {
            dir += File.separator;
        }
        File dirFile = new File(dir);
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            JOptionPane.showMessageDialog(null, "删除目录失败：" + dir + "不存在！", "提示", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = FileDelete.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } else if (files[i].isDirectory()) {
                flag = FileDelete.deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, "删除目录失败！", "提示", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (dirFile.delete()) {
            JOptionPane.showMessageDialog(null, "删除目录" + dir + "成功！", "提示", JOptionPane.WARNING_MESSAGE);
            return true;
        } else {
            return false;
        }
    }
}
