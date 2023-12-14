package subway.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NavigatorTest {

    @Test
    void findPathByTime() {
        Navigator navigator = Navigator.getInstance();
        List<Station> path = navigator.findPathByTime(StationRepository.findByName("교대역"),
                StationRepository.findByName("양재역"));
        int totalDistance = PathRepository.getTotalDistance(path);
        int totalTime = PathRepository.getTotalTime(path);
        Assertions.assertThat(totalDistance).isEqualTo(9);
        Assertions.assertThat(totalTime).isEqualTo(7);
    }

    @Test
    void findPathByDistance() {
        Navigator navigator = Navigator.getInstance();
        List<Station> path = navigator.findPathByDistance(StationRepository.findByName("교대역"),
                StationRepository.findByName("양재역"));
        int totalDistance = PathRepository.getTotalDistance(path);
        int totalTime = PathRepository.getTotalTime(path);
        Assertions.assertThat(totalDistance).isEqualTo(4);
        Assertions.assertThat(totalTime).isEqualTo(11);
    }
}