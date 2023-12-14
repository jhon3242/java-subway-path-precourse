package subway.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String selectMenu() {
        System.out.println("## 메인 화면");
        System.out.println("1. 경로 조회");
        System.out.println("Q. 종료");
        System.out.println();
        System.out.print("## 원하는 기능을 선택하세요.");
        String line = scanner.nextLine();
        validateMenu(line);
        return line;
    }

    public String selectOption() {
        System.out.println("## 경로 기준");
        System.out.println("1. 최단 거리");
        System.out.println("2. 최소 시간");
        System.out.println("B. 돌아가기");
        System.out.println();
        System.out.print("## 원하는 기능을 선택하세요.");
        String line = scanner.nextLine();
        validateOption(line);
        return line;
    }

    private void validateMenu(String menu) {
        if (menu == null || !"1Q".contains(menu)) {
            throw new IllegalArgumentException("메뉴를 잘못 입력하셨습니다.");
        }
    }

    private void validateOption(String option) {
        if (option == null || !"12B".contains(option)) {
            throw new IllegalArgumentException("옵션을 잘못 입력하셨습니다.");
        }
    }

    public String selectDepartureStation() {
        System.out.println("## 출발역을 입력하세요.");
        String line = scanner.nextLine();
        return line;
    }

    public String selectArrivalStation() {
        System.out.println("## 도착역을 입력하세요.");
        String line = scanner.nextLine();
        return line;
    }
}
