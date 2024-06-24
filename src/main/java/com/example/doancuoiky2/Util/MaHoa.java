package com.example.doancuoiky2.Util;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;
public class MaHoa {
    public static String toSHA1(String string){
        String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";// Làm cho mật khẩu phức tap
        String result = null;
        string = string + salt;
        try {
            byte[] dataBytes = string.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(toSHA1("123"));
    }
}
