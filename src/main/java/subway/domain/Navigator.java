package subway.domain;

import java.util.List;

public class Navigator {
    private final Graph timeGraph;
    private final Graph distanceGraph;

    private Navigator(Graph timePathRepository, Graph distancePathRepository) {
        this.timeGraph = timePathRepository;
        this.distanceGraph = distancePathRepository;
    }

    public static Navigator getInstance() {
        Graph timeGraph = new Graph();
        Graph distanceGraph = new Graph();
        PathRepository.init();
        initTimePath(timeGraph);
        initDistancePath(distanceGraph);
        return new Navigator(timeGraph, distanceGraph);
    }


    private static void initTimePath(Graph timeGraph) {
        List<Path> paths = PathRepository.getPaths();
        for (Path path : paths) {
            timeGraph.addEdge(path.getStation1(), path.getStation2(), path.getTime());
        }
    }

    private static void initDistancePath(Graph distancePath) {
        List<Path> paths = PathRepository.getPaths();
        for (Path path : paths) {
            distancePath.addEdge(path.getStation1(), path.getStation2(), path.getDistance());
        }
    }

    public List<Station> findPathByTime(Station startStation, Station endStation) {
        return timeGraph.findPath(startStation, endStation);
    }

    public List<Station> findPathByDistance(Station startStation, Station endStation) {
        return distanceGraph.findPath(startStation, endStation);
    }
}
