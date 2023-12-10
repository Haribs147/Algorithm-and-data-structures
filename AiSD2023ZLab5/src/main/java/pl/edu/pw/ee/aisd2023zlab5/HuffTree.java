package pl.edu.pw.ee.aisd2023zlab5;


import java.io.*;
import java.util.Objects;


public class HuffTree {

    private Node root;
    private String read;
    public String[] dictionary;

    public Node getRoot(){
        return root;
    }

    public void setRoot(Node root){
        this.root = root;
    }


    public String getRead(){
        return read;
    }
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

}
