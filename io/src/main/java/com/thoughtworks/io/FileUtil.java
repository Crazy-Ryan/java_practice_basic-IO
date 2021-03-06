package com.thoughtworks.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

    /**
     * 完成复制文件夹方法:
     * 1. 把给定文件夹from下的所有文件(包括子文件夹)复制到to文件夹下
     * 2. 保证to文件夹为空文件夹，如果to文件夹不存在则自动创建
     * <p>
     * 例如把a文件夹(a文件夹下有1.txt和一个空文件夹c)复制到b文件夹，复制完成以后b文件夹下也有一个1.txt和空文件夹c
     */
    public static void copyDirectory(File from, File to) throws IOException {
        copyDirOneWay(to, from);
        copyDirOneWay(from, to);
    }

    public static void copyDirOneWay(File from, File to) throws IOException {
        if (!to.exists()) {
            to.mkdir();
        }
        File[] subFiles = from.listFiles();
        if (null != subFiles) {
            for (File file : subFiles) {
                if (file.isDirectory()) {
                    copySingleFolder(file, to);
                    copyDirOneWay(file, new File(to, file.getName()));
                } else if (file.isFile()) {
                    copySingleFile(file, to);
                }
            }
        }
    }

    public static void copySingleFolder(File from, File to) {
        File newDir = new File(to, from.getName());
        if (!newDir.exists()) {
            new File(to, from.getName()).mkdir();
        }
    }

    public static void copySingleFile(File from, File to) throws IOException {
        File newFile = new File(to, from.getName());
        if (!newFile.exists()) {
            try (InputStream input = new FileInputStream(from);
                 OutputStream output = new FileOutputStream(newFile)
            ) {
                int byteRead;
                while ((byteRead = input.read()) != -1) {
                    output.write(byteRead);
                }
            }
        }
    }

}
