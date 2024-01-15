package pl.edu.pw.ee.aisd2023zlab7.shortestpaths.dijkstra;

import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2023zlab7.data.input.GraphUtils;
import pl.edu.pw.ee.aisd2023zlab7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2023zlab7.data.outcome.DijkstraResult;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_W_GRAPH_5_5;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_W_GRAPH_6_6;

public class DijkstraV1Test {

    private WeightedGraph graph;
    private DijkstraV1 dijkstra;

    @BeforeEach
    public void setup() {
        dijkstra = new DijkstraV1();
    }

    @Test
    public void should_ReturnMaxIntValue_When_PathNotFound() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_5_5);
        int srcId = 0;
        int destId = 4;

        // when
        DijkstraResult result = dijkstra.findShortestPath(graph, srcId, destId);

        // then
        int expectedResult = Integer.MAX_VALUE;
        assertThat(result.getMinDistance()).isEqualTo(expectedResult);
    }

    @Test
    public void should_ReturnMinDistance_When_PathExist() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_6_6);
        int srcId = 0;
        int destId = 3;

        // when
        DijkstraResult result = dijkstra.findShortestPath(graph, srcId, destId);

        // then
        int expectedResult = 10;
        assertThat(result.getMinDistance()).isEqualTo(expectedResult);
    }

    @Test
    public void should_ReturnAllMinDistances_When_PathsExists() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_6_6);
        int srcId = 0;
        int destId = 3;

        // when
        DijkstraResult result = dijkstra.findShortestPath(graph, srcId, destId);

        // then
        int[] expectedDistances = {0, 2, 5, 10, 4, 10};
        assertThat(result.getDistances()).containsExactly(expectedDistances);
    }

}
