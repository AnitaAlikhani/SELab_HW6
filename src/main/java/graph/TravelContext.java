package graph;

public class TravelContext {

    private TransportationStrategy strategy;

    public void setStrategy(TransportationStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculateDistance(Node from, Node to) {
        return strategy.calculateDistance(from, to);
    }

}
