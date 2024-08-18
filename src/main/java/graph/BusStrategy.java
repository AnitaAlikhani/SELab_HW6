package graph;

import java.util.ArrayList;

public class BusStrategy implements TransportationStrategy {

    @Override
    public int calculateDistance(Node from, Node to) {
        // Use Dijkstra's algorithm to calculate distance for buses
        Graph graph = new Graph(new ArrayList<>()); // Assume graph is initialized properly
        graph.dijkstra(from);
        return to.getDistance(); // Distance in time units
    }

}
