package pl.edu.pw.ee.aisd2023zlab5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Decompressor {
    private HuffTree tree;
    private int h;
    private int filled;
    private String read;
    private FileInputStream fis;
    public Decompressor(HuffTree tree ){
        this.tree = tree;
        this.h = 0;
        this.filled = 0;
        this.read = "";
    }

    public void decompress(String filePathRead, String filePathWrite){
        try (FileInputStream fis = new FileInputStream(filePathRead)) {
            int byteRead;
            byteRead = fis.read();
            String binaryRepresentation = String.format("%8s", Integer.toBinaryString(byteRead)).replace(' ', '0');
            String filledString = binaryRepresentation.substring(0, 3);
            filled = Integer.parseInt(filledString, 2);
            String wynik = binaryRepresentation.substring(3, 8);
            h = tree.builtTreeFromEncodedDictionary(binaryRepresentation, fis);
            read = tree.getRead();

            Node node = tree.getRoot();
            int lastDotIndex = filePathWrite.lastIndexOf('.');
            if (lastDotIndex != -1) {
                String prefix = filePathWrite.substring(0, lastDotIndex + 1);
                filePathWrite = prefix + "txt";
            } else {
                filePathWrite = filePathWrite + ".txt";
            }
            RandomAccessFile raf = new RandomAccessFile(filePathWrite, "rw");
            FileChannel output = FileChannel.open(Paths.get(filePathWrite), StandardOpenOption.WRITE);
            output.truncate(0);
            Node temp = node;
            if (h < 7) {
                for (int i = h+1; i < 8;) {
                    while (node.getLeft() != null && node.getRight() != null) {
                        if (read.charAt(i) == '0') {
                            node = node.getLeft();
                            i++;
                        } else {
                            node = node.getRight();
                            i++;
                        }
                        if( i == 8){
                            break;
                        }
                    }
                    if (i < 8) {
                        raf.write(node.getLetter());
                        node = temp;
                    }
                }
            }
            int counter = 0;
            int howManyBytes = fis.available();
            for(int j = 0; j < howManyBytes-1; j++){
                if(j%1000 == 0){
                    System.out.println((float)j/howManyBytes * 100 + "%");
                }
                counter = 0;
                byteRead = fis.read();
                binaryRepresentation = String.format("%8s", Integer.toBinaryString(byteRead & 0xFF)).replace(' ', '0');
                while(counter != 8) {
                    while (node.getLeft() != null && node.getRight() != null && counter != 8) {
                        if (binaryRepresentation.charAt(counter) == '0') {
                            node = node.getLeft();
                            counter++;
                        } else {
                            node = node.getRight();
                            counter++;
                        }
                    }
                    if (node.getLeft() == null && node.getRight() == null) {
                        raf.write(node.getLetter());
                        node = temp;
                    }
                }
            }
            byteRead = fis.read();
            binaryRepresentation = String.format("%8s", Integer.toBinaryString(byteRead & 0xFF)).replace(' ', '0');
            if( filled == 0){
                filled = 8;
            }
            for(int j = 0; j < filled;){
                while (node.getLeft() != null && node.getRight() != null && j != filled) {
                    if (binaryRepresentation.charAt(j) == '0') {
                        node = node.getLeft();
                        j++;
                    } else {
                        node = node.getRight();
                        j++;
                    }
                }
                raf.write(node.getLetter());
                node = temp;
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
