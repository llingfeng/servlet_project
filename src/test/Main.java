package test;

import java.io.File;

/**
 * Created by admin on 2016/11/22.
 */
public class Main {

    public static void main(String[] args) {
        String str = "http://i1.dpfile.com/2010-04-13/4072489_b.jpg(278x200)/thumb.jpg";
        String s = getStr(str,6);
        System.out.println(s);
    }

    public static String getStr(String str,int num){
        if(num == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] Arr = str.split("/");
        int k = Arr.length-num;
        if(k>0){
            for(int i=0;i<num;i++){
                sb.append(Arr[k]+"/");
                k++;
            }
        }else{
            sb.append(str);
        }
        return sb.lastIndexOf("/")==sb.length()-1?sb.substring(0,sb.length()-1):sb.toString();
    }

    private static String getSubStr(String str, int num) {
        String result = "";
        int i = 0;
        while(i < num) {
            int lastFirst = str.lastIndexOf('/');
            result = str.substring(lastFirst) + result;
            str = str.substring(0, lastFirst);
            i++;
        }
        return result.substring(1);
    }
}
