package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class CompressorTest {

    @Test
    public void testDictionaryEncoding() {
        Node leafA = new Node(5, 'A');
        Node leafB = new Node(3, 'B');
        Node root = new Node(0, 'R');
        root.setLeft(leafA);
        root.setRight(leafB);
        HuffTree tree = new HuffTree();
        tree.setRoot(root);
        Compressor compressor = new Compressor(tree);
        String encoded = compressor.dictionaryEncoding();
        String expected = "0101000001101000010";
        assertThat(encoded).isEqualTo(expected);
    }

    @Test
    public void testConstructorMakingTreeAndFileEncoding() throws IOException {
        String filePathRead = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\fileForTests\\fileEncodingTest.txt";
        String filePathWrite = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\encodedTestFile";
        String filePathTesting = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\fileForTests\\encodedTestFile1.comp";
        Compressor compressor = new Compressor(filePathRead);
        compressor.compressTheFile(compressor.dictionaryEncoding(), filePathWrite, filePathRead);

        Path path1 = Paths.get(filePathWrite);
        Path path2 = Paths.get(filePathTesting);
        boolean i = false;
        try {
            byte[] content1 = Files.readAllBytes(path1);
            byte[] content2 = Files.readAllBytes(path2);

            i = Arrays.equals(content1, content2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(i).isEqualTo(true);
    }

    @Test
    public void testConstructorMakingTreeAndFileEncodingWhenFilledIs8() throws IOException {
        String filePathRead = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\fileForTests\\fileEncodingTestFilled8.txt";
        String filePathWrite = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\encodedTestFile1";
        String filePathTesting = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\fileForTests\\encodedTestFileFilled8.comp";
        Compressor compressor = new Compressor(filePathRead);
        compressor.compressTheFile(compressor.dictionaryEncoding(), filePathWrite, filePathRead);

        Path path1 = Paths.get(filePathWrite);
        Path path2 = Paths.get(filePathTesting);
        boolean i = false;
        try {
            byte[] content1 = Files.readAllBytes(path1);
            byte[] content2 = Files.readAllBytes(path2);

            i = Arrays.equals(content1, content2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(i).isEqualTo(true);
    }

}