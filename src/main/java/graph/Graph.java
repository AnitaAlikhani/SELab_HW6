package graph;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import org.javatuples.Pair;


public class Graph {
    @Getter
    private ArrayList<Node> graph;
    private final CalculateDistanceStrategy distanceStrategy;

    public Graph(ArrayList<Node> graph) {
        this.graph = graph;
        this.distanceStrategy = getDistanceStrategy(graph);
    }

    public void resetVisits() {
        for (Node v : this.getGraph())
            v.setVisited(false);
    }

    public CalculateDistanceStrategy getDistanceStrategy(ArrayList<Node> graph) {
        for (Node n : graph) {
            for (Edge e : n.getEdges()) {
                if (e.getWeight() != 0) {
                    return new DijkstraStrategy();  // if it is a weighted graph, use Dijkstra
                }
            }
        }
        return new BFSStrategy();
    }

    public void calculateDistance(Node s) {
        this.resetVisits();
        this.distanceStrategy.calculateDistance(s);
    }

}
