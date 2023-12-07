package pl.edu.pw.ee.aisd2023zlab5;


import java.io.*;
import java.util.Objects;


public class HuffTree {

    private Node root;
    private String read;
    public String[] dictionary;

    public HuffTree(Heap huffHeap){
        Node removed1;
        Node removed2;
        Node newNode;
        int newNodeCounter = 0;

        while (huffHeap.getSize() > 1){
            newNodeCounter++;
            removed1 = huffHeap.remove();
            removed2 = huffHeap.remove();
            char c = (char) ('0'+ newNodeCounter);
            newNode = new Node( removed1.getValue() + removed2.getValue(), c);
            newNode.setLeft(removed1);
            newNode.setRight(removed2);
            huffHeap.insert(newNode);
        }
        root = huffHeap.remove();
        dictionary = new String[258];
    }

    public HuffTree( ){
        root = new Node(0, 'V');
        dictionary = new String[1000];
    }

    public Node getRoot(){
        return root;
    }

    public void buildDictionary( ){
        buildDictionary( root, "");
    }

    private void buildDictionary(Node node, String code) {
        if (node == null) {
            return;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            node.setCode(code);
            int letter = node.getLetter();
            dictionary[letter] = code;
        }

        buildDictionary(node.getLeft(), code + "0");

        buildDictionary(node.getRight(), code + "1");
    }


    public void printTreeWithValues(Node node, int indent) {
        if (node == null) {
            return;
        }
        printTreeWithValues(node.getRight(), indent + 4);
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        System.out.println(node.getLetter() );
        printTreeWithValues(node.getLeft(), indent + 4);
    }

    public void printTreeWithCodes(Node root, String code) {
        if (root == null) {
            return;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            System.out.println(root.getLetter() + ": " + code);
        }
        printTreeWithCodes(root.getRight(), code + "1");

        printTreeWithCodes(root.getLeft(), code + "0");
    }

    public void printDictionary(){
        for(int i = 0; i < 256; i++)
            if (!Objects.equals(dictionary[i], null)){
                System.out.println((char)i + " : " + dictionary[i]);
        }
    }

    public String dictionaryEncoding( ){
        return dictionaryEncoding( root, "");
    }

    private String dictionaryEncoding(Node node, String zapis) {
        if (node == null) {
            return zapis;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            zapis += '1';
            System.out.println(node.getLetter());
            String binaryChar = Integer.toBinaryString(node.getLetter());
            binaryChar = String.format("%8s", binaryChar).replace(' ', '0');
            System.out.println(binaryChar);
            zapis += binaryChar;
            System.out.println(zapis);
        }
        else {
            zapis += '0';
        }

        zapis = dictionaryEncoding(node.getLeft(), zapis);

        zapis = dictionaryEncoding(node.getRight(), zapis);
        return zapis;
    }

    public int builtTreeFromEncodedDictionary( String dictionary )  {
        return builtTreeFromEncodedDictionary( root, dictionary, 0);
    }

    private int builtTreeFromEncodedDictionary(Node node, String dictionary, int i){
        //System.out.println(dictionary.charAt(i));
        if( dictionary.charAt(i) == '1'){
            String temp;
            temp = dictionary.substring(i+1, i + 9);
            i+=8;
            int letter = Integer.parseInt(temp, 2);
            node.setValue(1);
            node.setLetter((char)letter);
            return i;
            //System.out.println("1");
        }
        else{
            //System.out.println("0");
            node.setLeft(new Node(0, 'l') );
            i++;
            i = builtTreeFromEncodedDictionary(node.getLeft(), dictionary, i);
            node.setRight(new Node(0, 'r') );
            i++;
            i = builtTreeFromEncodedDictionary(node.getRight(), dictionary, i);
        }
        return i;
    }

    public void decodeFileIntoAnotherFile( String dictionary , int i, int filled) throws IOException {
        decodeFileIntoAnotherFile( root, dictionary, i, filled);
    }

    private void decodeFileIntoAnotherFile(Node node, String dictionary, int i, int filled) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\user\\AiSD\\dekodowany.txt", "rw");
        Node temp = node;
        int lastByte = 0;
        if (filled == 0) {
            lastByte = 8;
        }
        else{
            lastByte = filled;
        }
        while ( i < dictionary.length() - 8 + lastByte){
            while(node.getLeft() != null && node.getRight() != null) {
                //System.out.println( "literka = " + dictionary.charAt(i) + "i = " + node.getLetter());
                if (dictionary.charAt(i) == '0') {
                    node = node.getLeft();
                    i++;
                } else {
                    node = node.getRight();
                    i++;
                }
            }
            //System.out.println(node.getLetter());
            raf.write(node.getLetter());
            node = temp;
        }
        raf.close();
    }



    public int builtTreeFromEncodedDictionary( String dictionary, FileInputStream fis ) throws IOException {
        read = dictionary;
        int i = builtTreeFromEncodedDictionary( root, 3, fis);
        return i;
    }

   private int builtTreeFromEncodedDictionary(Node node, int i, FileInputStream fis) throws IOException {

        if ( i == 8 ) {
            int byteRead = fis.read();
            read = String.format("%8s", Integer.toBinaryString(byteRead)).replace(' ', '0');
            i = 0;
        }

        if( read.charAt(i) == '1'){
            String temp;
            temp = read.substring(i+1);
            int byteRead = fis.read();
            read = String.format("%8s", Integer.toBinaryString(byteRead)).replace(' ', '0');
            temp += read.substring(0, i+1);
            int letter = Integer.parseInt(temp, 2);
            node.setValue(1);
            node.setLetter((char)letter);
            return i;
        }
        else{
            node.setLeft(new Node(0, 'l') );
            i++;
            i = builtTreeFromEncodedDictionary(node.getLeft(), i, fis);
            node.setRight(new Node(0, 'r') );
            i++;
            i = builtTreeFromEncodedDictionary(node.getRight(), i, fis);
        }
        return i;
    }
    public void decodeFileIntoAnotherFile(int h, FileInputStream fis, int filled) throws IOException {
        decodeFileIntoAnotherFile(h, root, fis, filled);
    }

    private void decodeFileIntoAnotherFile(int h, Node node, FileInputStream fis, int filled) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\user\\AiSD\\dekodowany.txt", "rw");
        Node temp = node;
        int flag = 0;
        String binaryRepresentation;
        System.out.println("read  = " + read);
        System.out.println( "h = "+h);
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
                    System.out.println(" co jest " + node.getLetter());
                    node = temp;
                }
            }
        }
        int counter = 0;
        int byteRead;
        int howManyBytes = fis.available();
        System.out.println("howManyBytes = "+ howManyBytes);
        for(int j = 0; j < howManyBytes-1; j++){
            counter = 0;
            byteRead = fis.read();
            binaryRepresentation = String.format("%8s", Integer.toBinaryString(byteRead & 0xFF)).replace(' ', '0');
            System.out.println(binaryRepresentation);
            while(counter != 8) {
                while (node.getLeft() != null && node.getRight() != null && counter != 8) {
                    if (binaryRepresentation.charAt(counter) == '0') {//tu counter może wyjsc za osiem trzbea dac w ifie cos
                        node = node.getLeft();
                        counter++;
                    } else {
                        node = node.getRight();
                        counter++;
                    }
                }
                if (node.getLeft() == null && node.getRight() == null) {
                    raf.write(node.getLetter());
                    System.out.println(node.getLetter());
                    node = temp;
                }
            }
        }
        System.out.println(node.getLetter());
        byteRead = fis.read();
        binaryRepresentation = String.format("%8s", Integer.toBinaryString(byteRead & 0xFF)).replace(' ', '0');
        if( filled == 0){
            filled = 8;
        }
        System.out.println(binaryRepresentation);
        for(int j = 0; j < filled;){
            while (node.getLeft() != null && node.getRight() != null && j != filled) {
                if (binaryRepresentation.charAt(j) == '0') {
                    System.out.println("0 j = " + j);
                    node = node.getLeft();
                    j++;
                } else {
                    System.out.println("1 j = " + j);
                    node = node.getRight();
                    j++;
                }
            }
            raf.write(node.getLetter());
            System.out.println(node.getLetter());
            node = temp;
        }

        System.out.println(counter);
        raf.close();
    }



}
