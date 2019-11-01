package method;


import java.io.*;
import java.util.*;

public class Compress {
    private File file;
    private int[] chars;
    private String[] charsCode;
    private BufferedOutputStream out;
    private BufferedInputStream in;


    public Compress(File file, BufferedOutputStream out) throws Exception {
        this.file = file;
        this.out = out;
        this.in = new BufferedInputStream(new FileInputStream(file));
    }

    //整合了所有的操作
    public void compressFile() throws Exception {
        if (file.length() != 0) {
            this.chars = FileUtil.countByte(file);
            HuffmanTree tree = new HuffmanTree();// 构建优先队列
            //先断言不会空吧……
            assert chars != null;
            PriorityQueue<HuffmanTree.Node> queue = tree.toQueue(chars);// 构建树
            HuffmanTree.Node root = tree.getHuffmanTree(queue);
            compressFile(root);
        } else {
            out.write(0);//代表文件尾
            out.write(0);//代表是文件
            writeName();
            in.close();
        }
    }

    private void compressFile(HuffmanTree.Node root) throws Exception {
        charsCode = FileUtil.encoding(root);
        writeHead();
        writeFile();
        in.close();
    }

    //写入文件头
    private void writeHead() throws Exception {
        out.write(0);//0代表是文件，1代表文件夹
        out.write(1);//第二个1代表不是空文件
        writeName();
        writeByteCount();
        writeBytesSize();
    }

    //写入文件名
    private void writeName() throws Exception {
        //写入源文件名
        String name = file.getName();
        out.write(name.getBytes().length);
        out.write(name.getBytes());
    }

    private void writeByteCount() throws Exception {
        int size = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 0) {
                size++;
            }
        }
        //写入文件字节种类数
        out.write(size);
        //写入文件字节次数，用于解压时构建Huffman树
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 0) {
                out.write(i);
                //因为byte比int低24位，所以用位移的方法将int转换成byte
                out.write((byte) chars[i]);
                out.write((byte) (chars[i] >> 8));
                out.write((byte) (chars[i] >> 16));
                out.write((byte) (chars[i] >> 24));
            }
        }
    }

    private void writeBytesSize() throws Exception {
        //写入文件重新编码后文件主体的字节数
        long bytesLength = getBytesLength();
        out.write((byte) bytesLength);
        out.write((byte) (bytesLength >> 8));
        out.write((byte) (bytesLength >> 16));
        out.write((byte) (bytesLength >> 24));
        out.write((byte) (bytesLength >> 32));
    }

    private void writeFile() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("");
        int tmp;
        while ((tmp = in.read()) != -1) {
            stringBuilder.append(charsCode[tmp]);
            if (stringBuilder.length() >= 8) {
                out.write(Integer.parseInt(stringBuilder.substring(0, 8), 2));
                stringBuilder.delete(0, 8);
            }
        }
        int hfmLength = stringBuilder.length();
        int byteNumber = hfmLength / 8;
        int restNumber = hfmLength % 8;
        for (int i = 0; i < byteNumber; i++) {
            String str = stringBuilder.substring(i * 8, (i + 1) * 8);
            out.write(FileUtil.bit2byte(str));
        }

        // 补0操作
        int zeroNumber = (8 - restNumber) % 8;
        StringBuilder str = new StringBuilder(stringBuilder.substring(hfmLength - restNumber));
        str.append("0");//若restNumber=0，则写入0，保证格式整齐，便于解压
        for (int i = 1; i < zeroNumber; i++) {
            str.append("0");
        }
        byte by = FileUtil.bit2byte(str.toString());
        out.write(by);

        String zeroLenStr = Integer.toBinaryString(zeroNumber);// 将补0的长度也记录下来保存到文件末尾
        byte zeroB = FileUtil.bit2byte(zeroLenStr);
        out.write(zeroB);
        out.flush();
    }

    private long getBytesLength() {
        long bytesLength = 0, size = chars.length;
        for (int i = 0; i < size; i++) {
            if (chars[i] > 0)
                bytesLength += chars[i] * charsCode[i].length();//字节出现次数乘以字节对应的Huffman编码长度
        }
        bytesLength /= 8;//字节数
        bytesLength += 2;//最后不足八位的编码一字节，以及补零的一字节
        return bytesLength;
    }
}
