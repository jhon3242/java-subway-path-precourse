package subway.domain;

public class Path {
    private final Station station1;
    private final Station station2;
    private final int distance;
    private final int time;

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

    public Station getStation1() {
        return station1;
    }

    public Station getStation2() {
        return station2;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
