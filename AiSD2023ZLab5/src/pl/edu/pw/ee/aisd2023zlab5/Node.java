package pl.edu.pw.ee.aisd2023zlab5;

public class Node implements Comparable<Node> {

    private int value;
    private char letter;
    private Node left, right;
    private String code = "";
    public Node(int value, char letter) {
        this.value = value;
        this.letter = letter;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Node  getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node leftNode) {
        left = leftNode;
    }

    public void setRight(Node rightNode) {
        right = rightNode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int compareTo(Node o) {
        return  this.value - o.value;
    }



}