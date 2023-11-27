package pl.edu.pw.ee.aisd2023zlab5;


import java.util.Objects;

public class HuffTree {

    private Node root;
    private String[] dictionary;

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
        dictionary = new String[256];
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
        String a;
        if (node.getLeft() == null && node.getRight() == null) {
            zapis += '1';
            a = "";
        }
        else
            a = "0";
        System.out.println("\n"+node.getLetter() + " left " + zapis);
        zapis = dictionaryEncoding(node.getLeft(), zapis + a);

        zapis = dictionaryEncoding(node.getRight(), zapis );
        return zapis;
    }


}
