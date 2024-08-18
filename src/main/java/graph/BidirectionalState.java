package graph;

public class BidirectionalState implements RouteState {

    @Override
    public void makeBidirectional(Graph graph) {
        for (Node node : graph.getGraph()) {
            for (Edge edge : node.getEdges()) {
                if (edge.isDirected()) {
                    Edge.createEdge(edge.getNodes().getValue1(), edge.getNodes().getValue0(), false, edge.getWeight());
                }
            }
        }
    }

    @Override
    public void makeUnidirectional(Graph graph) {
        graph.setState(new UnidirectionalState());
    }

}
