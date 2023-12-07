package pl.edu.pw.ee.aisd2023zlab5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main {

    public static void main(String[] args) throws IOException {
        String filePathRead = "C:\\Users\\user\\AiSD\\Romeo.txt"; //F:\AiSD\niemanie.txt
        String filePathWrite = "C:\\Users\\user\\AiSD\\kompresja.txt";
        int maxSize=100_000;
        // wyjmuje dwa sk³adam je w jeden z left i right i go wk³adam spowrotem
        Heap huffHeap = new Heap(maxSize * 2 + 1);
        huffHeap = huffHeap.buildHeap(maxSize, filePathRead);

        HuffTree tree = new HuffTree(huffHeap);

        tree.printTreeWithValues(tree.getRoot(),0);
        tree.buildDictionary();
        //tree.printTreeWithCodes( tree.getRoot(),"");
        tree.printDictionary();

        String wynik = tree.dictionaryEncoding();
        System.out.println(wynik);


        System.out.println("ELTON JOHN");
        int filled = 0;
        int byteValue = 0b00000000;
        String byteString;
        try (RandomAccessFile raf = new RandomAccessFile(filePathWrite, "rw")) {
            byteString = wynik.substring(0, 5 );
            byteValue = Integer.parseInt(byteString, 2);
            System.out.println( "pierwsze 5 bitów " +  Integer.toBinaryString(byteValue));
            int firstByte = byteValue;
            raf.write(byteValue);
            for (int i = 5; i < wynik.length() - 8; i += 8) {
                byteString = wynik.substring(i, Math.min(i + 8, wynik.length()));
                byteValue = Integer.parseInt(byteString, 2);
                System.out.println(Integer.toBinaryString(byteValue));
                raf.write(byteValue);
                filled = wynik.length() - i - 8;
            }
            System.out.println(filled);

            if (filled > 0) {
                byteString = wynik.substring(wynik.length() - filled);
                byteValue = Integer.parseInt(byteString, 2);
                byteString = Integer.toBinaryString(byteValue);
                byteString = String.format("%8s", byteString).replace(' ', '0');
                if(filled == 8){
                    System.out.println(Integer.toBinaryString(byteValue));
                    raf.write(byteValue);
                    filled = 0;
                    byteString = "";
                    byteValue = 0;
                }
            }
            else {
                byteString = "";
            }

            ////
            System.out.println("HALCIOOOOOOOOOO");
            //System.out.println(byteString); to chyba nie wa¿ne
            BufferedReader reader = new BufferedReader(new FileReader(filePathRead));
            int currentLetter;
            String code = "";
            while ((currentLetter = reader.read()) != -1) {
                code = tree.dictionary[currentLetter];
                //System.out.println(code);
                int i;
                for(i = 0; i < code.length() && filled < 8; i++){
                    byteString += code.charAt(i);
                    //System.out.println(byteString);
                    filled ++;
                    if(filled == 8){
                        byteValue = Integer.parseInt(byteString, 2);
                        System.out.println(Integer.toBinaryString(byteValue));
                        raf.write(byteValue);
                        filled = 0;
                        byteString = "";
                    }
                }
            }
            if (filled > 0) {
                byteValue = Integer.parseInt(byteString, 2);
                byteValue = byteValue << 8 - filled;
                System.out.println("COOOOOOOOOOOOOO " + Integer.toBinaryString(byteValue));
                raf.write(byteValue);
            }
            raf.seek(0);
            int temp = filled << 5;
            temp += firstByte;
            raf.write(temp);
            System.out.println("PIERWSZY BAJCIK");
            String binaryRepresentation = Integer.toBinaryString(temp);
            System.out.println(binaryRepresentation);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nDEKOMPRESJAAAAAAAAAAAAAA LESSS GO\n");

        System.out.println("STARE FILLED PRZED CZYTANIEM = " + filled);
        HuffTree tree1 = new HuffTree();
        /*
        try (FileInputStream fis = new FileInputStream(filePathWrite)) {
            int byteRead;
            byteRead = fis.read();
            String binaryRepresentation = String.format("%8s", Integer.toBinaryString(byteRead)).replace(' ', '0');
            System.out.println(binaryRepresentation);
            String filledString = binaryRepresentation.substring(0, 3);
            filled = Integer.parseInt(filledString, 2);
            System.out.println( "FILLED PO CZYTANIU = " + filled);
            wynik = binaryRepresentation.substring(3,8);
            System.out.println(wynik);
            System.out.println("Robimy coœ");
            tree1.builtTreeFromEncodedDictionary(binaryRepresentation, fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
        int j=0;
        String file="";
        try (FileInputStream fis = new FileInputStream(filePathWrite)) {
            int byteRead;
            file="";
            byteRead = fis.read();
            String binaryRepresentation = String.format("%8s", Integer.toBinaryString(byteRead)).replace(' ', '0');
            file += binaryRepresentation.substring(3,8);
            String filledString = binaryRepresentation.substring(0, 3);
            filled = Integer.parseInt(filledString, 2);
            System.out.println( "FILLED PO CZYTANIU = " + filled);
            int counter = 0;
            while ((byteRead = fis.read()) != -1) {
                binaryRepresentation = String.format("%8s", Integer.toBinaryString(byteRead & 0xFF)).replace(' ', '0'); // to mo¿e trzeba poprawiæ
                file += binaryRepresentation;
                counter ++;
                System.out.println(counter);
            }
            j = tree1.builtTreeFromEncodedDictionary(file);
            j++;
            System.out.println(" j = " + j + " len = " + file.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n\n");
        System.out.println(file.charAt(j));
        System.out.println(file.substring(j));

        //tree1.printTreeWithValues(tree1.getRoot(),0);
        tree1.decodeFileIntoAnotherFile(file, j, filled);
    }
}
