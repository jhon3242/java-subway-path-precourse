package subway.domain;

import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class Graph {
    private WeightedMultigraph<String, DefaultWeightedEdge> graph;

    public Graph() {
        graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        StationRepository.initStation();
        addVertex(StationRepository.findByName("교대역"));
        addVertex(StationRepository.findByName("강남역"));
        addVertex(StationRepository.findByName("역삼역"));
        addVertex(StationRepository.findByName("남부터미널역"));
        addVertex(StationRepository.findByName("양재역"));
        addVertex(StationRepository.findByName("양재시민의숲역"));
        addVertex(StationRepository.findByName("매봉역"));
    }

    private void addVertex(Station station) {
        graph.addVertex(station.getName());
    }

    public void addEdge(Station station1, Station station2, int weight) {
        graph.setEdgeWeight(graph.addEdge(station1.getName(), station2.getName()), weight);
    }

    public List<Station> findPath(Station startStation, Station endStation) {
        validatePath(startStation, endStation);
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList();
        return shortestPath.stream().map(StationRepository::findByName)
                .collect(Collectors.toList());
    }

    private void validatePath(Station startStation, Station endStation) {
        validateNull(startStation, endStation);
        validateDuplicate(startStation, endStation);
        validateHasPath(startStation, endStation);
    }

    private void validateNull(Station startStation, Station endStation) {
        if (startStation == null || endStation == null) {
            throw new IllegalArgumentException("출발역과 도착역이 존재하지 않습니다.");
        }
    }

    private void validateDuplicate(Station startStation, Station endStation) {
        if (startStation.equals(endStation)) {
            throw new IllegalArgumentException("출발역과 도착역이 동일합니다.");
        }
    }

    private void validateHasPath(Station startStation, Station endStation) {
        if (!graph.containsVertex(startStation.getName()) || !graph.containsVertex(endStation.getName())) {
            throw new IllegalArgumentException("출발역과 도착역이 존재하지 않습니다.");
        }
        if (graph.containsEdge(startStation.getName(), endStation.getName())) {
            throw new IllegalArgumentException("출발역과 도착역이 연결되어 있지 않습니다.");
        }
    }
}
