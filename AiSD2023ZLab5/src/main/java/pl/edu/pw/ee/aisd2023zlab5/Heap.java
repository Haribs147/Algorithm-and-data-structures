package pl.edu.pw.ee.aisd2023zlab5;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Heap {

    private Node[] heap;
    private int size;
    private int maxSize;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new Node[maxSize];
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos + 1);
    }

    private int rightChild(int pos) {
        return (2 * pos + 2);
    }

    public int getSize() {
        return size;
    }

    public void insert(Node newElem) {
        if (size >= maxSize) {
            return;
        }
        heap[size] = newElem;
        heapUp(size);
        size++;
    }

    private void heapUp(int pos) {
        int posParent = parent(pos);
        Node newElem = heap[pos];
        while (pos > 0 && newElem.compareTo(heap[posParent]) < 0) {
            heap[pos] = heap[posParent];
            pos = posParent;
            posParent = parent(pos);
        }
        heap[pos] = newElem;
    }

    public Node remove() {
        if (size == 0) {
            return null;
        }
        Node removed = heap[0];
        heap[0] = heap[--size];
        heapDown(0);
        return removed;
    }

    private void heapDown(int pos) {
        Node first = heap[pos];
        int left = leftChild(pos);
        int right = rightChild(pos);
        //dopóki nie jest liściem
        //sprawdź czy lewy jest większy od prawego i czy taki istnieje, jeżeli jest to idź do prawego
        //jak nie to do lewego
        //jeżeli nowy node jest większy od prawego/lewego to koniec
        //jak nie to zamień nowy node z prawym/lewym i ich indeksy
        int i;
        while (pos < size / 2) {
            if (right < size && heap[left].compareTo(heap[right]) > 0)
                i = right;
            else
                i = left;
            if (first.compareTo(heap[i]) <= 0)
                break;
            heap[pos] = heap[i];
            pos = i;
            left = leftChild(pos);
            right = rightChild(pos);
        }
        heap[pos] = first;
    }

    public Heap buildHeap(int maxSize, String filePath) throws IOException {
        int[] letters = new int[maxSize];
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int currentLetter;
        int n = 0;
        Path path = Path.of(filePath);
        if (Files.size(path) < 3) {
            throw new IllegalArgumentException("Po co ty chcesz kompresować mniej niż trzy znaki?!?!?!?");
        }
        while ((currentLetter = reader.read()) != -1) {
            if (currentLetter > 256) {
                throw new IllegalArgumentException("Prosze zapisac plik w ascii, a jak dalej nie bedzie dzialac to znaczy ze w pliku sa znaki nie ascii");
            }
            letters[currentLetter]++;
        }
        Heap huffHeap = new Heap(maxSize * 2 + 1);
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > 0) {
                huffHeap.insert(new Node(letters[i], (char) i));
                n++;
            }
        }
        if (n == 1) {
            huffHeap.insert(new Node(0, 'f'));
        }
        reader.close();
        return huffHeap;
    }
}
