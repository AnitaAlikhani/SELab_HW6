import graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TravelContextTest {

    private Graph graph;

    @BeforeEach
    public void setup() {
        //TODO create graph and edges
    }

    @Test
    public void testTrainDistanceCalculation() {
        Node cityA = new Node();
        Node cityB = new Node();

        TravelContext context = new TravelContext();
        context.setStrategy(new TrainStrategy(1));
        int distance = context.calculateDistance(cityA, cityB);

        assertEquals(1, distance);
    }

    @Test
    public void testBusDistanceCalculation() {
        Node cityA = new Node();
        Node cityB = new Node();

        TravelContext context = new TravelContext();
        context.setStrategy(new BusStrategy());
        int distance = context.calculateDistance(cityA, cityB);

        assertEquals(1, distance);
    }

}
