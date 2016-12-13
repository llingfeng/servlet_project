package test;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by admin on 2016/12/13.
 */
public class Main4 {
    public static void main(String[] args)throws Exception{
        File file = new File("demo//tempFile.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
        long start = 136;
        long end = 141;
        randomAccessFile.seek(start);
        while (start <= end){
            System.out.println(randomAccessFile.readByte());
            start = randomAccessFile.getFilePointer();
        }
    }
}
