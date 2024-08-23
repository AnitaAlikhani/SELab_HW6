package graph;

import lombok.Setter;

@Setter
public class TravelContext {

    private TransportationStrategy strategy;

    public int calculateDistance(Node from, Node to) {
        return strategy.calculateDistance(from, to);
    }
}
