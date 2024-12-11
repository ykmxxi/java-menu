package menu.presentation.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readCoachNames() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
        String input = readString();
        InputValidator.validate(input);
        return input;
    }

    public String readCanNotEatableMenus(final String coachName) {
        System.out.printf("%s(이)가 못 먹는 메뉴를 입력해 주세요." + System.lineSeparator(), coachName);
        return readString();
    }

    private String readString() {
        return Console.readLine()
                .strip();
    }

}
