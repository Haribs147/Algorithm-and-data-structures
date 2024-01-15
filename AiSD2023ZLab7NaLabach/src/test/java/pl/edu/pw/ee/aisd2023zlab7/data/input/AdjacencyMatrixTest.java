package pl.edu.pw.ee.aisd2023zlab7.data.input;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_GRAPH_2_2;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_GRAPH_3_3;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_GRAPH_5_5;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_GRAPH_9_9;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_INCORRECT_0_2;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_INCORRECT_2_0;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_INCORRECT_2_2_2;

public class AdjacencyMatrixTest {

    private Graph graph;

    @Test
    public void should_ThrowException_When_GraphDataFileNotExist() {
        // given
        String pathToFile = "file_does_not_exist.txt";

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            graph = new GraphUtils().readMatrix(pathToFile);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cannot read the file (file not found)!");
    }

    @Test
    public void should_ThrowException_When_MatrixDimsIsGreaterThanTwo() {
        // given
        String pathToFile = PATH_INCORRECT_2_2_2;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            graph = new GraphUtils().readMatrix(pathToFile);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Incorrect result of parsing line (lineId: 1, data.length: 3)!");
    }

    @Test
    public void should_ThrowException_When_NumOfRowsIsLessThanOne() {
        // given
        String pathToFile = PATH_INCORRECT_0_2;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            graph = new GraphUtils().readMatrix(pathToFile);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The size of the adjacency matrix cannot be less than one!");

    }

    @Test
    public void should_ThrowException_When_NumOfColsIsLessThanOne() {
        // given
        String pathToFile = PATH_INCORRECT_2_0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            graph = new GraphUtils().readMatrix(pathToFile);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The size of the adjacency matrix cannot be less than one!");
    }

    @Test
    public void should_ThrowException_When_GettingNeighboursOfNotExistedVertice() {
        // given
        graph = new GraphUtils().readMatrix(PATH_GRAPH_2_2);

        // when
        int verticeIdGreaterThanRows = 3;

        Throwable exceptionCaught = catchThrowable(() -> {
            graph.getNeighbours(verticeIdGreaterThanRows);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Vertice ID does not exist!");
    }

    @Test
    public void should_ReturnNumOfVertices_When_AllVerticesAreConnected() {
        // given
        graph = new GraphUtils().readMatrix(PATH_GRAPH_3_3);

        // when
        int numOfVertices = graph.getNumOfVertices();

        // then
        int expectedNumOfVertices = 3;

        assertThat(numOfVertices).isEqualTo(expectedNumOfVertices);
    }

    @Test
    public void should_ReturnNumOfVertices_When_NotAllVerticesAreConnected() {
        // given
        graph = new GraphUtils().readMatrix(PATH_GRAPH_5_5);

        // when
        int numOfVertices = graph.getNumOfVertices();

        // then
        int expectedNumOfVertices = 5;

        assertThat(numOfVertices).isEqualTo(expectedNumOfVertices);
    }

    @Test
    public void should_ReturnNumOfEdges_When_AllVerticesAreConnected() {
        // given
        graph = new GraphUtils().readMatrix(PATH_GRAPH_3_3);

        // when
        int numOfEdges = graph.getNumOfEdges();

        // then
        int expectedNumOfEdges = 2;

        assertThat(numOfEdges).isEqualTo(expectedNumOfEdges);
    }

    @Test
    public void should_ReturnNumOfEdges_When_NotAllVerticesAreConnected() {
        // given
        graph = new GraphUtils().readMatrix(PATH_GRAPH_5_5);

        // when
        int numOfEdges = graph.getNumOfEdges();

        // then
        int expectedNumOfEdges = 3;

        assertThat(numOfEdges).isEqualTo(expectedNumOfEdges);
    }

    @ParameterizedTest
    @CsvSource({
        "0, '1, 4'",
        "1, '0, 2'",
        "2, '1, 3, 6'",
        "3, '2, 4'",
        "4, '0, 3, 5'",
        "5, '4'",
        "6, '2'",
        "7, '8'",
        "8, '7'"
    })
    public void should_ReturnNeighbours_When_NotAllNeighboursAreConnected(int verticeId, String expectedNeighboursAsStr) {
        // given
        graph = new GraphUtils().readMatrix(PATH_GRAPH_9_9);

        // when
        int[] neighbours = graph.getNeighbours(verticeId);

        // then
        int[] expectedNeighbours = splitStringToIntArr(expectedNeighboursAsStr);

        assertThat(neighbours).containsExactly(expectedNeighbours);
    }

    @Test
    public void should_ReturnAllVertices_When_GraphIsCorrect() {
        // given
        graph = new GraphUtils().readMatrix(PATH_GRAPH_9_9);

        // when
        int[] vertices = graph.getVertices();

        // then
        int[] expectedVertices = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        assertThat(vertices).containsExactly(expectedVertices);
    }

    private int[] splitStringToIntArr(String textToSplit) {
        String separator = ",";

        return stream(textToSplit.split(separator))
                .map(val -> val.trim())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
