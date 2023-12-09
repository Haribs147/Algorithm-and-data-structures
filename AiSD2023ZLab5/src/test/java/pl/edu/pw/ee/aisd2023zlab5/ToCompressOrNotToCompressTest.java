package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ToCompressOrNotToCompressTest {

    @Test
    public void shouldReturnExceptionWhenInvalidArguments() {
        String[] noArguments = {};
        Throwable exceptionCaught = catchThrowable(() -> {
            new ToCompressOrNotToCompress(noArguments);
        });
        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Podaj plik wejściowy do kompresji i wyjściowy jaki ma być skompresowany, albo wejściowy do dekompresji i wyjściowy jaki ma być zdekompresowany");
    }

    @Test
    public void constructor_WithSameInputAndOutputFileNames_ThrowsRuntimeException() {
        String[] sameArguments = {"theSameName.txt", "theSameName.txt"};
        Throwable exceptionCaught = catchThrowable(() -> {
            new ToCompressOrNotToCompress(sameArguments);
        });
        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Plik wejściowy i wyjściowy nie mogą się tak samo nazywać");
    }

    @Test
    public void getFilePathReadandGetFilePathWriteWork() {
        String[] validArgs = {"input.txt", "output.txt"};
        ToCompressOrNotToCompress compressor = new ToCompressOrNotToCompress(validArgs);

        assertThat(compressor.getFilePathRead()).isEqualTo("input.txt");
        assertThat(compressor.getFilePathWrite()).isEqualTo("output.txt");
    }

    @Test
    public void checkIfDecompressionFileOnInputIsGoodOnOutput() {
        String[] validArgs = {"input.comp", "output.txt"};
        ToCompressOrNotToCompress compressor = new ToCompressOrNotToCompress(validArgs);

        int result = compressor.checkIfCompressAndChangeFileNames();

        assertThat(result).isEqualTo(1);
        assertThat(compressor.getFilePathWrite()).isEqualTo("output.txt");
    }

    @Test
    public void checkIfCompressionFileOnInputIsGoodOnOutput() {
        String[] validArgs = {"input.txt", "output.txt"};
        ToCompressOrNotToCompress compressor = new ToCompressOrNotToCompress(validArgs);

        int result = compressor.checkIfCompressAndChangeFileNames();

        assertThat(result).isEqualTo(0);
        assertThat(compressor.getFilePathWrite()).isEqualTo("output.comp");
    }
    @Test
    public void shouldAddCompToAFileWhenInputIsNotTxt() {
        String[] validArgs = {"input", "output"};
        ToCompressOrNotToCompress compressor = new ToCompressOrNotToCompress(validArgs);

        int result = compressor.checkIfCompressAndChangeFileNames();

        assertThat(compressor.getFilePathWrite()).isEqualTo("output.comp");
    }
    @Test
    public void shouldAddCompToAFileWhenInputIsTxt() {
        String[] validArgs = {"input.txt", "output"};
        ToCompressOrNotToCompress compressor = new ToCompressOrNotToCompress(validArgs);

        int result = compressor.checkIfCompressAndChangeFileNames();

        assertThat(compressor.getFilePathWrite()).isEqualTo("output.comp");
    }
    @Test
    public void shouldAddCompToAFileWhenInputIsNotTxtAndOutputIsTxt() {
        String[] validArgs = {"input", "output.txt"};
        ToCompressOrNotToCompress compressor = new ToCompressOrNotToCompress(validArgs);

        int result = compressor.checkIfCompressAndChangeFileNames();

        assertThat(compressor.getFilePathWrite()).isEqualTo("output.comp");
    }
}
