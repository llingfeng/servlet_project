package test;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by admin on 2016/12/13.
 */
public class Main2 {

    public static void main(String[] args) throws Exception{
        File dir = new File("demo");
        if(!dir.exists()){
            dir.mkdir();
        }
        File file = new File(dir,"a.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        raf.writeChars("abcdefg");
        raf.close();
    }
}
