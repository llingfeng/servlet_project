package test;

import util.CommonUtil;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by admin on 2016/12/31.
 */
public class Main5 {

    public static void main(String[] args) {
        String str = "中国";
        System.out.println(CommonUtil.getMD5Str(str, "ISO-8859-1"));
        StrToBinstr(str);
    }

    private static void StrToBinstr(String str) {
        byte[] bytes = new byte[0];
        try {
            bytes = str.getBytes("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (byte aByte : bytes) {
            System.out.print(Integer.toBinaryString(aByte & 0xff));
        }
    }
}
