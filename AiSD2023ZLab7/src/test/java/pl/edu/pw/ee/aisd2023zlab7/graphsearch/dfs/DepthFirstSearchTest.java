package pl.edu.pw.ee.aisd2023zlab7.graphsearch.dfs;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2023zlab7.data.input.AdjacencyMatrix;
import pl.edu.pw.ee.aisd2023zlab7.data.input.Graph;
import pl.edu.pw.ee.aisd2023zlab7.data.outcome.GraphDfsResult;
import pl.edu.pw.ee.aisd2023zlab7.graphsearch.services.GraphSearch;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_GRAPH_9_9;
import static pl.edu.pw.ee.aisd2023zlab7.utils.ConstPathsToFiles.PATH_GRAPH_3_3;

public class DepthFirstSearchTest {

    private GraphSearch graphSearch;

    @BeforeEach
    public void setup() {
        graphSearch = new DepthFirstSearch();
    }

    @Test
    public void should_ReturnDfsResult_When_GraphIsDisconected() {
        // given
        Graph graph = new AdjacencyMatrix(PATH_GRAPH_9_9);
        int startVerticeId = 0;

        // when
        GraphDfsResult result = (GraphDfsResult) graphSearch.searchGraphPaths(graph, startVerticeId);

        // then
        assertThat(result.getInputOrder()).containsExactly(new int[]{1, 2, 3, 4, 5, 6, 10, 15, 16});
        assertThat(result.getOutputOrder()).containsExactly(new int[]{14, 13, 12, 9, 8, 7, 11, 18, 17});
        assertThat(result.getPrevVertices()).containsExactly(new int[]{-1, 0, 1, 2, 3, 4, 2, -1, 7});
    }
    @Test
    public void should_ReturnDfsResult_When_GraphIsConnected() {
        // given
        Graph graph = new AdjacencyMatrix(PATH_GRAPH_3_3);
        int startVerticeId = 0;

        // when
        GraphDfsResult result = (GraphDfsResult) graphSearch.searchGraphPaths(graph, startVerticeId);

        // then
        assertThat(result.getInputOrder()).containsExactly(new int[]{1, 2, 4});
        assertThat(result.getOutputOrder()).containsExactly(new int[]{6, 3, 5});
        assertThat(result.getPrevVertices()).containsExactly(new int[]{-1, 0, 0});
    }

}
