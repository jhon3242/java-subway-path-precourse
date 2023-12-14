package subway;

import java.util.Scanner;
import subway.domain.Navigator;
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
            if (option.equals("1")) {
//                outputView.printShortestDistance();
                return;
            }
            if (option.equals("2")) {
//                outputView.printShortestDistance();
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

    private void selectStation(String option) {
        Station departureStation = selectDepartureStation();
        Station arrivalStation = selectArrivalStation();
        if (option.equals("1")) {
            Navigator.getInstance().findPathByDistance(departureStation, arrivalStation);
        }
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
