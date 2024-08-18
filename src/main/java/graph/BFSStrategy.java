package graph;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class BFSStrategy implements CalculateDistanceStrategy {
    @Override
    public void calculateDistance(Node s) {
        Queue<Pair<Node, Integer>> nodes = new LinkedList<>();
        nodes.add(new Pair<Node, Integer>(s, 0));
        while (!nodes.isEmpty()) {
            Pair<Node, Integer> front = nodes.poll();
            Node frontNode = front.getValue0();
            if (!frontNode.isVisited()) {
                frontNode.setVisited(true);
                int distance = front.getValue1();
                frontNode.setDistance(distance);
                nodes.addAll(frontNode.getAvailableNeighbors()
                        .stream()
                        .map(neighbor -> new Pair<Node, Integer>(neighbor, distance + 1))
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        }
    }
}
