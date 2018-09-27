package com.zmf.Utils;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zmf
 * @description frequent methods
 * @timestamp 2017-12-09 00:32
 */
public class Util {
    /**
     * get the last part of the url
     *
     * @param url the url
     * @return the last part
     */
    public static String getLastUrlPart(String url) {
        String[] strs = url.split("/");
        return strs[strs.length - 1];
    }

    public static String convertToNineBits(int num) {
        String numstr = "000000000" + num;
        return numstr.substring(numstr.length() - 9);
    }


    public static String getTimeString(Timestamp timeStamp) {
        try {
            return timeStamp.toString().split("\\.")[0];
        } catch (Exception e) {
            String string = timeStamp.toString();
            return string.substring(0, string.length() - 2);
        }
    }

    public static String getDateString(Timestamp timeStamp) {
        try {
            return timeStamp.toString().split(" ")[0];
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * write tht file to the chapter file path and change the file permission
     *
     * @param filePath the file path
     * @param file     the file
     */
    public static void writeFile(String filePath, MultipartFile file) {
        new Thread(() -> {
            try {
                File dest = new File(filePath);
                Util.createFile(dest);
                file.transferTo(dest);
                Util.changeFilePermission(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * create a file with the certain permission
     *
     * @param file the file
     */
    public static void createFile(File file) {
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                changeFilePermission(file.getParentFile());
            }
            try {
                file.createNewFile();
                changeFilePermission(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean deleteFile(File file) {
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    @Test
    public void test() {
        changeFilePermission(new File("/home/zmf/Downloads/download/casc/aaa"));
    }


    /**
     * judge whether the string is empty
     *
     * @param string the string
     * @return true if the string is empty
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }


    public static void changeFilePermission(File file) {
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_WRITE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);
        perms.add(PosixFilePermission.OTHERS_READ);
        try {
            Path path = Paths.get(file.getAbsolutePath());
            Files.setPosixFilePermissions(path, perms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
