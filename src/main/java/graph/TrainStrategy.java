package graph;

import java.util.ArrayList;

public class TrainStrategy implements TransportationStrategy {

    private final int trainTimeUnit;

    public TrainStrategy(int trainTimeUnit) {
        this.trainTimeUnit = trainTimeUnit;
    }

    @Override
    public int calculateDistance(Node from, Node to) {
        // Use BFS to calculate distance for trains (1 unit time)
        Graph graph = new Graph(new ArrayList<>()); // Assume graph is initialized properly
        graph.bfs(from);
        return to.getDistance() * trainTimeUnit; // Distance in time units
    }

}
