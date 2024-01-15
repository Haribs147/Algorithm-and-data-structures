package pl.edu.pw.ee.aisd2023zlab7.data.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.Test;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_INCORRECT_W_GRAPH_3_3;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_W_GRAPH_5_5;

public class WeightedAdjacencyMatrixTest {

    private WeightedGraph graph;

    @Test
    public void should_ThrowException_When_WeightIsEqualZero() {
        // given, when
        Throwable exceptionCaught = catchThrowable(() -> {
            graph = new GraphUtils().readWeightedMatrix(PATH_INCORRECT_W_GRAPH_3_3);
        });
        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The edge weight must be greater than 0!");
    }

    @Test
    public void should_ThrowException_When_SrcVerticeDoesNotExist() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_5_5);
        int unknownSrcId = 100;
        int knownDestId = 1;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            graph.getWeight(unknownSrcId, knownDestId);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Vertice ID (src or dest) does not exist!");
    }

    @Test
    public void should_ThrowException_When_DestVerticeDoesNotExist() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_5_5);
        int knownSrcId = 1;
        int unknownDestId = 100;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            graph.getWeight(knownSrcId, unknownDestId);
        });

        // then
        assertThat(exceptionCaught).
                isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Vertice ID (src or dest) does not exist!");
    }

    @Test
    public void should_ReturnWeight_When_EdgeExist() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_5_5);

        // when, then
        assertThat(graph.getWeight(0, 1)).isEqualTo(12);
        assertThat(graph.getWeight(1, 0)).isEqualTo(13);
        assertThat(graph.getWeight(1, 2)).isEqualTo(14);
        assertThat(graph.getWeight(2, 1)).isEqualTo(15);
        assertThat(graph.getWeight(3, 4)).isEqualTo(16);
        assertThat(graph.getWeight(4, 3)).isEqualTo(17);
    }

    // TODO: more test cases
}
