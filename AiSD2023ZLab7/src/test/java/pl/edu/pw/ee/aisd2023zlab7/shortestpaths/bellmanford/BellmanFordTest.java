package pl.edu.pw.ee.aisd2023zlab7.shortestpaths.bellmanford;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2023zlab7.data.input.GraphUtils;
import pl.edu.pw.ee.aisd2023zlab7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2023zlab7.data.outcome.BellmanFordResult;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_W_GRAPH_6_6;

public class BellmanFordTest {

    private WeightedGraph graph;
    private BellmanFord bellmanFord;

    @BeforeEach
    public void setup() {
        bellmanFord = new BellmanFord();
    }

    @Test
    public void should_ReturnMinDistance_When_PathExist() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_6_6);
        int srcId = 0;
        int destId = 3;

        // when
        BellmanFordResult result = bellmanFord.findShortestPath(graph, srcId, destId);

        // then
        int expectedMinDistance = 10;
        assertThat(result.getMinDistance()).isEqualTo(expectedMinDistance);
    }

    // TODO: more test cases
}
