package pl.edu.pw.ee.aisd2023zlab7.graphsearch.bfs;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import pl.edu.pw.ee.aisd2023zlab7.graphsearch.services.GraphSearch;

public class BreadthFirstSeaerchTest {

    private GraphSearch graphSearch;

    @BeforeEach
    public void setup() {
        graphSearch = new BreadthFirstSeaerch();
    }

//    @Test
//    public void should_ReturnBfsResult_when_GraphIsDisconnected() {
//        // given
//        Graph graph = new AdjacencyMatrix(PATH_GRAPH_9_9);
//        int startVerticeId = 0;
//
//        // when
//        GraphBfsResult result = (GraphBfsResult) graphSearch.searchGraphPaths(graph, startVerticeId);
//
//        // then
//        assertThat(result.getDistance()).containsExactly(new int[]{0, 1, 2, 2, 1, 2, 3, MAX_VALUE, MAX_VALUE});
//        assertThat(result.getPrevVertices()).containsExactly(new int[]{-1, 0, 1, 4, 0, 4, 2, -1, -1});
//    }

}
