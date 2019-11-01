package method;

import java.io.*;

public class operator {
    public void operateCompress(String des) throws Exception {
        Compress c  = new Compress(new File(des),output(des));
        c.compressFile();
    }

    public void operateCompressDir(String des) throws Exception {
        CompressDir c = new CompressDir(new File(des),outputDir(des));
        c.compressDir();
    }

    private BufferedOutputStream output(String des) throws FileNotFoundException {
        String depName = des.substring(0,des.lastIndexOf("."))+".Ru";
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(depName));
        return out;
    }

    private BufferedOutputStream outputDir(String des) throws FileNotFoundException {
        String depName = des+".Ru";
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(depName));
        return out;
    }

    public void operateDepress(String des) throws Exception {
        Decompress d = new Decompress(new File(des), new BufferedInputStream(new FileInputStream(des)));
        d.decompressFile();
    }

}
