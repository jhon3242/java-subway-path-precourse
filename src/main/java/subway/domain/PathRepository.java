package subway.domain;

import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class PathRepository {
    private WeightedMultigraph<String, DefaultWeightedEdge> graph;

    public PathRepository() {
        graph = new WeightedMultigraph(DefaultWeightedEdge.class);
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
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList();
        return shortestPath.stream().map(StationRepository::findByName)
                .collect(Collectors.toList());
    }
}
