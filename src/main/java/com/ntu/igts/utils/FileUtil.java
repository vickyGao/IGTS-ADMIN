package com.ntu.igts.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

public class FileUtil {

    private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

    /**
     * Get the size of a file
     * 
     * @param f
     *            The file object
     * @return The The size of the file
     */
    public static long getFileSizes(File f) {
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                s = fis.available();

            } catch (IOException e) {
                LOGGER.error("Error happens when creating file input stream from file", e);
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    LOGGER.error("Error happens when try to close the file input stream", e);
                }
            }
        } else {
            LOGGER.error("file not exists");
        }
        return s;
    }

    /**
     * Use recursion to get the size of folder
     * 
     * @param f
     *            The file object
     * @return The size of a file/folder
     */
    public static long getFileSize(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    /**
     * Format the size of a file/folder
     * 
     * @param fileSize
     *            The size of a file/folder
     * @return The formatted size of a file/folder
     */
    public static String formetFileSize(long fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * Use recursion to get the amount of files in a folder
     * 
     * @param f
     *            The file object of a folder
     * @return The amount of files in a folder
     */
    public static long getlist(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        size = flist.length;
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getlist(flist[i]);
                size--;
            }
        }
        return size;

    }

    public static int getFileAmountInFloder(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            // If it is a folder
            return Integer.valueOf(String.valueOf(getlist(file)));
        } else {
            // If it is a file
            return 1;
        }
    }

    public static String getFolderSize(String path) {
        File file = new File(path);
        long l = 0;
        if (file.isDirectory()) {
            // If it is a folder
            l = getFileSize(file);
            return formetFileSize(l);
        } else {
            // If it is a file
            l = getFileSizes(file);
            return formetFileSize(l);
        }
    }
}
