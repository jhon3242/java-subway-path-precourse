package subway.view;

public class OutputView {

    public void printException(IllegalArgumentException exception) {
        System.out.println("[ERROR] : " + exception.getMessage());
    }
}
