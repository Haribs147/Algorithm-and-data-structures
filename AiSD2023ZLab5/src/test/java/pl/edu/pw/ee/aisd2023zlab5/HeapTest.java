package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
public class HeapTest {

    private void fileBuilder(String fileName, String fileText){
        Path filePath = Path.of(fileName);
        try {
            Files.writeString(filePath, fileText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void heapShouldDeleteRight() {
        Heap heap = new Heap(10);
        heap.insert(new Node(5,'a'));
        heap.insert(new Node(3,'b'));
        heap.insert(new Node(17,'c'));
        heap.insert(new Node(10,'d'));
        heap.insert(new Node( 84,'e'));
        heap.insert(new Node(19,'f'));
        heap.insert(new Node(6,'g'));
        heap.insert(new Node(22,'h'));
        heap.insert(new Node(9,'i'));
        assertThat(heap.getSize()).isEqualTo(9);
        Node removed = heap.remove();
        assertThat(removed.getLetter()).isEqualTo('b');
        assertThat(removed.getValue()).isEqualTo(3);
        for(int i = 0; i <6; i++) {
            removed = heap.remove();
        }
        assertThat(heap.getSize()).isEqualTo(2);
    }

    @Test
    public void testEmptyHeap() {
        Heap heap = new Heap(5);

        assertThat(heap.getSize()).isEqualTo(0);

        assertThat(heap.remove()).isEqualTo(null);
    }


    @Test
    public void multipleInsertionsAndDeletionsShouldWork() {
        Heap heap = new Heap(10);

        heap.insert(new Node(3, 'A'));
        heap.insert(new Node(7, 'B'));
        heap.insert(new Node(5, 'C'));
        heap.insert(new Node(1, 'D'));

        assertThat(heap.getSize()).isEqualTo(4);
        Node removedNode = heap.remove();
        assertThat(removedNode.getValue()).isEqualTo(1);
        assertThat(removedNode.getLetter()).isEqualTo('D');

        removedNode = heap.remove();
        assertThat(removedNode.getValue()).isEqualTo(3);
        assertThat(removedNode.getLetter()).isEqualTo('A');

        assertThat(heap.getSize()).isEqualTo(2);
    }

    @Test
    public void testCompareToNodes() {
        Node node1 = new Node(5, 'A');
        Node node2 = new Node(7, 'B');
        Node node3 = new Node(5, 'C');
        assertThat(node1.compareTo(node2)).isLessThan(0);
        assertThat(node2.compareTo(node1)).isGreaterThan(0);
        assertThat(node1.compareTo(node3)).isEqualTo(0);
    }



    @Test
    public void testIsHeapBuiltRightFromAFile() {
        Heap heap = new Heap(512);;
        try {
            fileBuilder("src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\niemanie.txt", "niemanie");
            heap = heap.buildHeap(512, "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\niemanie.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(heap.getSize()).isEqualTo(5);
        assertThat(heap.remove().getLetter()).isEqualTo('a');
        assertThat(heap.remove().getLetter()).isEqualTo('m');
    }

    @Test
    public void testIfHeapIsMaxCannotInsertNew() {
        int maxSize = 2;
        Heap heap = new Heap(maxSize);
        heap.insert(new Node(5,'a'));
        heap.insert(new Node(3,'b'));
        int size = heap.getSize();
        assertThat(size).isEqualTo(2);
        heap.insert(new Node(17,'c'));
        size = heap.getSize();
        assertThat(size).isEqualTo(2);
    }

    @Test
    public void should_ThrowException_WhenFileIsLessThan3Bytes() {
        // given
        int size = 0;
        Heap heap = new Heap(512);
        Throwable exceptionCaught;

        // when
        exceptionCaught = catchThrowable(() -> {
            fileBuilder("src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\mniejNizTrzy.txt", "ab");
            heap.buildHeap(512, "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\mniejNizTrzy.txt");
        });
        // then
        String message = "Po co ty chcesz kompresować mniej niż trzy znaki?!?!?!?";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ThrowException_WhenFileHasMoreThan1ByteLetters() {
        // given
        int size = 0;
        Heap heap = new Heap(512);
        Throwable exceptionCaught;

        // when
        exceptionCaught = catchThrowable(() -> {
            fileBuilder("src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\notASCII.txt", "ąęłć");
            heap.buildHeap(512, "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\notASCII.txt");
        });
        // then
        String message = "Prosze zapisac plik w ascii, a jak dalej nie bedzie dzialac to znaczy ze w pliku sa znaki nie ascii";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

}