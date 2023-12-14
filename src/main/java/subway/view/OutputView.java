package subway.view;

import java.util.List;
import subway.domain.Station;

public class OutputView {

    public void printException(IllegalArgumentException exception) {
        System.out.println("[ERROR] : " + exception.getMessage());
    }

    public void printPath(int totalDistance, int totalTime, List<Station> path) {
        System.out.println("## 조회 결과");
        System.out.println("[INFO] ---");
        System.out.println("[INFO] 총 거리: " + totalDistance + "km");
        System.out.println("[INFO] 총 소요 시간: " + totalTime + "분");
        System.out.println("[INFO] ---");
        for (Station station : path) {
            System.out.printf("[INFO] %s\n", station.getName());
        }
        System.out.println();
    }
}
