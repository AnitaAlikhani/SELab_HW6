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
        Node a = new Node();
        a.setName("a");
        Node b = new Node();
        b.setName("b");
        Node c = new Node();
        c.setName("c");
        graph.getGraph().add(a);
        graph.getGraph().add(b);
        graph.getGraph().add(c);
        Edge e1 = new Edge(a, b, false, 0);
        Edge e2 = new Edge(b , c, true, 0);
        graph.getNodeByName("a").getEdges().add(e1);
        graph.getNodeByName("b").getEdges().add(e1);
        graph.getNodeByName("b").getEdges().add(e2);
        graph.getNodeByName("c").getEdges().add(e2);
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

}
