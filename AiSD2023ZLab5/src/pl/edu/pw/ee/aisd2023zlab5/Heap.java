package pl.edu.pw.ee.aisd2023zlab5;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Heap{

    private Node[] heap;
    private int size;
    private int maxSize;
    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new Node[maxSize];
    }

    private void swap(int firstPos, int SecondPos) {
        Node tmp;
        tmp = heap[firstPos];
        heap[firstPos] = heap[SecondPos];
        heap[SecondPos] = tmp;
    }

    public int getSize() { return size; }

    private int parent(int pos) { return (pos - 1) / 2; }

    private int leftChild(int pos) { return (2 * pos + 1); }

    private int rightChild(int pos) { return (2 * pos + 2); }

    private boolean isLeaf(int i) {
        if (rightChild(i) >= size || leftChild(i) >= size) {
            return true;
        }
        return false;
    }

    public void insert(Node element) {
        if (size >= maxSize) {
            return;
        }
        heap[size] = element;
        int current = size;

        while (heap[current].compareTo( heap[parent(current)] ) < 0 ) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    public Node remove() {
        Node removed = heap[0];
        heap[0] = heap[--size];
        minHeapify(0);
        return removed;
    }

    private void minHeapify(int i) {
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);
        int smallest = i;

        if (leftChild < size && heap[leftChild].compareTo(heap[smallest]) < 0) {
            smallest = leftChild;
        }

        if (rightChild < size && heap[rightChild].compareTo(heap[smallest]) < 0) {
            smallest = rightChild;
        }

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    public Heap buildHeap(int maxSize, String filePath) throws IOException {
        int[] letters = new int[maxSize];
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int currentLetter;
        while ((currentLetter = reader.read()) != -1) {
            if (currentLetter > 256) {
                System.out.println(currentLetter);
                System.out.println((char)currentLetter);
            }
            letters[currentLetter]++;
        }
        Heap huffHeap = new Heap(maxSize * 2 + 1);
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > 0) {
                huffHeap.insert(new Node(letters[i], (char) i));
                System.out.println((char) i + ": " + letters[i]);
            }
        }
        reader.close();
        return huffHeap;
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

    public static void buildDictionary(Node node, String code) {
        if (node == null) {
            return;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            node.setCode(code);
        }

        buildDictionary(node.getLeft(), code + "0");

        buildDictionary(node.getRight(), code + "1");
    }

}
