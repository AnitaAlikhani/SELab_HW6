package graph;

public class UnidirectionalState implements RouteState {

    @Override
    public void makeBidirectional(Graph graph) {
        graph.setState(new BidirectionalState());
    }

    @Override
    public void makeUnidirectional(Graph graph) {

    }

}
