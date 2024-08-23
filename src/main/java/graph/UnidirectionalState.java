package graph;

public class UnidirectionalState implements RouteState {
    @Override
    public void makeBidirectional(Graph graph) {
        for (Node node : graph.getGraph()) {
            for (Edge edge : node.getEdges()) {
                edge.setDirected(false);  // change all the ways to bi-directional
            }
        }
    }

    @Override
    public void makeUnidirectional(Graph graph) {
        // this route is already unidirectional
    }
}
