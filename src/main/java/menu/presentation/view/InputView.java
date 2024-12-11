package menu.presentation.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readCoachNames() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
        String input = readString();
        InputValidator.validate(input);
        return input;
    }

    private String readString() {
        return Console.readLine()
                .strip();
    }

}
