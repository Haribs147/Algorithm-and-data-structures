package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class HuffTreeTest {
    @Test
    public void buildDictionary_WithTree_BuildsDictionaryCorrectly() throws IOException {
        String filePathRead = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\niemanie.txt";
        fileBuilder(filePathRead, "alam");
        int maxSize = 1000;
        Heap huffHeap = new Heap(maxSize * 2 + 1);
        huffHeap = huffHeap.buildHeap(maxSize, filePathRead);
        HuffTree tree = new HuffTree(huffHeap);
        tree.buildDictionary();

        assertThat(tree.dictionary['a']).isEqualTo("0");
        assertThat(tree.dictionary['l']).isEqualTo("10");
        assertThat(tree.dictionary['m']).isEqualTo("11");
    }

    @Test
    public void builtTreeFromEncodedDictionary_WithEncodedData_BuildsTreeCorrectly() throws IOException {
        String filePathRead = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\kompresowany.comp";
        fileBuilder(filePathRead, "+e†€");
        int filled;
        int h;
        HuffTree tree = new HuffTree();
        try (FileInputStream fis = new FileInputStream(filePathRead)) {
            int byteRead;
            byteRead = fis.read();
            String binaryRepresentation = String.format("%8s", Integer.toBinaryString(byteRead)).replace(' ', '0');
            String filledString = binaryRepresentation.substring(0, 3);
            filled = Integer.parseInt(filledString, 2);
            String wynik = binaryRepresentation.substring(3, 8);
            h = tree.builtTreeFromEncodedDictionary(binaryRepresentation, fis);
            String read = tree.getRead();
            tree.printTreeWithValues(tree.getRoot(),4);

        } catch (IOException e) {
            e.printStackTrace();
        }


        assertThat(tree.getRoot().getLeft().getLetter()).isEqualTo('l');
        assertThat(tree.getRoot().getRight().getLetter()).isEqualTo('x');
    }
    private void fileBuilder(String fileName, String fileText){
        Path filePath = Path.of(fileName);
        try {
            Files.writeString(filePath, fileText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}