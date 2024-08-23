package graph;

import java.util.ArrayList;

public class BusStrategy implements TransportationStrategy {
    @Override
    public int calculateDistance(Node from, Node to) {
        Graph graph = new Graph(new ArrayList<>());
        graph.dijkstra(from);
        return to.getDistance();
    }
}
