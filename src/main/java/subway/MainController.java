package subway;

import java.util.List;
import java.util.Scanner;
import subway.domain.Navigator;
import subway.domain.PathRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(Scanner scanner) {
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
    }

    public void run() {
        while (true) {
            String menu = selectMenu();
            if (menu.equals("1")) {
                Navigator.getInstance(); // TODO 리팩터링
                handleOption();
                continue;
            }
            if (menu.equals("Q")) {
                break;
            }
        }
    }

    private void handleOption() {
        try {
            String option = selectOption();
            if ("12".contains(option)) {
                List<Station> path = handlePath(option);
                outputView.printPath(PathRepository.getTotalDistance(path), PathRepository.getTotalTime(path), path);
                return;
            }
            if (option.equals("B")) {
                return;
            }
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception);
            handleOption();
        }
    }

    private String selectMenu() {
        try {
            return inputView.selectMenu();
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception);
            return selectMenu();
        }
    }

    private String selectOption() {
        try {
            return inputView.selectOption();
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception);
            return selectOption();
        }
    }

    private List<Station> handlePath(String option) {
        Station departureStation = selectDepartureStation();
        Station arrivalStation = selectArrivalStation();
        if (option.equals("1")) {
            return Navigator.getInstance()
                    .findPathByDistance(departureStation, arrivalStation);
        }
        if (option.equals("2")) {
            return Navigator.getInstance()
                    .findPathByTime(departureStation, arrivalStation);
        }
        throw new IllegalArgumentException("옵션을 잘못 입력하셨습니다.");
    }

    private Station selectDepartureStation() {
        try {
            return StationRepository.findByName(inputView.selectDepartureStation());
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception);
            return selectDepartureStation();
        }
    }

    private Station selectArrivalStation() {
        try {
            return StationRepository.findByName(inputView.selectArrivalStation());
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception);
            return selectArrivalStation();
        }
    }
}
