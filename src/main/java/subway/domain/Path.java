package subway.domain;

import java.util.List;

public class Path {
    private Station station1;
    private Station station2;
    private int distance;
    private int time;

    public Path(Station station1, Station station2, int distance, int time) {
        this.station1 = station1;
        this.station2 = station2;
        this.distance = distance;
        this.time = time;
    }

    public boolean isSamePath(Station station1, Station station2) {
        return (this.station1 == station1 && this.station2 == station2) ||
                (this.station1 == station2 && this.station2 == station1);
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
