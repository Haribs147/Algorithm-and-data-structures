package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DecompressorTest {

    @Test
    public void testDecompress() throws IOException {

        String filePathRead = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\fileForTests\\decompressingTestFile.comp";
        String filePathWrite = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\decompressedFile1.txt";

        HuffTree huffTree = new HuffTree();
        Decompressor decompressor = new Decompressor(huffTree);
        decompressor.decompress(filePathRead, filePathWrite);

        String originalContent = "AAAABBBC";

        String decompressedContent = new String(Files.readAllBytes(Path.of(filePathWrite)));

        assertThat(originalContent).isEqualTo(decompressedContent);


    }

    @Test
    public void testDecompressWhenFilledIsNot8() throws IOException {

        String filePathRead = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\fileForTests\\decompressingTestFile1.comp";
        String filePathWrite = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\decompressedFile2";

        HuffTree huffTree = new HuffTree();
        Decompressor decompressor = new Decompressor(huffTree);
        decompressor.decompress(filePathRead, filePathWrite);

        String originalContent = "AAAABBBCD";

        String decompressedContent = new String(Files.readAllBytes(Path.of(filePathWrite + ".txt")));

        assertThat(originalContent).isEqualTo(decompressedContent);

    }

    @Test
    public void testDecompressWhenFilledIs0AndWithGreaterFile() throws IOException {

        String filePathRead = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\fileForTests\\decompressingTestFile2.comp";
        String filePathWrite = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\tempFiles\\decompressedFile3";

        HuffTree huffTree = new HuffTree();
        Decompressor decompressor = new Decompressor(huffTree);
        decompressor.decompress(filePathRead, filePathWrite);

        String originalContent = new String(Files.readAllBytes(Path.of("Romeo.txt")));

        String decompressedContent = new String(Files.readAllBytes(Path.of(filePathWrite + ".txt")));

        assertThat(originalContent).isEqualTo(decompressedContent);

    }

}