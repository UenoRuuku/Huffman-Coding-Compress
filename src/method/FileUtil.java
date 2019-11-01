package method;

import java.io.*;

//和io相关的api包装
//10/30完全封装禁止在动
public class FileUtil {
    //统计文件字节及相应字节出现次数
    public static int[] countByte(File file) {
        if (!file.exists()) { //如果文件不存在为空
            return null;
        } else if (file.length() == 0) {
            return null;
        } // 文件大小为0
        int[] chars = new int[256]; //用于储存文件每个字节对应的出现次数
        //
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            int size;
            while ((size = inputStream.read()) != -1) {
                chars[size]++;
            }
            inputStream.close(); //貌似关闭流操作中整合了flush操作
        } catch (Exception ignored) {
        }
        return chars;
    }

    public static String[] encoding(HuffmanTree.Node root) {
        String[] charsCode = new String[256];
        String code = "";
        encoding(root, code, charsCode);
        return charsCode;
    }

    private static void encoding(HuffmanTree.Node node, String code, String[] charsCode) {
        if (node != null) {
            code += node.getChildType();
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                charsCode[node.getValue()] = code;
            }
            encoding(node.getLeftChild(), code, charsCode);
            encoding(node.getRightChild(), code, charsCode);
        }
    }

    //将二进制字符串转换成二进制
    public static byte bit2byte(String str) {
        byte result = 0;
        for (int i = str.length() - 1, j = 0; i >= 0; i--, j++) {
            result += (Byte.parseByte(str.charAt(i) + "")<<j);
        }
        return result;
    }

    //将二进制转换成01字符串
    public static String byte2bits(int value) {
        int v = value;
        v |= 256;
        String str = Integer.toBinaryString(v);
        int len = str.length();
        return str.substring(len - 8, len);
    }
}



