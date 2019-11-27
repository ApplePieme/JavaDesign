package javaDesign_03;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;

public class GetFileIcon {
    public static Icon getSingleSmallIcon(String path) {
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File file = new File(path);
        Icon icon = fileSystemView.getSystemIcon(file);
        return icon;
    }

    public static Icon[] getSmallIcon(String path) {
        Icon[] icons = new Icon[999999];
        int counter = 0;
        if (path == "HOME") {
            List<String> Disks = MemoryInfo.getDisk();
            for (int i = 0; i < Disks.size(); ++i) {
                FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                File file = new File(Disks.get(i) + "\\");
                icons[counter++] = fileSystemView.getSystemIcon(file);
            }
        } else {
            File file = new File(path);
            File[] files = file.listFiles();
            for (File a : files) {
                if (a != null && a.exists()) {
                    FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                    icons[counter++] = fileSystemView.getSystemIcon(a);
                }
            }
        }
        return icons;
    }
}
