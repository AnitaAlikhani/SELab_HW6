package graph;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(new ArrayList<>());
        TravelContext travelContext = new TravelContext();

        Scanner scanner = new Scanner(System.in);

        // create and add nodes to graph
        System.out.println("Please enter the city names in each line, enter 'end' if the list is finished");
        while (true) {
            String in = scanner.nextLine();
            if (in.equalsIgnoreCase("end"))
                break;

            Node city = new Node(in.toLowerCase());
            graph.getGraph().add(city);
        }

        // create edges
        System.out.println("Please enter the name of 2 cities and the weight of their edge (if is not 0) which are connected, in each line (separated by space), enter 'end' if the list is finished");
        while (true) {
            String in = scanner.nextLine();
            if (in.equalsIgnoreCase("end"))
                break;

            String[] cities = in.split(" ");

            Node from = graph.getNodeByName(cities[0]);
            Node to = graph.getNodeByName(cities[1]);

            if (from == null) {
                System.out.println(cities[0] + " doesn't exist");
                continue;
            }
            if (to == null) {
                System.out.println(cities[1] + " doesn't exist");
                continue;
            }

            if (cities.length == 3)
                Edge.createEdge(from, to, false, Integer.parseInt(cities[2]));
            else
                Edge.createEdge(from, to, false, 0);
        }

        // processing requests
        while (true) {
            System.out.println("Enter your request: (if you want to end, enter 'exit')");
            System.out.println("Requests: uni-dir, bi-dir, ch train t, t dist by train, t dist by bus, fastest way by, can go from");
            String request = scanner.nextLine();

            if (request.equalsIgnoreCase("exit"))
                break;

            if (request.equalsIgnoreCase("uni-dir")) { // Governor requests
                graph.switchToUniDir();
            } else if (request.equalsIgnoreCase("bi-dir")) {
                graph.switchToBiDir();
            } else if (request.startsWith("ch train t")) {
                String[] parts = request.split(" ");
                int newTimeUnit = Integer.parseInt(parts[3]);
                travelContext.setStrategy(new TrainStrategy(newTimeUnit));
            } else if (request.startsWith("t dist by train")) {  // Citizen requests
                String[] parts = request.split(" ");
                Node from = graph.getNodeByName(parts[4]);
                Node to = graph.getNodeByName(parts[5]);
                System.out.println("Train distance: " + calculateDistanceByStrategy(travelContext, new TrainStrategy(1), from, to));
            } else if (request.startsWith("t dist by bus")) {
                String[] parts = request.split(" ");
                Node from = graph.getNodeByName(parts[4]);
                Node to = graph.getNodeByName(parts[5]);
                System.out.println("Bus distance: " + calculateDistanceByStrategy(travelContext, new BusStrategy(), from, to));
            } else if (request.startsWith("fastest way by")) {
                String[] parts = request.split(" ");
                Node from = graph.getNodeByName(parts[3]);
                Node to = graph.getNodeByName(parts[4]);
                int trainDistance = calculateDistanceByStrategy(travelContext, new TrainStrategy(1), from, to);
                int busDistance = calculateDistanceByStrategy(travelContext, new BusStrategy(), from, to);
                if (trainDistance < busDistance)
                    System.out.println("train");
                else
                    System.out.println("bus");
            } else if (request.startsWith("can go from")) {
                String[] parts = request.split(" ");
                Node from = graph.getNodeByName(parts[3]);
                Node to = graph.getNodeByName(parts[4]);
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

