package pl.edu.pw.ee.aisd2023zlab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Compressor {

    private HuffTree tree;
    public Compressor(String filePathRead ) throws IOException {
        int maxSize = 100_000;
        Heap huffHeap = new Heap(maxSize * 2 + 1);
        huffHeap = huffHeap.buildHeap(maxSize, filePathRead);
        HuffTree tree = new HuffTree(huffHeap);
        tree.buildDictionary();
        this.tree = tree;
    }
    public String dictionaryEncoding(){
        Node root = tree.getRoot();
        return dictionaryEncoding( root, "");
    }

    private String dictionaryEncoding(Node node, String zapis) {
        if (node == null) {
            return zapis;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            zapis += '1';
            String binaryChar = Integer.toBinaryString(node.getLetter());
            binaryChar = String.format("%8s", binaryChar).replace(' ', '0');
            zapis += binaryChar;
        }
        else {
            zapis += '0';
        }

        zapis = dictionaryEncoding(node.getLeft(), zapis);

        zapis = dictionaryEncoding(node.getRight(), zapis);
        return zapis;
    }

    public void compressTheFile(String wynik, String filePathWrite, String filePathRead){
        int filled = 0;
        int byteValue = 0b00000000;
        String byteString;
        try (RandomAccessFile raf = new RandomAccessFile(filePathWrite, "rw")) {
            FileChannel output = FileChannel.open(Paths.get(filePathWrite), StandardOpenOption.WRITE);
            output.truncate(0);
            byteString = wynik.substring(0, 5 );
            byteValue = Integer.parseInt(byteString, 2);
            int firstByte = byteValue;
            raf.write(byteValue);
            for (int i = 5; i < wynik.length() - 8; i += 8) {
                byteString = wynik.substring(i, Math.min(i + 8, wynik.length()));
                byteValue = Integer.parseInt(byteString, 2);
                raf.write(byteValue);
                filled = wynik.length() - i - 8;
            }

            if (filled > 0) {
                byteString = wynik.substring(wynik.length() - filled);
                byteValue = Integer.parseInt(byteString, 2);
                byteString = Integer.toBinaryString(byteValue);
                byteString = String.format("%8s", byteString).replace(' ', '0');
                if(filled == 8){
                    raf.write(byteValue);
                    filled = 0;
                    byteString = "";
                    byteValue = 0;
                }
            }
            else {
                byteString = "";
            }
            BufferedReader reader = new BufferedReader(new FileReader(filePathRead));
            int currentLetter;
            String code = "";
            while ((currentLetter = reader.read()) != -1) {
                code = tree.dictionary[currentLetter];
                int i;
                for(i = 0; i < code.length() && filled < 8; i++){
                    byteString += code.charAt(i);
                    filled ++;
                    if(filled == 8){
                        byteValue = Integer.parseInt(byteString, 2);
                        raf.write(byteValue);
                        filled = 0;
                        byteString = "";
                    }
                }
            }
            if (filled > 0) {
                byteValue = Integer.parseInt(byteString, 2);
                byteValue = byteValue << 8 - filled;
                raf.write(byteValue);
            }
            raf.seek(0);
            int temp = filled << 5;
            temp += firstByte;
            raf.write(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
