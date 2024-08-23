import graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TravelContextTest {

    private Graph graph;

    @BeforeEach
    public void setup() {
        graph = new Graph(new ArrayList<>());
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        graph.getGraph().add(a);
        graph.getGraph().add(b);
        graph.getGraph().add(c);
        Edge.createEdge(a, b, false, 0);
        Edge.createEdge(b , c, false, 0);  // bi-directional route
    }

    @Test
    public void testTrainDistanceCalculation() {
        TravelContext context = new TravelContext();
        context.setStrategy(new TrainStrategy(1));
        int distance = context.calculateDistance(graph.getNodeByName("a"), graph.getNodeByName("b"));

        assertEquals(1, distance);
    }

    @Test
    public void testBusDistanceCalculation() {
        TravelContext context = new TravelContext();
        context.setStrategy(new BusStrategy());
        int distance = context.calculateDistance(graph.getNodeByName("a"), graph.getNodeByName("c"));

        assertEquals(2, distance);
    }

    @Test
    public void testMakeBidirectional() {
        graph.switchToBiDir();
        for (Node node : graph.getGraph()) {
            for (Edge edge : node.getEdges()) {
                assertFalse(edge.isDirected());  // all the edges must be undirected
            }
        }
    }

    @Test
    public void testMakeUnidirectional() {
        graph.switchToUniDir();
        for (Node node : graph.getGraph()) {
            for (Edge edge : node.getEdges()) {
                assertTrue(edge.isDirected());  // all the edges must be directed
            }
        }
    }

}
