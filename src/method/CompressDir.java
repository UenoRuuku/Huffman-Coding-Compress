package method;

import java.io.BufferedOutputStream;
import java.io.File;

public class CompressDir {
    private File file;
    private long size;
    private BufferedOutputStream out;

    public CompressDir(File file, BufferedOutputStream out) {
        this.file = file;
        this.out = out;
    }

    public long compressDir() throws Exception {
        out.write(1);//1代表目录，第一个文件一定是目录
        size = compressDir(file, out);
        return size;
    }

    private long compressDir(File File, BufferedOutputStream out) throws Exception {
        //写入第一个目录信息，然后递归调用

        File[] files = File.listFiles();
        int length = 0;
        if (files == null) {
            System.out.println("空文件夹");
        } else {
             length = files.length;
        }
        if (length == 0) {
            out.write(0);//空目录
        } else {
            out.write(1); //非空目录
            out.write(length);
            for (File file : files) {
                if (file.isDirectory()) {
                    //TODO
                    out.write(1);
                    String name = file.getName();
                    out.write(name.length());
                    out.write(name.getBytes());
                    compressDir(file, out);
                } else {
                    size += file.length();
                    new Compress(file, out).compressFile();
                }
            }
        }
        return size;
    }

}
