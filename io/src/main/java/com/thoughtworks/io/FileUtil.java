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
//        File[] subFiles = from.listFiles();
    }

    public static void copySingleFolder(File from, File to) {
        new File(to, from.getName()).mkdir();
    }

    public static void copySingleFile(File from, File to) throws IOException {
        try (InputStream input = new FileInputStream(from);
             OutputStream output = new FileOutputStream(new File(to, from.getName()))
        ) {
            int byteRead;
            while ((byteRead = input.read()) != -1) {
                output.write(byteRead);
            }
        }
    }

}
