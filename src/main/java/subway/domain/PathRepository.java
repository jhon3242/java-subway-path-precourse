package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class PathRepository {
    private static List<Path> paths = new ArrayList<>();

    public static void init() {
        paths.add(new Path(StationRepository.findByName("교대역"), StationRepository.findByName("강남역"), 2, 3));
        paths.add(new Path(StationRepository.findByName("강남역"), StationRepository.findByName("역삼역"), 2, 3));
        paths.add(new Path(StationRepository.findByName("교대역"), StationRepository.findByName("남부터미널역"), 3, 2));
        paths.add(new Path(StationRepository.findByName("남부터미널역"), StationRepository.findByName("양재역"), 6, 5));
        paths.add(new Path(StationRepository.findByName("양재역"), StationRepository.findByName("매봉역"), 1, 1));
        paths.add(new Path(StationRepository.findByName("강남역"), StationRepository.findByName("양재역"), 2, 8));
        paths.add(new Path(StationRepository.findByName("양재역"), StationRepository.findByName("양재시민의숲역"), 10, 3));
    }

    public static int getTime(Station startStation, Station endStation) {
        init();
        for (Path path : paths) {
            if (path.isSamePath(startStation, endStation)) {
                return path.getTime();
            }
        }
        throw new IllegalArgumentException("시간을 계산할 수 없는 경로입니다.");
    }

    public static int getDistance(Station startStation, Station endStation) {
        init();
        for (Path path : paths) {
            if (path.isSamePath(startStation, endStation)) {
                return path.getDistance();
            }
        }
        throw new IllegalArgumentException("거리를 계산할 수 없는 경로입니다.");
    }

    public static int getTotalTime(List<Station> paths) {
        int totalTime = 0;
        for (int i = 0; i < paths.size() - 1; i++) {
            totalTime += getTime(paths.get(i), paths.get(i + 1));
        }
        return totalTime;
    }

    public static int getTotalDistance(List<Station> paths) {
        int totalDistance = 0;
        for (int i = 0; i < paths.size() - 1; i++) {
            totalDistance += getDistance(paths.get(i), paths.get(i + 1));
        }
        return totalDistance;
    }
}
