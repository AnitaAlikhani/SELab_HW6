package graph;

public class BidirectionalState implements RouteState {
    @Override
    public void makeBidirectional(Graph graph) {
        // this route is already bidirectional
    }

    @Override
    public void makeUnidirectional(Graph graph) {
        for (Node node : graph.getGraph()) {
            for (Edge edge : node.getEdges()) {
                edge.setDirected(true);  // change all the ways to uni-directional
            }
        }
    }
}
