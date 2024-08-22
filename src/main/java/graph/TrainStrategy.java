package graph;

import java.util.ArrayList;

public class TrainStrategy implements TransportationStrategy {

    private final int trainTimeUnit;

    public TrainStrategy(int trainTimeUnit) {
        this.trainTimeUnit = trainTimeUnit;
    }

    @Override
    public int calculateDistance(Node from, Node to) {
        Graph graph = new Graph(new ArrayList<>());
        graph.bfs(from);
        return to.getDistance() * trainTimeUnit;
    }

}
