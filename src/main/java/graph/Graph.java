package graph;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import lombok.Setter;
import org.javatuples.Pair;


public class Graph {
    @Getter
    private ArrayList<Node> graph;
    @Setter
    private RouteState state;

    public Graph(ArrayList<Node> graph) {
        this.graph = graph;
        this.state = new BidirectionalState();  // default direction is 2 way, directed = false
    }

    public void resetVisits() {
        for (Node v : this.getGraph())
            v.setVisited(false);
    }

    public Node getNodeByName(String nodeName) {
        for (Node n : graph) {
            if (n.getName().equals(nodeName))
                return n;
        }
        return null;
    }

    public void bfs(Node s) {
        this.resetVisits();

        Queue<Pair<Node, Integer>> nodes = new LinkedList<>();
        nodes.add(new Pair<>(s, 0));
        while (!nodes.isEmpty()) {
            Pair<Node, Integer> front = nodes.poll();
            Node frontNode = front.getValue0();
            if (!frontNode.isVisited()) {
                frontNode.setVisited(true);
                int distance = front.getValue1();
                frontNode.setDistance(distance);
                nodes.addAll(frontNode.getAvailableNeighbors()
                        .stream()
                        .map(neighbor -> new Pair<>(neighbor, distance + 1))
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        }
    }

    public void dijkstra(Node s) {
        this.resetVisits();

        PriorityQueue<Pair<Integer, Node>> nodes = new PriorityQueue<>();
        nodes.add(new Pair<>(0, s));
        while (!nodes.isEmpty()) {
            Pair<Integer, Node> front = nodes.poll();
            Node frontNode = front.getValue1();
            if (!frontNode.isVisited()) {
                frontNode.setVisited(true);
                int distance = front.getValue0();
                frontNode.setDistance(distance);
                nodes.addAll(frontNode.getAvailableWeightedNeighbors()
                        .stream()
                        .map(neighbor -> new Pair<>(neighbor.getValue1() + distance,
                                neighbor.getValue0()))
                        .collect(Collectors.toCollection(PriorityQueue::new)));
            }
        }
    }

    public void switchToUniDir() {
        this.state = new UnidirectionalState();
        this.state.makeUnidirectional(this);
    }

    public void switchToBiDir() {
        this.state = new BidirectionalState();
        this.state.makeBidirectional(this);
    }
}
