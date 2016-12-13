package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/**
 * Created by admin on 2016/12/13.
 */
public class Main3 {
    public static void main(String[] args) throws Exception {
        File file = new File("demo//a.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        File fos = new File("demo//b.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(fos, "rw");
        long start = 2;
        long end = 4;
        raf.seek(start);
        while (start < end) {
            randomAccessFile.write(raf.read());
            start = raf.getFilePointer();
        }
    }
}
