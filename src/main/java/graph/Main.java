package graph;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(new ArrayList<>()); // Initialize your graph with nodes and edges
        TravelContext travelContext = new TravelContext();
        RouteState state = new BidirectionalState();
        Node from, to;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the city names in each line, enter 'end' if the list is finished");
        while (true) {
            String in = scanner.nextLine();
            if (in.equalsIgnoreCase("end"))
                break;
            Node city = new Node();
            city.setName(in);
            graph.getGraph().add(city);
        }

        System.out.println("Please enter the name of 2 cities which are connected, in each line (separated by space), enter 'end' if the list is finished");
        while (true) {
            String in = scanner.nextLine();
            if (in.equalsIgnoreCase("end"))
                break;

            String[] cities = in.split(" ");
            from = graph.getNodeByName(cities[0]);
            to = graph.getNodeByName(cities[1]);
            Edge edge = new Edge(from, to, false, 0);
            from.getEdges().add(edge);
            to.getEdges().add(edge);
        }

        while (true) {
            System.out.println("Enter your request: (if you want to end, enter 'exit')");
            System.out.println("Requests: uni-dir, bi-dir, ch train t, t dist by train, t dist by bus, fastest way by, can go from");
            String request = scanner.nextLine();

            if (request.equalsIgnoreCase("exit"))
                break;

            if (request.equalsIgnoreCase("uni-dir")) {  // Governor requests
                state.makeUnidirectional(graph);
            } else if (request.equalsIgnoreCase("bi-dir")) {
                state.makeBidirectional(graph);
            } else if (request.startsWith("ch train t")) {
                String[] parts = request.split(" ");
                int newTimeUnit = Integer.parseInt(parts[3]);
                travelContext.setStrategy(new TrainStrategy(newTimeUnit));
            } else if (request.startsWith("t dist by train")) {  // Citizen requests
                String[] parts = request.split(" ");
                System.out.println("Train distance: " + calculateDistanceByStrategy(travelContext, new TrainStrategy(1), graph.getNodeByName(parts[4]), graph.getNodeByName(parts[5])));
            } else if (request.startsWith("t dist by bus")) {
                String[] parts = request.split(" ");
                System.out.println("Bus distance: " + calculateDistanceByStrategy(travelContext, new BusStrategy(), graph.getNodeByName(parts[4]), graph.getNodeByName(parts[5])));
            } else if (request.startsWith("fastest way by")) {
                String[] parts = request.split(" ");
                from = graph.getNodeByName(parts[3]);
                to = graph.getNodeByName(parts[4]);
                int trainDistance = calculateDistanceByStrategy(travelContext, new TrainStrategy(1), from, to);
                int busDistance = calculateDistanceByStrategy(travelContext, new BusStrategy(), from, to);
                if (trainDistance < busDistance)
                    System.out.println("train");
                else
                    System.out.println("bud");
            } else if (request.startsWith("can go from")) {
                String[] parts = request.split(" ");
                from = graph.getNodeByName(parts[3]);
                to = graph.getNodeByName(parts[4]);
                Node hateCity = graph.getNodeByName(parts[5]);
                hateCity.setVisited(true);
                int trainDistance = calculateDistanceByStrategy(travelContext, new TrainStrategy(1), from, to);
                int busDistance = calculateDistanceByStrategy(travelContext, new BusStrategy(), from, to);
                if (trainDistance != 0 || busDistance != 0)
                    System.out.println("yes");
                else
                    System.out.println("no");
            }
        }

        scanner.close();
    }

    private static int calculateDistanceByStrategy(TravelContext travelContext, TransportationStrategy strategy, Node from, Node to) {
        travelContext.setStrategy(strategy);
        return travelContext.calculateDistance(from, to);
    }

}

