package subway.domain;

import java.util.List;

public class Navigator {
    private Graph timePathRepository;
    private Graph distancePathRepository;

    private Navigator(Graph timePathRepository, Graph distancePathRepository) {
        initTimePath(timePathRepository);
        initDistancePath(distancePathRepository);
        this.timePathRepository = timePathRepository;
        this.distancePathRepository = distancePathRepository;
    }

    public static Navigator getInstance() {
        initLine();
        initStation();
        Graph timePathRepository = new Graph();
        Graph distancePathRepository = new Graph();
        return new Navigator(timePathRepository, distancePathRepository);
    }

    private static void initLine() {
        LineRepository.addLine(new Line("2호선"));
        LineRepository.addLine(new Line("3호선"));
        LineRepository.addLine(new Line("신분당선"));
    }

    private static void initStation() {
        StationRepository.addStation(new Station("교대역"));
        StationRepository.addStation(new Station("강남역"));
        StationRepository.addStation(new Station("역삼역"));
        StationRepository.addStation(new Station("남부터미널역"));
        StationRepository.addStation(new Station("양재역"));
        StationRepository.addStation(new Station("양재시민의숲역"));
        StationRepository.addStation(new Station("매봉역"));
    }

    private static void initTimePath(Graph timePathRepository) {
        timePathRepository.addEdge(StationRepository.findByName("교대역"), StationRepository.findByName("강남역"), 3);
        timePathRepository.addEdge(StationRepository.findByName("강남역"), StationRepository.findByName("역삼역"), 3);
        timePathRepository.addEdge(StationRepository.findByName("교대역"), StationRepository.findByName("남부터미널역"), 2);
        timePathRepository.addEdge(StationRepository.findByName("남부터미널역"), StationRepository.findByName("양재역"), 5);
        timePathRepository.addEdge(StationRepository.findByName("양재역"), StationRepository.findByName("매봉역"), 1);
        timePathRepository.addEdge(StationRepository.findByName("강남역"), StationRepository.findByName("양재역"), 8);
        timePathRepository.addEdge(StationRepository.findByName("양재역"), StationRepository.findByName("양재시민의숲역"), 3);
    }

    private static void initDistancePath(Graph distancePathRepository) {
        distancePathRepository.addEdge(StationRepository.findByName("교대역"), StationRepository.findByName("강남역"), 2);
        distancePathRepository.addEdge(StationRepository.findByName("강남역"), StationRepository.findByName("역삼역"), 2);
        distancePathRepository.addEdge(StationRepository.findByName("교대역"), StationRepository.findByName("남부터미널역"), 3);
        distancePathRepository.addEdge(StationRepository.findByName("남부터미널역"), StationRepository.findByName("양재역"), 6);
        distancePathRepository.addEdge(StationRepository.findByName("양재역"), StationRepository.findByName("매봉역"), 1);
        distancePathRepository.addEdge(StationRepository.findByName("강남역"), StationRepository.findByName("양재역"), 2);
        distancePathRepository.addEdge(StationRepository.findByName("양재역"), StationRepository.findByName("양재시민의숲역"), 10);
    }

    public List<Station> findPathByTime(Station startStation, Station endStation) {
        return timePathRepository.findPath(startStation, endStation);
    }

    public List<Station> findPathByDistance(Station startStation, Station endStation) {
        return distancePathRepository.findPath(startStation, endStation);
    }
}
