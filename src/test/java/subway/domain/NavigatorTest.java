package subway.domain;

import java.util.List;
import org.junit.jupiter.api.Test;

class NavigatorTest {

    @Test
    void findPathByTime() {
        Navigator navigator = Navigator.getInstance();
        List<String> path = navigator.findPathByTime(StationRepository.findByName("교대역"),
                StationRepository.findByName("양재역"));
        System.out.println(path);
    }

    @Test
    void findPathByDistance() {
        Navigator navigator = Navigator.getInstance();
        List<String> path = navigator.findPathByDistance(StationRepository.findByName("교대역"),
                StationRepository.findByName("양재역"));
        System.out.println(path);
    }
}