package menu.presentation.view;

public class OutputView {

    public void printServiceStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        System.out.println();
    }

    public void printErrorMessage(final String message) {
        System.out.println(String.join(
                " ", "[ERROR]", message, "다시 입력해주세요."
        ));
    }

}
