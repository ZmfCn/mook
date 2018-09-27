package com.zmf.errors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class UserErrors {
    @Value("${user_username_not_exist}")
    private String user_username_not_exist;
    @Value("${user_account_not_confirmed}")
    private String user_account_not_confirmed;

//    @Test
//    public void test() {
//        File file = new File("/var/www/html/download/dbwork/css3/66a18ba5cd2946628841e5be0c152c82.jpeg");
//        if (!file.exists()) {
//            if (!file.getParentFile().exists()) {
//            }
//        }
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            writer.write("asfsa");
//            writer.flush();
//            writer.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
