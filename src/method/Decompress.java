package method;


import java.io.*;
import java.util.PriorityQueue;

public class Decompress {
    private File srcFile;
    private BufferedInputStream in;

    public Decompress(File srcFile, BufferedInputStream in) throws Exception {
        this.srcFile = srcFile;
        this.in = in;
    }

    public void decompressFile() throws Exception {
        String tailname = srcFile.getName().substring(srcFile.getName().lastIndexOf("."));
        if (!tailname.equals(".Ru")) {
            System.out.println("不支持的类型");
        } else {
            srcFile = new File(srcFile.getAbsolutePath().substring(0, srcFile.getAbsolutePath().lastIndexOf(".")));
            decompress(srcFile);
        }
    }

    private void decompress(File file) throws Exception {
        int isDirectory;
        if ((isDirectory = in.read()) != -1)
            if (isDirectory != 0) {//目录
                //如果是文件夹，因为解压的时候直接写进文件夹里，单个文件则是返回上一级
                file.mkdir();
                directory(file);
            } else {//文件
                File tmpFile = new File(file.getParent());
                singleFile(tmpFile);
            }
    }

    private void directory(File file) throws Exception {
        int isDirectory;
        File tmpFile;
        int isEmpty;
        if ((isEmpty = in.read()) != -1)
            if (isEmpty != 0) {//目录非空
                int length = in.read();
                for (int i = 0; i < length; i++) {
                    if ((isDirectory = in.read()) != -1) {
                        if (isDirectory != 0) {
                            int nameLen = in.read();
                            byte[] bytes = new byte[nameLen];
                            in.read(bytes);
                            String name = new String(bytes);
                            String tmpPath = file.getAbsolutePath() + "\\" + name;
                            System.out.println(tmpPath);
                            tmpFile = new File(tmpPath);
                            directory(tmpFile);
                        } else singleFile(file);
                    }
                }
            }
    }

    private void singleFile(File file) throws Exception {
        File tmpFile;
        if (in.read() == 0) {          //空文件
            tmpFile = new File(getOutputPath(file));
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(tmpFile));
            outputStream.close();
        } else {
            tmpFile = new File(getOutputPath(file));
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(tmpFile));
            writeFile(outputStream);
            outputStream.flush();
            outputStream.close();
        }
    }

    private void writeFile(BufferedOutputStream outputStream) throws Exception {
        HuffmanTree.Node root = getHuffmanTree();//获取Huffman树
        HuffmanTree.Node node = root;
        long bytesLength = in.read() + (in.read() << 8) + (in.read() << 16) + (in.read() << 24) + (in.read() << 32);//获取文件字节数 文件大小
        int tmpByte, length = 0;
        StringBuilder string = new StringBuilder("");
        boolean complete = false;
        while (length < bytesLength && (tmpByte = in.read()) != -1) {
            string.append(FileUtil.byte2bits(tmpByte));
            length++;
            if (length == bytesLength - 1) {
                tmpByte = in.read();//补零个数
                tmpByte = tmpByte == 0 ? 8 : tmpByte;
                string.delete(string.length() - tmpByte, string.length());
                complete = true;
            }

            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '0') {
                    node = node.getLeftChild();
                } else
                    node = node.getRightChild();
                if (node.isLeaf()) {
                    outputStream.write(node.getValue());
                    node = root;
                }
            }
            string = new StringBuilder("");
            if (complete)
                break;
        }
    }

    private String getOutputPath(File file) throws Exception {
        //读取源文件名
        int len = in.read();
        byte[] bytes = new byte[len];
        in.read(bytes);
        String name = new String(bytes);
        return file.getAbsolutePath() + System.getProperty("file.separator") + name;
    }

    private HuffmanTree.Node getHuffmanTree() throws Exception {
        //用优先队列重建哈夫曼树
        PriorityQueue<HuffmanTree.Node> queue = new PriorityQueue<>();
        HuffmanTree tree = new HuffmanTree();
        HuffmanTree.Node root;
        int charsLength = in.read();//当初写入的字节种类数
        charsLength = charsLength == 0 ? 256 : charsLength;//byte里256和0一样，这样不用考虑空文件
        int index;
        for (int i = 0; i < charsLength; i++) {
            index = in.read();
            int tmp = in.read() + (in.read() << 8) + (in.read() << 16) + (in.read() << 24);
            HuffmanTree.Node node = new HuffmanTree.Node(index, tmp);
            queue.add(node);
        }
        root = tree.getHuffmanTree(queue);
        return root;
    }
}
